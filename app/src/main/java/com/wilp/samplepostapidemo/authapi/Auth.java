package com.wilp.samplepostapidemo.authapi;

import com.google.gson.annotations.SerializedName;

public class Auth {
    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("profile_image")
    private String profileImage;

    @SerializedName("_token")
    private String token;

    @SerializedName("created_at")
    private String created_at;

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getToken() {
        return token;
    }

    public String getCreated_at() {
        return created_at;
    }
}

