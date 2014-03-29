package edu.iiitb.facebook.action.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import edu.iiitb.facebook.action.dao.MessageDAO;
import edu.iiitb.facebook.action.model.LatestMessage;
import edu.iiitb.facebook.action.model.Message;
import edu.iiitb.facebook.util.ConnectionPool;

public class MessageDAOImpl implements MessageDAO
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.iiitb.facebook.action.dao.MessageDAO#getMessages(int, int)
	 */
	@Override
	public List<Message> getMessages(int sender, int recipient)
	{
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
				message.setSentAt(rs.getTimestamp("sent_at"));
				message.setSender(rs.getInt("sender"));
				message.setRecipient(rs.getInt("recipient"));
				
				//TODO: Disambiguiting by default here
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
	 * edu.iiitb.facebook.action.dao.MessageDAO#getLatestMessagesFromAllUsers
	 * (int)
	 */
	@Override
	public List<LatestMessage> getLatestMessagesFromAllUsers(int recipient)
	{
		final String query = "select user.id, user.first_name, user.last_name, message.text, message.sent_at from user, message,  (select max(sent_at) as sent_at, sender, recipient from message group by sender, recipient) as latest_message where user.id = message.sender and message.sender = latest_message.sender and message.sent_at = latest_message.sent_at and message.recipient = latest_message.recipient and message.recipient = ?";

		List<LatestMessage> latestMsgs = new LinkedList<LatestMessage>();

		Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt;
		try
		{
			System.out.println("Recipient is : " + recipient);
			System.out.println("Query is : " + query);
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, recipient);
			System.out.println("Prepared statement is : " + stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
			{
				LatestMessage latestMsg = new LatestMessage();

				latestMsg.setSender(rs.getInt("id"));
				latestMsg.setSenderFirstName(rs.getString("first_name"));
				latestMsg.setSenderLastName(rs.getString("last_name"));
				latestMsg.setText(rs.getString("text"));
				latestMsg.setSentAt(rs.getTimestamp("sent_at"));
				latestMsg.setRecipient(recipient);

				latestMsgs.add(latestMsg);
			}
			System.out.println("latest messages number : " + latestMsgs.size());
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
	public void insert(Message reply)
	{
		final String insert = "insert into message (text, sender, recipient) values ('"
				+ reply.getText()
				+ "', "
				+ reply.getSender()
				+ ", "
				+ reply.getRecipient() + ")";

		Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt;
		try
		{
			stmt = connection.prepareStatement(insert);
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionPool.freeConnection(connection);
		}
	}
}
