package com.example.ecom.loginActivity.modules.signup;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class Data{

	@SerializedName("emailAddress")
	private String emailAddress;

	@SerializedName("password")
	private String password;

	@SerializedName("role")
	private String role;

	@SerializedName("userId")
	private int userId;

	public Data(String emailAddress, String password, String role, int userId) {
		this.emailAddress = emailAddress;
		this.password = password;
		this.role = role;
		this.userId = userId;
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

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"emailAddress = '" + emailAddress + '\'' + 
			",password = '" + password + '\'' + 
			",role = '" + role + '\'' +
			"}";
		}

	//@Generated("com.robohorse.robopojogenerator")
	public static class SignUpRequest{

		@SerializedName("emailAddress")
		private String emailAddress;

		@SerializedName("password")
		private String password;

		@SerializedName("role")
		private String role;

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
}