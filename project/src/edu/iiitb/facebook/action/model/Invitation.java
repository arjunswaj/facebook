package edu.iiitb.facebook.action.model;

public class Invitation
{
	private String invitationId;
	private String eventId;
	private String eventName;
	private String eventPlace;
	private String eventTime;
	private String inviterId;
	private String inviterName;
	private String confirmation;
	
	public Invitation(String invitationId, String eventId, String eventName, String eventPlace, String eventTime, String inviterId, String inviterName, String confirmation)
	{
		this.invitationId=invitationId;
		this.eventId=eventId;
		this.eventName=eventName;
		this.eventPlace=eventPlace;
		this.eventTime=eventTime;
		this.inviterId=inviterId;
		this.inviterName=inviterName;
		this.confirmation=confirmation;
	}
	
	public String getInvitationId() {
		return invitationId;
	}
	public void setInvitationId(String invitationId) {
		this.invitationId = invitationId;
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
	public String getInviterId() {
		return inviterId;
	}
	public void setInviterId(String inviterId) {
		this.inviterId = inviterId;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	public String getInviterName() {
		return inviterName;
	}

	public void setInviterName(String inviterName) {
		this.inviterName = inviterName;
	}
}
