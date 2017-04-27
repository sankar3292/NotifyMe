package com.notifyme.utils;



import com.notifyme.model.PlacesModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by navneet on 17/7/16.
 */
public interface RetrofitMaps {

    /*
     * Retrofit get annotation with our URL
     * And our method that will return us details of student.
     */
    @GET("api/place/nearbysearch/json?sensor=true&key=AIzaSyBEHzjSB6rJv79AgZuikAH71E_7yGsM8Ps")
    Call<PlacesModel> getNearbyPlaces(@Query("type") String type, @Query("location") String location, @Query("radius") int radius);

}
