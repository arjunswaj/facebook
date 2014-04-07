package edu.iiitb.facebook.action.dao;

import java.util.List;

import edu.iiitb.facebook.action.model.LatestMessage;
import edu.iiitb.facebook.action.model.Message;

public interface MessageDAO
{
	/**
	 * Gets list of messages between specified sender and recipient
	 * @param recipient
	 * @param sender
	 * @return
	 */
	public List<Message> getMessages(int sender, int recipient);
	
	/**
	 * Gets the latest conversation between the specified user and each of the other users
	 * @param user The specified user
	 * @return
	 */
	public List<LatestMessage> getLatestConversationforAllUsersWith(int user);

	/**
	 * Insert the reply into the message table
	 * @param reply
	 * @return The id of the newly inserted tuple
	 */
	public int insert(Message reply);
}
