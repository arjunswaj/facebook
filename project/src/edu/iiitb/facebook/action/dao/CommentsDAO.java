package edu.iiitb.facebook.action.dao;

import java.util.List;

import edu.iiitb.facebook.action.model.User;

public interface CommentsDAO {
  public int addCommentForPost(int userId, int postId, String comment);

  public void likeAComment(int commentId, int userId);

  public int updateLikersCount(int commentId);

  public void unlikeAComment(int commentId, int userId);

  public List<User> peopleWholikeTheComment(int commentId);
  
  public void updateComment(int commentId, String updatedComment);
  
  public void deleteComment(int commentId);
}
