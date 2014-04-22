package edu.iiitb.facebook.action.about;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.User;

public class AboutAction extends ActionSupport implements SessionAware {
private String profession;
private String workplace;
private String school;
private String degree1;
private String university;
private String degree2;
private String relationship;
private String nativeplace;
private String currentplace;
private String year;
private String date_month;
private String gender;
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

public String getProfession() {
	return profession;
}


public void setProfession(String profession) {
	this.profession = profession;
}


public String getWorkplace() {
	return workplace;
}


public void setWorkplace(String workplace) {
	this.workplace = workplace;
}


public String getSchool() {
	return school;
}


public void setSchool(String school) {
	this.school = school;
}


public String getDegree1() {
	return degree1;
}


public void setDegree1(String degree1) {
	this.degree1 = degree1;
}


public String getUniversity() {
	return university;
}


public void setUniversity(String university) {
	this.university = university;
}


public String getDegree2() {
	return degree2;
}


public void setDegree2(String degree2) {
	this.degree2 = degree2;
}


public String getRelationship() {
	return relationship;
}


public void setRelationship(String relationship) {
	this.relationship = relationship;
}


public String getNativeplace() {
	return nativeplace;
}


public void setNativeplace(String nativeplace) {
	this.nativeplace = nativeplace;
}


public String getCurrentplace() {
	return currentplace;
}


public void setCurrentplace(String currentplace) {
	this.currentplace = currentplace;
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
	 profession = userdetails.getProfession();
	 workplace = userdetails.getWorkplace();
	 school = userdetails.getSchool();
	 degree1 = userdetails.getDegree1();
	 university = userdetails.getUniversity();
	 degree2 = userdetails.getDegree2();
	 relationship = userdetails.getRelationship();
	 if(relationship==null)
		 relationship="Add Your Relationship";
	 nativeplace = userdetails.getNativeplace();
	 currentplace = userdetails.getCurrentplace();
	 Date dob = userdetails.getDob();
	 Calendar cal = Calendar.getInstance();
	 cal.setTime(dob);
	 year = String.valueOf(cal.get(Calendar.YEAR));
	 String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
	 int month = cal.get(Calendar.MONTH);
	 String monthname = AboutAction.theMonth(month);
	 date_month = day+" "+monthname;
	 gender = userdetails.getGender();
	 return SUCCESS;
}
}
