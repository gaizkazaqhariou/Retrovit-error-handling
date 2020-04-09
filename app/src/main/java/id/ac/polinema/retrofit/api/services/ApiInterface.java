package id.ac.polinema.retrofit.api.services;

import id.ac.polinema.retrofit.api.models.AppVersion;
import id.ac.polinema.retrofit.api.models.Data;
import id.ac.polinema.retrofit.api.models.LoginRequest;
import id.ac.polinema.retrofit.api.models.LoginResponse;
import id.ac.polinema.retrofit.api.models.PasswordRequest;
import id.ac.polinema.retrofit.api.models.Profile;
import id.ac.polinema.retrofit.api.models.ProfileRequest;
import id.ac.polinema.retrofit.api.models.RegisterRequest;
import id.ac.polinema.retrofit.api.models.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("/")
    Call<AppVersion> getAppVersion();

    @POST("/api/auth/login")
    Call<LoginResponse> doLogin(@Body LoginRequest loginRequest);

    @POST("/api/auth/register")
    Call<RegisterResponse> doRegister(@Body RegisterRequest registerRequest);

    @GET("/api/auth/me")
    Call<Data<Profile>> getProfile(@Header("Authorization") String token);

    @PATCH("/api/account/profile")
    Call<Data<Profile>> editProfile(@Header("Authorization") String token, @Body ProfileRequest Profile);

    @PATCH("/api/account/password")
    Call<Data<Profile>> editPassword(@Header("Authorization") String token, @Body PasswordRequest Profile);
}
