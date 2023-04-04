package com.wilp.samplepostapidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wilp.samplepostapidemo.database.MyDatabase;

import java.util.Objects;


public class ProfileActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "shared_prefs";
    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    String email;

    TextView titleName,userFirstName,userLastName,userCreatedAt,userEmail;
    ImageView profileImg;
    Button showMoreDetails,logoutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Objects.requireNonNull(getSupportActionBar()).hide();

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // getting data from shared prefs and
        // storing it in our string variable.
        email = sharedpreferences.getString("EMAIL_KEY", null);

        titleName = findViewById(R.id.titleName);
        userFirstName = findViewById(R.id.profileFirstName);
        userCreatedAt = findViewById(R.id.profileCreatedAt);
        userLastName = findViewById(R.id.profileLastName);
        userEmail = findViewById(R.id.profileEmail);
        profileImg = findViewById(R.id.profileImg);
        showMoreDetails = findViewById(R.id.detailsButton);
        logoutButton = findViewById(R.id.logoutButton);

        showProfileData();
        showMoreDetails.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this,DetailsActivity.class);
            startActivity(intent);

        });

        logoutButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.apply();
            Intent i = new Intent(ProfileActivity.this, MainActivity.class);
            MyDatabase.getInstance(ProfileActivity.this).authTokenDao().deleteAuthToken();
            startActivity(i);
            finish();
        });


    }

    void showProfileData(){

        String firstName = getIntent().getStringExtra("firstName");
        String lastName = getIntent().getStringExtra("lastName");
        String email = getIntent().getStringExtra("email");
        String profileImage = getIntent().getStringExtra("profileImage");
        String created_at = getIntent().getStringExtra("created_at");

        String greetName = "Hello "+ firstName;

        titleName.setText(greetName);
        userFirstName.setText(firstName);
        userCreatedAt.setText(created_at);
        userLastName.setText(lastName);
        userEmail.setText(email);
        Glide.with(ProfileActivity.this).load(profileImage).into(profileImg);


    }

}