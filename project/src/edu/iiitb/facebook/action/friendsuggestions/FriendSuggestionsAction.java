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

public class FriendSuggestionsAction extends ActionSupport {
	
	private FriendsDAO friendsDAO = new FriendsDAOImpl();
	private List<FriendSuggestions> friendSuggestionsList;
	private int userId;
	
	public String execute() {
		setFriendSuggestionsList(friendsDAO.getFriendSuggestions(userId));
		return SUCCESS;
	}

	public List<FriendSuggestions> getFriendSuggestionsList() {
		return friendSuggestionsList;
	}

	public void setFriendSuggestionsList(List<FriendSuggestions> friendSuggestionsList) {
		this.friendSuggestionsList = friendSuggestionsList;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
