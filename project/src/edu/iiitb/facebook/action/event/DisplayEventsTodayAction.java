package edu.iiitb.facebook.action.event;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

@ParentPackage("tiles-default")
public class DisplayEventsTodayAction extends ActionSupport implements SessionAware
{
	private List<Invitation> eventsToday;
	private List<User> birthdayPeopleToday;
	private Map<String, Object> session;
	private User user;
	
	@Action
	(
		value="/displayEventsToday",
		results=
		{
			@Result(name="success", location="/event/eventsTodayPage.jsp"),
			@Result(name="login", location="/index.jsp")
		}
	)
	public String execute() throws SQLException, ParseException
	{
		user = (User) session.get("user");
		if(user==null)
			return LOGIN;
		
		Connection cn=ConnectionPool.getConnection();
		EventDAO eventDAO=new EventDAOImpl();
		
		
		String yyyy=((Integer)Calendar.getInstance().get(Calendar.YEAR)).toString();
		String M="0"+((Integer)(1+Calendar.getInstance().get(Calendar.MONTH))).toString();
		String d="0"+((Integer)Calendar.getInstance().get(Calendar.DATE)).toString();
		String MM=M.substring(M.length()-2);
		String dd=d.substring(d.length()-2);
		String dateTodayString=yyyy+"-"+MM+"-"+dd;
		
		birthdayPeopleToday=eventDAO.getBirthdayPeople(cn, user.getUserId(), dateTodayString);
		eventsToday=eventDAO.getEvents(cn, user.getUserId(), dateTodayString);
		
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

	public List<Invitation> getEventsToday() {
		return eventsToday;
	}

	public void setEventsToday(List<Invitation> eventsToday) {
		this.eventsToday = eventsToday;
	}

	public List<User> getBirthdayPeopleToday() {
		return birthdayPeopleToday;
	}

	public void setBirthdayPeopleToday(List<User> birthdayPeopleToday) {
		this.birthdayPeopleToday = birthdayPeopleToday;
	}

}
