package edu.iiitb.facebook.action.event;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/events")
@ResultPath("/")
public class DisplayEventsAction extends ActionSupport
{
	@Action
	(
		value="/displayEvents",
		results=
		{
			@Result(name="success", location="/event/eventsPage.jsp"),
			@Result(name="login", location="/index.jsp")
		}
	)
	public String execute()
	{
		return SUCCESS;
	}
}
