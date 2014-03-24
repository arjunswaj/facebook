package edu.iiitb.facebook.action.dao;

import java.sql.Connection;

import edu.iiitb.facebook.action.model.User;

public interface UserDAO {
  User getUserImageByUserId(Connection conn, String userId);
}
