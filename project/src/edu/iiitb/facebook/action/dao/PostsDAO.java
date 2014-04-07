package edu.iiitb.facebook.action.dao;

import java.util.List;

import edu.iiitb.facebook.action.model.NewsFeed;

public interface PostsDAO
{
	public List<NewsFeed> getNewsFeedsForUser(int userId);

	public int updateStatusForUser(int userId, String status);

	public int deletePost(String postId);

	public int updatePost(String postId, String updatedText);
}
