package edu.iiitb.facebook.action.model;

import java.sql.Timestamp;

public class LatestMessage
{
	private int sender;
	private String senderFirstName;
	private String senderLastName;
	private String text;
	private int recipient;
	private Timestamp sentAt;
	
	public int getSender()
	{
		return sender;
	}
	public void setSender(int sender)
	{
		this.sender = sender;
	}
	public String getSenderFirstName()
	{
		return senderFirstName;
	}
	public void setSenderFirstName(String senderFirstName)
	{
		this.senderFirstName = senderFirstName;
	}
	public Timestamp getSentAt()
	{
		return sentAt;
	}
	public void setSentAt(Timestamp sentAt)
	{
		this.sentAt = sentAt;
	}
	public String getText()
	{
		return text;
	}
	public void setText(String text)
	{
		this.text = text;
	}
	public int getRecipient()
	{
		return recipient;
	}
	public void setRecipient(int recipient)
	{
		this.recipient = recipient;
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
