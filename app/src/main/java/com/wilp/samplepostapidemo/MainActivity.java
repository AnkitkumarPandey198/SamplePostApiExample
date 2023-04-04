package com.wilp.samplepostapidemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wilp.samplepostapidemo.authapi.Auth;
import com.wilp.samplepostapidemo.authapi.AuthApi;
import com.wilp.samplepostapidemo.database.AuthToken;
import com.wilp.samplepostapidemo.database.MyDatabase;

import java.util.HashMap;
import java.util.Objects;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";
    // key for storing email.
    public static final String EMAIL_KEY = "email_key";
    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";
    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    String email, password;

    EditText emailEditText,passwordEditText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();

        emailEditText = findViewById(R.id.login_email);
        passwordEditText = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        email = sharedpreferences.getString("EMAIL_KEY", null);
        password = sharedpreferences.getString("PASSWORD_KEY", null);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://staging.greytrunk.cloudzmall.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient().newBuilder().build())
                .build();

        // Create the API endpoint instance
        AuthApi authApi = retrofit.create(AuthApi.class);

        loginButton.setOnClickListener(v -> {

            String userEmail = emailEditText.getText().toString().trim();
            String userPassword = passwordEditText.getText().toString().trim();

            // Make the API call
            HashMap<String, String> body = new HashMap<>();
            body.put("email", userEmail);
            body.put("password", userPassword);

            Call<Auth> call = authApi.authenticate("android",
                    "IqJit4XEaiM71D2tBPauHG6dS78BfuMLQuJVZiwsw6Y=",
                    "application/json", body);

            call.enqueue(new Callback<Auth>() {
                @Override
                public void onResponse(@NonNull Call<Auth> call, @NonNull Response<Auth> response) {

                    if (response.isSuccessful()) {
                        // Get the data from the response and display it in the UI
                        Auth auth = response.body();
//                        Log.e("API CALL","Hello! " + Objects.requireNonNull(auth).getFirstName());

                        String firstName = Objects.requireNonNull(auth).getFirstName();
                        String lastName = auth.getLastName();
                        String email = auth.getEmail();
                        String profileImage = auth.getProfileImage();
                        String created_at = auth.getCreated_at();

                        // Getting and Saving Token In Database
                        String AuthToken = "berer " + auth.getToken();
                        MyDatabase.getInstance(MainActivity.this).authTokenDao().saveAuthToken(new AuthToken(AuthToken));

                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(EMAIL_KEY, emailEditText.getText().toString().trim());
                        editor.putString(PASSWORD_KEY, passwordEditText.getText().toString().trim());
                        editor.apply();

                        Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                        intent.putExtra("firstName",firstName);
                        intent.putExtra("lastName",lastName);
                        intent.putExtra("email",email);
                        intent.putExtra("profileImage",profileImage);
                        intent.putExtra("created_at",created_at);
                        startActivity(intent);
                        finish();
                        Toast.makeText(MainActivity.this, "User is LoggedIN", Toast.LENGTH_SHORT).show();


                    } else {
                        // Handle error response
                        Toast.makeText(MainActivity.this, "ERROR! Api Auth Not getting data", Toast.LENGTH_SHORT).show();
                        Log.e("API CALL","ERROR! Api Auth Not getting data");
                    }

                }
                @Override
                public void onFailure(@NonNull Call<Auth> call, @NonNull Throwable t) {

                    Toast.makeText(MainActivity.this, "ERROR! Api Auth Not Working and Is Wrong", Toast.LENGTH_SHORT).show();
                    Log.e("API CALL","ERROR! Api Auth Not Working and Is Wrong");

                }
            });
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (email != null && password != null) {
            Intent i = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(i);
        }
    }
}