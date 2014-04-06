package edu.iiitb.facebook.action.event;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
import edu.iiitb.facebook.action.model.Event;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.ConnectionPool;

@Namespace("event")
@ParentPackage("tiles-default")
public class EventAction extends ActionSupport implements SessionAware
{
	private User inviter;
	private String confirmationIfUserIsInvitee="going";
	private String eventId;
	private String eventName;
	private String eventDescription;
	private String eventPlace;
	private String eventDate;
	private String eventTime;
	private String going="0";
	private String maybe="0";
	private String invited="0";
	private Map<String, Object> session;
	private User user;
	
	@Action
	(
		value="/event",
		results=
		{
			@Result(name="success", type="tiles", location="eventPage.tiles"),
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
		Event e=eventDAO.getEvent(cn, Integer.parseInt(eventId));
		eventName=e.getEventName();
		eventDescription=e.getEventDescription();
		eventPlace=e.getEventPlace();
		eventDate=e.getEventDate();
		eventTime=e.getEventTime();
		
		going=((Integer)eventDAO.getInvitees(cn, user.getUserId(), Integer.parseInt(eventId), "join").size()).toString();
		maybe=((Integer)eventDAO.getInvitees(cn, user.getUserId(), Integer.parseInt(eventId), "maybe").size()).toString();
		invited=((Integer)eventDAO.getInvitees(cn, user.getUserId(), Integer.parseInt(eventId), "pending").size()).toString();
		
		setInviter(eventDAO.getInviter(cn, Integer.parseInt(eventId)));
		confirmationIfUserIsInvitee=eventDAO.getConfirmationStatus(cn, Integer.parseInt(eventId), user.getUserId());
		
		ConnectionPool.freeConnection(cn);
		return SUCCESS;
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

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventPlace() {
		return eventPlace;
	}

	public void setEventPlace(String eventPlace) {
		this.eventPlace = eventPlace;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getGoing() {
		return going;
	}

	public void setGoing(String going) {
		this.going = going;
	}

	public String getMaybe() {
		return maybe;
	}

	public void setMaybe(String maybe) {
		this.maybe = maybe;
	}

	public String getInvited() {
		return invited;
	}

	public void setInvited(String invited) {
		this.invited = invited;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		session=arg0;
	}

	public User getInviter() {
		return inviter;
	}

	public void setInviter(User inviter) {
		this.inviter = inviter;
	}

	public String getConfirmationIfUserIsInvitee() {
		return confirmationIfUserIsInvitee;
	}

	public void setConfirmationIfUserIsInvitee(
			String confirmationIfUserIsInvitee) {
		this.confirmationIfUserIsInvitee = confirmationIfUserIsInvitee;
	}
}
