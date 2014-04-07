package edu.iiitb.facebook.action.model;

import java.sql.Timestamp;

public class LatestMessage
{
	private int user;
	private int otherUser;
	private String senderFirstName;
	private String senderLastName;
	private String latestMessage;
	private Timestamp sentAt;
	
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
	public String getSenderLastName()
	{
		return senderLastName;
	}
	public void setSenderLastName(String senderLastName)
	{
		this.senderLastName = senderLastName;
	}
	public int getUser()
	{
		return user;
	}
	public void setUser(int user)
	{
		this.user = user;
	}
	public int getOtherUser()
	{
		return otherUser;
	}
	public void setOtherUser(int otherUser)
	{
		this.otherUser = otherUser;
	}
	public String getLatestMessage()
	{
		return latestMessage;
	}
	public void setLatestMessage(String latestMessage)
	{
		this.latestMessage = latestMessage;
	}
}
