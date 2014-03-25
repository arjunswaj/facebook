/**
 * 
 */
package edu.iiitb.facebook.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.util.ConnectionPool;

/**
 * @author arjun
 * 
 */
public class Employee extends ActionSupport
{

	/**
	 * serial id
	 */
	private static final long serialVersionUID = -5144911560131497106L;

	private String name;

	private int age;
	
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

}
