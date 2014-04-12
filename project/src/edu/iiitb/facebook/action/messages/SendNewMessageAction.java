package edu.iiitb.facebook.action.messages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.MessageDAO;
import edu.iiitb.facebook.action.dao.impl.MessageDAOImpl;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.LatestConversation;
import edu.iiitb.facebook.action.model.Message;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.Constants;

public class SendNewMessageAction extends ActionSupport implements SessionAware
{
	private static final long serialVersionUID = -1229947406728255002L;

	private Map<String, Object> session;
	private Message newMessage;

	private List<Message> selectedConversationThread;
	private List<LatestConversation> latestConversations;
	private LatestConversation selectedLatestConversation;

	public String send()
	{
		MessageDAO dao = new MessageDAOImpl();

		User user = (User) session.get(Constants.USER);
		User otherUser = new UserDAOImpl().getUserByUserId(newMessage
				.getRecipient());

		// Insert new messsage into db
		newMessage.setSender(user.getUserId());
		newMessage.setSenderFirstName(user.getFirstName());
		newMessage.setSenderLastName(user.getLastName());
		newMessage.setRecipient(otherUser.getUserId());
		newMessage.setRecipientFirstName(otherUser.getFirstName());
		newMessage.setRecipientLastName(otherUser.getLastName());
		dao.insert(newMessage);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	    newMessage.setSentAt(sdf.format(new Date()));

		// set latest conversations
		latestConversations = dao.getLatestConversationsFor(user.getUserId());

		// set selectedLatestConversation
		selectedLatestConversation = new LatestConversation();
		selectedLatestConversation.setLatestMessage(newMessage.getText());
		selectedLatestConversation.setOtherUser(newMessage.getRecipient());
		selectedLatestConversation.setOtherUserFirstName(newMessage
				.getRecipientFirstName());
		selectedLatestConversation.setOtherUserLastName(newMessage
				.getRecipientLastName());
		selectedLatestConversation.setSentAt(newMessage.getSentAt());
		selectedLatestConversation.setUser(user.getUserId());

		// set selected conversation thread
		selectedConversationThread = dao.getConversationThread(
				selectedLatestConversation.getOtherUser(), user.getUserId());

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

	public Message getNewMessage()
	{
		return newMessage;
	}

	public void setNewMessage(Message newMessage)
	{
		this.newMessage = newMessage;
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

	public List<LatestConversation> getLatestConversations()
	{
		return latestConversations;
	}

	public void setLatestConversations(
			List<LatestConversation> latestConversations)
	{
		this.latestConversations = latestConversations;
	}

	public LatestConversation getSelectedLatestConversation()
	{
		return selectedLatestConversation;
	}

	public void setSelectedLatestConversation(
			LatestConversation selectedLatestConversation)
	{
		this.selectedLatestConversation = selectedLatestConversation;
	}

}
