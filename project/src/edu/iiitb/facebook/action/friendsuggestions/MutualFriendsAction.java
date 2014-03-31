package edu.iiitb.facebook.action.friendsuggestions;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.FriendsDAO;
import edu.iiitb.facebook.action.dao.impl.FriendsDAOImpl;
import edu.iiitb.facebook.action.model.FriendSuggestions;
import edu.iiitb.facebook.action.model.NewsFeed;

/**
 * 
 * @author rahul
 *
 */

public class MutualFriendsAction extends ActionSupport {
	
	private FriendsDAO friendsDAO = new FriendsDAOImpl();
	private List<FriendSuggestions> mutualFriendsList;
	private int userId;
	private int friendId;
	
	public String execute() {
		setMutualFriendsList(friendsDAO.getMutualFriends(userId, friendId));
		return SUCCESS;
	}

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	
	public List<FriendSuggestions> getMutualFriendsList() {
		return mutualFriendsList;
	}


	public void setMutualFriendsList(List<FriendSuggestions> mutualFriendsList) {
		this.mutualFriendsList = mutualFriendsList;
	}

}
