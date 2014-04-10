package edu.iiitb.facebook.action.messages;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.impl.MessageDAOImpl;
import edu.iiitb.facebook.action.model.Message;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.Constants;

public class SendNewMessageAction extends ActionSupport implements SessionAware
{
	private static final long serialVersionUID = -1229947406728255002L;
	
	private Map<String, Object> session;
	private Message newMessage;
	
	public String send()
	{
		User user = (User) session.get(Constants.USER);
		newMessage.setSender(user.getUserId());
		newMessage.setSenderFirstName(user.getFirstName());
		newMessage.setSenderLastName(user.getLastName());
		new MessageDAOImpl().insert(newMessage);
		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
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

}
