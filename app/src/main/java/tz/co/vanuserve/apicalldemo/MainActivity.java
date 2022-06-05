package tz.co.vanuserve.apicalldemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.players_recycler);
        progressBar=findViewById(R.id.progress_circular);

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
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                List<Player> players=response.body();
                for(Player player:players){
                    Log.d(TAG, "onResponse: "+player.getImage());
                }

                //Set recycler
                PlayersAdapter adapter=new PlayersAdapter(players,MainActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}