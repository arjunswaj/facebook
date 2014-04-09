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

	private List<Message> selectedConversationThread;
	private List<LatestMessage> latestConversations;
	private int otherUser = -1; // the other user with whom this 'user' is
								// having the conversation.

	/**
	 * Load the latest conversations and select the latest amongst them all for
	 * the expanded selected conversation view
	 * 
	 * @return
	 */
	public String load()
	{
		int user = ((User) (session.get(Constants.USER))).getUserId();

		MessageDAO dao = new MessageDAOImpl();
		latestConversations = dao.getLatestConversationsFor(user);

		// set default conversation with latest other user
		if (otherUser < 0 && !latestConversations.isEmpty())
			otherUser = latestConversations.get(0).getOtherUser();

		setSelectedConversationThread(dao
				.getConversationThread(otherUser, user));
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@Override
	public void setSession(Map<String, Object> arg0)
	{
		this.session = arg0;
	}

	public List<LatestMessage> getLatestConversations()
	{
		return latestConversations;
	}

	public void setLatestConversations(List<LatestMessage> latestConversations)
	{
		this.latestConversations = latestConversations;
	}

	public List<Message> getSelectedConversationThread()
	{
		return selectedConversationThread;
	}

	public void setSelectedConversationThread(
			List<Message> selectedConversationThread)
	{
		this.selectedConversationThread = selectedConversationThread;
	}
}