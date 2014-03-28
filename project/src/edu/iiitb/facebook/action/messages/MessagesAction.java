package edu.iiitb.facebook.action.messages;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.MessageDAO;
import edu.iiitb.facebook.action.dao.impl.MessageDAOImpl;
import edu.iiitb.facebook.action.model.Message;

@Results({ @Result(name = "success", location = "/messages/messages.jsp"),
		@Result(name = "error", location = "/messages/error.jsp") })
public class MessagesAction extends ActionSupport
{
	private static final long serialVersionUID = 7253053184925533403L;

	private List<Message> messages;
	private int sender = 0;
	private int recipient = 0;
	/**
	 * Load messages
	 * @return
	 */
	@Action(value = "/messages")
	public String messages()
	{
		if (sender == 0 || recipient == 0)
			return ERROR;

		MessageDAO dao = new MessageDAOImpl();
		messages = dao.getMessages(sender, recipient);
		return SUCCESS;
	}

	private String reply;
	private int from;
	private int to;
	/**
	 * Send reply
	 * @return
	 */
	@Action(value = "/reply")
	public String reply()
	{	
		// Sent(insert) message
		Message replyMsg = new Message();
		replyMsg.setText(reply);
		replyMsg.setSender(from);
		replyMsg.setRecipient(to);
		MessageDAO dao = new MessageDAOImpl();
		dao.insert(replyMsg);
		
		// get the messages for display
		messages = dao.getMessages(to, from);
		sender=to;
		recipient=from;

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

	public int getSender()
	{
		return sender;
	}

	public void setSender(int sender)
	{
		this.sender = sender;
	}

	public int getRecipient()
	{
		return recipient;
	}

	public void setRecipient(int recipient)
	{
		this.recipient = recipient;
	}

	public String getReply()
	{
		return reply;
	}

	public void setReply(String reply)
	{
		this.reply = reply;
	}

	public int getFrom()
	{
		return from;
	}

	public void setFrom(int from)
	{
		this.from = from;
	}

	public int getTo()
	{
		return to;
	}

	public void setTo(int to)
	{
		this.to = to;
	}
}
