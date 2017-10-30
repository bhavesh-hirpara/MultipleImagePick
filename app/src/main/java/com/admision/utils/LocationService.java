//package com.admision.utils;
//
//import android.content.Context;
//import android.content.Intent;
//import android.location.Location;
//import android.os.Bundle;
//import android.support.v4.content.LocalBroadcastManager;
//
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GooglePlayServicesUtil;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.common.api.PendingResult;
//import com.google.android.gms.common.api.Status;
//import com.google.android.gms.location.LocationListener;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationServices;
//
//import java.text.DateFormat;
//import java.util.Date;
//
//public class LocationService implements
//        LocationListener,
//        GoogleApiClient.ConnectionCallbacks,
//        GoogleApiClient.OnConnectionFailedListener {
//
//    private static final String TAG = "LocationActivity";
//    private static final long INTERVAL = 60 * 60 * 1000;
//    private static final long FASTEST_INTERVAL = 1000 * 60;
//    LocationRequest mLocationRequest;
//    GoogleApiClient mGoogleApiClient;
//    Location mCurrentLocation;
//    String mLastUpdateTime;
//
//    public static final String TOKEN = "token";
//
//    private boolean isOneTime;
//
//    Context context;
//    String token;
//
//    public LocationService(String token) {
//        this.token = token;
//    }
//
//    protected void createLocationRequest() {
//        mLocationRequest = new LocationRequest();
//
//        if (hasClientConfig) {
//            mLocationRequest.setInterval(interval);
//        } else {
//            mLocationRequest.setInterval(INTERVAL);
//        }
//
//        if (hasClientConfig) {
//            mLocationRequest.setFastestInterval(fastestInterval);
//        } else {
//            mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
//        }
//
//        mLocationRequest.setSmallestDisplacement(smallestDisplacementMeters);
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//    }
//
//    boolean hasClientConfig = false;
//    long interval = 9 * 1000;
//    long fastestInterval = 5 * 1000;
//
//    float smallestDisplacementMeters = 10;
//
//    public void setInterval(long interval) {
//        hasClientConfig = true;
//        this.interval = interval;
//    }
//
//    public void setFastestInterval(long fastestInterval) {
//        hasClientConfig = true;
//        this.fastestInterval = fastestInterval;
//    }
//
//    public void init(Context context) {
//        this.context = context;
//        Debug.d(TAG, "Init location service ...............................");
//
//        //show error dialog if GoolglePlayServices not available
//        if (!isGooglePlayServicesAvailable()) {
//            return;
//        }
//
//        createLocationRequest();
//        mGoogleApiClient = new GoogleApiClient.Builder(context)
//                .addApi(LocationServices.API)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .build();
//
//        mGoogleApiClient.connect();
//    }
//
//    public void stop() {
//        Debug.d(TAG, "onStop fired ..............");
//        mGoogleApiClient.disconnect();
//        Debug.d(TAG, "isConnected ...............: " + mGoogleApiClient.isConnected());
//    }
//
//    private boolean isGooglePlayServicesAvailable() {
//        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
//        if (ConnectionResult.SUCCESS == status) {
//            return true;
//        } else {
////            GooglePlayServicesUtil.getErrorDialog(status, context, 0).show();
//            return false;
//        }
//    }
//
//    @Override
//    public void onConnected(Bundle bundle) {
//        Debug.d(TAG, "onConnected - isConnected ...............: " + mGoogleApiClient.isConnected());
//        startLocationUpdates();
//    }
//
//    protected void startLocationUpdates() {
//        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
//                mGoogleApiClient, mLocationRequest, this);
//        Debug.d(TAG, "Location update started ..............: ");
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(ConnectionResult connectionResult) {
//        Debug.d(TAG, "Connection failed: " + connectionResult.toString());
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//        Debug.d(TAG, "Firing onLocationChanged..............................................");
//
//        mCurrentLocation = location;
//        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
//
//        Utils.setPref(context, Constant.USER_LONGITUDE,
//                "" + location.getLongitude());
//        Utils.setPref(context, Constant.USER_LATITUDE,
//                "" + location.getLatitude());
//
//        Intent intent = new Intent(Constant.LOCATION_UPDATED);
//        intent.putExtra(TOKEN, "" + token);
//        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
//
//        updateUI();
//
//        if (isOneTime) {
//            stopLocationUpdates();
//            stop();
//        }
//    }
//
//    private void updateUI() {
//        Debug.d(TAG, "UI update initiated .............");
//        if (null != mCurrentLocation) {
//            String lat = String.valueOf(mCurrentLocation.getLatitude());
//            String lng = String.valueOf(mCurrentLocation.getLongitude());
//            Debug.d("", "At Time: " + mLastUpdateTime + "\n" +
//                    "Latitude: " + lat + "\n" +
//                    "Longitude: " + lng + "\n" +
//                    "Accuracy: " + mCurrentLocation.getAccuracy() + "\n" +
//                    "Provider: " + mCurrentLocation.getProvider());
//        } else {
//            Debug.d(TAG, "location is null ...............");
//        }
//    }
//
//    protected void stopLocationUpdates() {
//        LocationServices.FusedLocationApi.removeLocationUpdates(
//                mGoogleApiClient, this);
//        Debug.d(TAG, "Location update stopped .......................");
//    }
//
//    public boolean isOneTime() {
//        return isOneTime;
//    }
//
//    public void setOneTime(boolean isOneTime) {
//        this.isOneTime = isOneTime;
//    }
//}
