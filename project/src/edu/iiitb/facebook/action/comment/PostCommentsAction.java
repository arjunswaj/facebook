/**
 * 
 */
package edu.iiitb.facebook.action.comment;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

import edu.iiitb.facebook.action.dao.CommentsDAO;
import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.dao.impl.CommentsDAOImpl;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.User;

/**
 * @author arjun
 * 
 */
public class PostCommentsAction extends ActionSupport {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 3973933993539075766L;

  private CommentsDAO commentsDAO = new CommentsDAOImpl();
  private UserDAO userDao = new UserDAOImpl();
  
  private int userId;
  private String fullname;
  private int postId;
  private String comment;
  private int commentId;
  private String now;

  
  public String execute() {
    commentId = commentsDAO.addCommentForPost(userId, postId, comment);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    now = sdf.format(new Date());
    User user = userDao.getUserImageByUserId(userId);
    fullname = user.getFirstName() + " " + user.getLastName();
    return SUCCESS;
  }

  @IntRangeFieldValidator(message = "The userId must be positive", min = "1")
  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  @IntRangeFieldValidator(message = "The postId must be positive", min = "1")
  public int getPostId() {
    return postId;
  }

  public void setPostId(int postId) {
    this.postId = postId;
  }

  @RequiredStringValidator(type = ValidatorType.FIELD, message = "This is a required field!")
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

}
