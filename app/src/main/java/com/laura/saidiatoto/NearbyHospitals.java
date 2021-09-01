package com.laura.saidiatoto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class NearbyHospitals extends AppCompatActivity implements OnMapReadyCallback, LocationListener, GoogleMap.OnMarkerClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    public static final int ROUND = 10;
    private GoogleMap mMap;
    public GoogleApiClient googleApiClient;
    private ChildEventListener mChildEventListener;
    private DatabaseReference mHospitals;
    Marker marker;
    public FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_hospitals);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ChildEventListener mChildEventListener;
        mHospitals= FirebaseDatabase.getInstance().getReference("hospitals");
        mHospitals.push().setValue(marker);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void onConnected(@Nullable @org.jetbrains.annotations.Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull @NotNull ConnectionResult connectionResult) {

    }

    @Override
    public boolean onMarkerClick(@NonNull @NotNull Marker marker) {
        return false;
    }

    @Override
    public void onMapReady(@NonNull @NotNull GoogleMap googleMap) {
        /*try {
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(this, R.raw.google_style));
            if (!success) {
                // Handle map style load failure
                Log.e("map_style","map style updated please do check it");
            }
        } catch (Resources.NotFoundException e) {
            // Oops, looks like the map style resource couldn't be found!
            Log.e("map_style","map is not updated yet ... do some other stuff");
        }*/

        mMap = googleMap;
        googleMap.setOnMarkerClickListener(this);
        //googleMap.setMapType(R.raw.google_style);

        //setting the size of marker in map by using Bitmap Class
        int height = 80;
        int width = 80;
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.mipmap.hospital_pin);
        Bitmap b=bitmapdraw.getBitmap();
        final Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        mHospitals.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot s : dataSnapshot.getChildren()){
                    NearbyHospitalsModel user = s.getValue(NearbyHospitalsModel.class);

                    LatLng location=new LatLng(Double.parseDouble(user.getLatitude()),Double.parseDouble(user.getLongitude()));
                    mMap.addMarker(new MarkerOptions().position(location).title(user.getHospitalName())).setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 13));

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    /*
    public Bitmap convertToBitmap(){
        try {
            Bitmap bitmap;
            Drawable drawable = R.drawable.ic_baseline_pin_drop_24;
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            // Handle the error
            return null;
        }
    }*/

}