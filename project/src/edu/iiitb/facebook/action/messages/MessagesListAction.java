package edu.iiitb.facebook.action.messages;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.MessageDAO;
import edu.iiitb.facebook.action.dao.impl.MessageDAOImpl;
import edu.iiitb.facebook.action.model.LatestMessage;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.Constants;

public class MessagesListAction extends ActionSupport implements SessionAware
{
	private static final long serialVersionUID = -5115586460910679145L;

	private List<LatestMessage> latestMessages;
	
	private Map<String, Object> session;

	public String listLatestMessages()
	{
		int user = ((User)(session.get(Constants.USER))).getUserId();
		MessageDAO dao = new MessageDAOImpl();
		latestMessages = dao.getLatestMessagesFromAllUsers(user);
		return SUCCESS;
	}

	public List<LatestMessage> getLatestMessages()
	{
		return latestMessages;
	}

	public void setLatestMessages(List<LatestMessage> latestMessages)
	{
		this.latestMessages = latestMessages;
	}

	public Map<String, Object> getSession()
	{
		return session;
	}

	public void setSession(Map<String, Object> session)
	{
		this.session = session;
	}
}
