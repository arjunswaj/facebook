package edu.iiitb.facebook.action.model;

import java.util.List;


public class Conversation
{
	private int id;
	private Message latestMessage;
	private int unreadMessagesCount;
	private List<User> otherParticipants; // Except the logged in user
	private boolean allFriends;
	
	public int getUnreadMessagesCount()
	{
		return unreadMessagesCount;
	}
	public void setUnreadMessagesCount(int unreadMessagesCount)
	{
		this.unreadMessagesCount = unreadMessagesCount;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public List<User> getOtherParticipants()
	{
		return otherParticipants;
	}
	public void setOtherParticipants(List<User> otherParticipants)
	{
		this.otherParticipants = otherParticipants;
	}
	public boolean isAllFriends()
	{
		return allFriends;
	}
	public void setAllFriends(boolean allFriends)
	{
		this.allFriends = allFriends;
	}
	public Message getLatestMessage()
	{
		return latestMessage;
	}
	public void setLatestMessage(Message latestMessage)
	{
		this.latestMessage = latestMessage;
	}
}
