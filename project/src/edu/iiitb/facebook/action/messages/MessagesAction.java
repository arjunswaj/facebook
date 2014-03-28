package edu.iiitb.facebook.action.messages;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.MessageDAO;
import edu.iiitb.facebook.action.dao.impl.MessageDAOIMpl;
import edu.iiitb.facebook.action.model.Message;


@Results({@Result(name = "success", location = "/messages/messages.jsp")})
public class MessagesAction extends ActionSupport
{
	private static final long serialVersionUID = 7253053184925533403L;
	
	private List<Message> messages;
	private int sender = 1;
	private int recipient = 2;
	
	@Action(value = "/messages")
	public String execute()
	{
		MessageDAO dao = new MessageDAOIMpl();
		messages = dao.getMessages(sender, recipient);
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
}
