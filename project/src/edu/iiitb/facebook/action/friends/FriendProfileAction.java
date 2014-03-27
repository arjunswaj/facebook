package edu.iiitb.facebook.action.friends;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@ResultPath(value = "/")
public class FriendProfileAction extends ActionSupport
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Action(value = "/friendProfileAction", results = { @Result(name=SUCCESS, location="/friendProfile.jsp") })
	public String execute()
	{

		return SUCCESS;
	}

}
