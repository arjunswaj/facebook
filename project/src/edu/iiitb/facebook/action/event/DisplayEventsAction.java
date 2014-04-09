package edu.iiitb.facebook.action.event;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.EventDAO;
import edu.iiitb.facebook.action.dao.impl.EventDAOImpl;
import edu.iiitb.facebook.action.model.Invitation;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.ConnectionPool;

@Namespace("event")
@ParentPackage("tiles-default")
public class DisplayEventsAction extends ActionSupport implements SessionAware
{
	private Map<String, List<Invitation>> invitationListMap;
	private Map<String, Object> session;
	private User user;
	
	@Action
	(
		value="/displayEvents",
		results=
		{
			@Result(name="success", type="tiles", location="eventsPage.tiles"),
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
		invitationListMap=new LinkedHashMap<String, List<Invitation>>();
		for(String date:eventDAO.getDatesOfEvents(cn, user.getUserId()))
			invitationListMap.put(date, eventDAO.getEvents(cn, user.getUserId(), date));
		ConnectionPool.freeConnection(cn);
		
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		session=arg0;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, List<Invitation>> getInvitationListMap() {
		return invitationListMap;
	}

	public void setInvitationListMap(Map<String, List<Invitation>> invitationListMap) {
		this.invitationListMap = invitationListMap;
	}
}
