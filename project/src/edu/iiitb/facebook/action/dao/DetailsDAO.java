package edu.iiitb.facebook.action.dao;
import java.util.*;

import edu.iiitb.facebook.action.model.*;

public interface DetailsDAO {
public List<OrganizationDetails> getOrganizationDetailsForUser(int userId);
public List<InstituteDetails> getInstituteDetailsForUser(int userId);
public void setUserDetails(String relationship,String nativeplace,String place,Date dob,String gender,int id);
public void setInstituteDetailsForUser(int userId,Date startdate,Date enddate,int status,String description,String name);
public void setOrganizationDetailsForUser(int userId,Date startdate,Date enddate,String name,String position);
public void setRelationshipForUser(int userId,String relationship);
public void setLocationDetailsForUser(int userId,String hometown,String currentcity);
public void setDOBGenderForUser(int userId,java.sql.Date dob,String gender);
}
