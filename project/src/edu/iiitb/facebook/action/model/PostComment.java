package edu.iiitb.facebook.action.model;

public class PostComment {
  private int commentId;
  private int commenterUserId;
  private String commenterFirstName;
  private String commenterLastName;
  private String commentText;
  private String commentTime;
  private int likeCount;
  private boolean haveILiked;

  public PostComment(int commentId, int commenterUserId,
      String commenterFirstName, String commenterLastName, String commentText,
      String commentTime, int likeCount, boolean haveILiked) {
    super();
    this.commentId = commentId;
    this.commenterUserId = commenterUserId;
    this.commenterFirstName = commenterFirstName;
    this.commenterLastName = commenterLastName;
    this.commentText = commentText;
    this.commentTime = commentTime;
    this.likeCount = likeCount;
    this.haveILiked = haveILiked;
  }

  public int getCommentId() {
    return commentId;
  }

  public void setCommentId(int commentId) {
    this.commentId = commentId;
  }

  public int getCommenterUserId() {
    return commenterUserId;
  }

  public void setCommenterUserId(int commenterUserId) {
    this.commenterUserId = commenterUserId;
  }

  public String getCommenterFirstName() {
    return commenterFirstName;
  }

  public void setCommenterFirstName(String commenterFirstName) {
    this.commenterFirstName = commenterFirstName;
  }

  public String getCommenterLastName() {
    return commenterLastName;
  }

  public void setCommenterLastName(String commenterLastName) {
    this.commenterLastName = commenterLastName;
  }

  public String getCommentText() {
    return commentText;
  }

  public void setCommentText(String commentText) {
    this.commentText = commentText;
  }

  public String getCommentTime() {
    return commentTime;
  }

  public void setCommentTime(String commentTime) {
    this.commentTime = commentTime;
  }

  public int getLikeCount() {
    return likeCount;
  }

  public void setLikeCount(int likeCount) {
    this.likeCount = likeCount;
  }

  public boolean isHaveILiked() {
    return haveILiked;
  }

  public void setHaveILiked(boolean haveILiked) {
    this.haveILiked = haveILiked;
  }
}
