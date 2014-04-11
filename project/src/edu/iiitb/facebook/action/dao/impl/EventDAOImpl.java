package edu.iiitb.facebook.action.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.iiitb.facebook.action.dao.EventDAO;
import edu.iiitb.facebook.action.model.Event;
import edu.iiitb.facebook.action.model.Invitation;
import edu.iiitb.facebook.action.model.User;

public class EventDAOImpl implements EventDAO
{
	private static final String CREATE_EVENT_QUERY=
			"insert into event(title, description, time, place, created_by) values(?, ?, ?, ?, ?);"; 
	
	private static final String GET_EVENT_ID_QUERY=
			"select max(id) from event where created_by=?;";
	
	private static final String GET_EVENT_QUERY=
			"select * from event where id=?;";
	
	private static final String SEND_INVITE_QUERY=
			"insert into invitation(sent_by, sent_to, event_id) values(?, ?, ?);";
	
	private static final String GET_POTENTIAL_INVITEES_QUERY=
			"select u.id, u.first_name, u.last_name from user u where u.id in (select request_for from friends_with where request_by=? and status='accepted') or u.id in (select request_by from friends_with where request_for=? and status='accepted');";
	
	private static final String GET_BIRTHDAY_PEOPLE_QUERY=
			"select u.id, u.first_name, u.last_name, u.dob from user u where u.dob like ? and (u.id=? or u.id in (select request_for from friends_with where request_by=? and status='accepted') or u.id in (select request_by from friends_with where request_for=? and status='accepted'));";
	
	private static final String GET_BIRTHDATES_QUERY=
			"select distinct(concat(?, substr(u.dob, 5, 6))) from user u where (u.id=? or u.id in (select request_for from friends_with where request_by=? and status='accepted') or u.id in (select request_by from friends_with where request_for=? and status='accepted')) order by u.dob;";
	
	private static final String GET_INVITEES_QUERY=
			"select user.id, user.first_name, user.last_name from user, invitation where invitation.event_id=? and invitation.sent_to=user.id and invitation.confirmation in ";
	
	private static final String DELETE_INVITATION_QUERY=
			"delete from invitation where sent_by=? and sent_to=? and event_id=?;";
	
	private static final String EDIT_EVENT_QUERY=
			"update event set title=?, description=?, time=?, place=? where id=?;";
	
	private static final String GET_EVENTS_QUERY=
			"(select i.id, e.id, e.title, e.place, e.time, i.sent_by, u.first_name, u.last_name, i.confirmation from event e, invitation i, user u where e.id=i.event_id and i.sent_to=? and i.sent_by=u.id and time like ?)"
			+" union "
			+"(select 0, id, title, place, time, ?, 'You', 'are', 'join' from event where created_by=? and time like ?)"
			+" order by time;";
	
	private static final String GET_DATES_OF_EVENTS_AND_BIRTHDAYS_QUERY=
			"(select distinct substr(e.time, 1, 10) as date from event e, invitation i where e.id=i.event_id and i.sent_to=? order by date)"
			+" union "
			+"(select distinct substr(e.time, 1, 10) as date from event e where e.created_by=?)"
			+" union "
			+"(select distinct(concat(?, substr(u.dob, 5, 6))) as date from user u where (u.id=? or u.id in (select request_for from friends_with where request_by=? and status='accepted') or u.id in (select request_by from friends_with where request_for=? and status='accepted')))"
			+" order by date;";
	
	private static final String GET_INVITER_QUERY=
			"select u.* from user u, event e where e.id=? and e.created_by=u.id;";
	
	private static final String GET_CONFIRMATION_STATUS_QUERY=
			"select confirmation from invitation where event_id=? and sent_to=?;";
	
	private static final String SET_CONFIRMATION_STATUS_QUERY=
			"update invitation set confirmation=? where event_id=? and sent_to=?;";
	
	private static final String DELETE_ALL_INVITATIONS_BETWEEN_USERS_QUERY=
			"delete from invitation where (sent_by=? and sent_to=?);";
	
	private static final String DELETE_EVENT_QUERY=
			"delete from event where id=?;";
	
	private static final String DELETE_EVENT_INVITATIONS_QUERY=
			"delete from invitation where event_id=?;";
	
	public void deleteEventAndInvitations(Connection cn, int eventId) throws SQLException
	{
		PreparedStatement ps=cn.prepareStatement(DELETE_EVENT_INVITATIONS_QUERY);
		ps.setInt(1, eventId);
		ps.executeUpdate();
		
		ps=cn.prepareStatement(DELETE_EVENT_QUERY);
		ps.setInt(1, eventId);
		ps.executeUpdate();
		
		ps.close();
	}
	
	public List<User> getBirthdayPeople(Connection cn, int userId, String date) throws SQLException
	{
		PreparedStatement ps=cn.prepareStatement(GET_BIRTHDAY_PEOPLE_QUERY);
		ps.setString(1, "____"+date.substring(4));
		ps.setInt(2, userId);
		ps.setInt(3, userId);
		ps.setInt(4, userId);
		ResultSet rs=ps.executeQuery();
		List<User> birthdayPeople=new ArrayList<User>();
		while(rs.next())
		{
			User u=new User();
			u.setUserId(rs.getInt(1));
			u.setFirstName(rs.getString(2));
			u.setLastName(rs.getString(3));
			birthdayPeople.add(u);
		}
		rs.close();
		ps.close();
		return birthdayPeople;
	}
	
	public List<String> getDatesOfBirthdays(Connection cn, int userId) throws SQLException
	{
		PreparedStatement ps=cn.prepareStatement(GET_BIRTHDATES_QUERY);
		ps.setString(1, ((Integer)Calendar.getInstance().get(Calendar.YEAR)).toString());
		ps.setInt(2, userId);
		ps.setInt(3, userId);
		ps.setInt(4, userId);
		ResultSet rs=ps.executeQuery();
		List<String> birthDates=new ArrayList<String>();
		while(rs.next())
			birthDates.add(rs.getString(1));
		rs.close();
		ps.close();
		return birthDates;
	}
	
	@Override
	public int createEvent(Connection cn, int userId, Event e) throws SQLException
	{
		PreparedStatement ps=cn.prepareStatement(CREATE_EVENT_QUERY);
		ps.setString(1, e.getEventName());
		ps.setString(2, e.getEventDescription());
		ps.setString(3, e.getEventDate()+" "+e.getEventTime());
		ps.setString(4, e.getEventPlace());
		ps.setInt(5, userId);
		ps.executeUpdate();
		
		ps=cn.prepareStatement(GET_EVENT_ID_QUERY);
		ps.setInt(1, userId);
		ResultSet rs=ps.executeQuery();
		rs.next();
		int r=rs.getInt(1);
		rs.close();
		ps.close();
		return r;
	}
	
	public void deleteAllInvitationsBetweenUsers(Connection cn, int userId1, int userId2) throws SQLException
	{
		PreparedStatement ps=cn.prepareStatement(DELETE_ALL_INVITATIONS_BETWEEN_USERS_QUERY);
		
		ps.setInt(1, userId1);
		ps.setInt(2, userId2);
		ps.executeUpdate();
		
		ps.setInt(1, userId2);
		ps.setInt(2, userId1);
		ps.executeUpdate();
		
		ps.close();
	}
	
	public Event getEvent(Connection cn, int eventId) throws SQLException
	{
		PreparedStatement ps=cn.prepareStatement(GET_EVENT_QUERY);
		ps.setInt(1, eventId);
		ResultSet rs=ps.executeQuery();
		rs.next();
		Event e=new Event(
				rs.getString(2), 
				rs.getString(3), 
				rs.getString(5), 
				rs.getString(4).split(" ")[0], 
				rs.getString(4).split(" ")[1].substring(0, 8)
		);
		rs.close();
		ps.close();
		return e;
	}
	
	public void sendInvites(Connection cn, int userId, List<Integer> inviteesList, int eventId) throws SQLException
	{
		PreparedStatement ps=cn.prepareStatement(SEND_INVITE_QUERY);
		
		for(Integer inviteeId:inviteesList)
		{
			ps.setInt(1, userId);
			ps.setInt(2, inviteeId);
			ps.setInt(3, eventId);
			ps.executeUpdate();
		}
		
		ps.close();
	}
	
	public Map<String, String> getPotentialInvitees(Connection cn, int userId, int eventId) throws SQLException
	{
		PreparedStatement ps=cn.prepareStatement(GET_POTENTIAL_INVITEES_QUERY);
		ps.setInt(1, userId);
		ps.setInt(2, userId);
		ResultSet rs=ps.executeQuery();
		Map<String, String> m=new HashMap<String, String>();
		Map<String, String> m2=getInvitees(cn, userId, eventId, "'join', 'maybe', 'pending', 'nope'");
		while(rs.next())
		{
			if(m2.get(rs.getString(1))==null)
				m.put(rs.getString(1), rs.getString(2)+" "+rs.getString(3));
			else
				m.put("-"+rs.getString(1), rs.getString(2)+" "+rs.getString(3));
		}
		rs.close();
		ps.close();
		return m;
	}
	
	//confirmation could be pending, join, maybe, nope
	public Map<String, String> getInvitees(Connection cn, int inviterId, int eventId, String confirmation) throws SQLException
	{
		PreparedStatement ps=cn.prepareStatement(GET_INVITEES_QUERY+"("+confirmation+");");
		ps.setInt(1, eventId);
		ResultSet rs=ps.executeQuery();
		Map<String, String> m=new HashMap<String, String>();
		while(rs.next())
			m.put(rs.getString(1), rs.getString(2)+" "+rs.getString(3));
		rs.close();
		ps.close();
		return m;
	}
	
	public void deleteInvitation(Connection cn, int inviterId, int inviteeId, int eventId) throws SQLException
	{
		PreparedStatement ps=cn.prepareStatement(DELETE_INVITATION_QUERY);
		ps.setInt(1, inviterId);
		ps.setInt(2, inviteeId);
		ps.setInt(3, eventId);
		ps.executeUpdate();
		ps.close();
	}
	
	public void editEvent(Connection cn, int userId, int eventId, Event e) throws SQLException
	{
		PreparedStatement ps=cn.prepareStatement(EDIT_EVENT_QUERY);
		ps.setString(1, e.getEventName());
		ps.setString(2, e.getEventDescription());
		ps.setString(3, e.getEventDate()+" "+e.getEventTime());
		ps.setString(4, e.getEventPlace());
		ps.setInt(5, eventId);
		ps.executeUpdate();
		ps.close();
	}
	
	public List<Invitation> getEvents(Connection cn, int inviteeId, String date) throws SQLException
	{
		PreparedStatement ps=cn.prepareStatement(GET_EVENTS_QUERY);
		ps.setInt(1, inviteeId);
		ps.setString(2, date+"%");
		ps.setInt(3, inviteeId);
		ps.setInt(4, inviteeId);
		ps.setString(5, date+"%");
		List<Invitation> l=new ArrayList<Invitation>();
		ResultSet rs=ps.executeQuery();
		while(rs.next())
			l.add(new Invitation(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5).split("[ ]")[1].substring(0, 5), rs.getString(6), rs.getString(7)+" "+rs.getString(8), rs.getString(9)));
		rs.close();
		ps.close();
		return l;
	}
	
	public List<String> getDatesOfEventsAndBirthdays(Connection cn, int inviteeId) throws SQLException
	{
		PreparedStatement ps=cn.prepareStatement(GET_DATES_OF_EVENTS_AND_BIRTHDAYS_QUERY);
		ps.setInt(1, inviteeId);
		ps.setInt(2, inviteeId);
		
		ps.setString(3, ((Integer)Calendar.getInstance().get(Calendar.YEAR)).toString());
		ps.setInt(4, inviteeId);
		ps.setInt(5, inviteeId);
		ps.setInt(6, inviteeId);
		
		List<String> l=new ArrayList<String>();
		ResultSet rs=ps.executeQuery();
		while(rs.next())
			l.add(rs.getString(1));
		rs.close();
		ps.close();
		return l;
	}
	
	public User getInviter(Connection cn, int eventId) throws SQLException
	{
		PreparedStatement ps=cn.prepareStatement(GET_INVITER_QUERY);
		ps.setInt(1, eventId);
		ResultSet rs=ps.executeQuery();
		User u=null;
		if(rs.next())
		{
			u=new User();
			u.setUserId(rs.getInt("id"));
			u.setEmail(rs.getString("email"));
			u.setFirstName(rs.getString("first_name"));
			u.setLastName(rs.getString("last_name"));
		}
		rs.close();
		ps.close();
		return u;
	}
	
	public String getConfirmationStatus(Connection cn, int eventId, int inviteeId) throws SQLException
	{
		PreparedStatement ps=cn.prepareStatement(GET_CONFIRMATION_STATUS_QUERY);
		ps.setInt(1, eventId);
		ps.setInt(2, inviteeId);
		ResultSet rs=ps.executeQuery();
		String cs="";
		if(rs.next())
			cs=rs.getString(1);
		rs.close();
		ps.close();
		return cs;
	}
	
	public void setConfirmationStatus(Connection cn, int eventId, int inviteeId, String confirmation) throws SQLException
	{
		PreparedStatement ps=cn.prepareStatement(SET_CONFIRMATION_STATUS_QUERY);
		ps.setString(1, confirmation);
		ps.setInt(2, eventId);
		ps.setInt(3, inviteeId);
		ps.executeUpdate();
		ps.close();
	}
}
