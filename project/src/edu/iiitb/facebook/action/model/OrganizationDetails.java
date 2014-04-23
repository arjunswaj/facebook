package edu.iiitb.facebook.action.model;

import java.util.Date;

public class OrganizationDetails {
Date working_from;
Date working_to;
String name;
String position;
public Date getWorking_from() {
	return working_from;
}
public void setWorking_from(Date working_from) {
	this.working_from = working_from;
}
public Date getWorking_to() {
	return working_to;
}
public void setWorking_to(Date working_to) {
	this.working_to = working_to;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPosition() {
	return position;
}
public void setPosition(String position) {
	this.position = position;
}
public OrganizationDetails(Date working_from, Date working_to, String name,
		String position) {
	super();
	this.working_from = working_from;
	this.working_to = working_to;
	this.name = name;
	this.position = position;
}
public OrganizationDetails() {
	// TODO Auto-generated constructor stub
}

}
