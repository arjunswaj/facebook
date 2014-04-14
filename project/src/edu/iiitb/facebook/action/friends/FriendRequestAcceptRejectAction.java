package edu.iiitb.facebook.action.friends;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.FriendsDAO;
import edu.iiitb.facebook.action.dao.impl.FriendsDAOImpl;
import edu.iiitb.facebook.action.model.User;

/**
 * @author arjun
 * 
 */
public class FriendRequestAcceptRejectAction extends ActionSupport implements
    SessionAware {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -2655904984362444275L;

  private Map<String, Object> session;

  private int myId;
  private int friendId;

  private FriendsDAO friendsDAO = new FriendsDAOImpl();

  public String execute() {
    User user = (User) session.get("user");
    if (null != user) {
      myId = user.getUserId();
      friendsDAO.confirmFriend(myId, friendId);
      return SUCCESS;
    } else {
      return LOGIN;
    }
  }

  public String rejectFriend() {
    User user = (User) session.get("user");
    if (null != user) {
      myId = user.getUserId();
      friendsDAO.rejectFriend(myId, friendId);
      return SUCCESS;
    } else {
      return LOGIN;
    }
  }

  public int getMyId() {
    return myId;
  }

  public void setMyId(int myId) {
    this.myId = myId;
  }

  public int getFriendId() {
    return friendId;
  }

  public void setFriendId(int friendId) {
    this.friendId = friendId;
  }

  @Override
  public void setSession(Map<String, Object> session) {
    this.session = session;
  }

}
