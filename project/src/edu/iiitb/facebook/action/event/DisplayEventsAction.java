package edu.iiitb.facebook.action.event;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.model.User;

@Namespace("event")
@ParentPackage("tiles-default")
public class DisplayEventsAction extends ActionSupport implements SessionAware
{
	private Map<String, Object> session;
	private User user;
	
	@Action
	(
		value="/displayEvents",
		results=
		{
			@Result(name="success", type="tiles", location="eventsPage.tiles"),
			@Result(name="login", location="/index.jsp")
		}
	)
	public String execute()
	{
		user = (User) session.get("user");
		if(user==null)
			return LOGIN;
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		session=arg0;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
