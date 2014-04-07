package edu.iiitb.facebook.action.messages;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.MessageDAO;
import edu.iiitb.facebook.action.dao.impl.MessageDAOImpl;
import edu.iiitb.facebook.action.model.LatestMessage;
import edu.iiitb.facebook.action.model.Message;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.Constants;

public class MessagesAction extends ActionSupport implements SessionAware
{
	private static final long serialVersionUID = 7253053184925533403L;
	private Map<String, Object> session;
	
	private List<Message> conversation;
	private List<LatestMessage> conversations;
	private int otherUser = -1; // the other user with whom this 'user' is having the conversation.
	/**
	 * Load the messages
	 * @return
	 */
	public String loadMessages()
	{
		int user = ((User)(session.get(Constants.USER))).getUserId();

		// latest messages list
		MessageDAO dao = new MessageDAOImpl();
		conversations = dao.getLatestConversationforAllUsersWith(user);
		if (otherUser < 0 && !conversations.isEmpty())
			otherUser = conversations.get(0).getOtherUser();
		
		// message thread
		setConversation(dao.getMessages(otherUser, user));
		return SUCCESS;
	}

	public int getOtherUser()
	{
		return otherUser;
	}

	public void setOtherUser(int otherUser)
	{
		this.otherUser = otherUser;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@Override
	public void setSession(Map<String, Object> arg0)
	{
		this.session = arg0;
		
	}

	public List<LatestMessage> getConversations()
	{
		return conversations;
	}

	public void setConversations(List<LatestMessage> conversations)
	{
		this.conversations = conversations;
	}

	public List<Message> getConversation()
	{
		return conversation;
	}

	public void setConversation(List<Message> conversation)
	{
		this.conversation = conversation;
	}
}