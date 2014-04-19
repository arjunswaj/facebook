/**
 * 
 */
package edu.iiitb.facebook.action.comment;

import java.text.SimpleDateFormat;
import java.util.Date;
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
public class PostCommentsAction extends ActionSupport implements SessionAware {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 3973933993539075766L;
  private Map<String, Object> session;
  private CommentsDAO commentsDAO = new CommentsDAOImpl();

  private String fullname;
  private int userId;
  private int postId;
  private String comment;
  private int commentId;
  private String now;

  public String execute() {
    User user = (User) session.get("user");
    if (null != user) {
      userId = user.getUserId();
      commentId = commentsDAO.addCommentForPost(userId, postId, comment);
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
      now = sdf.format(new Date());
      fullname = user.getFirstName() + " " + user.getLastName();
      return SUCCESS;
    } else {
      return LOGIN;
    }
  }

  public String updateComment() {
    User user = (User) session.get("user");
    if (null != user) {
      userId = user.getUserId();
      commentsDAO.updateComment(commentId, comment);      
      fullname = user.getFirstName() + " " + user.getLastName();
      return SUCCESS;
    } else {
      return LOGIN;
    }
  }
  
  public String deleteComment() {
    User user = (User) session.get("user");
    if (null != user) {
      userId = user.getUserId();
      commentsDAO.deleteComment(commentId);
      fullname = user.getFirstName() + " " + user.getLastName();
      return SUCCESS;
    } else {
      return LOGIN;
    }
  }
  
  public int getPostId() {
    return postId;
  }

  public void setPostId(int postId) {
    this.postId = postId;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public int getCommentId() {
    return commentId;
  }

  public void setCommentId(int commentId) {
    this.commentId = commentId;
  }

  public String getNow() {
    return now;
  }

  public void setNow(String now) {
    this.now = now;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
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
}
