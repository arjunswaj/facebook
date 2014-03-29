package edu.iiitb.facebook.action.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.iiitb.facebook.action.dao.FriendsDAO;
import edu.iiitb.facebook.action.model.FriendInfo;
import edu.iiitb.facebook.action.model.FriendInfo.RequestStatus;
import edu.iiitb.facebook.action.model.FriendSuggestions;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.CommonUtil;
import edu.iiitb.facebook.util.ConnectionPool;

public class FriendsDAOImpl implements FriendsDAO
{

	String ARE_THEY_FRIENDS_QRY = "select * from friends_with f1 where f1.request_by=? and f1.request_for=?" + " union "
			+ " select * from friends_with f1 where f1.request_by=? and f1.request_for=?";

	// Added by Rahul
	private static final String SUGGEST_FRIENDS_QUERY = "" +
			"SELECT id, first_name, last_name " +
			"FROM facebookdb.user " +
			"WHERE id in ( " +
			"   SELECT request_for " +
			"    FROM facebookdb.friends_with " +
			"    WHERE request_by in ( " +
			"        SELECT request_for " +
			"        FROM facebookdb.friends_with " +
			"        WHERE request_by = ? " +
			"        AND request_status = 'accepted' " +
			"        UNION " +
			"        SELECT request_by " +
			"        FROM facebookdb.friends_with " +
			"        WHERE request_for = ? " +
			"        AND request_status = 'accepted' " +
			"    ) " +
			"    AND request_status = 'accepted' " +
			"    AND request_for != ? " +
			"    UNION " +
			"    SELECT request_by " +
			"    FROM facebookdb.friends_with " +
			"    WHERE request_for in ( " +
			"        SELECT request_for " +
			"        FROM facebookdb.friends_with " +
			"        WHERE request_by = ? " +
			"        AND request_status = 'accepted' " +
			"        UNION " +
			"        SELECT request_by " +
			"        FROM facebookdb.friends_with " +
			"        WHERE request_for = ? " +
			"        AND request_status = 'accepted' " +
			"    ) " +
			"    AND request_status = 'accepted' " +
			"    AND request_by != ? " +
			")";

	String ADD_FRIEND_QRY = "insert into friends_with(request_status,blocked_status,request_by,request_for,friend_request_sent) values(?,?,?,?,?)";

	String CONFIRM_FRIEND_QRY = "update friends_with set request_status=?, friend_request_accepted =? where request_by=? and request_for=? ";

	String REJECT_FRIEND_QRY = "delete from  friends_with  where request_by=? and request_for=? ";

	@Override
	public FriendInfo getFriendRequestStatus(int loggedInUserId, int otherUserId)
	{
		FriendInfo friendInfo = null;

		Connection conn = ConnectionPool.getConnection();

		try
		{
			PreparedStatement stmt = conn.prepareStatement(ARE_THEY_FRIENDS_QRY);

			stmt.setInt(1, loggedInUserId);
			stmt.setInt(2, otherUserId);
			stmt.setInt(3, otherUserId);
			stmt.setInt(4, loggedInUserId);
			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next())
			{
				friendInfo = new FriendInfo();

				friendInfo.setRequestedBy(resultSet.getInt(FriendsDAO.REQUEST_BY));
				friendInfo.setRequestFor(resultSet.getInt(FriendsDAO.REQUEST_FOR));
				RequestStatus reqstatus = FriendInfo.RequestStatus.fromString(resultSet.getString(FriendsDAO.REQUEST_STATUS));
				if (reqstatus != null)
				{
					friendInfo.setRequestStatus(reqstatus);
				}
				RequestStatus blockStatus = FriendInfo.RequestStatus.fromString(resultSet.getString(FriendsDAO.BLOCKED_STATUS));
				if (blockStatus != null)
				{

					friendInfo.setBlockedStatus(blockStatus);
				}
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.freeConnection(conn);
		}

		return friendInfo;

	}

	@Override
	public List<User> getFriendsList(int userId)
	{
		return null;
	}

	@Override
	public boolean addFriend(int loggedInUserId, int otherUserId)
	{

		Connection conn = ConnectionPool.getConnection();

		try
		{
			PreparedStatement preparedstmt = conn.prepareStatement(ADD_FRIEND_QRY);

			preparedstmt.setString(1, FriendInfo.RequestStatus.PENDING.getReqstat());
			preparedstmt.setString(2, FriendInfo.RequestStatus.UNBLOCKED.getReqstat());
			preparedstmt.setInt(3, loggedInUserId);
			preparedstmt.setInt(4, otherUserId);

			preparedstmt.setString(5, CommonUtil.getCurrentTimeStamp());
			if (preparedstmt.executeUpdate() > 0)
			{
				return true;
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.freeConnection(conn);
		}

		return false;
	}

	@Override
	public boolean confirmFriend(int loggedInUserId, int otherUserId)
	{
		Connection conn = ConnectionPool.getConnection();

		try
		{
			PreparedStatement preparedstmt = conn.prepareStatement(CONFIRM_FRIEND_QRY);

			preparedstmt.setString(1, FriendInfo.RequestStatus.ACCEPTED.getReqstat());
			preparedstmt.setString(2, CommonUtil.getCurrentTimeStamp());
			preparedstmt.setInt(3, otherUserId);
			preparedstmt.setInt(4, loggedInUserId);

			if (preparedstmt.executeUpdate() > 0)
			{
				return true;
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.freeConnection(conn);
		}

		return false;
	}

	@Override
	public boolean rejectFriend(int loggedInUserId, int otherUserId)
	{
		Connection conn = ConnectionPool.getConnection();

		try
		{
			PreparedStatement preparedstmt = conn.prepareStatement(REJECT_FRIEND_QRY);

			preparedstmt.setInt(1, otherUserId);
			preparedstmt.setInt(2, loggedInUserId);

			if (preparedstmt.executeUpdate() > 0)
			{
				return true;
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.freeConnection(conn);
		}

		return false;
	}

	@Override
	public boolean blockFriend(int loggedInUserId, int otherUserId)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unblockFriend(int loggedInUserId, int otherUserId)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * added by Rahul
	 */
	@Override
	public List<FriendSuggestions> getFriendSuggestions(int userId)
	{

		List<FriendSuggestions> friendSuggestionsList = new ArrayList<FriendSuggestions>();

		Connection connection = ConnectionPool.getConnection();
		try
		{
			PreparedStatement stmt = connection.prepareStatement(SUGGEST_FRIENDS_QUERY);
			stmt.setInt(1, userId);
			stmt.setInt(2, userId);
			stmt.setInt(3, userId);
			stmt.setInt(4, userId);
			stmt.setInt(5, userId);
			stmt.setInt(6, userId);
			ResultSet rs = stmt.executeQuery();

			FriendSuggestions fs = null;

			while (rs.next())
			{
				int friendId = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				fs = new FriendSuggestions(friendId, firstName, lastName);
				friendSuggestionsList.add(fs);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.freeConnection(connection);
		}

		return friendSuggestionsList;
	}

}
