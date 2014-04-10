package edu.iiitb.facebook.action.login;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.interceptor.SessionAware;

import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.User;

public class SecondSignUpAction extends ActionSupport implements SessionAware {

	private String phone_number;
	private String secret_question;
	private String secret_answer;
	String ret;

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getSecret_question() {
		return secret_question;
	}

	public void setSecret_question(String secret_question) {
		this.secret_question = secret_question;
	}

	public String getSecret_answer() {
		return secret_answer;
	}

	public void setSecret_answer(String secret_answer) {
		this.secret_answer = secret_answer;
	}

	private Map<String, Object> session;

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	UserDAO dao = new UserDAOImpl();
	User user = new User();

	public String execute() {
		user = (User) session.get("user");
		System.out.println(getPhone_number());
		user.setPhoneNumber(getPhone_number());
		user.setSecretQuestion(getSecret_question());
		user.setSecretAnswer(getSecret_answer());
		session.put("user", user);

		ret = dao.setUserwithoutphotos(user);
		System.out.println(ret);
		return ret;
	}

	public void validate() {

		if (StringUtils.isEmpty(phone_number)) {
			addFieldError(phone_number, "phone number cannot be empty");
		}

		if (StringUtils.isEmpty(secret_question)) {
			addFieldError(secret_question, "secret question cannot be empty");
		}

		if (StringUtils.isEmpty(secret_answer)) {
			addFieldError(secret_answer, "secret answer cannot be empty");
		}

	}
}
