package edu.iiitb.facebook.action.dao;

import java.util.List;

import edu.iiitb.facebook.action.model.FriendInfo;
import edu.iiitb.facebook.action.model.FriendSuggestions;
import edu.iiitb.facebook.action.model.User;

public interface FriendsDAO
{

	String ID = "id";
	String REQUEST_STATUS = "status"; // enum('pending','accepted','blocked')
	String REQUEST_BY = "request_by";
	String REQUEST_FOR = "request_for";
	String FRIEND_REQUEST_SENT = "friend_request_sent";
	String FRIEND_REQUEST_ACCEPTED = "friend_request_accepted";

	public FriendInfo getFriendRequestStatus(int loggedInUserId, int otherUserId);

	public List<User> getFriendsList(int userId);

	public List<User> getFriendsRequestsByStrangers(int userId);
	
	public boolean addFriend(int loggedInUserId, int otherUserId);

	public boolean confirmFriend(int loggedInUserId, int otherUserId);

	public boolean rejectFriend(int loggedInUserId, int otherUserId);

	public boolean blockFriend(int loggedInUserId, int otherUserId);

	public boolean unblockFriend(int loggedInUserId, int otherUserId);
	
	public List<FriendSuggestions> getFriendSuggestions(int userId);
	
	public List<FriendSuggestions> getMutualFriends(int userId, int friendId);
	
	public List<FriendSuggestions> getBlockedFriends(int userId);
	
	

}
