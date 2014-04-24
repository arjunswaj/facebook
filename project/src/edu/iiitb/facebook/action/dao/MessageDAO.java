package edu.iiitb.facebook.action.dao;

import java.util.List;

import edu.iiitb.facebook.action.model.Conversation;
import edu.iiitb.facebook.action.model.Message;
import edu.iiitb.facebook.action.model.User;

public interface MessageDAO
{
	/**
	 * Gets list of messages of the specified conversationId for the specified
	 * inbox/user
	 * 
	 * @param inbox
	 * @param conversationId
	 * @return
	 */
	public List<Message> getConversationThread(int inbox, int conversationId);

	/**
	 * Gets the conversations of the specified user/inbox
	 * 
	 * @param user The specified user
	 * @return
	 */
	public List<Conversation> getConversations(int user);

	/**
	 * Get the participants in the specified conversation other than the logged
	 * in user
	 * 
	 * @param conversationId
	 * @return
	 */
	public List<User> getOtherParticipants(int conversationId, int loggedInUser);

	/**
	 * Associate the specified message as a part of a new conversation and
	 * insert into inbox's of all specified participants
	 * 
	 * @param message
	 * @param participants
	 * @return The new conversation
	 */
	public int insertIntoNewConversation(Message message,
			List<Integer> participants);

	/**
	 * Associate the specified message as a part of an existing conversation(set
	 * in the specified message) and insert into inbox's of all participants who
	 * are part of that conversation
	 * 
	 * @param message
	 */
	public void insertIntoExistingConversation(Message message);

	/**
	 * Delete messages from the given conversation from the users inbox
	 * @param conversation
	 * @param user
	 */
	public void deleteMessagesFromConversation(int conversation, int inbox);

	/**
	 * Delete specified message from specified inbox
	 * @param id
	 * @param uinbox
	 */
	public void deleteMessageFromInbox(int id, int inbox);

	/**
	 * Mark all the unread messages in the inbox of the specified conversation as read
	 * @param conversation
	 * @param inbox
	 */
	public void markAsRead(int inbox, int conversation);
}
