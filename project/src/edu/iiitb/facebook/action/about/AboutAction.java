package edu.iiitb.facebook.action.about;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;


import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.DetailsDAO;
import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.dao.impl.DetailsDAOImpl;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.*;

public class AboutAction extends ActionSupport implements SessionAware {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String relationship;
private List<OrganizationDetails> organization_details=new ArrayList<OrganizationDetails>();
private List<InstituteDetails> institute_details=new ArrayList<InstituteDetails>();
private DetailsDAO detailsDAO=new DetailsDAOImpl();
private String place;
private String nativePlace;
private String year;
private String date_month;
private String gender;

public String getNativePlace() {
	return nativePlace;
}


public void setNativePlace(String nativePlace) {
	this.nativePlace = nativePlace;
}


public String getGender() {
	return gender;
}


public void setGender(String gender) {
	this.gender = gender;
}


private UserDAO userDAO;
public UserDAO getUserDAO() {
	return userDAO;
}


public void setUserDAO(UserDAO userDAO) {
	this.userDAO = userDAO;
}


private Map<String, Object> session;

public Map<String, Object> getSession() {
	return session;
}


public void setSession(Map<String, Object> session) {
	this.session = session;
}

public void setRelationship(String relationship) {
	this.relationship = relationship;
}

public String getPlace() {
	return place;
}

public void setPlace(String place) {
	this.place = place;
}

public String getRelationship() {
	return relationship;
}

public String getYear() {
	return year;
}

public void setYear(String year) {
	this.year = year;
}

public String getDate_month() {
	return date_month;
}

public void setDate_month(String date_month) {
	this.date_month = date_month;
}
public static String theMonth(int month){
    String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    return monthNames[month];
}

public String execute()
{
	 User user = (User) session.get("user");
	 int ref = user.getUserId();
	 String tmp_ref = (String) session.get("profileReference");
	 int fref = -1;
	 if(tmp_ref!=null)
	 fref = Integer.parseInt(tmp_ref);
	 System.out.println("Friend reference:"+fref);
	 userDAO = new UserDAOImpl();
	 User userdetails;
	 if(tmp_ref==null)
	 {
	 userdetails=userDAO.getUserByUserId(ref);
	 }
	 else
	 {
	 userdetails=userDAO.getUserByUserId(fref);
	 }
	 relationship = userdetails.getRelationship();
	 if(relationship==null)
		 relationship="Add Your Relationship";
	 nativePlace = userdetails.getNativeplace();
	 place = userdetails.getPlace();
	 if(nativePlace==null)
		 nativePlace=place;
	 Date dob = userdetails.getDob();
	 Calendar cal = Calendar.getInstance();
	 cal.setTime(dob);
	 year = String.valueOf(cal.get(Calendar.YEAR));
	 String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
	 int month = cal.get(Calendar.MONTH);
	 String monthname = AboutAction.theMonth(month);
	 date_month = day+" "+monthname;
	 gender = userdetails.getGender();
	 organization_details=detailsDAO.getOrganizationDetailsForUser(userdetails.getUserId()); 
	 institute_details=detailsDAO.getInstituteDetailsForUser(userdetails.getUserId());
			 
	 return SUCCESS;
}


public List<OrganizationDetails> getOrganization_details() {
	return organization_details;
}


public void setOrganization_details(
		List<OrganizationDetails> organization_details) {
	this.organization_details = organization_details;
}


public List<InstituteDetails> getInstitute_details() {
	return institute_details;
}


public void setInstitute_details(List<InstituteDetails> institute_details) {
	this.institute_details = institute_details;
}


public DetailsDAO getDetailsDAO() {
	return detailsDAO;
}


public void setDetailsDAO(DetailsDAO detailsDAO) {
	this.detailsDAO = detailsDAO;
}
}
