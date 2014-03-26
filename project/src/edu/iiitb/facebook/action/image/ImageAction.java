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
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.ConnectionPool;

@Namespace("/default")
@ResultPath(value = "/")
public class ImageAction extends ActionSupport implements SessionAware{

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -8188116769915480525L; 

  private String userId;
  private String picType;

  private Map<String, Object> session;

  private UserDAO userDao = new UserDAOImpl();
  
  @Action(value = "/image")
  public String execute() throws SQLException, IOException {
    Connection connection = ConnectionPool.getConnection();
    User user = userDao.getUserImageByUserId(Integer.parseInt(userId));
    HttpServletResponse response = ServletActionContext.getResponse();
    response.setContentType("image/jpeg");
    InputStream in = user.getCurrentProfilePic();
    if (null != picType && picType.equals("cover")) {
      in = user.getCurrentCoverPic();
    }
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
  
  public String getPicType() {
    return picType;
  }

  public void setPicType(String picType) {
    this.picType = picType;
  }

  @Override
  public void setSession(Map<String, Object> session) {
    this.session = session;
  }


   
}
