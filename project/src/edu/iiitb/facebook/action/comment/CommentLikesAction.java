/**
 * 
 */
package edu.iiitb.facebook.action.comment;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.CommentsDAO;
import edu.iiitb.facebook.action.dao.impl.CommentsDAOImpl;
import edu.iiitb.facebook.action.model.User;

/**
 * @author arjun
 * 
 */
public class CommentLikesAction extends ActionSupport implements SessionAware {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 5572352478134643009L;

  private Map<String, Object> session;

  private CommentsDAO commentsDAO = new CommentsDAOImpl();

  private List<User> likers;

  private int userId;
  private int commentId;
  private int likersCount;

  public String execute() {
    User user = (User) session.get("user");
    if (null != user) {
      userId = user.getUserId();
      commentsDAO.likeAComment(commentId, userId);
      likersCount = commentsDAO.updateLikersCount(commentId);
      return SUCCESS;
    } else {
      return LOGIN;
    }
  }

  public String unlike() {
    User user = (User) session.get("user");
    if (null != user) {
      userId = user.getUserId();
      commentsDAO.unlikeAComment(commentId, userId);
      likersCount = commentsDAO.updateLikersCount(commentId);
      return SUCCESS;
    } else {
      return LOGIN;
    }
  }

  public String commentlikers() {
    User user = (User) session.get("user");
    if (null != user) {
      likers = commentsDAO.peopleWholikeTheComment(commentId);
      return SUCCESS;
    } else {
      return LOGIN;
    }
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  @Override
  public void setSession(Map<String, Object> session) {
    this.session = session;
  }

  public int getCommentId() {
    return commentId;
  }

  public void setCommentId(int commentId) {
    this.commentId = commentId;
  }

  public int getLikersCount() {
    return likersCount;
  }

  public void setLikersCount(int likersCount) {
    this.likersCount = likersCount;
  }

  public List<User> getLikers() {
    return likers;
  }

  public void setLikers(List<User> likers) {
    this.likers = likers;
  }

}
