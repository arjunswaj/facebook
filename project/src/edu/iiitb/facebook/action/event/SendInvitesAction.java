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
public class SendInvitesAction extends ActionSupport implements SessionAware
{
	private String caller;
	private String eventId;
	private String invitees;
	private List<Integer> inviteesList;
	private Map<String, Object> session;
	private User user;
	
	@Action
	(
		value="/sendInvites",
		results=
		{
			@Result(name="success", type="chain", location="event"),
			@Result(name="success2", type="redirect", location="displayEvents"),
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
		
		inviteesList=new ArrayList<Integer>();
		String[] sa=invitees.split("[|]");
		for(int k=0; k<sa.length; k++)
			if(!sa[k].equals(""))
				inviteesList.add(Integer.parseInt(sa[k]));
		eventDAO.sendInvites(cn, user.getUserId(), inviteesList, Integer.parseInt(eventId));
		
		
		ConnectionPool.freeConnection(cn);
		
		if(caller.equals("eventsPage"))
			return "success2";
		
		return SUCCESS;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public String getInvitees() {
		return invitees;
	}

	public void setInvitees(String invitees) {
		this.invitees = invitees;
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

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}
}
