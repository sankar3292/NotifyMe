package com.notifyme.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.notifyme.R;
import com.notifyme.adapter.PlacesAdapter;
import com.notifyme.model.PlacesModel;
import com.notifyme.model.Result;
import com.notifyme.service.GPSTracker;
import com.notifyme.utils.RetrofitMaps;
import com.notifyme.utils.TakeAroundConstants;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by netvation on 4/26/2017.
 */

public class SearchResultActivity extends AppCompatActivity
{
    double latitude;
    double longitude;
    private int PROXIMITY_RADIUS = 3000;
    private String nearbyplace;
    private String location;
    private String key,sensor,radius;
    private PlacesAdapter placesAdapter;
    private RecyclerView placesRecyclerView;
    private GPSTracker gpsTracker;
    private ImageView back;
    private List<Result> resultList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchresults);
        back= (ImageView) findViewById(R.id.back_searchpage);
        placesRecyclerView= (RecyclerView) findViewById(R.id.search_recyclerview);
        resultList=new ArrayList<>();
        if(getIntent().getExtras()!=null)
        {
            nearbyplace=getIntent().getExtras().getString("service");
            gpsTracker=new GPSTracker(getApplicationContext());
            latitude=gpsTracker.getLatitude();
            longitude=gpsTracker.getLongitude();
            location=String.valueOf(latitude)+String.valueOf(longitude);
            key="AIzaSyBEHzjSB6rJv79AgZuikAH71E_7yGsM8Ps";
            sensor="true";
            radius=String.valueOf(PROXIMITY_RADIUS);
            build_retrofit_and_get_response(nearbyplace);

        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void build_retrofit_and_get_response(String type)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TakeAroundConstants.PLACES_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitMaps service = retrofit.create(RetrofitMaps.class);

        Call<PlacesModel> call = service.getNearbyPlaces(type, latitude + "," + longitude, PROXIMITY_RADIUS);

        call.enqueue(new Callback<PlacesModel>()
        {
            @Override
            public void onResponse(Response<PlacesModel> response, Retrofit retrofit) {

                try
                {

//                    // This loop will go through all the results and add marker on each location.
                    for (int i = 0; i < response.body().getResults().size(); i++) {
                        Double lat = response.body().getResults().get(i).getGeometry().getLocation().getLat();
                        Double lng = response.body().getResults().get(i).getGeometry().getLocation().getLng();
                        String placeName = response.body().getResults().get(i).getName();
                        String vicinity = response.body().getResults().get(i).getVicinity();
                        Log.e("hotel name", String.valueOf(placeName));
                        Log.e("Place name", String.valueOf(vicinity));

                    }

                    resultList=response.body().getResults();

//                    Log.e("total count", String.valueOf(response.body().getResults().size()));
////                        Log.e("response",String.valueOf(response.body().getResults().get(0).getIcon()));
//                    placesAdapter=new PlacesAdapter(resultList,SearchResultActivity.this);
//                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(SearchResultActivity.this);
//                    placesRecyclerView.setLayoutManager(mLayoutManager);
//                    placesRecyclerView.setAdapter(placesAdapter);

//                    for (int i = 0; i < response.body().getResults().size(); i++)
//                    {
//
//                    }



                } catch (Exception e)
                {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });

    }
}
