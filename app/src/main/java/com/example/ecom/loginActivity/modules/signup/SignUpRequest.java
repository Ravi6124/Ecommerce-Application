package com.example.ecom.loginActivity.modules.signup;

import com.google.gson.annotations.SerializedName;

public class SignUpRequest{
	@SerializedName("emailAddress")
	private String emailAddress;
	@SerializedName("password")
	private String password;
	@SerializedName("role")
	private String role;

	public SignUpRequest(String emailAddress, String password, String role) {
		this.emailAddress = emailAddress;
		this.password = password;
		this.role = role;
	}

	public void setEmailAddress(String emailAddress){
		this.emailAddress = emailAddress;
	}

	public String getEmailAddress(){
		return emailAddress;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

	@Override
 	public String toString(){
		return 
			"SignUpRequest{" + 
			"emailAddress = '" + emailAddress + '\'' + 
			",password = '" + password + '\'' + 
			",role = '" + role + '\'' + 
			"}";
		}
}
