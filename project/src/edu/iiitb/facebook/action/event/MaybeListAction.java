package edu.iiitb.facebook.action.event;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.EventDAO;
import edu.iiitb.facebook.action.dao.impl.EventDAOImpl;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.ConnectionPool;

@Namespace("event")
public class MaybeListAction extends ActionSupport implements SessionAware
{
	private String eventId;
	private Map<String, String> map;
	private Map<String, Object> session;
	private User user;
	
	@Action
	(
		value="/maybeList",
		results=
		{
			@Result(name="success", location="/event/maybeList.jsp"),
			@Result(name="login", location="/index.jsp")
		}
	)
	public String execute() throws SQLException
	{
		user = (User) session.get("user");
		
		
		if(user==null)
			return LOGIN;
		
		Connection cn=ConnectionPool.getConnection();
		
		EventDAO eventDAO=new EventDAOImpl();
		map=eventDAO.getInvitees(cn, user.getUserId(), Integer.parseInt(eventId), "maybe");
		
		ConnectionPool.freeConnection(cn);
		return SUCCESS;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		session=arg0;
	}
}
