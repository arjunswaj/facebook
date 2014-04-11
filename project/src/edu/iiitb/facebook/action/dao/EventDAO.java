package edu.iiitb.facebook.action.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import edu.iiitb.facebook.action.model.Event;
import edu.iiitb.facebook.action.model.Invitation;
import edu.iiitb.facebook.action.model.User;

public interface EventDAO
{
	public int createEvent(Connection cn, int userId, Event e) throws SQLException;
	public Event getEvent(Connection cn, int eventId) throws SQLException;
	public void sendInvites(Connection cn, int userId, List<Integer> inviteesList, int eventId) throws SQLException;
	public Map<String, String> getPotentialInvitees(Connection cn, int userId, int eventId) throws SQLException;
	public Map<String, String> getInvitees(Connection cn, int inviterId, int eventId, String confirmation) throws SQLException;
	public void deleteInvitation(Connection cn, int inviterId, int inviteeId, int eventId) throws SQLException;
	public void editEvent(Connection cn, int userId, int eventId, Event e) throws SQLException;
	public List<Invitation> getEvents(Connection cn, int inviteeId, String date) throws SQLException;
	public List<String> getDatesOfEventsAndBirthdays(Connection cn, int inviteeId) throws SQLException;
	public User getInviter(Connection cn, int eventId) throws SQLException;
	public String getConfirmationStatus(Connection cn, int eventId, int inviteeId) throws SQLException;
	public void setConfirmationStatus(Connection cn, int eventId, int inviteeId, String confirmation) throws SQLException;
	public void deleteAllInvitationsBetweenUsers(Connection cn, int userId1, int userId2) throws SQLException;
	public void deleteEventAndInvitations(Connection cn, int eventId) throws SQLException;
	public List<User> getBirthdayPeople(Connection cn, int userId, String date) throws SQLException;
	public List<String> getDatesOfBirthdays(Connection cn, int userId) throws SQLException;
}
