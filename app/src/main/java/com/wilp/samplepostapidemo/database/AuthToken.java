package com.wilp.samplepostapidemo.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "auth_token")
public class AuthToken {
    @PrimaryKey
    @NonNull
    private String token;

    public AuthToken(@NonNull String token) {
        this.token = token;
    }

    @NonNull
    public String getToken() {
        return token;
    }
}

