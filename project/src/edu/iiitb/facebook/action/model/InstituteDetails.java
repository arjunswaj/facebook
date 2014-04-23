package edu.iiitb.facebook.action.model;

import java.util.Date;

public class InstituteDetails implements Comparable<InstituteDetails> {
Date working_from;
Date working_to;
int has_graduated;
String description;
String name;
public int compareTo(InstituteDetails inst) {
	 
	int compareQuantity = ((InstituteDetails) inst).getHas_graduated(); 

	//ascending order
	return this.has_graduated - compareQuantity;

	//descending order
	//return compareQuantity - this.quantity;

}	
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
public int getHas_graduated() {
	return has_graduated;
}
public void setHas_graduated(int has_graduated) {
	this.has_graduated = has_graduated;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

}
