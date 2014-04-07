package edu.iiitb.facebook.action.search;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

public class SearchAction extends ActionSupport
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

		if (fref != null && !StringUtils.isEmpty(fref))
		{

			setFref(fref);
			return SUCCESS;
		}
		else
		{
			return ERROR;
		}

	}

}
