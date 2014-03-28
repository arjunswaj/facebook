package edu.iiitb.facebook.action.messages;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.MessageDAO;
import edu.iiitb.facebook.action.dao.impl.MessageDAOIMpl;
import edu.iiitb.facebook.action.model.LatestMessage;

@Results({@Result(name = "success", location = "/messages/latest_messages.jsp")})
public class LatestMessagesAction extends ActionSupport
{
	private static final long serialVersionUID = -5115586460910679145L;
	
	private List<LatestMessage> latestMessages;
	private int user = 1; 

	@Action(value = "/latest_messages")
	public String execute()
	{
		MessageDAO dao = new MessageDAOIMpl();
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

}
