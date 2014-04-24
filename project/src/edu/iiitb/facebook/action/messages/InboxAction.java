package edu.iiitb.facebook.action.messages;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.MessageDAO;
import edu.iiitb.facebook.action.dao.impl.MessageDAOImpl;
import edu.iiitb.facebook.action.model.Conversation;
import edu.iiitb.facebook.action.model.Message;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.Constants;

public class InboxAction extends ActionSupport implements SessionAware
{
	private static final long serialVersionUID = 7253053184925533403L;
	private Map<String, Object> session;
	private final static String NO_CONVERSATIONS = "no_conversations";

	private List<Conversation> conversations;
	private Conversation selectedConversation;
	private List<Message> selectedConversationThread;
	
	/**
	 * Load the conversations of the logged in user and select the latest amongst them all for
	 * the expanded selected conversation view
	 * 
	 * @return
	 */
	public String load()
	{
		int user = ((User) (session.get(Constants.USER))).getUserId();
		MessageDAO dao = new MessageDAOImpl();
		
		conversations = dao.getConversations(user);
		
		// set default conversation
		if (selectedConversation == null)
		{ 
			if (conversations.isEmpty()) // No conversations
				return NO_CONVERSATIONS;
			selectedConversation = conversations.get(0); // Initial load of inbox
		}
		
		// else selected conversation id is set by the view
		selectedConversationThread = dao
				.getConversationThread(user, selectedConversation.getId());

		// now based on the selected conversation id set by the view set the selected conversation 
		for (Conversation conversation : conversations)
			if (conversation.getId() == selectedConversation.getId())
			{
				selectedConversation = conversation;
				break;
			}
		
		if (selectedConversation.getUnreadMessagesCount() > 0)
			dao.markAsRead(user, selectedConversation.getId());
		
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

	public List<Conversation> getConversations()
	{
		return conversations;
	}

	public void setConversations(List<Conversation> conversations)
	{
		this.conversations = conversations;
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

	public Conversation getSelectedConversation()
	{
		return selectedConversation;
	}

	public void setSelectedConversation(Conversation selectedConversation)
	{
		this.selectedConversation = selectedConversation;
	}
}