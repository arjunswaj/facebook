package edu.iiitb.facebook.action.viewfriends;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.FriendsDAO;
import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.dao.impl.FriendsDAOImpl;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.User;

public class ViewFriendsAction extends ActionSupport implements SessionAware,
		RequestAware {
	int loggedInUserId;

	String friendUserId;

	String fref;

	public String getFref() {
		return fref;
	}

	public void setFref(String fref) {
		this.fref = fref;
	}

	public String getFriendUserId() {
		return friendUserId;
	}

	public void setFriendUserId(String friendUserId) {
		this.friendUserId = friendUserId;
	}

	public int getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(int loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	List<User> friendsList;

	public List<User> getFriendsList() {
		return friendsList;
	}

	public void setFriendsList(List<User> friendsList) {
		this.friendsList = friendsList;
	}

	/**
 * 
 */
	private static final long serialVersionUID = 1L;

	public String execute() {

		User user = (User) session.get("user");
		UserDAO userDAO = new UserDAOImpl();
		FriendsDAO friendsDao = new FriendsDAOImpl();
		loggedInUserId = user.getUserId();

		
		System.out.println("fref   ");
		

		fref = (String) session.get("profileReference");

		if (fref == null) {
			friendsList = friendsDao.getFriendsList(loggedInUserId);
			setFriendsList(friendsDao.getFriendsList(loggedInUserId));
		} else {
			System.out.println(Integer.parseInt(fref));
			friendsList = friendsDao.getFriendsList(Integer.parseInt(fref));
			setFriendsList(friendsDao.getFriendsList(Integer.parseInt(fref)));
		}

		// for (User user : friendsList) {
		// System.out.println(user.getUserId() + "! " + user.getFirstName());
		// }
		System.out.println("view friends main class");
		if (friendsList != null) {
			return SUCCESS;
		} else {
			return "error";
		}
	}

	Map<String, Object> session;
	Map<String, Object> request;

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
	}
}
