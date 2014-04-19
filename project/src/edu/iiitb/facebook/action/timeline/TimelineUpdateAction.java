/**
 * 
 */
package edu.iiitb.facebook.action.timeline;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

import edu.iiitb.facebook.action.dao.PostsDAO;
import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.dao.impl.PostsDAOImpl;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.User;

/**
 * @author saigv
 * 
 */
public class TimelineUpdateAction extends ActionSupport implements SessionAware {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
   * serialVersionUID
   */
  

  private Map<String, Object> session;

  private PostsDAO postsDAO = new PostsDAOImpl();

  private int userId;
  private int refuserId;
  private int postId;
  private String wallpost;
  private String fullName;
  private String now;

  @Override
  public String execute() {
    User user = (User) session.get("user");
    String tmp = (String) session.get("profileReference");
    UserDAO p = new UserDAOImpl();
    User ruser = p.getUserByUserId(Integer.parseInt(tmp));
    System.out.println("Wallpost"+wallpost);
    if(user!=null)
    	userId = user.getUserId();
    if(ruser!=null)
    	refuserId = Integer.parseInt(tmp);
    if (null != user && userId != refuserId) {
      postId = postsDAO.updatewallpostForUser(userId,refuserId , wallpost);
      System.out.println("Testing:"+postId);
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
      now = sdf.format(new Date());
      fullName = user.getFirstName() + " " + user.getLastName();
      System.out.println("Full Name : "+fullName);
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
  public String getWallpost() {
    return wallpost;
  }

  public void setWallpost(String wallpost) {
    this.wallpost = wallpost;
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
