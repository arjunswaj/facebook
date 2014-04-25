package edu.iiitb.facebook.action.friends;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.EventDAO;
import edu.iiitb.facebook.action.dao.FriendsDAO;
import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.dao.impl.EventDAOImpl;
import edu.iiitb.facebook.action.dao.impl.FriendsDAOImpl;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.FriendInfo;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.ConnectionPool;

/**
 * @author prashanth
 * 
 */
// @Namespace("/")
// @ResultPath(value = "/")
// @ParentPackage("tiles-default")
public class FriendProfileAction extends ActionSupport implements SessionAware
{
	String lref;

	public String getLref()
	{
		return lref;
	}

	public void setLref(String lref)
	{
		this.lref = lref;
	}

	String fref;

	public String getFref()
	{
		return fref;
	}

	public void setFref(String fref)
	{
		this.fref = fref;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @Action(value = "/profile", results = { @Result(name = SUCCESS, location
	// = "friends.tiles", type = "tiles") })
	public String execute()
	{

		FriendsDAO friendsDAO = null;
		FriendInfo friendInfo = null;

		User user = (User) session.get("user");

		if (user != null)
		{
			setLref(user.getUserId() + "");

			if ((fref != null && fref.equals(lref)) || fref == null || "".equals(fref))
			{
				if (fref == null || "".equals(fref))
				{
					setFref(user.getUserId() + "");
				}
				setRequestStatus(FriendInfo.RequestStatus.MYPROFILE.getReqstat());
				session.put("requestStatus", requestStatus);
				session.put("profileReference", fref);
				return SUCCESS;

			}
			else if (fref != null)
			{
				friendsDAO = new FriendsDAOImpl();
				friendInfo = friendsDAO.getFriendRequestStatus(Integer.parseInt(lref), Integer.parseInt(fref));

				UserDAO userDAO = new UserDAOImpl();
				User friendProfile = userDAO.getUserByUserId(Integer.parseInt(fref));

				// relationship exists in friends_with table
				if (friendProfile != null && friendInfo != null)
				{
					if (!FriendInfo.RequestStatus.BLOCKED.equals(friendInfo.getRequestStatus()))
					{

						if (Integer.parseInt(lref) == friendInfo.getRequestedBy())
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
						// friend is blocked
						return ERROR;

					}
				} // relationship doesn't exists in friends_with table

				else if (friendProfile != null && friendInfo == null)
				{
					setRequestStatus(FriendInfo.RequestStatus.ADD_FRIEND.getReqstat());

				} // friend doesn't exists in database.
				else
				{
					return ERROR;

				}
			}

			session.put("requestStatus", requestStatus);
			session.put("profileReference", fref);

		}
		else
		{
			return LOGIN;
		}
		return SUCCESS;
	}

	// @Action(value = "/addfriend", results = { @Result(name = SUCCESS,
	// location = "profile?fref=%{fref}", type = "redirectAction") })
	public String addfriendReqeust()
	{

		User user = (User) session.get("user");

		if (user != null)
		{
			setLref(user.getUserId() + "");

			if (fref != null)
			{
				FriendsDAO friendsDAO = new FriendsDAOImpl();

				try
				{
					if (!friendsDAO.addFriend(Integer.parseInt(lref), Integer.parseInt(fref)))
					{

						return ERROR;
					}
				}
				catch (NumberFormatException ex)
				{
					return ERROR;
				}

				setRequestStatus(FriendInfo.RequestStatus.PENDING.getReqstat());
			}
			session.put("requestStatus", requestStatus);
		}
		else
		{
			return LOGIN;
		}
		return SUCCESS;
	}

	// @Action(value = "/confirmRequest", results = { @Result(name = SUCCESS,
	// location = "profile?fref=%{fref}", type = "redirectAction") })
	public String confirmAddFriend()
	{

		User user = (User) session.get("user");

		if (user != null)
		{
			setLref(user.getUserId() + "");
			if (fref != null)
			{
				FriendsDAO friendsDAO = new FriendsDAOImpl();

				if (!friendsDAO.confirmFriend(Integer.parseInt(lref), Integer.parseInt(fref)))
				{

					return ERROR;
				}

				setRequestStatus(FriendInfo.RequestStatus.ACCEPTED.getReqstat());
			}

			else
			{
				return ERROR;
			}

			session.put("requestStatus", requestStatus);
		}
		else
		{
			return LOGIN;
		}
		return SUCCESS;
	}

	// @Action(value = "/rejectRequest", results = { @Result(name = SUCCESS,
	// location = "profile?fref=%{fref}", type = "redirectAction") })
	public String rejectFriend()
	{

		User user = (User) session.get("user");

		if (user != null)
		{
			setLref(user.getUserId() + "");
			if (fref != null)
			{
				FriendsDAO friendsDAO = new FriendsDAOImpl();

				if (!friendsDAO.rejectFriend(Integer.parseInt(lref), Integer.parseInt(fref)))
				{

					return ERROR;
				}

				setRequestStatus(FriendInfo.RequestStatus.ADD_FRIEND.getReqstat());
			}

			else
			{
				return ERROR;
			}
			session.put("requestStatus", requestStatus);
		}
		else
		{
			return LOGIN;
		}
		return SUCCESS;
	}

	public String blockfriend() throws SQLException
	{
		User user = (User) session.get("user");
		if (user != null)
		{
			setLref(user.getUserId() + "");
			if (fref != null)
			{
				FriendsDAO friendsDAO = new FriendsDAOImpl();
				if (!friendsDAO.blockFriend(Integer.parseInt(lref), Integer.parseInt(fref)))
				{
					return ERROR;
				}
				else
				{
					setRequestStatus(FriendInfo.RequestStatus.BLOCKED.getReqstat());

					Connection cn = ConnectionPool.getConnection();
					EventDAO eventDAO = new EventDAOImpl();
					eventDAO.deleteAllInvitationsBetweenUsers(cn, Integer.parseInt(lref), Integer.parseInt(fref));
					ConnectionPool.freeConnection(cn);
				}
				session.put("requestStatus", requestStatus);
			}
			else
			{
				return ERROR;
			}

		}
		else
		{
			return LOGIN;
		}
		return SUCCESS;
	}

	public String unblockfriend()
	{
		User user = (User) session.get("user");
		if (user == null)
			return LOGIN;
		setLref(user.getUserId() + "");
		if (fref == null)
			return ERROR;
		FriendsDAO friendsDAO = new FriendsDAOImpl();
		if (friendsDAO.unblockFriend(Integer.parseInt(lref), Integer.parseInt(fref)) == false)
			return ERROR;
		return SUCCESS;
	}

	Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> arg0)
	{
		this.session = arg0;
	}
}
