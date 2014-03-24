/**
 * 
 */
package edu.iiitb.facebook.action.teacher;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/**
 * @author arjun
 * 
 */
@Namespace("/default")
@ResultPath(value="/")
@Results({ @Result(name = "success", location = "/success.jsp"),
    @Result(name = "input", location = "/teacherindex.jsp") })
public class Teacher extends ActionSupport {

  /**
   * 
   */
  private static final long serialVersionUID = -6720874722482304954L;

  private String name;

  private int age;

  @Action(value = "/teacherinfo")
  public String execute() {
    if (name.equals("Teac")) {
      return SUCCESS;
    } else {
      return INPUT;
    }
  }

  @RequiredStringValidator(type = ValidatorType.FIELD, message = "This is a required field!")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @IntRangeFieldValidator(message = "The age must be between 15 and 25", min = "15", max = "25")
  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

}
