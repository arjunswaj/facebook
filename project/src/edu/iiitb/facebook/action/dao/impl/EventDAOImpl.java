package edu.iiitb.facebook.action.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.iiitb.facebook.action.dao.EventDAO;
import edu.iiitb.facebook.action.model.Event;

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
			"select u.id, u.first_name, u.last_name from user u where u.id in (select request_for from friends_with where request_by=? and request_status='accepted') or u.id in (select request_by from friends_with where request_for=? and request_status='accepted');";
	
	private static final String GET_INVITEES_QUERY=
			"select user.id, user.first_name, user.last_name from user, invitation where invitation.sent_by=? and invitation.event_id=? and invitation.sent_to=user.id and invitation.confirmation like ?;";
	
	private static final String DELETE_INVITATION_QUERY=
			"delete from invitation where sent_by=? and sent_to=? and event_id=?;";
	
	private static final String EDIT_EVENT_QUERY=
			"update event set title=?, description=?, time=?, place=? where id=?;";
	
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
		Map<String, String> m2=getInvitees(cn, userId, eventId, "%");
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
	
	//confirmation could be %, join, maybe, nope
	public Map<String, String> getInvitees(Connection cn, int inviterId, int eventId, String confirmation) throws SQLException
	{
		PreparedStatement ps=cn.prepareStatement(GET_INVITEES_QUERY);
		ps.setInt(1, inviterId);
		ps.setInt(2, eventId);
		ps.setString(3, confirmation);
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
}
