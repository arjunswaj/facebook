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
public class DisplayEventsAction extends ActionSupport implements SessionAware
{
	private String dateTodayString;
	private Map<String, List<Invitation>> invitationListMap;
	private Map<String, List<User>> birthdayPeopleListMap;
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
	public String execute() throws SQLException, ParseException
	{
		user = (User) session.get("user");
		if(user==null)
			return LOGIN;
		
		Connection cn=ConnectionPool.getConnection();
		EventDAO eventDAO=new EventDAOImpl();
		
		invitationListMap=new LinkedHashMap<String, List<Invitation>>();
		birthdayPeopleListMap=new LinkedHashMap<String, List<User>>();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date dateToday=sdf.parse(Calendar.getInstance().get(Calendar.YEAR)+"-"
				+(Calendar.getInstance().get(Calendar.MONTH)+1)+"-"+Calendar.getInstance().get(Calendar.DATE));
		
		List<String> eventAndBirthDates=eventDAO.getDatesOfEventsAndBirthdays(cn, user.getUserId());
		List<String> birthDates=eventDAO.getDatesOfBirthdays(cn, user.getUserId());
		
		for(String date:eventAndBirthDates)
			if(sdf.parse(date).getTime()-dateToday.getTime()>=0)
				invitationListMap.put(date, eventDAO.getEvents(cn, user.getUserId(), date));
		
		for(String date:birthDates)
			if(sdf.parse(date).getTime()-dateToday.getTime()>=0)
				birthdayPeopleListMap.put(date, eventDAO.getBirthdayPeople(cn, user.getUserId(), date));
		
		ConnectionPool.freeConnection(cn);
		
		
		
		
		String yyyy=((Integer)Calendar.getInstance().get(Calendar.YEAR)).toString();
		String M="0"+((Integer)(1+Calendar.getInstance().get(Calendar.MONTH))).toString();
		String d="0"+((Integer)Calendar.getInstance().get(Calendar.DATE)).toString();
		String MM=M.substring(M.length()-2);
		String dd=d.substring(d.length()-2);
		dateTodayString=yyyy+"-"+MM+"-"+dd;
		
		
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

	public Map<String, List<User>> getBirthdayPeopleListMap() {
		return birthdayPeopleListMap;
	}

	public void setBirthdayPeopleListMap(Map<String, List<User>> birthdayPeopleListMap) {
		this.birthdayPeopleListMap = birthdayPeopleListMap;
	}

	public String getDateTodayString() {
		return dateTodayString;
	}

	public void setDateTodayString(String dateTodayString) {
		this.dateTodayString = dateTodayString;
	}
}
