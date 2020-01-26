package com.example.ecom.loginActivity.modules.login;

public class LoginRequest{
	private String emailAddress;
	private String password;
	private String role;
	private String guestId;
	private String type;
	private String loginSource;

	{
		type = "android";
		loginSource = "local";
	}

	public LoginRequest(String emailAddress, String password, String role, String guestId) {
		this.emailAddress = emailAddress;
		this.password = password;
		this.role = role;
		this.guestId = guestId;
	}

	public String getGuestId() {
		return guestId;
	}

	public void setGuestId(String guestId) {
		this.guestId = guestId;
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
			"LoginRequest{" + 
			"emailAddress = '" + emailAddress + '\'' + 
			",password = '" + password + '\'' + 
			",role = '" + role + '\'' + 
			"}";
		}
}
