package edu.iiitb.facebook.action.model;

import java.sql.Timestamp;

/**
 * Models an entity of the 'message' class in the DB
 * @author kempa
 *
 */
public class Message
{
	private int id;
	private String text;
	private Timestamp sentAt;
	private int sender;
	private String senderFirstName;
	private String senderLastName;
	private int recipient;
	private String recipientFirstName;
	private String recipientLastName;
	
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
	public Timestamp getSentAt()
	{
		return sentAt;
	}
	public void setSentAt(Timestamp sentAt)
	{
		this.sentAt = sentAt;
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
	public String getRecipientFirstName()
	{
		return recipientFirstName;
	}
	public void setRecipientFirstName(String recipientFirstName)
	{
		this.recipientFirstName = recipientFirstName;
	}
	public String getRecipientLastName()
	{
		return recipientLastName;
	}
	public void setRecipientLastName(String recipientLastName)
	{
		this.recipientLastName = recipientLastName;
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

}
