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

public class MessageDAOIMpl implements MessageDAO
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.iiitb.facebook.action.dao.MessageDAO#getMessages(int, int)
	 */
	@Override
	public List<Message> getMessages(int sender, int recipient)
	{
		final String query = "select *" + " from message" + " where"
				+ " sender = ?" + " and recipient = ?";
		List<Message> messages = new LinkedList<Message>();

		Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt;
		try
		{
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, sender);
			stmt.setInt(2, recipient);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
			{
				Message message = new Message();
				message.setId(rs.getInt("id"));
				message.setText(rs.getString("text"));
				message.setSentAt(rs.getTimestamp("sent_at"));
				message.setSender(rs.getInt("sender"));
				message.setRecipient(rs.getInt("recipient"));
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
		final String query = "SELECT"
				+ " user.id,"
				+ " user.first_name,"
				+ " user.last_name, "
				+ " message.text,"
				+ " message.sent_at"
				+ " FROM"
				+ " message,"
				+ " user,"
				+ " (select sender, max(sent_at) as sent_at from message group "
				+ " by sender) AS latest" + " WHERE "
				+ " message.sender = user.id"
				+ " and message.sender = latest.sender "
				+ " and message.sent_at = latest.sent_at"
				+ " and message.recipient = ?";

		List<LatestMessage> latestMsgs = new LinkedList<LatestMessage>();

		Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt;
		try
		{
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, recipient);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
			{
				LatestMessage latestMsg = new LatestMessage();
				
				latestMsg.setSender(rs.getInt("id"));
				latestMsg.setSenderFirstName(rs.getString("first_name"));
				latestMsg.setSenderLastName(rs.getString("last_name"));
				latestMsg.setText(rs.getString("text"));
				latestMsg.setSentAt(rs.getTimestamp("sent_at"));
				
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
}
