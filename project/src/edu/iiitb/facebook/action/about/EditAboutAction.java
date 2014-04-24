package edu.iiitb.facebook.action.about;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.User;

public class EditAboutAction extends ActionSupport implements SessionAware, RequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User userInfo = new User();
	private Map<String, Object> session;
	private Map<String, Object> request;
	public User getUserInfo() {
		return userInfo;
	}



	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}



	public String getFref() {
		return fref;
	}



	public void setFref(String fref) {
		this.fref = fref;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public Map<String, Object> getSession() {
		return session;
	}



	public Map<String, Object> getRequest() {
		return request;
	}



	private String fref;
	
	

	public String execute()
	{
		User user = (User) session.get("user");
		UserDAO tmp = new UserDAOImpl();
		fref = (String) session.get("profileReference");
		if(fref==null){
			fref=user.getUserId()+"";
		}
		System.out.println("Friend reference:" + fref);
		User currentuser = tmp.getUserByUserId(Integer.parseInt(fref));
		userInfo.setDob(currentuser.getDob());
		userInfo.setRelationship(currentuser.getRelationship());
		userInfo.setGender(currentuser.getGender());
		userInfo.setNativeplace(currentuser.getNativeplace());
		userInfo.setPlace(currentuser.getPlace());
		return SUCCESS;
	}



	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
		
	}



	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}

}
