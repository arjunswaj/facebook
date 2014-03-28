package edu.iiitb.facebook.action.dao;

import edu.iiitb.facebook.action.model.User;

public interface UserDAO
{

	String ID = "id";
	String EMAIL = "email";
	String PASSWORD = "password";
	String DOB = "dob";
	String PHONE_NUMBER = "phone_number";
	String FIRST_NAME = "first_name";
	String LAST_NAME = "last_name";
	String PLACE = "place";
	String CURRENT_PROFILE_PIC = "current_profile_pic";
	String CURRENT_COVER_PIC = "current_cover_pic";
	String SECRET_QUESTION = "secret_question";
	String SECRET_ANSWER = "secret_answer";
	String CREATED = "created";
	

	User getUserImageByUserId(int userId);

	User getUserByUserId(int userId);
}
