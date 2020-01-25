package com.example.ecom.loginActivity.modules;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class LoginResponse{

	@SerializedName("emailAddress")
	private String emailAddress;

	@SerializedName("message")
	private String message;

	@SerializedName("accessToken")
	private String accessToken;

	@SerializedName("userId")
	private String userId;

	@SerializedName("guestId")
	private String guestId;

	@SerializedName("statusCode")
	private int statusCode;

	public void setEmailAddress(String emailAddress){
		this.emailAddress = emailAddress;
	}

	public String getEmailAddress(){
		return emailAddress;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setGuestId(String guestId){
		this.guestId = guestId;
	}

	public String getGuestId(){
		return guestId;
	}

	public void setStatusCode(int statusCode){
		this.statusCode = statusCode;
	}

	public int getStatusCode(){
		return statusCode;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponse{" + 
			"emailAddress = '" + emailAddress + '\'' + 
			",message = '" + message + '\'' + 
			",accessToken = '" + accessToken + '\'' + 
			",userId = '" + userId + '\'' + 
			",guestId = '" + guestId + '\'' + 
			",statusCode = '" + statusCode + '\'' + 
			"}";
		}
}