package nz.co.redice.myapplication.service;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

class LocationUpdateHelper {

    private Context mContext;
    private OnNewLocationListener mLocationListener;

    /**
     * The desired interval for location updates. Inexact. Updates may be more or less frequent.
     */
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    /**
     * The fastest rate for active location updates. Updates will never be more frequent
     * than this value.
     */
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;


    /**
     * Contains parameters used by {@link com.google.android.gms.location.FusedLocationProviderApi}.
     */
    private LocationRequest mLocationRequest;


    /**
     * Provides access to the Fused Location Provider API.
     */
    private FusedLocationProviderClient mFusedLocationClient;

    /**
     * Callback for changes in location.
     */
    private LocationCallback mLocationCallback;


    interface OnNewLocationListener {
        void onNewLocation(Location location);

    }

    public LocationUpdateHelper(Context context, OnNewLocationListener listener) {
        mContext = context;
        mLocationListener = listener;

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext);


        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                mLocationListener.onNewLocation(locationResult.getLastLocation());
            }
        };

        createLocationRequest();
    }

    /**
     * Sets the location request parameters.
     */
    private void createLocationRequest() {
        mLocationRequest = new LocationRequest()
                .setInterval(UPDATE_INTERVAL_IN_MILLISECONDS)
                .setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @SuppressLint("MissingPermission")
    void launchLocationUpdates() {
//        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    void cancelLocationUpdates() {
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }

    @SuppressLint("MissingPermission")
    Location getLastKnownLocation() {
        final Location[] location = new Location[1];
//        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION)
//                        != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return TODO;
//        }
        mFusedLocationClient.getLastLocation()
                .addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            location[0] = task.getResult();
                        } else {
                            Log.w("LocationHelper", "Failed to get location.");
                        }
                    }
                });
        return location[0];
    }
}
