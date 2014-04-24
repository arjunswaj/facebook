package edu.iiitb.facebook.action.login;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

public class LogoutAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7751903212229027485L;
	private Map<String, Object> session;
	private HttpServletRequest request;
	private HttpServletResponse response;

	@Override
	public void setSession(Map<String, Object> session)
	{
		this.session = session;

	}

	@Override
	public String execute() throws NumberFormatException, SQLException
	{
		if (session.get("user") != null)
		{
			session.remove("user");
			this.session.remove("profileReference");
			session.remove("requestStatus");
		}

		Cookie cookies[] = this.request.getCookies();

		Cookie requiredCookie = null;
		if (cookies != null)
		{
			for (Cookie current : cookies)
			{
				if (current.getName().equals("userID"))
				{
					current.setMaxAge(0);
					requiredCookie = current;
				}
			}
		}

		if (requiredCookie != null)
		{
			this.response.addCookie(requiredCookie);
		}
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0)
	{
		this.request = arg0;

	}

	@Override
	public void setServletResponse(HttpServletResponse arg0)
	{
		this.response = arg0;

	}

}
