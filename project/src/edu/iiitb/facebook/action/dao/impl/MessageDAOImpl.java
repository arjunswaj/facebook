package edu.iiitb.facebook.action.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.iiitb.facebook.action.dao.MessageDAO;
import edu.iiitb.facebook.action.model.Conversation;
import edu.iiitb.facebook.action.model.Message;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.ConnectionPool;

public class MessageDAOImpl implements MessageDAO
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.iiitb.facebook.action.dao.MessageDAO#getConversationThread(int, int)
	 */
	@Override
	public List<Message> getConversationThread(int inbox, int conversation)
	{
		// TODO : Use StringBuilder and append here
		final String query = "select" 
								+ " message.id,"
								+ " message.sent_at,"
								+ " message.text,"
								+ " message.read_status,"
								+ " message.sender,"
								+ " user.first_name,"
								+ " user.last_name"
							+ " from"
								+ "     message,"
								+ " 	user"
							+ " where"
								+ " message.inbox = ?"
								+ " and message.conversation = ?"
								+ " and message.sender = user.id"
								+ " order by message.sent_at asc;";

		List<Message> messages = new LinkedList<Message>();

		Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt;
		try
		{
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, inbox);
			stmt.setInt(2, conversation);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
			{
				Message message = new Message();
				message.setId(rs.getInt("id"));
				message.setConversation(conversation);
				message.setInbox(inbox);
				message.setText(rs.getString("text"));
				message.setSentAt(rs.getTimestamp("sent_at").toString());
				message.setReadStatus(rs.getString("read_status"));
				message.setSender(rs.getInt("sender"));
				message.setSenderFirstName(rs.getString("first_name"));
				message.setSenderLastName(rs.getString("last_name"));
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

	public List<User> getOtherParticipants(int conversationId, int loggedInUser)
	{
		final String query = "select" 
			    + " user.id, user.first_name, user.last_name"
			    + " from"
			        + " user,"
			        + " user_conversation"
			    + " where"
			        + " user_conversation.conversation = ?"
			            + " and user.id = user_conversation.user"
			            + " and user.id != ?";
		
		List<User> participants = new LinkedList<User>();

		Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt;
		try
		{
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, conversationId);
			stmt.setInt(2, loggedInUser);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
			{
				User participant = new User();
				participant.setUserId(rs.getInt("id"));
				participant.setFirstName(rs.getString("first_name"));
				participant.setLastName(rs.getString("last_name"));
				participants.add(participant);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionPool.freeConnection(connection);
		}
		return participants;
		
	}
	
	private List<Integer> getParticipants(int conversationId)
	{
		final String query = "select" 
			    + " user.id"
			    + " from"
			        + " user,"
			        + " user_conversation"
			    + " where"
			        + " user_conversation.conversation = ?"
			            + " and user.id = user_conversation.user";
		
		List<Integer> participants = new LinkedList<Integer>();

		Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt;
		try
		{
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, conversationId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
				participants.add(rs.getInt("id"));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionPool.freeConnection(connection);
		}
		return participants;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.iiitb.facebook.action.dao.MessageDAO#getConversations
	 * (int)
	 */
	@Override
	public List<Conversation> getConversations(int user)
	{
		// TODO : Use StringBuilder and append here
		final String query = "select "
				+ " message.id,"
				+ " message.conversation,"
			    + " message.text,"
			    + " message.sent_at,"
			    + " message.sender,"
			    + " message.inbox,"
			    + " message.read_status"
			+ " from"
			    + " user,"
				+ " message,"
			    + " (select "
			        + " message.conversation as id,"
			            + " max(message.sent_at) as sent_at"
			    + " from"
			        + " message"
				+ " where message.inbox = ?"
			    + " group by message.conversation) as conversation"
			+ " where"
			    	+ " message.inbox = ?"
			        + " and user.id = message.inbox"
					+ " and message.conversation = conversation.id"
					+ " and message.sent_at = conversation.sent_at"
			+ " order by message.sent_at desc;";

		List<Conversation> conversations = new LinkedList<Conversation>();

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
				Conversation conversation = new Conversation();
				conversation.setId(rs.getInt("conversation"));
				
				Message latestMessage = new Message();
				latestMessage.setId(rs.getInt("id"));
				latestMessage.setConversation(rs.getInt("conversation"));
				latestMessage.setText(rs.getString("text"));
				latestMessage.setSentAt(rs.getTimestamp("sent_at").toString());
				latestMessage.setSender(rs.getInt("sender"));
				latestMessage.setInbox(rs.getInt("inbox"));
				latestMessage.setReadStatus(rs.getString("read_status"));
				
				conversation.setLatestMessage(latestMessage);
				
				// TODO: Can these db calls be avoided??
				conversation.setUnreadMessagesCount(getUnreadCount(rs.getInt("conversation"), user));
				conversation.setOtherParticipants(getOtherParticipants(rs.getInt("conversation"), user));
				
				conversations.add(conversation);
			}
			
			// Mark all conversations which have only accepted friends in them
			Map<Integer, String> friendshipStatus = getFriendShipStatus(user);
			for (Conversation conversation: conversations)
			{
				conversation.setAllFriends(true);
				for(User otherParticipant : conversation.getOtherParticipants())
				{
					if (friendshipStatus.get(otherParticipant.getUserId()) == null)
					{
						// This guy is not a friend and he is yet a participant in a conversation
						// => He was blocked and then unblocked
						conversation.setAllFriends(false);
						break;
					}
						
					if (!friendshipStatus.get(otherParticipant.getUserId()).equals("accepted"))
					{
						// This guy is a friend but is either blocked or has not accepted the friend request(or vice versa)
						conversation.setAllFriends(false);
						break;
					}
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionPool.freeConnection(connection);
		}
		return conversations;
	}

	/**
	 * @param conversation
	 * @param user
	 * @return
	 */
	private int getUnreadCount(int conversation, int inbox)
	{
		final String query = "select count(*) as unread_count from message where inbox = ? and conversation = ? and read_status = 'unread'";
		Connection connection = ConnectionPool.getConnection();
		int count = 9999999;
		PreparedStatement stmt;
		try
		{
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, inbox);
			stmt.setInt(2, conversation);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
				count = rs.getInt("unread_count");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionPool.freeConnection(connection);
		}
		return count;
	}

	/**
	 * @param user
	 * @return
	 */
	private Map<Integer, String> getFriendShipStatus(int user)
	{
		final String query = "select * from friends_with where request_by = ? or request_for = ?";

		Map<Integer, String> friendshipStatus = new HashMap<Integer, String>();
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
				int request_by = rs.getInt("request_by");
				int request_for = rs.getInt("request_for");
				
				if (request_by == user)
					friendshipStatus.put(request_for, rs.getString("status"));
				else
					friendshipStatus.put(request_by, rs.getString("status"));
			}			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionPool.freeConnection(connection);
		}
		return friendshipStatus;
	}

	private void insert(Message message)
	{
		// TODO : Use StringBuilder and append here
		final String insert = "insert into message (conversation, inbox, text, sender, read_status) values (?, ? , ?, ?, ?)";
			
		Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt;
		try
		{	
			stmt = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, message.getConversation());
			stmt.setInt(2, message.getInbox());
			stmt.setString(3, message.getText());
			stmt.setInt(4, message.getSender());
			if (message.getSender() == message.getInbox())
				stmt.setString(5, "read");
			else
				stmt.setString(5, "unread");
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

	/* (non-Javadoc)
	 * @see edu.iiitb.facebook.action.dao.MessageDAO#insertIntoNewConversation(edu.iiitb.facebook.action.model.Message, java.util.List)
	 */
	@Override
	public int insertIntoNewConversation(Message message,
			List<Integer> participants)
	{
		String createNewConversation = "insert into conversation () values ();";
		String createNewUserConversationMapping = "insert into user_conversation (user, conversation) values (?, ?);";
		Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt;
		int cid = -1;
		try
		{
			// Create new conversation
			stmt = connection.prepareStatement(createNewConversation, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			cid = rs.getInt(1);

			// Associate participants with new conversation and save message in their respective inboxes
			for (int participant : participants )
			{
				stmt = connection.prepareStatement(createNewUserConversationMapping, Statement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, participant);
				stmt.setInt(2, cid);
				stmt.executeUpdate();
				
				message.setConversation(cid);
				message.setInbox(participant);
				insert(message);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionPool.freeConnection(connection);
		}
		return cid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.iiitb.facebook.action.dao.MessageDAO#insertIntoExistingConversation
	 * (edu.iiitb.facebook.action.model.Message)
	 */
	@Override
	public void insertIntoExistingConversation(Message message)
	{
		for (int participant : getParticipants(message.getConversation()))
		{
			message.setInbox(participant);
			insert(message);
		}
	}

	/* (non-Javadoc)
	 * @see edu.iiitb.facebook.action.dao.MessageDAO#deleteMesagesFromConversation(int, int)
	 */
	@Override
	public void deleteMessagesFromConversation(int conversation, int inbox)
	{
		// TODO : Use StringBuilder and append here
		final String deleteMessages = "delete from message where inbox = ? and conversation = ?";
			
		Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt;
		try
		{	
			stmt = connection.prepareStatement(deleteMessages);
			stmt.setInt(1, inbox);
			stmt.setInt(2, conversation);
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

	/* (non-Javadoc)
	 * @see edu.iiitb.facebook.action.dao.MessageDAO#deleteMessageFromInbox(int, int)
	 */
	@Override
	public void deleteMessageFromInbox(int id, int inbox)
	{
		// TODO : Use StringBuilder and append here
		final String deleteMessage = "delete from message where inbox = ? and id = ?";
			
		Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt;
		try
		{	
			stmt = connection.prepareStatement(deleteMessage);
			stmt.setInt(1, inbox);
			stmt.setInt(2, id);
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

	/* (non-Javadoc)
	 * @see edu.iiitb.facebook.action.dao.MessageDAO#markAsRead(int, edu.iiitb.facebook.action.model.Conversation)
	 */
	@Override
	public void markAsRead(int inbox, int conversation)
	{
		final String deleteMessage = "update message set read_status = 'read' where inbox = ? and conversation = ?";
		
		Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt;
		try
		{	
			stmt = connection.prepareStatement(deleteMessage);
			stmt.setInt(1, inbox);
			stmt.setInt(2, conversation);
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
