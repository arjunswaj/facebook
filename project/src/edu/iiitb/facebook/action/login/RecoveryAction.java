package edu.iiitb.facebook.action.login;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.User;

public class RecoveryAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -3927650660405287420L;

	String secret_question;
	String email;
	String secret_answer;

	UserDAO dao = new UserDAOImpl();
	User user = new User();

	public String getSecret_question() {
		return secret_question;
	}

	public void setSecret_question(String secret_question) {
		this.secret_question = secret_question;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String execute() {
		String ret = ERROR;
		System.out.println("in Revovery Action");
		User loggedInUser = (User) this.session.get("user");
		if (loggedInUser != null) {
			ret = LOGIN;
			System.out.print("if there is user in session");
		} else {
			user = dao.getUserByUserEmail(getEmail());
			System.out.print("while there is no user\n");
			if (user != null) {
				if (getSecret_answer().equalsIgnoreCase(user.getSecretAnswer())) {
					session.put("user", user);
					ret = SUCCESS;

				} else
					ret = ERROR;
			} else {
				ret = ERROR;
			}

		}
		System.out.println(ret);
		return ret;
	}

	public String getsecretquestion() throws Exception {
		System.out.println("in getsecretquestion Action");
		User loggedInUser = (User) this.session.get("user");
		if (loggedInUser == null) {
			user = dao.getUserByUserEmail(getEmail());
			if (user != null) {

				setSecret_question(user.getSecretQuestion());
				System.out.println(getSecret_question());
				return SUCCESS;
			}
			setSecret_question("Email ID doesn't exist");

			return SUCCESS;
		} else {
			setSecret_question("Email ID doesn't exist");
			return LOGIN;
		}
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
