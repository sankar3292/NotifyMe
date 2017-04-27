package com.notifyme.service;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

public class GPSTracker extends Activity implements LocationListener
{

    private final Context mContext;
    // flag for GPS status
    boolean isGPSEnabled = false;
    // flag for network status
    boolean isNetworkEnabled = false;
    // flag for GPS status
    boolean canGetLocation = false;
    Location location; // location
    double latitude; // latitude
    double longitude; // longitude
//    private LocationClient mLocationClient;

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1000; // 100 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 15; // 1 minute

    // Declaring a Location Manager
    protected LocationManager locationManager;

    public GPSTracker(Context context)
    {
        super();
        this.mContext = context;
        getLocation();
    }

    public Location getLocation() throws SecurityException
    {
        try
        {
            locationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                // First get location from Network Provider
                if (isNetworkEnabled)
                {
                    locationManager.requestLocationUpdates
                            (
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null)
                        {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null)
                        {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null)
                            {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app
     * */
    public void stopUsingGPS()
    {
        if(locationManager != null)
        {
            try
            {
                locationManager.removeUpdates(GPSTracker.this);
            }
            catch (SecurityException e){

            }
        }
    }

    /**
     * Function to get latitude
     * */
    public double getLatitude(){
        if(location != null){
            latitude = location.getLatitude();
        }

        // return latitude
        return latitude;
    }

    /**
     * Function to get longitude
     * */
    public double getLongitude(){
        if(location != null){
            longitude = location.getLongitude();
        }

        // return longitude
        return longitude;
    }

    /**
     * Function to check GPS/wifi enabled
     * @return boolean
     * */
    public boolean canGetLocation() {

        return this.canGetLocation;
    }

    /**
     * Function to show settings alert dialog
     * On pressing Settings button will lauch Settings Options
     * */
    public void showSettingsAlert()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        // Setting Dialog Title
        alertDialog.setTitle("GPS  settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
    public Location getLastBestStaleLocation()
    {
        Location bestResult = null;
//        LocationManager locMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        Location lastFusedLocation=mLocationClient.getLastLocation();
//        Location gpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        Location networkLocation = locMgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//        if (gpsLocation != null && networkLocation != null) {
//            if (gpsLocation.getTime() > networkLocation.getTime())
//                bestResult = gpsLocation;
//        } else if (gpsLocation != null) {
//            bestResult = gpsLocation;
//        } else if (networkLocation != null) {
//            bestResult = networkLocation;
//        }
//
//        //take Fused Location in to consideration while checking for last stale location
//        if (bestResult != null && lastFusedLocation != null) {
//            if (bestResult.getTime() < lastFusedLocation.getTime())
//                bestResult = lastFusedLocation;
//        }
        bestResult=location;


        return bestResult;
    }

    @Override
    public void onLocationChanged(Location location)
    {
//        getLocation();
//        if(location != null)
//        {
//            double latitude = location.getLatitude();
//            double longitude = location.getLongitude();
//            Log.i("info", "Latitude :: " + latitude);
//            Log.i("info", "Longitude :: " + longitude);
//            //sending location details
//        }
//        getLocation();
//        Toast.makeText(GPSTracker.this, "My Position !!!"+ location.getLatitude() + location.getLongitude(),
//                Toast.LENGTH_LONG).show();
//        String msg = "New Latitude: " + location.getLatitude()
//                + "New Longitude: " + location.getLongitude();
//
////        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
//        Log.e("location changed", String.valueOf(location.getLatitude()));
        stopUsingGPS();
    }

    @Override
    public void onProviderDisabled(String provider)
    {

    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {

    }

    @Override
    protected void onDestroy() {
        Log.e("stopped tracking","ondestroy");
        super.onDestroy();

        stopUsingGPS();
    }
}
