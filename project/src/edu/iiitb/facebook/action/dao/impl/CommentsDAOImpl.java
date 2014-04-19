/**
 * 
 */
package edu.iiitb.facebook.action.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.iiitb.facebook.action.dao.CommentsDAO;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.ConnectionPool;

/**
 * @author arjun
 *
 */
public class CommentsDAOImpl implements CommentsDAO {

  private static final String COMMENT_FOR_POST = "INSERT INTO comment(text, commented_by, belongs_to_post) " + 
  		"      VALUES(?, ?, ?);"; 
      
  private static final String LIKE_COMMENT_BY_USER = "INSERT INTO comment_like(user_id, comment_id) "
      + "VALUES(?, ?);";
  private static final String UNLIKE_COMMENT_BY_USER = "DELETE FROM comment_like WHERE user_id = ? AND comment_id = ? ;";
  
  private static final String UPDATE_LIKE_COUNT = "UPDATE comment set like_count = ? WHERE id = ? ;";      
  
  private static final String LIKERS_COUNT_OF_COMMENT = "SELECT COUNT(*) AS likers_count FROM comment_like where comment_id  = ? ;";
  
  private static final String COMMENT_LIKERS = "SELECT  " + 
      "    user.id AS user_id, " + 
      "    user.first_name AS first_name, " + 
      "    user.last_name AS last_name " + 
      "FROM " + 
      "    comment_like " + 
      "        JOIN " + 
      "    user ON user.id = comment_like.user_id " + 
      "where " + 
      "    comment_id = ? ;";
  
  private static final String DELETE_COMMENT = "DELETE FROM comment where id=?";  
  
  private static final String UPDATE_COMMENT = "UPDATE comment set text=? where id=?";
  
  /* (non-Javadoc)
   * @see edu.iiitb.facebook.action.dao.CommentsDAO#addCommentForPost(int, int, java.lang.String)
   */
  @Override
  public int addCommentForPost(int userId, int postId, String comment) {
    int statusId = -1;
    Connection connection = ConnectionPool.getConnection();
    try {
      PreparedStatement stmt = connection
          .prepareStatement(COMMENT_FOR_POST, Statement.RETURN_GENERATED_KEYS);
      int index = 1;
      stmt.setString(index++, comment);
      stmt.setInt(index++, userId);
      stmt.setInt(index++, postId);
      stmt.executeUpdate();
      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next())
      {
        statusId = rs.getInt(1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      ConnectionPool.freeConnection(connection);
    }
    return statusId;
  }

  @Override
  public void likeAComment(int commentId, int userId) {
    Connection conn = ConnectionPool.getConnection();    
    try {
      PreparedStatement stmt = conn.prepareStatement(LIKE_COMMENT_BY_USER);
      int index = 1;
      stmt.setInt(index++, userId);
      stmt.setInt(index++, commentId);      
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      ConnectionPool.freeConnection(conn);
    }   
  }

  @Override
  public int updateLikersCount(int commentId) {
    int likersCount = likersCountOfTheComment(commentId);
    updateLikeCountOfTheComment(likersCount, commentId);
    return likersCount;
  }

  @Override
  public void unlikeAComment(int commentId, int userId) {
    Connection conn = ConnectionPool.getConnection();    
    try {
      PreparedStatement stmt = conn.prepareStatement(UNLIKE_COMMENT_BY_USER);
      int index = 1;
      stmt.setInt(index++, userId);
      stmt.setInt(index++, commentId);      
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      ConnectionPool.freeConnection(conn);
    }     
  }

  private void updateLikeCountOfTheComment(int likersCount, int commentId) {
    Connection conn = ConnectionPool.getConnection();
    try {
      PreparedStatement stmt = conn.prepareStatement(UPDATE_LIKE_COUNT);
      int index = 1;      
      stmt.setInt(index++, likersCount);
      stmt.setInt(index++, commentId);
      stmt.executeUpdate();            
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      ConnectionPool.freeConnection(conn);
    }
  }
  
  private int likersCountOfTheComment(int commentId) {
    Connection conn = ConnectionPool.getConnection();
    int likersCount = -1;
    try {
      PreparedStatement stmt = conn.prepareStatement(LIKERS_COUNT_OF_COMMENT);
      int index = 1;      
      stmt.setInt(index++, commentId);      
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
  public List<User> peopleWholikeTheComment(int commentId) {
    Connection conn = ConnectionPool.getConnection();   
    List<User> likerList = new ArrayList<User>();
    try {
      PreparedStatement stmt = conn.prepareStatement(COMMENT_LIKERS);
      stmt.setInt(1, commentId);
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

  @Override
  public void updateComment(int commentId, String updatedComment) {
    Connection conn = ConnectionPool.getConnection();
    try {
      PreparedStatement stmt = conn.prepareStatement(UPDATE_COMMENT);
      int index = 1;            
      stmt.setString(index++, updatedComment);
      stmt.setInt(index++, commentId);
      stmt.executeUpdate();            
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      ConnectionPool.freeConnection(conn);
    }
  }

  @Override
  public void deleteComment(int commentId) {
    Connection conn = ConnectionPool.getConnection();    
    try {
      PreparedStatement stmt = conn.prepareStatement(DELETE_COMMENT);
      int index = 1;
      stmt.setInt(index++, commentId);      
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      ConnectionPool.freeConnection(conn);
    } 
  }

}
