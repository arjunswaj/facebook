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
import edu.iiitb.facebook.action.model.PostComment;
import edu.iiitb.facebook.util.ConnectionPool;

public class PostsDAOImpl implements PostsDAO {

  private static final String NEWS_FEEDS_FOR_USER_QUERY = "SELECT "
      + "  my_post.id AS post_id, " + "  comment.id AS comment_id, "
      + "    from_user.id AS from_user_id, "
      + "    from_user.first_name AS from_user_first_name, "
      + "    from_user.last_name AS from_user_last_name, "
      + "    to_user.id AS to_user_id, "
      + "    to_user.first_name AS to_user_first_name, "
      + "    to_user.last_name AS to_user_last_name, "
      + "    my_post.text AS post_text, " + "    my_post.type AS post_type, "
      + "    my_post.updated AS updated_time, "
      + "    comment.text AS comment_text, "
      + "    commenter.id AS commenter_user_id, "
      + "    commenter.first_name AS commenter_first_name, "
      + "    commenter.last_name AS commenter_last_name, "
      + "    comment.updated AS comment_updated_time " + "FROM "
      + "    post my_post " + "        LEFT OUTER JOIN "
      + "    user from_user ON from_user.id = my_post.posted_by "
      + "        LEFT OUTER JOIN "
      + "    user to_user ON to_user.id = my_post.posted_for "
      + "        LEFT OUTER JOIN "
      + "    comment comment ON comment.belongs_to_post = my_post.id "
      + "        LEFT OUTER JOIN "
      + "    user commenter ON commenter.id = comment.commented_by " + "WHERE "
      + "    to_user.id = ?  " + "UNION ALL SELECT  "
      + "  friends_post.id AS post_id, " + "  comment.id AS comment_id, "
      + "    from_user.id AS from_user_id, "
      + "    from_user.first_name AS from_user_first_name, "
      + "    from_user.last_name AS from_user_last_name, "
      + "    to_user.id AS to_user_id, "
      + "    to_user.first_name AS to_user_first_name, "
      + "    to_user.last_name AS to_user_last_name, "
      + "    friends_post.text AS post_text, "
      + "    friends_post.type AS post_type, "
      + "    friends_post.updated AS updated_time, "
      + "    comment.text AS comment_text, "
      + "    commenter.id AS commenter_user_id, "
      + "    commenter.first_name AS commenter_first_name, "
      + "    commenter.last_name AS commenter_last_name, "
      + "    comment.updated AS comment_updated_time " + "FROM "
      + "    (SELECT  " + "        friends1.id AS user_id " + "    FROM "
      + "        friends_with rsbm "
      + "    LEFT OUTER JOIN user friends1 ON friends1.id = rsbm.request_for "
      + "    LEFT OUTER JOIN user me ON me.id = rsbm.request_by "
      + "    WHERE " + "        me.id = ? "
      + "            AND rsbm.request_status = 'accepted' UNION ALL SELECT  "
      + "        friends2.id AS user_id " + "    FROM "
      + "        friends_with rsbt "
      + "    LEFT OUTER JOIN user friends2 ON friends2.id = rsbt.request_by "
      + "    LEFT OUTER JOIN user me ON me.id = rsbt.request_for "
      + "    WHERE " + "        me.id = ? "
      + "            AND rsbt.request_status = 'accepted') AS friends "
      + "        LEFT OUTER JOIN "
      + "    post friends_post ON friends.user_id = friends_post.posted_for "
      + "        LEFT OUTER JOIN "
      + "    user from_user ON from_user.id = friends_post.posted_by "
      + "        LEFT OUTER JOIN "
      + "    user to_user ON to_user.id = friends_post.posted_for "
      + "        LEFT OUTER JOIN "
      + "    comment comment ON comment.belongs_to_post = friends_post.id "
      + "        LEFT OUTER JOIN "
      + "    user commenter ON commenter.id = comment.commented_by "
      + "ORDER BY post_id DESC, comment_updated_time , updated_time DESC;";

  private static final String STATUS_UPDATE_FOR_USER = "INSERT INTO post(text, type, created, posted_by, posted_for) "
      + "VALUES(?, ?, CURRENT_TIMESTAMP, ?, ?);";

  private static final String STATUS = "status";

  @Override
  public List<NewsFeed> getNewsFeedsForUser(int userId) {
    List<NewsFeed> newsFeeds = new ArrayList<NewsFeed>();
    Connection connection = ConnectionPool.getConnection();
    try {
      PreparedStatement stmt = connection
          .prepareStatement(NEWS_FEEDS_FOR_USER_QUERY);
      int index = 1;
      stmt.setInt(index++, userId);
      stmt.setInt(index++, userId);
      stmt.setInt(index++, userId);

      int prevPostId = -1;
      ResultSet rs = stmt.executeQuery();
      NewsFeed newsFeed = null;
      while (rs.next()) {        
        int postId = rs.getInt("post_id");
        Integer commentId = rs.getInt("comment_id");
        int fromUserId = rs.getInt("from_user_id");
        String fromUserFirstName = rs.getString("from_user_first_name");
        String fromUserLastName = rs.getString("from_user_last_name");

        int toUserId = rs.getInt("to_user_id");
        String toUserFirstName = rs.getString("to_user_first_name");
        String toUserLastName = rs.getString("to_user_last_name");

        String postText = rs.getString("post_text");
        String postType = rs.getString("post_type");
        Date updatedTime = rs.getTimestamp("updated_time");

        int commenterUserId = rs.getInt("commenter_user_id");
        String commenterFirstName = rs.getString("commenter_first_name");
        String commenterLastName = rs.getString("commenter_last_name");
        String commentText = rs.getString("comment_text");

        Date commentTime = rs.getTimestamp("comment_updated_time");
        if (prevPostId != postId) {
          newsFeed = new NewsFeed(postId, fromUserId, fromUserFirstName,
              fromUserLastName, toUserId, toUserFirstName, toUserLastName,
              postText, postType, updatedTime);
          newsFeeds.add(newsFeed);
        }

        if (null != commentId && 0 != commentId) {
          PostComment postComment = new PostComment(commentId, commenterUserId,
              commenterFirstName, commenterLastName, commentText, commentTime);
          newsFeed.getPostComments().add(postComment);
        }
        prevPostId = postId;
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      ConnectionPool.freeConnection(connection);
    }

    return newsFeeds;
  }

  @Override
  public int updateStatusForUser(int userId, String status) {
    int statusId = -1;
    Connection connection = ConnectionPool.getConnection();
    try {
      PreparedStatement stmt = connection
          .prepareStatement(STATUS_UPDATE_FOR_USER);
      int index = 1;
      stmt.setString(index++, status);
      stmt.setString(index++, STATUS);
      stmt.setInt(index++, userId);
      stmt.setInt(index++, userId);
      statusId = stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      ConnectionPool.freeConnection(connection);
    }
    return statusId;
  }

}
