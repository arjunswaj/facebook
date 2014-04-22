/**
 * 
 */
package edu.iiitb.facebook.action.timeline;


import java.util.ArrayList;
import java.util.List;

import edu.iiitb.facebook.action.dao.*;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.PostsDAO;
import edu.iiitb.facebook.action.dao.impl.FriendsDAOImpl;
import edu.iiitb.facebook.action.dao.impl.PostsDAOImpl;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.NewsFeed;
import edu.iiitb.facebook.action.model.User;

public class TimelineAction extends ActionSupport implements SessionAware,RequestAware {

  private Map<String, Object> session;
  private Map<String, Object> request;
  private List<Integer> friendIds;

  private PostsDAO postsDAO = new PostsDAOImpl();
  private UserDAO userDAO=new UserDAOImpl();

  public List<Integer> getFriendIds() {
	return friendIds;
}

public void setFriendIds(List<Integer> friendIds) {
	this.friendIds = friendIds;
}

public UserDAO getUserDAO() {
	return userDAO;
}

public void setUserDAO(UserDAO userDAO) {
	this.userDAO = userDAO;
}

private List<NewsFeed> newsFeeds;
  private List<NewsFeed> timelineFeeds;
  public PostsDAO getPostsDAO() {
	return postsDAO;
}

public void setPostsDAO(PostsDAO postsDAO) {
	this.postsDAO = postsDAO;
}

public List<NewsFeed> getTimelineFeeds() {
	return timelineFeeds;
}

public void setTimelineFeeds(List<NewsFeed> timelineFeeds) {
	this.timelineFeeds = timelineFeeds;
}

public List<UserDAO> getUser() {
	return user;
}

public void setUser(List<UserDAO> user) {
	this.user = user;
}

public String getFref() {
	return fref;
}

public void setFref(String fref) {
	this.fref = fref;
}

public int getFuserId() {
	return fuserId;
}

public void setFuserId(int fuserId) {
	this.fuserId = fuserId;
}

public Map<String, Object> getSession() {
	return session;
}

public Map<String, Object> getRequest() {
	return request;
}

private List<UserDAO> user;
  private String fref;
  private String profession;
  private String school;
  private String currentplace;
  private String nativeplace;
  private String workplace;
  private String relationship;

  public String getRelationship() {
	return relationship;
}

public void setRelationship(String relationship) {
	this.relationship = relationship;
}

public String getWorkplace() {
	return workplace;
}

public void setWorkplace(String workplace) {
	this.workplace = workplace;
}

public String getProfession() {
	return profession;
}

public void setProfession(String profession) {
	this.profession = profession;
}

public String getSchool() {
	return school;
}

public void setSchool(String school) {
	this.school = school;
}

public String getCurrentplace() {
	return currentplace;
}

public void setCurrentplace(String currentplace) {
	this.currentplace = currentplace;
}

public String getNativeplace() {
	return nativeplace;
}

public void setNativeplace(String nativeplace) {
	this.nativeplace = nativeplace;
}

private int userId;
private int fuserId;

  public String execute() {
    User user = (User) session.get("user");
    fref = (String) session.get("profileReference");
    System.out.println("Friend reference:"+fref);
    userId = user.getUserId();
    if(fref!=null)
    fuserId=Integer.parseInt(fref);
    else
    fuserId=-1;
    User userdetails;
    timelineFeeds=new ArrayList<NewsFeed>();
    FriendsDAO friendsDao = new FriendsDAOImpl();
    List<User> friendsList;

    if(fref==null)
    {
    	newsFeeds=postsDAO.getNewsFeedsForUser(userId);
    	userdetails= userDAO.getUserByUserId(userId);
    	friendsList = friendsDao.getFriendsList(userId);
    	
    }
    else
    {
    	newsFeeds = postsDAO.getNewsFeedsForUser(fuserId);
    	userdetails= userDAO.getUserByUserId(fuserId);
    	friendsList = friendsDao.getFriendsList(fuserId);
    	
    }
    profession = userdetails.getProfession();
	relationship = userdetails.getRelationship();
	school = userdetails.getSchool();
	currentplace = userdetails.getCurrentplace();
	nativeplace = userdetails.getNativeplace();
	workplace = userdetails.getWorkplace();
	
    for(NewsFeed n:newsFeeds)
    {
    	if(fuserId!=-1)
    	{
    	if(n.getToUserId()==fuserId)
    		timelineFeeds.add(n);
    	 		
    	}
    	else
    	{
    	if(n.getToUserId()==userId)
        	timelineFeeds.add(n);	
    	}
    }
    friendIds = new ArrayList<Integer>();
    int count = -1;
    for(User p : friendsList)
    {
    	if(count==8)
    		break;
    	count++;
    	friendIds.add(p.getUserId());
    	System.out.println("Friend Id:"+p.getUserId());
    }
    if(count<8)
    {
    	for(int j=count;j != 8;j++)
    		friendIds.add(0);
    }
    
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
  
	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
	}
}
