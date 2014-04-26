package edu.iiitb.facebook.action.about;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;

import java.util.Date;

import edu.iiitb.facebook.action.dao.DetailsDAO;
import edu.iiitb.facebook.action.dao.impl.DetailsDAOImpl;
import edu.iiitb.facebook.action.model.User;


public class SubmitAboutAction extends ActionSupport implements SessionAware, RequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map<String, Object> request;
	private String relationship;
	private String hometown;
	private String currentcity;
	private String birthday;
	private String gender;
	private String wrkplace;
	private String role;
	private String collegename;
	private String degree;
	private String schoolname;
	private String standard;
	private String joindate;
	private String leftdate;
	private String collegejoindate;
	private String collegeleftdate;
	private String schooljoindate;
	private String schoolleftdate;
		
	public String getWrkplace() {
		return wrkplace;
	}
	public void setWrkplace(String wrkplace) {
		this.wrkplace = wrkplace;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getCollegename() {
		return collegename;
	}
	public void setCollegename(String collegename) {
		this.collegename = collegename;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getSchoolname() {
		return schoolname;
	}
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getJoindate() {
		return joindate;
	}
	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}
	public String getLeftdate() {
		return leftdate;
	}
	public void setLeftdate(String leftdate) {
		this.leftdate = leftdate;
	}
	public String getCollegejoindate() {
		return collegejoindate;
	}
	public void setCollegejoindate(String collegejoindate) {
		this.collegejoindate = collegejoindate;
	}
	public String getCollegeleftdate() {
		return collegeleftdate;
	}
	public void setCollegeleftdate(String collegeleftdate) {
		this.collegeleftdate = collegeleftdate;
	}
	public String getSchooljoindate() {
		return schooljoindate;
	}
	public void setSchooljoindate(String schooljoindate) {
		this.schooljoindate = schooljoindate;
	}
	public String getSchoolleftdate() {
		return schoolleftdate;
	}
	public void setSchoolleftdate(String schoolleftdate) {
		this.schoolleftdate = schoolleftdate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public String getCurrentcity() {
		return currentcity;
	}

	public void setCurrentcity(String currentcity) {
		this.currentcity = currentcity;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public Map<String, Object> getRequest() {
		return request;
	}
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	public String execute() throws ParseException
	{
		
		User user = (User) session.get("user");
		DetailsDAO adduserdetails = new DetailsDAOImpl();
		if(wrkplace!=null)
		{
			java.sql.Date wjoindate=null;
			java.sql.Date wlastdate=null;
			Date convertedCurrentDate1 = null;
			Date convertedCurrentDate2 = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(joindate!=null && !joindate.equals(""))
		    convertedCurrentDate1 = sdf.parse(joindate);
		    if(leftdate!=null && !leftdate.equals(""))
		    convertedCurrentDate2 = sdf.parse(leftdate);
		    if(convertedCurrentDate1!=null && !convertedCurrentDate1.equals(""))
		    wjoindate=new java.sql.Date(convertedCurrentDate1.getTime());
		    if(convertedCurrentDate2!=null && !convertedCurrentDate2.equals(""))
		    wlastdate=new java.sql.Date(convertedCurrentDate2.getTime());
		    System.out.println(wjoindate+"/////"+wlastdate);
			adduserdetails.setOrganizationDetailsForUser(user.getUserId(),wjoindate,wlastdate,wrkplace, role);
		}
		else if(collegename!=null)
		{
			java.sql.Date cjoindate=null;
			java.sql.Date clastdate=null;
			Date convertedCurrentDate1 = null;
			Date convertedCurrentDate2 = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(collegejoindate!=null && !collegejoindate.equals(""))
		    convertedCurrentDate1 = sdf.parse(collegejoindate);
		    if(collegeleftdate!=null && !collegeleftdate.equals(""))
		    convertedCurrentDate2 = sdf.parse(collegeleftdate);
		    if(convertedCurrentDate1!=null && !convertedCurrentDate1.equals(""))
		    cjoindate=new java.sql.Date(convertedCurrentDate1.getTime());
		    if(convertedCurrentDate2!=null && !convertedCurrentDate2.equals(""))
		    clastdate=new java.sql.Date(convertedCurrentDate2.getTime());
		     int status;
		    if(collegeleftdate==null || collegeleftdate.equals(""))
		    	status=0;
		    else
		    	status=1;
		    adduserdetails.setInstituteDetailsForUser(user.getUserId(),cjoindate,clastdate, status,degree, collegename);
		}
		else if(schoolname!=null)
		{
			java.sql.Date sjoindate=null;
			java.sql.Date slastdate=null;
			Date convertedCurrentDate1 = null;
			Date convertedCurrentDate2 = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(schooljoindate!=null && !schooljoindate.equals(""))
		    convertedCurrentDate1 = sdf.parse(schooljoindate);
		    if(schoolleftdate!=null && !schoolleftdate.equals(""))
		    convertedCurrentDate2 = sdf.parse(schoolleftdate);
		    if(convertedCurrentDate1!=null && !convertedCurrentDate1.equals(""))
		    sjoindate=new java.sql.Date(convertedCurrentDate1.getTime());
		    if(convertedCurrentDate2!=null && !convertedCurrentDate2.equals(""))
		    slastdate=new java.sql.Date(convertedCurrentDate2.getTime());
		    int status;
		    if(schoolleftdate==null || schoolleftdate.equals(""))
		    	status=0;
		    else
		    	status=1;
			adduserdetails.setInstituteDetailsForUser(user.getUserId(),sjoindate,slastdate, status,standard, schoolname);
		}
		else if(relationship!=null && !relationship.equals(""))
		{
			
			adduserdetails.setRelationshipForUser(user.getUserId(), relationship);
		}
		else if((hometown!=null && !hometown.equals("")) || currentcity!=null && !currentcity.equals(""))
		{
			if(hometown.equals(""))
				hometown=currentcity;
			if(currentcity.equals(""))
				currentcity=hometown;
			adduserdetails.setLocationDetailsForUser(user.getUserId(), hometown, currentcity);
		}
		else
		{
			java.sql.Date bday = null;
			Date convertedCurrentDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 convertedCurrentDate = sdf.parse(birthday);
			 bday = new java.sql.Date(convertedCurrentDate.getTime());
			adduserdetails.setDOBGenderForUser(user.getUserId(), bday, gender);
		}
		
		return SUCCESS;
	}	
	public String convertToSqlDate(String date){
		String converted_date="";
		converted_date+=date.substring(date.lastIndexOf('-')+1,date.length());
		converted_date+="-"+date.substring(0,date.indexOf('-'));
		converted_date+="-"+date.substring(date.indexOf('-')+1,date.lastIndexOf('-'));

		return converted_date;

	} 
	
	
}
