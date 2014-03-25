package edu.iiitb.facebook.action.dao.impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.util.ConnectionPool;

public class UserDAOImpl implements UserDAO {

  @Override
  public User getUserImageByUserId(String userId) {    
    User user = new User();
    Connection connection = ConnectionPool.getConnection();
    try {      
      PreparedStatement stmt = connection
          .prepareStatement("SELECT * from user WHERE id = ?");
      stmt.setString(1, userId);

      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        InputStream currentProfilePic = rs.getBinaryStream("current_profile_pic");
        InputStream currentCoverPic = rs.getBinaryStream("current_cover_pic");
        user.setCurrentProfilePic(currentProfilePic);
        user.setCurrentCoverPic(currentCoverPic);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      ConnectionPool.freeConnection(connection);
    }
    return user;
  }


}
