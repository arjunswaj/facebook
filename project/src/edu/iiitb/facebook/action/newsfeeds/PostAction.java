package edu.iiitb.facebook.action.newsfeeds;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.PostsDAO;
import edu.iiitb.facebook.action.dao.impl.PostsDAOImpl;

public class PostAction extends ActionSupport implements SessionAware
{
	Map<String, Object> session;

	String value;

	String postId;

	String postText;

	public String getPostId()
	{
		return postId;
	}

	public void setPostId(String postId)
	{
		this.postId = postId;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	@Override
	public void setSession(Map<String, Object> arg0)
	{

		this.session = arg0;
	}

	public String execute()
	{

		PostsDAO dao = new PostsDAOImpl();
		System.out.println("Delete Post");
		System.out.println("Delete Post2");

		int result = dao.deletePost(postId);
		if (result > 0)
		{
			setValue("true");
			//System.out.println("true");
		}
		else
		{
			setValue("false");
			//System.out.println("false");
		}

		return SUCCESS;
	}

	public String editPost()
	{

		PostsDAO dao = new PostsDAOImpl();

		int result = dao.updatePost(postId, postText);
		if (result > 0)
		{
			setValue("true");
			//System.out.println("true");
		}
		else
		{
			setValue("false");
			//System.out.println("false");
		}

		return SUCCESS;
	}

	public String getPost()
	{

		PostsDAO dao = new PostsDAOImpl();

		setPostText(dao.getText(postId));

		return SUCCESS;
	}

	public String getPostText()
	{
		return postText;
	}

	public void setPostText(String postText)
	{
		this.postText = postText;
	}

	public Map<String, Object> getSession()
	{
		return session;
	}
}