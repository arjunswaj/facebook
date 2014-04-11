package edu.iiitb.facebook.action.model;

public class FriendSuggestions {
	
	private int friendId;
	private String firstName;
	private String lastName;
	
	public FriendSuggestions(int friendId, String firstName, String lastName) {
		this.friendId = friendId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public FriendSuggestions() {
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
}
