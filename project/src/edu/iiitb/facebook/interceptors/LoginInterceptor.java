package edu.iiitb.facebook.interceptors;

import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;

import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.User;

public class LoginInterceptor implements Interceptor, SessionAware
{

	private String cookied = "false";

	public void destroy()
	{
		System.out.println("destroy");
	}

	public void init()
	{
		System.out.println("init");
	}

	private Map<String, Object> session;

	@Override
	public String intercept(ActionInvocation ai) throws Exception
	{

		if (getCookie("userID") != null)
		{
			UserDAO dao = new UserDAOImpl();

			String cookieValue = getCookie("userID").getValue();

			this.session = ai.getInvocationContext().getSession();

			User user = dao.getUserByUserEmail(cookieValue);

			if (user != null)
			{
				this.session.put("user", user);
				return ai.invoke();

			}

		}
		return ActionSupport.LOGIN;

	}

	public Cookie getCookie(String name)
	{

		Cookie cookies[] = ServletActionContext.getRequest().getCookies();
		Cookie requestedCookie = null;
		if (cookies != null)
		{
			for (Cookie current : cookies)
			{
				if (current.getName().equals(name))
				{
					requestedCookie = current;
					break;
				}
			}
		}
		return requestedCookie;

	}

	public String getCookied()
	{
		return cookied;
	}

	public void setCookied(String cookied)
	{
		this.cookied = cookied;
	}

	@Override
	public void setSession(Map<String, Object> arg0)
	{
		this.session = arg0;

	}

}
