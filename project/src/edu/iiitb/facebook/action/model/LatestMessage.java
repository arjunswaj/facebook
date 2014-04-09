package edu.iiitb.facebook.action.model;

import java.sql.Timestamp;

public class LatestMessage
{
	private int user;
	private int otherUser;
	private String otherUserFirstName;
	private String otherUserLastName;
	private String latestMessage;
	private Timestamp sentAt;
	
	public Timestamp getSentAt()
	{
		return sentAt;
	}
	public void setSentAt(Timestamp sentAt)
	{
		this.sentAt = sentAt;
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
	public String getOtherUserFirstName()
	{
		return otherUserFirstName;
	}
	public void setOtherUserFirstName(String otherUserFirstName)
	{
		this.otherUserFirstName = otherUserFirstName;
	}
	public String getOtherUserLastName()
	{
		return otherUserLastName;
	}
	public void setOtherUserLastName(String otherUserLastName)
	{
		this.otherUserLastName = otherUserLastName;
	}
}
