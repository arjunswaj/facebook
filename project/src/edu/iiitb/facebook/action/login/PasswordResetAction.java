package edu.iiitb.facebook.action.login;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;

public class PasswordResetAction extends ActionSupport {

	private String email;
	private String password;
	UserDAO dao = new UserDAOImpl();

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

	public String execute() {

		String ret;
		ret = dao.resetpassword(email, password);
		System.out.println("in the passwordReset and return value is " + ret);

		return ret;

	}
}
