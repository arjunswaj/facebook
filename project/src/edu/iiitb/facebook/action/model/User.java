package edu.iiitb.facebook.action.model;

import java.io.InputStream;
import java.util.Date;

public class User {

  private int userId;
  private String email;
  private String password;
  private Date dob;
  private String phoneNumber;
  private String firstName;
  private String lastName;
  private InputStream currentProfilePic;
  private InputStream currentCoverPic;
  private String secretQuestion;
  private String secretAnswer;

  public User() {

  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public InputStream getCurrentProfilePic() {
    return currentProfilePic;
  }

  public void setCurrentProfilePic(InputStream currentProfilePic) {
    this.currentProfilePic = currentProfilePic;
  }

  public InputStream getCurrentCoverPic() {
    return currentCoverPic;
  }

  public void setCurrentCoverPic(InputStream currentCoverPic) {
    this.currentCoverPic = currentCoverPic;
  }

  public String getSecretQuestion() {
    return secretQuestion;
  }

  public void setSecretQuestion(String secretQuestion) {
    this.secretQuestion = secretQuestion;
  }

  public String getSecretAnswer() {
    return secretAnswer;
  }

  public void setSecretAnswer(String secretAnswer) {
    this.secretAnswer = secretAnswer;
  }

}
