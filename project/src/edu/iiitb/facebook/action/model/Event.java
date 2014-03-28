package edu.iiitb.facebook.action.model;

public class Event
{
	private String eventName;
	private String eventDescription;
	private String eventPlace;
	private String eventDate;
	private String eventTime;
	
	public Event(String eventName,
			String eventDescription,
			String eventPlace,
			String eventDate,
			String eventTime)
	{
		this.setEventName(eventName);
		this.setEventDescription(eventDescription);
		this.setEventPlace(eventPlace);
		this.setEventDate(eventDate);
		this.setEventTime(eventTime);
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
}
