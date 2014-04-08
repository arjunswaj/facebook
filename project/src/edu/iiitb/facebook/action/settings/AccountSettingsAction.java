package edu.iiitb.facebook.action.settings;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.model.User;

public class AccountSettingsAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -4398801602286563549L;
	private Map<String, Object> session;
	private int userId;
	
	public String execute() {
		User user = (User) session.get("user");
		if (user == null) return LOGIN;
	    userId = user.getUserId();

		return SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}

}
