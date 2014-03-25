package edu.iiitb.facebook.action.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.iiitb.facebook.action.dao.PostsDAO;
import edu.iiitb.facebook.action.model.NewsFeed;
import edu.iiitb.facebook.util.ConnectionPool;

public class PostsDAOImpl implements PostsDAO {

  private static final String NEWS_FEEDS_FOR_USER_QUERY = "SELECT  " + 
  		"    from_user.id AS from_user_id, " + 
  		"    from_user.first_name AS from_user_first_name, " + 
  		"    from_user.last_name AS from_user_last_name, " + 
  		"    to_user.id AS to_user_id, " + 
  		"    to_user.first_name AS to_user_first_name, " + 
  		"    to_user.last_name AS to_user_last_name, " + 
  		"    my_post.text AS post_text, " + 
  		"    my_post.type AS post_type, " + 
  		"    my_post.updated AS updated_time " + 
  		"FROM " + 
  		"    post my_post " + 
  		"        LEFT OUTER JOIN " + 
  		"    user from_user ON from_user.id = my_post.posted_by " + 
  		"        LEFT OUTER JOIN " + 
  		"    user to_user ON to_user.id = my_post.posted_for " + 
  		"WHERE " + 
  		"    to_user.id = ?  " + 
  		"UNION ALL SELECT  " + 
  		"    from_user.id AS from_user_id, " + 
  		"    from_user.first_name AS from_user_first_name, " + 
  		"    from_user.last_name AS from_user_last_name, " + 
  		"    to_user.id AS to_user_id, " + 
  		"    to_user.first_name AS to_user_first_name, " + 
  		"    to_user.last_name AS to_user_last_name, " + 
  		"    friends_post.text AS post_text, " + 
  		"    friends_post.type AS post_type, " + 
  		"    friends_post.updated AS updated_time " + 
  		"FROM " + 
  		"    (SELECT  " + 
  		"        friends1.id AS user_id " + 
  		"    FROM " + 
  		"        friends_with rsbm " + 
  		"    LEFT OUTER JOIN user friends1 ON friends1.id = rsbm.request_for " + 
  		"    LEFT OUTER JOIN user me ON me.id = rsbm.request_by " + 
  		"    WHERE " + 
  		"        me.id = ? " + 
  		"            AND rsbm.request_status = 'accepted' UNION ALL SELECT  " + 
  		"        friends2.id AS user_id " + 
  		"    FROM " + 
  		"        friends_with rsbt " + 
  		"    LEFT OUTER JOIN user friends2 ON friends2.id = rsbt.request_by " + 
  		"    LEFT OUTER JOIN user me ON me.id = rsbt.request_for " + 
  		"    WHERE " + 
  		"        me.id = ? " + 
  		"            AND rsbt.request_status = 'accepted') AS friends " + 
  		"        LEFT OUTER JOIN " + 
  		"    post friends_post ON friends.user_id = friends_post.posted_for " + 
  		"        LEFT OUTER JOIN " + 
  		"    user from_user ON from_user.id = friends_post.posted_by " + 
  		"        LEFT OUTER JOIN " + 
  		"    user to_user ON to_user.id = friends_post.posted_for " + 
  		"ORDER BY updated_time DESC; ";
  
  @Override
  public List<NewsFeed> getNewsFeedsForUser(String userId) {
    List<NewsFeed> newsFeeds = new ArrayList<NewsFeed>();
    Connection connection = ConnectionPool.getConnection();
    try {
      PreparedStatement stmt = connection
          .prepareStatement(NEWS_FEEDS_FOR_USER_QUERY);
      int index = 1;
      stmt.setString(index++, userId);
      stmt.setString(index++, userId);
      stmt.setString(index++, userId);

      ResultSet rs = stmt.executeQuery();
      while(rs.next()) {
        int fromUserId = rs.getInt("from_user_id");
        String fromUserFirstName = rs.getString("from_user_first_name");
        String fromUserLastName = rs.getString("from_user_last_name");

        int toUserId = rs.getInt("to_user_id");
        String toUserFirstName = rs.getString("to_user_first_name");
        String toUserLastName = rs.getString("to_user_last_name");
        
        String postText = rs.getString("post_text");
        String postType = rs.getString("post_type");
        Date updatedTime = rs.getTimestamp("updated_time");
        
        NewsFeed newsFeed = new NewsFeed(fromUserId, fromUserFirstName,
            fromUserLastName, toUserId, toUserFirstName, toUserLastName,
            postText, postType, updatedTime);  
        newsFeeds.add(newsFeed);        
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      ConnectionPool.freeConnection(connection);
    }

    return newsFeeds;
  }

}
