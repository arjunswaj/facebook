package edu.iiitb.facebook.action.model;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;

public class User
{
	private int userId;
	private String email;
	private String password;
	private String date;
	private Date dob;
	private String phoneNumber;
	private String firstName;
	private String lastName;
	private InputStream currentProfilePic;
	private InputStream currentCoverPic;
	private String secretQuestion;
	private String secretAnswer;
	private String place;
	private String profession;
	private String nativeplace;
	private String relationship;
	private String gender;
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public Timestamp getCreated() {
		return created;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getNativeplace() {
		return nativeplace;
	}

	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
	}

	private Timestamp created;
		
	public User(int userId, String firstName, String lastName) {
    super();
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public User(String email, String password)
	{
		super();
		this.email = email;
		this.password = password;
		
	}

	public String getPlace()
	{
		return place;
	}

	public User()
	{

	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public Date getDob()
	{
		return dob;
	}

	public void setDob(Date dob)
	{
		this.dob = dob;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public InputStream getCurrentProfilePic()
	{
		return currentProfilePic;
	}

	public void setCurrentProfilePic(InputStream currentProfilePic)
	{
		this.currentProfilePic = currentProfilePic;
	}

	public InputStream getCurrentCoverPic()
	{
		return currentCoverPic;
	}

	public void setCurrentCoverPic(InputStream currentCoverPic)
	{
		this.currentCoverPic = currentCoverPic;
	}

	public String getSecretQuestion()
	{
		return secretQuestion;
	}

	public void setSecretQuestion(String secretQuestion)
	{
		this.secretQuestion = secretQuestion;
	}

	public String getSecretAnswer()
	{
		return secretAnswer;
	}

	public void setSecretAnswer(String secretAnswer)
	{
		this.secretAnswer = secretAnswer;
	}

	public void setPlace(String place)
	{
		this.place = place;
	}

	public Timestamp getCreatedOn()
	{
		return created;
	}

	public void setCreated(Timestamp created)
	{
		this.created = created;
	}
}
