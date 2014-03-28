package edu.iiitb.facebook.action.messages;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.MessageDAO;
import edu.iiitb.facebook.action.dao.impl.MessageDAOImpl;
import edu.iiitb.facebook.action.model.LatestMessage;

@Results({
		@Result(name = "success", location = "/messages/messages_list.jsp"),
		@Result(name = "error", location = "/messages/error.jsp") 
		})
public class MessagesListAction extends ActionSupport
{
	private static final long serialVersionUID = -5115586460910679145L;

	private List<LatestMessage> latestMessages;
	private int user = 0;

	@Action(value = "/messages_list")
	public String execute()
	{
		if (user == 0)
			return ERROR;
		MessageDAO dao = new MessageDAOImpl();
		System.out.println("getting latest messages for " + user);
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

	public int getUser()
	{
		return user;
	}

	public void setUser(int user)
	{
		this.user = user;
	}

}
