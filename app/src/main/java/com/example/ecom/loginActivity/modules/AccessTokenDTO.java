package com.example.ecom.loginActivity.modules;

import com.google.gson.annotations.SerializedName;

public class AccessTokenDTO {

    @SerializedName("accessToken")
    String accessToken;

    @SerializedName("role")
    String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public AccessTokenDTO(String accessToken, String role) {
        this.accessToken = accessToken;
        this.role = role;
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
