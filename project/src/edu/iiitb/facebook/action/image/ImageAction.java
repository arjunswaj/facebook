package edu.iiitb.facebook.action.image;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.util.ConnectionPool;

@Namespace("/default")
@ResultPath(value = "/")
public class ImageAction extends ActionSupport implements SessionAware,
    ServletResponseAware {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -8188116769915480525L;

  private HttpServletResponse response;

  private String userId;

  private Map<String, Object> session;

  private UserDAO userDao = new UserDAOImpl();

  @Action(value = "/image")
  public String execute() throws SQLException, IOException {
    Connection connection = ConnectionPool.getConnection();
    User user = userDao.getUserImageByUserId(userId);
    response.setContentType("image/png");
    InputStream in = user.getCurrentProfilePic();
    OutputStream out = response.getOutputStream();
    byte[] buffer = new byte[1024];
    int len;
    while ((len = in.read(buffer)) != -1) {
      out.write(buffer, 0, len);
    }
    ConnectionPool.freeConnection(connection);
    return NONE;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Override
  public void setSession(Map<String, Object> session) {
    this.session = session;
  }

  public void setServletResponse(HttpServletResponse response) {
    this.response = response;
  }

  public HttpServletResponse getServletResponse() {
    return response;
  }
}
