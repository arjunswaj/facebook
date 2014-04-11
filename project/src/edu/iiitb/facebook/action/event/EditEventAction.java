package edu.iiitb.facebook.action.event;

import java.sql.Connection;
import java.sql.SQLException;
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
import edu.iiitb.facebook.action.model.Event;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.ConnectionPool;

@ParentPackage("tiles-default")
public class EditEventAction extends ActionSupport implements SessionAware
{
	private String eventId;
	private String eventName;
	private String eventDescription;
	private String eventPlace;
	private String eventDate;
	private String eventTime;
	private Map<String, Object> session;
	private User user;
	
	@Action
	(
		value="/editEvent",
		results=
		{
			@Result(name="edit", location="/event/editEventPage.jsp"),
			@Result(name="success", type="chain", location="displayEvent"),
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
		
		if(eventName==null || eventName.equals(""))
		{
			Event e=eventDAO.getEvent(cn, Integer.parseInt(eventId));
			eventName=e.getEventName();
			eventDescription=e.getEventDescription();
			eventPlace=e.getEventPlace();
			eventDate=e.getEventDate();
			eventTime=e.getEventTime();
			ConnectionPool.freeConnection(cn);
			return "edit";
		}
		else
		{
			Event e=new Event(eventName, eventDescription, eventPlace, eventDate, eventTime);
			eventDAO.editEvent(cn, user.getUserId(), Integer.parseInt(eventId), e);
			ConnectionPool.freeConnection(cn);
			return SUCCESS;
		}
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEventPlace() {
		return eventPlace;
	}

	public void setEventPlace(String eventPlace) {
		this.eventPlace = eventPlace;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
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
