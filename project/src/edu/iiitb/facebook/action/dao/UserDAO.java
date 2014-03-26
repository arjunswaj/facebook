package edu.iiitb.facebook.action.dao;

import edu.iiitb.facebook.action.model.User;

public interface UserDAO {
  User getUserImageByUserId(int userId);
}
