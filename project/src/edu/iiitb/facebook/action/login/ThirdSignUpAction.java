package edu.iiitb.facebook.action.login;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import javax.naming.NamingException;

import org.apache.struts2.interceptor.SessionAware;

import edu.iiitb.facebook.action.dao.UserDAO;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.User;

public class ThirdSignUpAction extends ActionSupport implements SessionAware,
		ServletRequestAware {

	String profileContentType;
	String coverContentType;
	private String profileFileName;
	private String coverFileName;
	private File profile;
	private File cover;
	File fileToCreate;

	public String getProfileContentType() {
		return profileContentType;
	}

	public void setProfileContentType(String profileContentType) {
		this.profileContentType = profileContentType;
	}

	public String getCoverContentType() {
		return coverContentType;
	}

	public void setCoverContentType(String coverContentType) {
		this.coverContentType = coverContentType;
	}

	public String getProfileFileName() {
		return profileFileName;
	}

	public void setProfileFileName(String profileFileName) {
		this.profileFileName = profileFileName;
	}

	public String getCoverFileName() {
		return coverFileName;
	}

	public void setCoverFileName(String coverFileName) {
		this.coverFileName = coverFileName;
	}

	public File getProfile() {
		return profile;
	}

	public void setProfile(File profile) {
		this.profile = profile;
	}

	public File getCover() {
		return cover;
	}

	public void setCover(File cover) {
		this.cover = cover;
	}

	private HttpServletRequest servletRequest;

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;

	}

	private Map<String, Object> session;

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	UserDAO dao = new UserDAOImpl();
	User user = new User();

	public String execute() throws NamingException, FileNotFoundException {
		user = (User) session.get("user");
		/*
		 * if (user.getCurrentProfilePic() == null && user.getCurrentCoverPic()
		 * == null) { addFieldError("current_profile_pic",
		 * "ADD current_profile_pic then press proceed");
		 * addFieldError("current_cover_pic",
		 * "ADD current_cover_pic then press proceed"); return ERROR; } else if
		 * (user.getCurrentProfilePic() == null) {
		 * addFieldError("current_profile_pic",
		 * "ADD current_profile_pic then press proceed"); return ERROR; } else
		 * if (user.getCurrentCoverPic() == null) {
		 * addFieldError("current_cover_pic",
		 * "ADD current_cover_pic then press proceed"); return ERROR; } else
		 */
		return SUCCESS;

	}

	public String profile() throws NamingException, FileNotFoundException {
		user = (User) session.get("user");
		try {
		      String destpath = servletRequest.getSession().getServletContext()
		              .getRealPath("/");
		          System.out.println("Server path:" + destpath);
		          File destFile = new File(destpath, profileFileName);

		          try {
		            FileUtils.copyFile(profile, destFile);

		          } catch (IOException e) {
		            e.printStackTrace();
		            return ERROR;
		          }
		          FileInputStream inputStream = new FileInputStream(destFile);
			// System.out.println("in input Stream :" + inputStream);
			System.out.println("place4 in 3rdsignup");
			user.setCurrentProfilePic(inputStream);
			dao.setProfileImageByUserId(user.getUserId(), inputStream);
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
			return INPUT;
		}
		session.put("user", user);
		return SUCCESS;
	}

	public String cover() throws NamingException, FileNotFoundException {
		user = (User) session.get("user");
		try {
		      String destpath = servletRequest.getSession().getServletContext()
		              .getRealPath("/");
		          System.out.println("Server path:" + destpath);
		          File destFile = new File(destpath, coverFileName);

		          try {
		            FileUtils.copyFile(cover, destFile);

		          } catch (IOException e) {
		            e.printStackTrace();
		            return ERROR;
		          }
		          FileInputStream inputStream = new FileInputStream(destFile);
			// System.out.println("in input Stream :" + inputStream);
			System.out.println("place4 in 3rdsignup");
			user.setCurrentCoverPic(inputStream);
			dao.setCoverImageByUserId(user.getUserId(), inputStream);
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
			return INPUT;
		}
		session.put("user", user);
		return SUCCESS;
	}

}
