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
	 * Gets the latest message from all users to the specified recipient
	 * @param recipient
	 * @return
	 */
	public List<LatestMessage> getLatestMessagesFromAllUsers(int recipient);

	/**
	 * Insert the reply into the message table
	 * @param replyMsg
	 */
	public void insert(Message reply);
}
