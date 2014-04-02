/**
 * 
 */
package edu.iiitb.facebook.action.status;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

import edu.iiitb.facebook.action.dao.PostsDAO;
import edu.iiitb.facebook.action.dao.impl.PostsDAOImpl;
import edu.iiitb.facebook.action.model.User;

/**
 * @author arjun
 * 
 */
public class StatusUpdateAction extends ActionSupport implements SessionAware {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 5907009549083317309L;

  private Map<String, Object> session;

  private PostsDAO postsDAO = new PostsDAOImpl();

  private int userId;
  private int postId;
  private String status;
  private String fullName;
  private String now;

  public String execute() {
    User user = (User) session.get("user");
    if (null != user) {
      userId = user.getUserId();
      postId = postsDAO.updateStatusForUser(userId, status);
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
      now = sdf.format(new Date());
      fullName = user.getFirstName() + " " + user.getLastName();
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

  @RequiredStringValidator(type = ValidatorType.FIELD, message = "This is a required field!")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public void setSession(Map<String, Object> session) {
    this.session = session;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public int getPostId() {
    return postId;
  }

  public void setPostId(int postId) {
    this.postId = postId;
  }

  public String getNow() {
    return now;
  }

  public void setNow(String now) {
    this.now = now;
  }
}
