/**
 * 
 */
package edu.iiitb.facebook.action.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.iiitb.facebook.action.dao.CommentsDAO;
import edu.iiitb.facebook.util.ConnectionPool;

/**
 * @author arjun
 *
 */
public class CommentsDAOImpl implements CommentsDAO {

  private static final String COMMENT_FOR_POST = "INSERT INTO comment(text, commented_by, belongs_to_post) " + 
  		"      VALUES(?, ?, ?);"; 
      
  /* (non-Javadoc)
   * @see edu.iiitb.facebook.action.dao.CommentsDAO#addCommentForPost(int, int, java.lang.String)
   */
  @Override
  public int addCommentForPost(int userId, int postId, String comment) {
    int statusId = -1;
    Connection connection = ConnectionPool.getConnection();
    try {
      PreparedStatement stmt = connection
          .prepareStatement(COMMENT_FOR_POST);
      int index = 1;
      stmt.setString(index++, comment);
      stmt.setInt(index++, userId);
      stmt.setInt(index++, postId);
      statusId = stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      ConnectionPool.freeConnection(connection);
    }
    return statusId;
  }

}
