package edu.iiitb.facebook.action.messages;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.MessageDAO;
import edu.iiitb.facebook.action.dao.impl.MessageDAOImpl;
import edu.iiitb.facebook.action.model.Message;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.Constants;

public class DeleteMessageAction extends ActionSupport implements SessionAware
{
	private static final long serialVersionUID = 7253053184925533403L;
	private Map<String, Object> session;

	private Message message;
	
	public String delete()
	{
		int user = ((User) (session.get(Constants.USER))).getUserId();
		MessageDAO dao = new MessageDAOImpl();

		dao.deleteMessageFromInbox(message.getId(), user);
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

	public Message getMessage()
	{
		return message;
	}

	public void setMessage(Message message)
	{
		this.message = message;
	}
}