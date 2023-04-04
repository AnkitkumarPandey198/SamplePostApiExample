package com.wilp.samplepostapidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class ProfileActivity extends AppCompatActivity {

    TextView titleName,userFirstName,userLastName,userCreatedAt,userEmail;
    ImageView profileImg;
    Button showMoreDetails,logoutButton;

    String firstName ,lastName , email ,profileImage ,created_at , greetName;

    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Objects.requireNonNull(getSupportActionBar()).hide();

        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching Profile Data..");
        dialog.show();

        firstName = getIntent().getStringExtra("firstName");
        lastName = getIntent().getStringExtra("lastName");
        email = getIntent().getStringExtra("email");
        profileImage = getIntent().getStringExtra("profileImage");
        created_at = getIntent().getStringExtra("created_at");

        greetName = "Hello "+ firstName;

        titleName = findViewById(R.id.titleName);
        userFirstName = findViewById(R.id.profileFirstName);
        userCreatedAt = findViewById(R.id.profileCreatedAt);
        userLastName = findViewById(R.id.profileLastName);
        userEmail = findViewById(R.id.profileEmail);
        profileImg = findViewById(R.id.profileImg);
        showMoreDetails = findViewById(R.id.detailsButton);
        logoutButton = findViewById(R.id.logoutButton);

        showMoreDetails.setOnClickListener(v -> {
            String token = getIntent().getStringExtra("token");
            Log.e("API CALL",token);
            Intent intent = new Intent(ProfileActivity.this,DetailsActivity.class);
            intent.putExtra("auth_token",token);
            startActivity(intent);

        });

        logoutButton.setOnClickListener(v -> {
            Intent i = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        });

        showProfileData();

        Timer  timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dialog.dismiss();
                timer.cancel();

            }
        },6000,6000);


    }

    void showProfileData(){

        titleName.setText(greetName);
        userFirstName.setText(firstName);
        userCreatedAt.setText(created_at);
        userLastName.setText(lastName);
        userEmail.setText(email);
        Glide.with(ProfileActivity.this).load(profileImage).into(profileImg);

    }

}