package edu.iiitb.facebook.action.model;

/**
 * Models an entity of the 'message' class in the DB
 * @author kempa
 *
 */
public class Message
{
	private int id;
	private int conversation;
	private String text;
	private String sentAt;
	private int sender;
	private int inbox;
	private String senderFirstName;
	private String senderLastName;
	private String readStatus;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getText()
	{
		return text;
	}
	public void setText(String text)
	{
		this.text = text;
	}
	public String getSentAt()
	{
		return sentAt;
	}
	public void setSentAt(String sentAt)
	{
		this.sentAt = sentAt;
	}
	public String getSenderFirstName()
	{
		return senderFirstName;
	}
	public void setSenderFirstName(String senderFirstName)
	{
		this.senderFirstName = senderFirstName;
	}
	public String getSenderLastName()
	{
		return senderLastName;
	}
	public void setSenderLastName(String senderLastName)
	{
		this.senderLastName = senderLastName;
	}
	public String getReadStatus()
	{
		return readStatus;
	}
	public void setReadStatus(String readStatus)
	{
		this.readStatus = readStatus;
	}
	public int getSender()
	{
		return sender;
	}
	public void setSender(int sender)
	{
		this.sender = sender;
	}
	public int getInbox()
	{
		return inbox;
	}
	public void setInbox(int inbox)
	{
		this.inbox = inbox;
	}
	public int getConversation()
	{
		return conversation;
	}
	public void setConversation(int conversation)
	{
		this.conversation = conversation;
	}
}
