package edu.iiitb.facebook.action.login;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;

import antlr.StringUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.User;


@ResultPath(value = "/")
@Results({ @Result(name = "success", location = "/login/sign_up.jsp"),
		@Result(name = "input", location = "/login/sign_up.jsp"),
		@Result(name = "error", location = "/login/sign_up.jsp")})
public class SignupAction extends ActionSupport {
	
	private String email;
	private String reemail;
	private String password;
	private Date dob;
	private String phone_number;
	private String first_name;
	private String last_name;
	private InputStream current_profile_pic;
	private InputStream current_cover_pic;
	private String secret_question;
	private String secret_answer;
	private String place;
	
	
	UserDAO dao = new UserDAOImpl();
	User user=new User();

	@Action(value = "/signup")
	public String execute() {
		user.setEmail(getEmail());
		user.setPassword(getPassword());
		user.setDob(getDob());
		user.setPhoneNumber(getPhone_number());
		user.setFirstName(getFirst_name());
		user.setLastName(getLast_name());
		user.setCurrentProfilePic(getCurrent_profile_pic());
		user.setCurrentCoverPic(getCurrent_cover_pic());
		user.setSecretQuestion(getSecret_question());
		user.setSecretAnswer(getSecret_answer());
		user.setPlace(getPlace());
		java.util.Date date= new java.util.Date();
		user.setCreated(new Timestamp(date.getTime()));
		if(email.equalsIgnoreCase(reemail))
			return dao.setUser(user);
		else 
		{
			addFieldError("email doesn't match", reemail);
			return "error";
		}	
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "This is a required field!")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "This is a required field!")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "This is a required field!")
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "This is a required field!")
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "This is a required field!")
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "This is a required field!")
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public InputStream getCurrent_profile_pic() {
		return current_profile_pic;
	}
	public void setCurrent_profile_pic(InputStream current_profile_pic) {
		this.current_profile_pic = current_profile_pic;
	}
	public InputStream getCurrent_cover_pic() {
		return current_cover_pic;
	}
	public void setCurrent_cover_pic(InputStream current_cover_pic) {
		this.current_cover_pic = current_cover_pic;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "This is a required field!")
	public String getSecret_question() {
		return secret_question;
	}
	public void setSecret_question(String secret_question) {
		this.secret_question = secret_question;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "This is a required field!")
	public String getSecret_answer() {
		return secret_answer;
	}
	public void setSecret_answer(String secret_answer) {
		this.secret_answer = secret_answer;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
	

}
