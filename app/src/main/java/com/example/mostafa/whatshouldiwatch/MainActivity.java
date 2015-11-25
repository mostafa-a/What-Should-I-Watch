package com.example.mostafa.whatshouldiwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    String[] mMoviesList = {"The godfather", "Fight Club", "Scarface","Shawshank Redemption", "True Romance",
            "Pulp Fiction", "American HistoryX", "Memento", "Road to Perdition", "American Psycho",
            "The Usual Suspects","Seven", "Philadelphia", "Pulp Fiction"};
    String mQueryUrl = "";

    Response mResponseObj;
    CustomAdapter mCustomAdapter;

    AsyncHttpClient mAsyncHttpClient;
    Gson gson;

    ListView mResultsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAsyncHttpClient = new AsyncHttpClient();

        mQueryUrl = "http://api.themoviedb.org/3/search/movie?api_key=7c3617b11c86a617a16f0552cfa224d1&query=";
        mResultsList = (ListView) findViewById(R.id.resultsListView);
    }

    /**
     * generates the movie and gets its details by calling the corresponding api method and displaying the result
     */
    public void generateMovie(View view) {
        String chosenMovieTitle = generateMovieTitle();
        mAsyncHttpClient.get(MainActivity.this, mQueryUrl + chosenMovieTitle, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseStr = new String(responseBody);
                gson = new Gson();
                mResponseObj = gson.fromJson(responseStr, Response.class);
                mCustomAdapter = new CustomAdapter(MainActivity.this, mResponseObj);
                mResultsList.setAdapter(mCustomAdapter);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    /**
     * chooses a random movie out of the list and returns the chosen one
     *
     * @return String the title of the chosen movie
     */
    public String generateMovieTitle() {
        return mMoviesList[((int) (Math.random() * (mMoviesList.length)))];
    }
}
