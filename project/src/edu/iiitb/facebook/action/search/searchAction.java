package edu.iiitb.facebook.action.search;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;

public class searchAction extends ActionSupport
{

	private String email;

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getFref()
	{
		return fref;
	}

	public void setFref(String fref)
	{
		this.fref = fref;
	}

	private String fref;

	public String execute()
	{

		if (email != null)
		{

			setFref(new UserDAOImpl().getUserByUserEmail(email).getUserId() + "");
		}

		return SUCCESS;

	}

}
