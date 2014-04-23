package edu.iiitb.facebook.action.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import edu.iiitb.facebook.action.dao.*;
import edu.iiitb.facebook.action.model.InstituteDetails;
import edu.iiitb.facebook.action.model.OrganizationDetails;
import edu.iiitb.facebook.util.ConnectionPool;
public class DetailsDAOImpl implements DetailsDAO{
	List<OrganizationDetails> organization_details=new ArrayList<OrganizationDetails>();
	List<InstituteDetails> institute_details=new ArrayList<InstituteDetails>();
	
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

}
