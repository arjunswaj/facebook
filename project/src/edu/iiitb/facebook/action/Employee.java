/**
 * 
 */
package edu.iiitb.facebook.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

import edu.iiitb.facebook.util.ConnectionPool;

/**
 * @author arjun
 * 
 */
@Namespace("/default")
@ResultPath(value = "/")
@Results({ @Result(name = "success", location = "/success.jsp"), @Result(name = "input", location = "/index.jsp") })
public class Employee extends ActionSupport
{

	/**
	 * serial id
	 */
	private static final long serialVersionUID = -5144911560131497106L;

	private String name;

	private int age;

	@Action(value = "/empinfo")
	public String execute()
	{
		if (name.equals("Rock"))
		{

			Connection conn = ConnectionPool.getConnection();

			try
			{
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery("select count(*) from user");
				if (resultSet.next())
				{
					System.out.println(resultSet.getInt(1));
				}

			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return SUCCESS;
		}
		else
		{
			return INPUT;
		}
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "This is a required field!")
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@IntRangeFieldValidator(message = "The age must be between 25 and 65", min = "25", max = "65")
	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

}
