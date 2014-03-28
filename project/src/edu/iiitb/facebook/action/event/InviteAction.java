package edu.iiitb.facebook.action.event;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.EventDAO;
import edu.iiitb.facebook.action.dao.impl.EventDAOImpl;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.ConnectionPool;

@Namespace("/events")
@ResultPath("/")
public class InviteAction extends ActionSupport
{
	private String eventId;
	private Map<String, String> map;
	
	private User user;
	
	@Action
	(
		value="/invite",
		results=
		{
			@Result(name="success", location="/event/invitePage.jsp"),
			@Result(name="login", location="/index.jsp")
		}
	)
	public String execute() throws SQLException
	{
		/////////replace with user from session
		user=new User();
		user.setUserId(3);
		user.setFirstName("Dnyanesh");
		user.setEmail("dnyanesh@dnyanesh.com");
		///////////////////////////////////////
		
		
		if(user==null)
			return LOGIN;
		
		Connection cn=ConnectionPool.getConnection();
		
		EventDAO eventDAO=new EventDAOImpl();
		map=eventDAO.getPotentialInvitees(cn, user.getUserId(), Integer.parseInt(eventId));
		
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
}
