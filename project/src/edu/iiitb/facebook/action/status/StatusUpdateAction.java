/**
 * 
 */
package edu.iiitb.facebook.action.status;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

import edu.iiitb.facebook.action.dao.PostsDAO;
import edu.iiitb.facebook.action.dao.impl.PostsDAOImpl;

/**
 * @author arjun
 * 
 */
@Namespace("/default")
@ResultPath(value = "/")
@Results({ @Result(name = "success", location = "/success.jsp"),
    @Result(name = "input", location = "/status/status.jsp"),
    @Result(name = "login", location = "/index.jsp") })
public class StatusUpdateAction extends ActionSupport {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 5907009549083317309L;

  private PostsDAO postsDAO = new PostsDAOImpl();

  private int userId;
  private String status;

  @Action(value = "/statusupdate")
  public String execute() {
    int statusId = postsDAO.updateStatusForUser(userId, status);
    return SUCCESS;
  }

  @IntRangeFieldValidator(message = "The userId must be positive", min = "1")
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

}
