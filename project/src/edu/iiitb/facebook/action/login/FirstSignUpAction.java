package edu.iiitb.facebook.action.login;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.User;

public class FirstSignUpAction extends ActionSupport implements SessionAware {

	private String email;
	private String reemail;
	private String password;
	private Date dob = null;
	private String first_name;
	private String last_name;
	private String place;
	String ret;
	private Map<String, Object> session;

	public Map<String, Object> getSession() {
		return session;
	}

	UserDAO dao = new UserDAOImpl();
	User user = new User();

	@Override
	public String execute() {
		System.out.println("place1");
		user.setEmail(getEmail());
		user.setPassword(getPassword());
		user.setDob(getDob());
		user.setPhoneNumber(null);
		user.setFirstName(getFirst_name());
		user.setLastName(getLast_name());
		user.setPlace(getPlace());
		user.setSecretQuestion("fornowitsnull");
		user.setSecretAnswer("fornowitsnull");
		System.out.println(user.getPassword());
		session.put("user", user);
		ret = SUCCESS;// dao.setUserwithoutphotos(user);

		return ret;
	}

	public String getReemail() {
		return reemail;
	}

	public void setReemail(String reemail) {
		this.reemail = reemail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void validate() {

		if (StringUtils.isEmpty(email)) {
			addFieldError(email, "email cannot be empty");
		}

		if (StringUtils.isEmpty(reemail)) {
			addFieldError(reemail, "email cannot be empty");
		}

		if (StringUtils.isEmpty(password)) {
			addFieldError(password, "password cannot be empty");
		}

		if (StringUtils.isEmpty(first_name)) {
			addFieldError(first_name, "first name cannot be empty");
		}

		if (StringUtils.isEmpty(last_name)) {
			addFieldError(last_name, "last name cannot be empty");
		}
	}
}
