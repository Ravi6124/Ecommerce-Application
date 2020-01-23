package com.example.ecom.loginActivity.modules;

import com.google.gson.annotations.SerializedName;

public class AccessTokenDTO {

    @SerializedName("accessToken")
    String accessToken;

    public AccessTokenDTO(String accessToken) {
        this.accessToken = accessToken;
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
