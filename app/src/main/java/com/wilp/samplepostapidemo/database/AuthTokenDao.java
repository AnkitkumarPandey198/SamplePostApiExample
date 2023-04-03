package com.wilp.samplepostapidemo.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface AuthTokenDao {
    @Query("SELECT * FROM auth_token LIMIT 1")
    AuthToken getAuthToken();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAuthToken(AuthToken authToken);

    @Query("DELETE FROM auth_token")
    void deleteAuthToken();
}

