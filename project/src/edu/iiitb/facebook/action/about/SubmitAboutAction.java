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
	private String workstartdate;
	private String workenddate;
	private String collegestartdate;
	private String collegeenddate;
	private String schoolstartdate;
	private String schoolenddate;
	
	
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
	public String getWorkstartdate() {
		return workstartdate;
	}
	public void setWorkstartdate(String workstartdate) {
		this.workstartdate = workstartdate;
	}
	public String getWorkenddate() {
		return workenddate;
	}
	public void setWorkenddate(String workenddate) {
		this.workenddate = workenddate;
	}
	public String getCollegestartdate() {
		return collegestartdate;
	}
	public void setCollegestartdate(String collegestartdate) {
		this.collegestartdate = collegestartdate;
	}
	public String getCollegeenddate() {
		return collegeenddate;
	}
	public void setCollegeenddate(String collegeenddate) {
		this.collegeenddate = collegeenddate;
	}
	public String getSchoolstartdate() {
		return schoolstartdate;
	}
	public void setSchoolstartdate(String schoolstartdate) {
		this.schoolstartdate = schoolstartdate;
	}
	public String getSchoolenddate() {
		return schoolenddate;
	}
	public void setSchoolenddate(String schoolenddate) {
		this.schoolenddate = schoolenddate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String execute() throws ParseException
	{
		User user = (User) session.get("user");
		DetailsDAO adduserdetails = new DetailsDAOImpl();
		System.out.println("****");
		System.out.println(relationship);
		System.out.println(hometown);
		System.out.println(currentcity);
		System.out.println(birthday);
		System.out.println(schoolstartdate);
		System.out.println(schoolenddate);
		System.out.println(collegestartdate);
		System.out.println(collegeenddate);
		System.out.println(workstartdate);
		System.out.println(workenddate);
		System.out.println(wrkplace);
		System.out.println( role);
		System.out.println(collegename);
		System.out.println(degree);
		System.out.println(schoolname);
		System.out.println(standard);
		System.out.println("***");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
		String date1=null;
		Date birthdate =null;
		java.sql.Date sd=null;
		if(birthday!=null)
		{
		date1 = convertToSqlDate(birthday);
		birthdate = sdf.parse(date1);
		sd = new java.sql.Date(birthdate.getTime());
		}
		System.out.println("1");
		System.out.println(date1);
		System.out.println(birthdate);
		adduserdetails.setUserDetails(relationship, hometown,currentcity,sd, gender,user.getUserId());
		System.out.println("2");
		String date2=null;
		String date3=null;
		if(!workstartdate.equals(""))
		date2 = convertToSqlDate(workstartdate);
		if(!workenddate.equals(""))
		date3 = convertToSqlDate(workenddate);
		System.out.println(date2);
		System.out.println(date3);
		Date wrkdate=null;
		if(date2!=null)
		wrkdate = sdf.parse(date2);
		Date wrkenddate=null;
		if(date3!=null)		
		wrkenddate = sdf.parse(date3);
		java.sql.Date sd1=null;
		java.sql.Date sd2=null;
		if(wrkdate!=null)		
		sd1 = new java.sql.Date(wrkdate.getTime()); 
		if(wrkenddate!=null)
		sd2 = new java.sql.Date(wrkenddate.getTime());
		if(!wrkplace.equals(""))
		adduserdetails.setOrganizationDetailsForUser(user.getUserId(),sd1, sd2,wrkplace, role);
		System.out.println("3");
		int status;
		if(schoolenddate==null)
			status=0;
		else
			status=1;
		String date4=null;
		String date5=null;
		if(!schoolstartdate.equals(""))
		date4 = convertToSqlDate(schoolstartdate);
		if(!schoolenddate.equals(""))
		date5 = convertToSqlDate(schoolenddate);
		System.out.println(date4);
		System.out.println(date5);
		Date schooldate=null;
		Date schoolenddate=null;
		if(date4!=null)
		schooldate = sdf.parse(date4);
		if(date5!=null)
		schoolenddate = sdf.parse(date5);
		java.sql.Date sd3=null;
		if(schooldate!=null)
		sd3= new java.sql.Date(schooldate.getTime()); 
		java.sql.Date sd4=null;
		if(schoolenddate!=null)
		sd4= new java.sql.Date(schoolenddate.getTime()); 
		if(!schoolname.equals(""))
		{
		adduserdetails.setInstituteDetailsForUser(user.getUserId(), sd3, sd4,status, standard,schoolname);
		System.out.println("school");
		}
		System.out.println("");
		if(collegeenddate==null)
			status=0;
		else
			status=1;
		
		String date6=null;
		if(!collegestartdate.equals(""))
		date6= convertToSqlDate(collegestartdate);
		String date7 =null;
		if(!collegeenddate.equals(""))
		date7= convertToSqlDate(collegeenddate);
		System.out.println(date6);
		System.out.println(date7);
		Date collegedate=null;
		if(date6!=null)
		collegedate= sdf.parse(date6);
		Date collegeend = null;
		if(date7!=null)
		collegeend=sdf.parse(date7);
		java.sql.Date sd5=null;
		java.sql.Date sd6=null;
		if(collegedate!=null)
		sd5 = new java.sql.Date(collegedate.getTime()); 
		if(collegeend!=null)
		sd6 = new java.sql.Date(collegeend.getTime()); 
		if(!collegename.equals(""))
		{
		adduserdetails.setInstituteDetailsForUser(user.getUserId(), sd5, sd6,status, degree,collegename);
		System.out.println("college");
		}
		return SUCCESS;
	}	
	public String convertToSqlDate(String date){
		String converted_date="";
		System.out.println("Inn.."+date);
		converted_date+=date.substring(date.lastIndexOf('-')+1,date.length());
		converted_date+="-"+date.substring(0,date.indexOf('-'));
		converted_date+="-"+date.substring(date.indexOf('-')+1,date.lastIndexOf('-'));

		return converted_date;

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
}
