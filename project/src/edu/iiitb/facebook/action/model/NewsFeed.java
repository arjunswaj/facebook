package edu.iiitb.facebook.action.model;

import java.util.ArrayList;
import java.util.List;

public class NewsFeed {
  private int postId;
  private int fromUserId;
  private String fromUserFirstName;
  private String fromUserLastName;

  private int toUserId;
  private String toUserFirstName;
  private String toUserLastName;

  private String postText;
  private String postType;

  private String updatedTime;
  private int likeCount;
  private boolean haveILiked;

  private List<PostComment> postComments = new ArrayList<PostComment>();

  public NewsFeed(int postId, int fromUserId, String fromUserFirstName,
      String fromUserLastName, int toUserId, String toUserFirstName,
      String toUserLastName, String postText, String postType,
      String updatedTime, int likeCount, boolean haveILiked) {
    super();
    this.postId = postId;
    this.fromUserId = fromUserId;
    this.fromUserFirstName = fromUserFirstName;
    this.fromUserLastName = fromUserLastName;
    this.toUserId = toUserId;
    this.toUserFirstName = toUserFirstName;
    this.toUserLastName = toUserLastName;
    this.postText = postText;
    this.postType = postType;
    this.updatedTime = updatedTime;
    this.likeCount = likeCount;
    this.haveILiked = haveILiked;
  }

  public int getPostId() {
    return postId;
  }

  public void setPostId(int postId) {
    this.postId = postId;
  }

  public int getFromUserId() {
    return fromUserId;
  }

  public void setFromUserId(int fromUserId) {
    this.fromUserId = fromUserId;
  }

  public String getFromUserFirstName() {
    return fromUserFirstName;
  }

  public void setFromUserFirstName(String fromUserFirstName) {
    this.fromUserFirstName = fromUserFirstName;
  }

  public String getFromUserLastName() {
    return fromUserLastName;
  }

  public void setFromUserLastName(String fromUserLastName) {
    this.fromUserLastName = fromUserLastName;
  }

  public int getToUserId() {
    return toUserId;
  }

  public void setToUserId(int toUserId) {
    this.toUserId = toUserId;
  }

  public String getToUserFirstName() {
    return toUserFirstName;
  }

  public void setToUserFirstName(String toUserFirstName) {
    this.toUserFirstName = toUserFirstName;
  }

  public String getToUserLastName() {
    return toUserLastName;
  }

  public void setToUserLastName(String toUserLastName) {
    this.toUserLastName = toUserLastName;
  }

  public String getPostText() {
    return postText;
  }

  public void setPostText(String postText) {
    this.postText = postText;
  }

  public String getPostType() {
    return postType;
  }

  public void setPostType(String postType) {
    this.postType = postType;
  }

  public String getUpdatedTime() {
    return updatedTime;
  }

  public void setUpdatedTime(String updatedTime) {
    this.updatedTime = updatedTime;
  }

  public List<PostComment> getPostComments() {
    return postComments;
  }

  public void setPostComments(List<PostComment> postComments) {
    this.postComments = postComments;
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
