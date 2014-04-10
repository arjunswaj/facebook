/**
 * 
 */
package edu.iiitb.facebook.action.posts;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import edu.iiitb.facebook.action.dao.PostsDAO;
import edu.iiitb.facebook.action.dao.impl.PostsDAOImpl;
import edu.iiitb.facebook.action.model.User;

/**
 * @author arjun
 * 
 */
public class LikesAction extends ActionSupport implements SessionAware {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 5907009549083317309L;

  private Map<String, Object> session;

  private PostsDAO postsDAO = new PostsDAOImpl();

  private List<User> likers;

  private int userId;
  private int postId;
  private int likersCount;

  public String execute() {
    User user = (User) session.get("user");
    if (null != user) {
      userId = user.getUserId();
      postsDAO.likeAPost(postId, userId);
      likersCount = postsDAO.updateLikersCount(postId);
      return SUCCESS;
    } else {
      return LOGIN;
    }
  }

  public String unlike() {
    User user = (User) session.get("user");
    if (null != user) {
      userId = user.getUserId();
      postsDAO.unlikeAPost(postId, userId);
      likersCount = postsDAO.updateLikersCount(postId);
      return SUCCESS;
    } else {
      return LOGIN;
    }
  }

  public String postlikers() {
    User user = (User) session.get("user");
    if (null != user) {      
      likers = postsDAO.peopleWholikeThePost(postId);
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

  public int getPostId() {
    return postId;
  }

  public void setPostId(int postId) {
    this.postId = postId;
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
