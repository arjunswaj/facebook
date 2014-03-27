package edu.iiitb.facebook.action.friends;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.FriendsDAO;
import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.dao.impl.FriendsDAOImpl;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.FriendInfo;
import edu.iiitb.facebook.action.model.User;

@Namespace("/friends")
@ResultPath(value = "/")
public class FriendProfileAction extends ActionSupport
{

	String loggedInUserId;

	String friendUserId;

	public String getFriendUserId()
	{
		return friendUserId;
	}

	public void setFriendUserId(String friendUserId)
	{
		this.friendUserId = friendUserId;
	}

	String requestStatus;

	public String getRequestStatus()
	{
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus)
	{
		this.requestStatus = requestStatus;
	}

	public String getLoggedInUserId()
	{
		return loggedInUserId;
	}

	public void setLoggedInUserId(String loggedInUserId)
	{
		this.loggedInUserId = loggedInUserId;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Action(value = "/friendProfileAction", results = { @Result(name = SUCCESS, location = "/friends/friendProfile.jsp") })
	public String execute()
	{

		this.setLoggedInUserId("1");
		this.setFriendUserId("3");

		FriendsDAO friendsDAO = new FriendsDAOImpl();
		FriendInfo friendInfo = friendsDAO.getFriendRequestStatus(Integer.parseInt(loggedInUserId), Integer.parseInt(friendUserId));

		UserDAO userDAO = new UserDAOImpl();
		User friendProfile = userDAO.getUserByUserId(Integer.parseInt(friendUserId));

		if (friendProfile != null && FriendInfo.RequestStatus.UNBLOCKED.equals(friendInfo.getBlockedStatus()))
		{

			if (Integer.parseInt(loggedInUserId) == friendInfo.getRequestedBy())
			{
				if (FriendInfo.RequestStatus.PENDING.equals(friendInfo.getRequestStatus()))
				{
					setRequestStatus(FriendInfo.RequestStatus.PENDING.getReqstat());
				}
				else if (FriendInfo.RequestStatus.ACCEPTED.equals(friendInfo.getRequestStatus()))
				{
					setRequestStatus(FriendInfo.RequestStatus.ACCEPTED.getReqstat());

				}

			}
			else
			{

				if (FriendInfo.RequestStatus.PENDING.equals(friendInfo.getRequestStatus()))
				{
					setRequestStatus(FriendInfo.RequestStatus.CONFIRM_REQUEST.getReqstat());
				}
				else if (FriendInfo.RequestStatus.ACCEPTED.equals(friendInfo.getRequestStatus()))
				{
					setRequestStatus(FriendInfo.RequestStatus.ACCEPTED.getReqstat());

				}

			}
		}
		else
		{
			return ERROR;

		}

		return SUCCESS;
	}
}
