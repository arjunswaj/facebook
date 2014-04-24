package edu.iiitb.facebook.action.settings;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.FriendsDAO;
import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.dao.impl.FriendsDAOImpl;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.FriendSuggestions;
import edu.iiitb.facebook.action.model.User;

public class AccountSettingsAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -4398801602286563549L;
	private Map<String, Object> session;
	private int userId;
	private FriendsDAO friendsDAO = new FriendsDAOImpl();
	private UserDAO userDAO = new UserDAOImpl();
	private List<FriendSuggestions> blockedFriendsList;
	
	private String localeCode;
	
	public String execute() {
		User user = (User) session.get("user");
		if (user == null) return LOGIN;
	    setUserId(user.getUserId());
	    setBlockedFriendsList( friendsDAO.getBlockedFriends(userId) );
		return SUCCESS;
	}
	
	public String edit() {
		User user = (User) session.get("user");
		if (user == null) return LOGIN;
	    setUserId(user.getUserId());		
		return SUCCESS;
	}
	
	public String update() {
		User user = (User) session.get("user");
		if (user == null) return LOGIN;
		setUserId(user.getUserId());
		userDAO.setLocale(userId, localeCode);
		user.setLocale(localeCode);
		return SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<FriendSuggestions> getBlockedFriendsList() {
		return blockedFriendsList;
	}

	public void setBlockedFriendsList(List<FriendSuggestions> blockedFriendsList) {
		this.blockedFriendsList = blockedFriendsList;
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}


}
