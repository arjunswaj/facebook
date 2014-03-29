package edu.iiitb.facebook.action.login;

import java.util.Map;

import javax.naming.NamingException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.User;

public class LoginAction extends ActionSupport implements SessionAware
{

	private String email;
	private String password;

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
		else
		{
			// System.out.println("place2");

			User newUser = new User(email, password);
			if (isValidUser(newUser))
			{
				// System.out.println("place3");

				newUser = tempuser;
				session.put("user", newUser);

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

}
