package edu.iiitb.facebook.action.dao.impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.ConnectionPool;

public class UserDAOImpl implements UserDAO
{

	final String GET_USER_BY_ID_QRY = "select * from user where id=?";

	@Override
	public User getUserImageByUserId(int userId)
	{
		User user = new User();
		Connection connection = ConnectionPool.getConnection();
		try
		{
			PreparedStatement stmt = connection.prepareStatement(GET_USER_BY_ID_QRY);
			stmt.setInt(1, userId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next())
			{
				InputStream currentProfilePic = rs.getBinaryStream(UserDAO.CURRENT_PROFILE_PIC);
				InputStream currentCoverPic = rs.getBinaryStream(UserDAO.CURRENT_COVER_PIC);
				user.setFirstName(rs.getString(UserDAO.FIRST_NAME));
				user.setLastName(rs.getString(UserDAO.LAST_NAME));
				user.setCurrentProfilePic(currentProfilePic);
				user.setCurrentCoverPic(currentCoverPic);
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
		return user;
	}

	@Override
	public User getUserByUserId(int userId)
	{

		User user = null;
		Connection conn = ConnectionPool.getConnection();

		PreparedStatement preparedStmt;
		try
		{
			preparedStmt = conn.prepareStatement(GET_USER_BY_ID_QRY);
			preparedStmt.setInt(1, userId);

			ResultSet resultSet = preparedStmt.executeQuery();

			if (resultSet.next())
			{

				user = new User();
				user.setUserId(userId);
				user.setEmail(resultSet.getString(UserDAO.EMAIL));

				user.setPassword(resultSet.getString(UserDAO.PASSWORD));
				user.setDob(resultSet.getDate(UserDAO.DOB));

				user.setPhoneNumber(resultSet.getString(UserDAO.PHONE_NUMBER));

				InputStream currentProfilePic = resultSet.getBinaryStream(UserDAO.CURRENT_PROFILE_PIC);
				InputStream currentCoverPic = resultSet.getBinaryStream(UserDAO.CURRENT_COVER_PIC);
				user.setFirstName(resultSet.getString(UserDAO.FIRST_NAME));
				user.setLastName(resultSet.getString(UserDAO.LAST_NAME));
				user.setCurrentProfilePic(currentProfilePic);
				user.setCurrentCoverPic(currentCoverPic);

				user.setPlace(resultSet.getString(UserDAO.PLACE));

				user.setSecretQuestion(resultSet.getString(UserDAO.SECRET_QUESTION));

				user.setSecretAnswer(resultSet.getString(UserDAO.SECRET_ANSWER));
				user.setCreated(resultSet.getTimestamp(UserDAO.CREATED));

			}
		}
		catch (SQLException e)
		{

			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.freeConnection(conn);
			;
		}

		return user;
	}
}
