package tz.co.vanuserve.apicalldemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlayerApi {
    @GET("footballers")
    Call<List<Player>> getAllPlayers();
}
