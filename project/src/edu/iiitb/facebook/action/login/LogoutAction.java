package edu.iiitb.facebook.action.login;

import java.sql.SQLException;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.interceptor.SessionAware;

public class LogoutAction extends ActionSupport implements SessionAware
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7751903212229027485L;
	private Map<String, Object> session;

	public Map<String, Object> getSession()
	{
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session)
	{
		this.session = session;

	}

	@Override
	public String execute() throws NumberFormatException, SQLException
	{
		if (session.get("user") != null)
			session.remove("user");
		return SUCCESS;
	}

}
