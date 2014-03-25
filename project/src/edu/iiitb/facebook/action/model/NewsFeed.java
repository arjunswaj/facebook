package edu.iiitb.facebook.action.model;

import java.util.ArrayList;
import java.util.Date;
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

  private Date updatedTime;

  private List<PostComment> postComments = new ArrayList<PostComment>();

  public NewsFeed(int postId, int fromUserId, String fromUserFirstName,
      String fromUserLastName, int toUserId, String toUserFirstName,
      String toUserLastName, String postText, String postType,
      Date updatedTime) {
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

  public Date getUpdatedTime() {
    return updatedTime;
  }

  public void setUpdatedTime(Date updatedTime) {
    this.updatedTime = updatedTime;
  }

  public List<PostComment> getPostComments() {
    return postComments;
  }

  public void setPostComments(List<PostComment> postComments) {
    this.postComments = postComments;
  }

}
