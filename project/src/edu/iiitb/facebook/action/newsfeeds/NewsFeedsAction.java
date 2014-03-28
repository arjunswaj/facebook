/**
 * 
 */
package edu.iiitb.facebook.action.newsfeeds;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.PostsDAO;
import edu.iiitb.facebook.action.dao.impl.PostsDAOImpl;
import edu.iiitb.facebook.action.model.NewsFeed;

/**
 * @author arjun
 * 
 */
@Namespace("/default")
@ResultPath(value = "/")
@Results({ @Result(name = "success", location = "/feeds/feeds.jsp"),
    @Result(name = "login", location = "/index.jsp") })
public class NewsFeedsAction extends ActionSupport {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -7724100238927300603L;

  private PostsDAO postsDAO = new PostsDAOImpl();

  private List<NewsFeed> newsFeeds;
  private int userId;

  @Action(value = "/newsfeeds")
  public String execute() {
    newsFeeds = postsDAO.getNewsFeedsForUser(userId);
    return SUCCESS;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public List<NewsFeed> getNewsFeeds() {
    return newsFeeds;
  }

  public void setNewsFeeds(List<NewsFeed> newsFeeds) {
    this.newsFeeds = newsFeeds;
  }

}
