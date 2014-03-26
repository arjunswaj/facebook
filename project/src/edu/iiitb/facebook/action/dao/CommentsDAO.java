package edu.iiitb.facebook.action.dao;


public interface CommentsDAO {  
  public int addCommentForPost(int userId, int postId, String comment);  
}
