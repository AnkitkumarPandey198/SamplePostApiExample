package com.wilp.samplepostapidemo.authapi;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("auth")
    Call<Auth> authenticate(@Header("Device-Type") String deviceType,
                            @Header("api-key") String apiKey,
                            @Header("Content-Type") String contentType,
                            @Body HashMap<String, String> body);
}
