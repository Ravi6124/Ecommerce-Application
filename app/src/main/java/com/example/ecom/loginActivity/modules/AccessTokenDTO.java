package com.example.ecom.loginActivity.modules;

import com.google.gson.annotations.SerializedName;

public class AccessTokenDTO {

    @SerializedName("accessToken")
    String accessToken;

    @SerializedName("role")
    String role;

    @SerializedName("guestId")
    String guestId;

    @SerializedName("loginSource")
    String loginSource;

    @SerializedName("type")
    String type;

    {
        type = "android";
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public AccessTokenDTO(String accessToken, String role, String guestId,String loginSource) {
        this.accessToken = accessToken;
        this.role = role;
        this.guestId = guestId;
        this.loginSource = loginSource;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "AccessTokenDTO{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }
}
