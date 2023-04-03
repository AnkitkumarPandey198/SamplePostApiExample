package com.wilp.samplepostapidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    TextView titleName,userFirstName,userLastName,userCreatedAt,userEmail;
    ImageView profileImg;
    Button showMoreDetails;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        titleName = findViewById(R.id.titleName);
        userFirstName = findViewById(R.id.profileFirstName);
        userCreatedAt = findViewById(R.id.profileCreatedAt);
        userLastName = findViewById(R.id.profileLastName);
        userEmail = findViewById(R.id.profileEmail);
        profileImg = findViewById(R.id.profileImg);
        showMoreDetails = findViewById(R.id.detailsButton);
        showProfileData();
        showMoreDetails.setOnClickListener(v -> {

            Intent intent = new Intent(ProfileActivity.this,DetailsActivity.class);
            startActivity(intent);

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