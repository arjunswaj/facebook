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

// TODO: Fix names
public class MessagesAction extends ActionSupport implements SessionAware
{
	private static final long serialVersionUID = 7253053184925533403L;
	private Map<String, Object> session;
	
	private List<Message> messages;
	private List<LatestMessage> latestMessages;
	private int withUser = -1; // the current other user with whom this 'user' is having the conversation.
	private int user;
	/**
	 * Load the messages
	 * @return
	 */
	public String loadMessages()
	{
		int user = ((User)(session.get(Constants.USER))).getUserId();

		// latest messages list
		MessageDAO dao = new MessageDAOImpl();
		latestMessages = dao.getLatestConversationforAllUsersWith(user);
		if (withUser < 0 && !latestMessages.isEmpty())
			withUser = latestMessages.get(0).getOtherUser();
		
		// message thread
		messages = dao.getMessages(withUser, user);
		return SUCCESS;
	}

	public List<Message> getMessages()
	{
		return messages;
	}

	public void setMessages(List<Message> messages)
	{
		this.messages = messages;
	}

	public int getWithUser()
	{
		return withUser;
	}

	public void setWithUser(int withUser)
	{
		this.withUser = withUser;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@Override
	public void setSession(Map<String, Object> arg0)
	{
		this.session = arg0;
		
	}

	public List<LatestMessage> getLatestMessages()
	{
		return latestMessages;
	}

	public void setLatestMessages(List<LatestMessage> latestMessages)
	{
		this.latestMessages = latestMessages;
	}

	public int getUser()
	{
		return user;
	}

	public void setUser(int user)
	{
		this.user = user;
	}
}