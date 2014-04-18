package edu.iiitb.facebook.action.messages;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.FriendsDAO;
import edu.iiitb.facebook.action.dao.impl.FriendsDAOImpl;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.Constants;

public class CreateNewMessageAction extends ActionSupport implements SessionAware
{
	private static final long serialVersionUID = -1229947406728255002L;

	private Map<String, Object> session;
	private List<User> friends;

	public String friendsList()
	{
		FriendsDAO dao = new FriendsDAOImpl();
		User user = (User) session.get(Constants.USER);
		setFriends(dao.getFriendsList(user.getUserId()));

		return SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@Override
	public void setSession(Map<String, Object> arg0)
	{
		this.session = arg0;
	}

	public List<User> getFriends()
	{
		return friends;
	}

	public void setFriends(List<User> friends)
	{
		this.friends = friends;
	}
}
