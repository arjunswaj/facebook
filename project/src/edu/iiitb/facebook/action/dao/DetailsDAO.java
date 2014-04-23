package edu.iiitb.facebook.action.dao;
import java.util.*;
import edu.iiitb.facebook.action.model.*;

public interface DetailsDAO {
public List<OrganizationDetails> getOrganizationDetailsForUser(int userId);
public List<InstituteDetails> getInstituteDetailsForUser(int userId);
}
