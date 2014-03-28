package edu.iiitb.facebook.action.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import edu.iiitb.facebook.action.model.Event;

public interface EventDAO
{
	public int createEvent(Connection cn, int userId, Event e) throws SQLException;
	public Event getEvent(Connection cn, int eventId) throws SQLException;
	public void sendInvites(Connection cn, int userId, List<Integer> inviteesList, int eventId) throws SQLException;
	public Map<String, String> getPotentialInvitees(Connection cn, int userId, int eventId) throws SQLException;
	public Map<String, String> getInvitees(Connection cn, int inviterId, int eventId, String confirmation) throws SQLException;
	public void deleteInvitation(Connection cn, int inviterId, int inviteeId, int eventId) throws SQLException;
	public void editEvent(Connection cn, int userId, int eventId, Event e) throws SQLException;
}
