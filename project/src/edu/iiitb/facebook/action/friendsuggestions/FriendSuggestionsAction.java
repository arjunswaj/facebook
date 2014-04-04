package edu.iiitb.facebook.action.friendsuggestions;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.FriendsDAO;
import edu.iiitb.facebook.action.dao.impl.FriendsDAOImpl;
import edu.iiitb.facebook.action.model.FriendSuggestions;
import edu.iiitb.facebook.action.model.User;

/**
 * 
 * @author rahul
 *
 */

public class FriendSuggestionsAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = -8974523974587158560L;
	private FriendsDAO friendsDAO = new FriendsDAOImpl();
	private List<FriendSuggestions> friendSuggestionsList;
	private int userId;
	private Map<String, Object> session;
	
	public String execute() {
		User user = (User) session.get("user");
	    userId = user.getUserId();
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

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	

}
