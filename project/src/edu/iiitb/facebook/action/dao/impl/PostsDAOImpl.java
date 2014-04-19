package edu.iiitb.facebook.action.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.iiitb.facebook.action.dao.PostsDAO;
import edu.iiitb.facebook.action.model.NewsFeed;
import edu.iiitb.facebook.action.model.PostComment;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.ConnectionPool;

public class PostsDAOImpl implements PostsDAO
{

  private static final String NEWS_FEEDS_FOR_USER_QUERY = "SELECT  "
      + "    my_post.id AS post_id, "
      + "    comment.id AS comment_id, "
      + "    from_user.id AS from_user_id, "
      + "    from_user.first_name AS from_user_first_name, "
      + "    from_user.last_name AS from_user_last_name, "
      + "    to_user.id AS to_user_id, "
      + "    to_user.first_name AS to_user_first_name, "
      + "    to_user.last_name AS to_user_last_name, "
      + "    my_post.text AS post_text, "
      + "    my_post.type AS post_type, "
      + "    my_post.updated AS updated_time, "
      + "    my_post.like_count AS post_like_count, "
      + "    my_post_likes.user_id AS me_liked_post, "
      + "    comment.text AS comment_text, "
      + "    commenter.id AS commenter_user_id, "
      + "    commenter.first_name AS commenter_first_name, "
      + "    commenter.last_name AS commenter_last_name, "
      + "    comment.updated AS comment_updated_time, "
      + "    comment.created AS comment_created_time, "      
      + "    comment.like_count AS comment_like_count, "
      + "    my_comment_likes.user_id AS me_liked_comment "
      + "FROM "
      + "    post my_post "
      + "        LEFT OUTER JOIN "
      + "    user from_user ON from_user.id = my_post.posted_by "
      + "        LEFT OUTER JOIN "
      + "    user to_user ON to_user.id = my_post.posted_for "
      + "        LEFT OUTER JOIN "
      + "    comment comment ON comment.belongs_to_post = my_post.id "
      + "        LEFT OUTER JOIN "
      + "    user commenter ON commenter.id = comment.commented_by "
      + "        LEFT OUTER JOIN "
      + "    post_like my_post_likes ON (my_post_likes.post_id = my_post.id "
      + "        AND my_post_likes.user_id = to_user.id) "
      + "        LEFT OUTER JOIN "
      + "    comment_like my_comment_likes ON (my_comment_likes.comment_id = comment.id "
      + "        AND my_comment_likes.user_id = to_user.id) "
      + "WHERE "
      + "    to_user.id = ?  "
      + "UNION ALL SELECT  "
      + "    friends_post.id AS post_id, "
      + "    comment.id AS comment_id, "
      + "    from_user.id AS from_user_id, "
      + "    from_user.first_name AS from_user_first_name, "
      + "    from_user.last_name AS from_user_last_name, "
      + "    to_user.id AS to_user_id, "
      + "    to_user.first_name AS to_user_first_name, "
      + "    to_user.last_name AS to_user_last_name, "
      + "    friends_post.text AS post_text, "
      + "    friends_post.type AS post_type, "
      + "    friends_post.updated AS updated_time, "
      + "    friends_post.like_count AS post_like_count, "
      + "    friends_post_likes.user_id AS me_liked, "
      + "    comment.text AS comment_text, "
      + "    commenter.id AS commenter_user_id, "
      + "    commenter.first_name AS commenter_first_name, "
      + "    commenter.last_name AS commenter_last_name, "
      + "    comment.updated AS comment_updated_time, "
      + "    comment.created AS comment_created_time, "       
      + "    comment.like_count AS comment_like_count, "
      + "    friends_comment_likes.user_id AS me_liked_comment "
      + "FROM "
      + "    (SELECT  "
      + "        friends1.id AS user_id "
      + "    FROM "
      + "        friends_with rsbm "
      + "    LEFT OUTER JOIN user friends1 ON friends1.id = rsbm.request_for "
      + "    LEFT OUTER JOIN user me ON me.id = rsbm.request_by "
      + "    WHERE "
      + "        me.id = ? AND rsbm.status = 'accepted' UNION ALL SELECT  "
      + "        friends2.id AS user_id "
      + "    FROM "
      + "        friends_with rsbt "
      + "    LEFT OUTER JOIN user friends2 ON friends2.id = rsbt.request_by "
      + "    LEFT OUTER JOIN user me ON me.id = rsbt.request_for "
      + "    WHERE "
      + "        me.id = ? AND rsbt.status = 'accepted') AS friends "
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
      + "        LEFT OUTER JOIN "
      + "    post_like friends_post_likes ON (friends_post_likes.post_id = friends_post.id "
      + "        AND friends_post_likes.user_id = ?) "
      + "        LEFT OUTER JOIN "
      + "    comment_like friends_comment_likes ON (friends_comment_likes.comment_id = comment.id "
      + "        AND friends_comment_likes.user_id = ?) "
      + "ORDER BY post_id DESC , comment_created_time , updated_time DESC; ";

	private static final String STATUS_UPDATE_FOR_USER = "INSERT INTO post(text, type, created, posted_by, posted_for) "
			+ "VALUES(?, ?, CURRENT_TIMESTAMP, ?, ?);";
	
	private static final String WALLPOST_UPDATE_FOR_USER = "INSERT INTO post(text, type, created, posted_by, posted_for) "
			+ "VALUES(?, ?, CURRENT_TIMESTAMP, ?, ?);";

	private static final String LIKE_POST_BY_USER = "INSERT INTO post_like(user_id, post_id) "
      + "VALUES(?, ?);";
	private static final String UNLIKE_POST_BY_USER = "DELETE FROM post_like WHERE user_id = ? AND post_id = ? ;";
	
	private static final String UPDATE_LIKE_COUNT = "UPDATE post set like_count = ? WHERE id = ? ;";      
	
	private static final String LIKERS_COUNT_OF_POST = "SELECT COUNT(*) AS likers_count FROM post_like where post_id  = ? ;";
	
	private static final String STATUS = "status";
	
	private static final String WALLPOST = "wallpost";
	
	private static final String DELETE_POST = "delete from post where id=?";

	private static final String GET_POST = "select text from post where id=?";
	
	private static final String UPDATE_POST = "update post set text=? where id=?";

	private static final String POST_LIKERS = "SELECT  " + 
			"    user.id AS user_id, " + 
			"    user.first_name AS first_name, " + 
			"    user.last_name AS last_name " + 
			"FROM " + 
			"    post_like " + 
			"        JOIN " + 
			"    user ON user.id = post_like.user_id " + 
			"where " + 
			"    post_id = ? ;";
	@Override
	public List<NewsFeed> getNewsFeedsForUser(int userId)
	{
		List<NewsFeed> newsFeeds = new ArrayList<NewsFeed>();
		Connection connection = ConnectionPool.getConnection();
		try
		{
			PreparedStatement stmt = connection.prepareStatement(NEWS_FEEDS_FOR_USER_QUERY);
			int index = 1;
			stmt.setInt(index++, userId);
			stmt.setInt(index++, userId);
			stmt.setInt(index++, userId);
			stmt.setInt(index++, userId);
			stmt.setInt(index++, userId);

			int prevPostId = -1;
			ResultSet rs = stmt.executeQuery();
			NewsFeed newsFeed = null;
			while (rs.next())
			{
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
				int likeCount = rs.getInt("post_like_count");
				boolean haveILiked = (0 == rs.getInt("me_liked_post")) ? false : true;
				int commenterUserId = rs.getInt("commenter_user_id");
				String commenterFirstName = rs.getString("commenter_first_name");
				String commenterLastName = rs.getString("commenter_last_name");
				String commentText = rs.getString("comment_text");
				int commentLikeCount = rs.getInt("comment_like_count");
				boolean haveILikedComment = (0 == rs.getInt("me_liked_comment")) ? false : true;

				Date commentTime = rs.getTimestamp("comment_updated_time");
				if (prevPostId != postId && 0 != postId)
				{
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					String updatedTimeFormatted = sdf.format(updatedTime);
					newsFeed = new NewsFeed(postId, fromUserId, fromUserFirstName, fromUserLastName, toUserId, toUserFirstName, toUserLastName,
							postText, postType, updatedTimeFormatted, likeCount, haveILiked);
					newsFeeds.add(newsFeed);
				}

				if (null != commentId && 0 != commentId)
				{
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					String commentTimeFormatted = sdf.format(commentTime);
					PostComment postComment = new PostComment(commentId, commenterUserId, commenterFirstName, commenterLastName, commentText,
							commentTimeFormatted, commentLikeCount, haveILikedComment);
					newsFeed.getPostComments().add(postComment);
				}
				prevPostId = postId;
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

		return newsFeeds;
	}

	@Override
	public int updateStatusForUser(int userId, String status)
	{
		int statusId = -1;
		Connection connection = ConnectionPool.getConnection();
		try
		{
			PreparedStatement stmt = connection.prepareStatement(STATUS_UPDATE_FOR_USER, Statement.RETURN_GENERATED_KEYS);
			int index = 1;
			stmt.setString(index++, status);
			stmt.setString(index++, STATUS);
			stmt.setInt(index++, userId);
			stmt.setInt(index++, userId);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next())
			{
				statusId = rs.getInt(1);
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
		return statusId;
	}
	
	public int updatewallpostForUser(int userId,int refuserId,String wallpost)
	{
		int statusId = -1;
		Connection connection = ConnectionPool.getConnection();
		try
		{
			PreparedStatement stmt = connection.prepareStatement(WALLPOST_UPDATE_FOR_USER, Statement.RETURN_GENERATED_KEYS);
			int index = 1;
			stmt.setString(index++, wallpost);
			stmt.setString(index++, WALLPOST);
			stmt.setInt(index++, userId);
			stmt.setInt(index++, refuserId);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next())
			{
				statusId = rs.getInt(1);
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
		return statusId;
	}

	@Override
	public int deletePost(String postId)
	{
		Connection conn = ConnectionPool.getConnection();

		try
		{
			PreparedStatement stmt = conn.prepareStatement(DELETE_POST);
			stmt.setString(1, postId);

			return stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.freeConnection(conn);
		}

		return -1;
	}

	@Override
	public int updatePost(String postId, String updatedText)
	{
		Connection conn = ConnectionPool.getConnection();

		try
		{
			PreparedStatement stmt = conn.prepareStatement(UPDATE_POST);
			stmt.setString(1, updatedText);
			stmt.setString(2, postId);
			return stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.freeConnection(conn);
		}

		return -1;
	}

	@Override
	public String getText(String postId)
	{
		Connection conn = ConnectionPool.getConnection();

		String postText = "";

		try
		{
			PreparedStatement stmt = conn.prepareStatement(GET_POST);
			stmt.setString(1, postId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next())
			{
				postText = rs.getString(1);
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

		return postText;
	}

  @Override
  public void likeAPost(int postId, int userId) {
    Connection conn = ConnectionPool.getConnection();    
    try {
      PreparedStatement stmt = conn.prepareStatement(LIKE_POST_BY_USER);
      int index = 1;
      stmt.setInt(index++, userId);
      stmt.setInt(index++, postId);      
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      ConnectionPool.freeConnection(conn);
    }   
  }
  
  @Override
  public void unlikeAPost(int postId, int userId) {
    Connection conn = ConnectionPool.getConnection();    
    try {
      PreparedStatement stmt = conn.prepareStatement(UNLIKE_POST_BY_USER);
      int index = 1;
      stmt.setInt(index++, userId);
      stmt.setInt(index++, postId);      
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      ConnectionPool.freeConnection(conn);
    }  
  }

  @Override
  public List<User> peopleWholikeThePost(int postId) {
    Connection conn = ConnectionPool.getConnection();   
    List<User> likerList = new ArrayList<User>();
    try {
      PreparedStatement stmt = conn.prepareStatement(POST_LIKERS);
      stmt.setInt(1, postId);
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        int userId = rs.getInt("user_id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        User liker = new User(userId, firstName, lastName);
        likerList.add(liker);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      ConnectionPool.freeConnection(conn);
    }
    return likerList;
  }
  
  private void updateLikeCountOfThePost(int likersCount, int postId) {
    Connection conn = ConnectionPool.getConnection();
    try {
      PreparedStatement stmt = conn.prepareStatement(UPDATE_LIKE_COUNT);
      int index = 1;      
      stmt.setInt(index++, likersCount);
      stmt.setInt(index++, postId);
      stmt.executeUpdate();            
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      ConnectionPool.freeConnection(conn);
    }
  }
  
  private int likersCountOfThePost(int postId) {
    Connection conn = ConnectionPool.getConnection();
    int likersCount = -1;
    try {
      PreparedStatement stmt = conn.prepareStatement(LIKERS_COUNT_OF_POST);
      int index = 1;      
      stmt.setInt(index++, postId);      
      ResultSet rs = stmt.executeQuery();
      
      if (rs.next()) {
        likersCount = rs.getInt("likers_count");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      ConnectionPool.freeConnection(conn);
    }
    return likersCount;
  }

  @Override
  public int updateLikersCount(int postId) {
    int likersCount = likersCountOfThePost(postId);
    updateLikeCountOfThePost(likersCount, postId);
    return likersCount;
  }

}
