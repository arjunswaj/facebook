package edu.iiitb.facebook.action.event;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.EventDAO;
import edu.iiitb.facebook.action.dao.impl.EventDAOImpl;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.ConnectionPool;

@Namespace("event")
@ParentPackage("tiles-default")
public class ConfirmationAction extends ActionSupport implements SessionAware
{
	private String caller="eventsPage";
	private String eventId;
	private String confirmation;
	
	private Map<String, Object> session;
	private User user;
	
	@Action
	(
		value="/confirm",
		results=
		{
			@Result(name="success1", location="/event/confirmationDropDownInnerHTML.html"),
			@Result(name="success2", type="chain", location="event"),
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
		System.out.println();
		eventDAO.setConfirmationStatus(cn, Integer.parseInt(eventId), user.getUserId(), confirmation);
		ConnectionPool.freeConnection(cn);
		
		if(caller.equals("eventsPage"))
			return "success1";
		else
			return "success2";
	}
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session=arg0;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}
}
