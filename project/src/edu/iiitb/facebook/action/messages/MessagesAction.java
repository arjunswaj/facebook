package edu.iiitb.facebook.action.messages;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.MessageDAO;
import edu.iiitb.facebook.action.dao.impl.MessageDAOImpl;
import edu.iiitb.facebook.action.model.LatestConversation;
import edu.iiitb.facebook.action.model.Message;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.Constants;

public class MessagesAction extends ActionSupport implements SessionAware
{
	private static final long serialVersionUID = 7253053184925533403L;
	private Map<String, Object> session;

	private List<Message> selectedConversationThread;
	private List<LatestConversation> latestConversations;
	private LatestConversation selectedLatestConversation;
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
		if (selectedLatestConversation == null && !latestConversations.isEmpty())
			selectedLatestConversation = latestConversations.get(0);

		setSelectedConversationThread(dao
				.getConversationThread(selectedLatestConversation.getOtherUser(), user));
		for (LatestConversation latestMessage : latestConversations)
			if (latestMessage.getUser() == selectedLatestConversation.getOtherUser())
				selectedLatestConversation = latestMessage;
		
		return SUCCESS;
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

	public List<LatestConversation> getLatestConversations()
	{
		return latestConversations;
	}


	public void setLatestConversations(List<LatestConversation> latestConversations)
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

	public LatestConversation getSelectedLatestConversation()
	{
		return selectedLatestConversation;
	}

	public void setSelectedLatestConversation(LatestConversation selectedLatestConversation)
	{
		this.selectedLatestConversation = selectedLatestConversation;
	}
}