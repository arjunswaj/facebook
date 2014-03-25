package edu.iiitb.facebook.action.model;

import java.util.Date;

public class NewsFeed {
  private int fromUserId;
  private String fromUserFirstName;
  private String fromUserLastName;

  private int toUserId;
  private String toUserFirstName;
  private String toUserLastName;

  private String postText;
  private String postType;

  private Date updatedTime;

  public NewsFeed(int fromUserId, String fromUserFirstName,
      String fromUserLastName, int toUserId, String toUserFirstName,
      String toUserLastName, String postText, String postType, Date updatedTime) {
    super();
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

}
