package tz.co.vanuserve.apicalldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView playersTV=findViewById(R.id.players);

        //Create an instance of Retrofit
        Retrofit retrofit=new retrofit2.Retrofit.Builder()
                .baseUrl("https://footballers-app.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Create API Service
        PlayerApi playerApi=retrofit.create(PlayerApi.class);

        //Get a call object that we will use to make an asynchronous call in background thread
        Call<List<Player>> call=playerApi.getAllPlayers();

        //Make the remote API call
        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                List<Player> players=response.body();
                for(Player player:players){
                    Log.d(TAG, "onResponse: "+player.getName());
                    playersTV.append(player.getName()+"\n");
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {

            }
        });

    }
}