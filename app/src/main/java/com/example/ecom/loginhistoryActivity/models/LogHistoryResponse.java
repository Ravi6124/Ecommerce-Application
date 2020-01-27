package com.example.ecom.loginhistoryActivity.models;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class LogHistoryResponse{

	@SerializedName("loginId")
	private int loginId;

	@SerializedName("loginTime")
	private String loginTime;

	@SerializedName("type")
	private String type;

	@SerializedName("userId")
	private int userId;

	@SerializedName("loginSource")
	private String loginSource;

	public void setLoginId(int loginId){
		this.loginId = loginId;
	}

	public int getLoginId(){
		return loginId;
	}

	public void setLoginTime(String loginTime){
		this.loginTime = loginTime;
	}

	public String getLoginTime(){
		return loginTime;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setLoginSource(String loginSource){
		this.loginSource = loginSource;
	}

	public String getLoginSource(){
		return loginSource;
	}

	@Override
 	public String toString(){
		return 
			"LogHistoryResponse{" + 
			"loginId = '" + loginId + '\'' + 
			",loginTime = '" + loginTime + '\'' + 
			",type = '" + type + '\'' + 
			",userId = '" + userId + '\'' + 
			",loginSource = '" + loginSource + '\'' + 
			"}";
		}
}