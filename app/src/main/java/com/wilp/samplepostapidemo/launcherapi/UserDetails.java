package com.wilp.samplepostapidemo.launcherapi;

import com.google.gson.annotations.SerializedName;

public class UserDetails {


    public UserDetails(String email, String firstName, String lastName, String createdAt, String roles) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = createdAt;
        this.roles = roles;
    }

    @SerializedName("email")
    private final String email;

    @SerializedName("first_name")
    private final String firstName;

    @SerializedName("last_name")
    private final String lastName;

    @SerializedName("created_at")
    private final String createdAt;

    @SerializedName("roles")
    private final String roles;

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getRoles() {
        return roles;
    }
}
