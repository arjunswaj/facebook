package edu.iiitb.facebook.action.dao;

import java.util.List;

import edu.iiitb.facebook.action.model.NewsFeed;
import edu.iiitb.facebook.action.model.User;

public interface PostsDAO
{
	public List<NewsFeed> getNewsFeedsForUser(int userId);

	public int updateStatusForUser(int userId, String status);
	
	public int updatewallpostForUser(int userId,int refuserId,String wallpost);
	
	public void likeAPost(int postId, int userId);
	
	public int updateLikersCount(int postId);
	
	public void unlikeAPost(int postId, int userId);
	
	public List<User> peopleWholikeThePost(int postId);

	public int deletePost(String postId);

	public int updatePost(String postId, String updatedText);

	public  String getText(String postId);
}
