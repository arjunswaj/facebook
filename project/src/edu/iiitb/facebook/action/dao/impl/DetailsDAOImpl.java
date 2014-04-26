package edu.iiitb.facebook.action.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import edu.iiitb.facebook.action.dao.DetailsDAO;
import edu.iiitb.facebook.action.model.InstituteDetails;
import edu.iiitb.facebook.action.model.OrganizationDetails;
import edu.iiitb.facebook.util.ConnectionPool;
public class DetailsDAOImpl implements DetailsDAO{
	List<OrganizationDetails> organization_details=new ArrayList<OrganizationDetails>();
	List<InstituteDetails> institute_details=new ArrayList<InstituteDetails>();
	
	public void setUserDetails(String relationship,String nativeplace,String place,Date dob,String gender,int id)
	{
		final String query = "update user set relationship=?,place=?,nativeplace=?,gender=?,dob=? where id=?";
		Connection connection = ConnectionPool.getConnection();
		
		try {
			PreparedStatement stmt=connection.prepareStatement(query);
			stmt.setString(1,relationship);
			stmt.setString(2, place);
			stmt.setString(3, nativeplace);
			stmt.setString(4, gender);
			stmt.setDate(5, (java.sql.Date) dob);
			stmt.setInt(6, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public List<OrganizationDetails> getOrganizationDetailsForUser(int userId) {
		// TODO Auto-generated method stub
		final String query = "select working_from,working_to,name,position from organization where user_id=?";
		Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt;
		try
		{
			stmt = connection.prepareStatement(query);
			stmt.setInt(1,userId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				OrganizationDetails org =new OrganizationDetails();
				org.setName(rs.getString("name"));
				org.setPosition(rs.getString("position"));
				org.setWorking_from(rs.getDate("working_from"));
				try
				{
				org.setWorking_to(rs.getDate("working_to"));
				}
				catch(Exception e)
				{
				org.setWorking_to(null);
				}
				organization_details.add(org);
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionPool.freeConnection(connection);
		}
	return organization_details;
	
	}

	@Override
	public List<InstituteDetails> getInstituteDetailsForUser(int userId) {
		// TODO Auto-generated method stub
		final String query = "select working_from,working_to,has_graduated,description,name from institute where user_id=?";
		Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt;
		try
		{
			stmt = connection.prepareStatement(query);
			stmt.setInt(1,userId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				InstituteDetails inst =new InstituteDetails();
				inst.setDescription(rs.getString("description"));
				inst.setWorking_from(rs.getDate("working_from"));
				try
				{
				inst.setWorking_to(rs.getDate("working_to"));
				}
				catch(Exception e)
				{
					inst.setWorking_to(null);
				}
				inst.setHas_graduated(rs.getInt("has_graduated"));
				inst.setName(rs.getString("name"));
				institute_details.add(inst);
			}
			Collections.sort(institute_details);
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionPool.freeConnection(connection);
		}
	return institute_details;
	
	
	}
	
	public void setInstituteDetailsForUser(int userId,Date startdate,Date enddate,int status,String description,String name) {
		// TODO Auto-generated method stub
		final String query = "insert into institute(working_from,working_to,has_graduated,description,name,user_id) values(?,?,?,?,?,?)";
		Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt;
		try
		{
			stmt = connection.prepareStatement(query);
			stmt.setDate(1,(java.sql.Date)startdate);
			stmt.setDate(2,(java.sql.Date)enddate);
			stmt.setInt(3, status);
			stmt.setString(4, description);
			stmt.setString(5,name);
			stmt.setInt(6,userId);
			int rs = stmt.executeUpdate();
			
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionPool.freeConnection(connection);
		}
	}
	public void setOrganizationDetailsForUser(int userId,Date startdate,Date enddate,String name,String position) {
		// TODO Auto-generated method stub
		final String query = "insert into organization(working_from,working_to,name,position,user_id) values(?,?,?,?,?)";
		Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt;
		try
		{
			stmt = connection.prepareStatement(query);
			stmt.setDate(1,(java.sql.Date)startdate);
			stmt.setDate(2,(java.sql.Date) enddate);
			stmt.setString(3,name);
			stmt.setString(4,position);
			stmt.setInt(5,userId);
			int rs = stmt.executeUpdate();
			
					
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionPool.freeConnection(connection);
		}
	
	
	}

	@Override
	public void setRelationshipForUser(int userId, String relationship) {
		// TODO Auto-generated method stub
		final String query = "update user set relationship=? where id=?";
		Connection connection = ConnectionPool.getConnection();
		
		try {
			PreparedStatement stmt=connection.prepareStatement(query);
			stmt.setString(1,relationship);
			stmt.setInt(2, userId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setLocationDetailsForUser(int userId, String hometown,
			String currentcity) {
		// TODO Auto-generated method stub
		final String query = "update user set place=?,nativeplace=? where id=?";
		Connection connection = ConnectionPool.getConnection();
		
		try {
			PreparedStatement stmt=connection.prepareStatement(query);
			stmt.setString(1,currentcity);
			stmt.setString(2, hometown);
			stmt.setInt(3,userId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void setDOBGenderForUser(int userId, java.sql.Date dob, String gender) {
		// TODO Auto-generated method stub
		final String query = "update user set dob=?,gender=? where id=?";
		Connection connection = ConnectionPool.getConnection();
		
		try {
			PreparedStatement stmt=connection.prepareStatement(query);
			stmt.setDate(1,dob);
			stmt.setString(2,gender);
			stmt.setInt(3, userId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
