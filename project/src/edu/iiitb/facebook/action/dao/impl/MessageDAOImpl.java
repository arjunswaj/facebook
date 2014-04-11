package edu.iiitb.facebook.action.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import edu.iiitb.facebook.action.dao.MessageDAO;
import edu.iiitb.facebook.action.model.LatestConversation;
import edu.iiitb.facebook.action.model.Message;
import edu.iiitb.facebook.util.ConnectionPool;

public class MessageDAOImpl implements MessageDAO
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.iiitb.facebook.action.dao.MessageDAO#getConversationThread(int, int)
	 */
	@Override
	public List<Message> getConversationThread(int sender, int recipient)
	{
		// TODO : Use StringBuilder and append here
		// Assuming that a conversation thread with a blocked user will not be asked for
		final String query = "select * from user as from_user, message, user as to_user "
				+ " where "
				+ " from_user.id = message.sender and to_user.id = message.recipient "
				+ " and "
				+ " ((message.sender = ? and message.recipient = ? ) or "
				+ " (message.sender = ? and message.recipient = ?))"
				+ " order by sent_at asc";

		List<Message> messages = new LinkedList<Message>();

		Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt;
		try
		{
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, sender);
			stmt.setInt(2, recipient);
			stmt.setInt(3, recipient);
			stmt.setInt(4, sender);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
			{
				Message message = new Message();
				message.setId(rs.getInt("id"));
				message.setText(rs.getString("text"));
				message.setSentAt(rs.getTimestamp("sent_at").toString());
				message.setSender(rs.getInt("sender"));
				message.setRecipient(rs.getInt("recipient"));
				
				//TODO: Disambiguating by default here
				message.setSenderFirstName(rs.getString("first_name"));
				message.setSenderLastName(rs.getString("last_name"));
				message.setRecipientFirstName(rs.getString("first_name"));
				message.setRecipientLastName(rs.getString("last_name"));
				messages.add(message);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionPool.freeConnection(connection);
		}
		return messages;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.iiitb.facebook.action.dao.MessageDAO#getLatestConversationsFor
	 * (int)
	 */
	@Override
	public List<LatestConversation> getLatestConversationsFor(int user)
	{
		// TODO : Use StringBuilder and append here
		final String query = "select" 
				+ " *"
				+ " from"
				+ " user as sender, message," 
				+ " (select "
				+ "		message.conversation_id, max(message.sent_at) as sent_at"
				+ "		from message "
				+ "		where "
				+ "		(message.sender = ? or message.recipient = ? ) "
				+ "		group by conversation_id) as latest_message,"
				+ " user as recipient, "
				+ " friends_with "
				+ " where"
				+ " sender.id = message.sender"
				+ " and message.conversation_id = latest_message.conversation_id"
				+ " and message.sent_at = latest_message.sent_at"
				+ " and message.recipient = recipient.id"
				+ " and message.conversation_id = friends_with.id"
				+ " and friends_with.status = 'accepted'"
				+ " order by latest_message.sent_at desc;";

		List<LatestConversation> latestMsgs = new LinkedList<LatestConversation>();

		Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt;
		try
		{
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, user);
			stmt.setInt(2, user);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
			{
				LatestConversation latestMsg = new LatestConversation();

				int other = rs.getInt("sender.id");
				String firstName = rs.getString("sender.first_name");
				String lastName = rs.getString("sender.last_name");
				if (other == user)
				{
					other = rs.getInt("recipient.id");
					firstName = rs.getString("recipient.first_name");
					lastName = rs.getString("recipient.last_name");
				}
				latestMsg.setOtherUser(other);
				latestMsg.setOtherUserFirstName(firstName);
				latestMsg.setOtherUserLastName(lastName);
				latestMsg.setLatestMessage(rs.getString("text"));
				latestMsg.setSentAt(rs.getTimestamp("sent_at").toString());
				latestMsg.setUser(user);

				latestMsgs.add(latestMsg);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionPool.freeConnection(connection);
		}
		return latestMsgs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.iiitb.facebook.action.dao.MessageDAO#insert(edu.iiitb.facebook.action
	 * .model.Message)
	 */
	@Override
	public int insert(Message reply)
	{
		// TODO : Use StringBuilder and append here
		final String insert = "insert into message (text, sender, recipient, conversation_id) values (?, ? , ?, ?)";
		final String query = "select id from friends_with where (request_by = ? and request_for = ?) or (request_by = ? and request_for = ?)";
		int id = -1;
		int conversationId = -1;
	
		Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt;
		try
		{
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, reply.getSender());
			stmt.setInt(2, reply.getRecipient());
			stmt.setInt(3, reply.getRecipient());
			stmt.setInt(4, reply.getSender());
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				conversationId = rs.getInt("id");
			else
				throw new RuntimeException("TODO: Handle this. People who are not friends are being allowed to message");
			
			stmt = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, reply.getText());
			stmt.setInt(2,  reply.getSender());
			stmt.setInt(3, reply.getRecipient());
			stmt.setInt(4, conversationId);
			stmt.executeUpdate();
			
			rs = stmt.getGeneratedKeys();
			if(rs.next())
				id = rs.getInt(1);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionPool.freeConnection(connection);
		}
		return id;
	}
}
