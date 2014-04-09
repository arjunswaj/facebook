package edu.iiitb.facebook.action.viewfriends;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.FriendsDAO;
import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.dao.impl.FriendsDAOImpl;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.FriendInfo;
import edu.iiitb.facebook.action.model.User;


public class ViewFriendsAction extends ActionSupport implements SessionAware{
	int loggedInUserId;

	String friendUserId;

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
		loggedInUserId=user.getUserId();
		FriendsDAO friendsDao = new FriendsDAOImpl();
		
		friendsList=friendsDao.getFriendsList(loggedInUserId);
		for(User userr: friendsList){
			System.out.println(user.getEmail());
			
		}
		setFriendsList(friendsDao.getFriendsList(loggedInUserId));

//		for (User user : friendsList) {
//			System.out.println(user.getUserId() + "! " + user.getFirstName());
//		}
		System.out.println("view friends main class");
		return SUCCESS;
	}

	public String viewFriends() {
		User user = (User) session.get("user");
		loggedInUserId=user.getUserId();
		FriendsDAO friendsDao = new FriendsDAOImpl();
		friendsDao.getFriendsList(loggedInUserId);

		System.out.println("view friends executed");
		System.out.println(loggedInUserId);
		System.out.println(friendUserId);
		return SUCCESS;
	}
	Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> arg0)
	{
		this.session = arg0;
	}
}
