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
	private int recipient;
	
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

}
