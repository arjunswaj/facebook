/**
 * 
 */
package edu.iiitb.facebook.action.newsfeeds;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.PostsDAO;
import edu.iiitb.facebook.action.dao.impl.PostsDAOImpl;
import edu.iiitb.facebook.action.model.NewsFeed;
import edu.iiitb.facebook.action.model.User;

/**
 * @author arjun
 * 
 */
@Namespace("/default")
@ResultPath(value = "/")
@Results({
    @Result(name = "success", location = "newsfeeds.tiles", type = "tiles"),
    @Result(name = "login", location = "/login/login.jsp") })
public class NewsFeedsAction extends ActionSupport implements SessionAware {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -7724100238927300603L;

  private Map<String, Object> session;

  private PostsDAO postsDAO = new PostsDAOImpl();

  private List<NewsFeed> newsFeeds;

  private int userId;

  @Action(value = "/newsfeeds", interceptorRefs = { @InterceptorRef(value = "secureAccess") })
  public String execute() {
    User user = (User) session.get("user");
    userId = user.getUserId();
    newsFeeds = postsDAO.getNewsFeedsForUser(userId);
    return SUCCESS;
  }

  public List<NewsFeed> getNewsFeeds() {
    return newsFeeds;
  }

  public void setNewsFeeds(List<NewsFeed> newsFeeds) {
    this.newsFeeds = newsFeeds;
  }

  @Override
  public void setSession(Map<String, Object> session) {
    this.session = session;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

}
