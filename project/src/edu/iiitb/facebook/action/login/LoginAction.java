package edu.iiitb.facebook.action.login;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.User;

public class LoginAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware
{

	private String email;
	private String password;
	private String persistent;
	private HttpServletResponse servletResponse;

	private HttpServletRequest servletRequest;

	public String getPersistent()
	{
		return persistent;
	}

	public void setPersistent(String persistent)
	{
		this.persistent = persistent;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	private Map<String, Object> session;

	public Map<String, Object> getSession()
	{
		return session;
	}

	UserDAO dao = new UserDAOImpl();
	User tempuser = new User();

	public String execute()
	{

		User user = (User) session.get("user");

		if (user != null)
		{

			return SUCCESS;
		}
		else if (getCookie("userID") != null)
		{
			UserDAO dao = new UserDAOImpl();

			Cookie cookie = getCookie("userID");

			user = dao.getUserByUserEmail(getCookie("userID").getValue());

			if (user != null)
			{
				cookie.setMaxAge(60 * 60 * 4);

				this.servletResponse.addCookie(cookie);
				this.session.put("user", user);
				return SUCCESS;
			}
			else
			{
				cookie.setMaxAge(0);

				this.servletResponse.addCookie(cookie);
				return INPUT;

			}

		}
		else
		{
			// System.out.println("place2");

			User newUser = new User(email, password);
			if (isValidUser(newUser))
			{
				// System.out.println("place3");

				newUser = tempuser;
				session.put("user", newUser);

				if (persistent != null)
				{
					Cookie userCookie = new Cookie("userID", tempuser.getEmail());
					userCookie.setMaxAge(60 * 60 * 4);
					servletResponse.addCookie(userCookie);
				}
				else
				{

					Cookie userCookie = new Cookie("userID", tempuser.getEmail());
					userCookie.setMaxAge(0);
					servletResponse.addCookie(userCookie);

				}
				return SUCCESS;
			}
			else
			{
				return INPUT;
			}

		}
	}

	@Override
	public void setSession(Map<String, Object> session)
	{
		this.session = session;
	}

	private boolean isValidUser(User user)
	{
		// System.out.println(user.getEmail());
		tempuser = dao.getUserByUserEmail(user.getEmail());
		// System.out.println(tempuser.getFirstName());
		if (tempuser != null)
		{
			if (!user.getPassword().equals(tempuser.getPassword()))
			{
				addFieldError("wrong password", password);
				// System.out.println("place4");
				return false;
			}
			else
			{
				// System.out.println("place5");
				return true;
			}
		}
		else
		{
			// System.out.println("place6");
			addFieldError("INVALID_USER", email);
			return false;
		}

	}

	@Override
	public void setServletResponse(HttpServletResponse arg0)
	{
		this.servletResponse = arg0;

	}

	@Override
	public void setServletRequest(HttpServletRequest arg0)
	{
		this.servletRequest = arg0;
	}

	public Cookie getCookie(String name)
	{

		Cookie cookies[] = this.servletRequest.getCookies();
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

}
