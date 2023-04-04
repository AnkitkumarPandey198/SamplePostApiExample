package com.wilp.samplepostapidemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.wilp.samplepostapidemo.database.AuthToken;
import com.wilp.samplepostapidemo.database.MyDatabase;
import com.wilp.samplepostapidemo.launcherapi.LauncherApi;
import com.wilp.samplepostapidemo.launcherapi.LauncherApiService;

import java.util.Objects;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {

    private TextView emailTextView,firstNameTextView,lastNameTextView,createdAtTextView,rolesTextView;
    private TextView availableAssetsCountTextView, checkoutAssetsCountTextView, disposedAssetsCountTextView, lostAssetsCountTextView;
    private TextView totalAssetsCountTextView ,totalCostOfAssetsTextView ,assetCountLastYearTextView ;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Objects.requireNonNull(getSupportActionBar()).hide();

        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching data..");
        dialog.show();

        emailTextView = findViewById(R.id.email_value);
        firstNameTextView = findViewById(R.id.first_name_value);
        lastNameTextView = findViewById(R.id.last_name_value);
        createdAtTextView = findViewById(R.id.created_at_value);
        rolesTextView = findViewById(R.id.roles_value);

        availableAssetsCountTextView = findViewById(R.id.availableAssetsCountValue);
        checkoutAssetsCountTextView = findViewById(R.id.checkoutAssetsCountValue);
        disposedAssetsCountTextView = findViewById(R.id.disposedAssetsCountValue);
        lostAssetsCountTextView = findViewById(R.id.lostAssetsCountValue);

        totalAssetsCountTextView = findViewById(R.id.totalAssetsCountValue);
        totalCostOfAssetsTextView = findViewById(R.id.totalCostOfAssetsValue);
        assetCountLastYearTextView = findViewById(R.id.assetCountLastYearValue);

        AuthToken authToken = MyDatabase.getInstance(DetailsActivity.this).authTokenDao().getAuthToken();
        String token = authToken.getToken();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://staging.greytrunk.cloudzmall.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient().newBuilder().build())
                .build();


        LauncherApiService api = retrofit.create(LauncherApiService.class);

        Call<LauncherApi> call = api.getLauncherApiData("application/json",
                "android",
                "IqJit4XEaiM71D2tBPauHG6dS78BfuMLQuJVZiwsw6Y=", token);

        call.enqueue(new Callback<LauncherApi>() {
            @Override
            public void onResponse(@NonNull Call<LauncherApi> call, @NonNull Response<LauncherApi> response) {

                if(response.isSuccessful()){

                    LauncherApi launcherApi = response.body();
                    // userDetails data getting from the API
                    String firstName = Objects.requireNonNull(launcherApi).getUserDetails().getFirstName();
                    String lastName = launcherApi.getUserDetails().getLastName();
                    String email = launcherApi.getUserDetails().getEmail();
                    String roles = launcherApi.getUserDetails().getRoles();
                    String created_at = launcherApi.getUserDetails().getCreatedAt();

                    firstNameTextView.setText(firstName);
                    lastNameTextView.setText(lastName);
                    emailTextView.setText(email);
                    rolesTextView.setText(roles);
                    createdAtTextView.setText(created_at);

                    // Dashboard Data is fetched from the API

                    String availableAssetsCount = String.valueOf(launcherApi.getmDashbaordData().getAssetCountByStatus().getAvailableAssetsCount());
                    String checkoutAssetsCount = String.valueOf(launcherApi.getmDashbaordData().getAssetCountByStatus().getCheckoutAssetsCount());
                    String disposedAssetsCount = String.valueOf(launcherApi.getmDashbaordData().getAssetCountByStatus().getDisposedAssetsCount());
                    String lostAssetsCount = String.valueOf(launcherApi.getmDashbaordData().getAssetCountByStatus().getLostAssetsCount());

                    String totalAssetsCount = String.valueOf(launcherApi.getmDashbaordData().getAssetCountData().getTotalAssetsCount());
                    String totalCostOfAssets = launcherApi.getmDashbaordData().getAssetCountData().getTotalCostOfAssets();
                    String assetCountLastYear = String.valueOf(launcherApi.getmDashbaordData().getAssetCountData().getAssetCountLastYear());

                    availableAssetsCountTextView.setText(availableAssetsCount);
                    checkoutAssetsCountTextView.setText(checkoutAssetsCount);
                    disposedAssetsCountTextView.setText(disposedAssetsCount);
                    lostAssetsCountTextView.setText(lostAssetsCount);
                    totalAssetsCountTextView.setText(totalAssetsCount);
                    totalCostOfAssetsTextView.setText(totalCostOfAssets);
                    assetCountLastYearTextView.setText(assetCountLastYear);

                    dialog.dismiss();

                }else{
                    Toast.makeText(DetailsActivity.this, "Error! API is not Responding", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<LauncherApi> call, @NonNull Throwable t) {
                Toast.makeText(DetailsActivity.this, "Error! API is not Responding and working", Toast.LENGTH_SHORT).show();
            }
        });


    }
}