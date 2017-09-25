package com.example.damian.projektgrupowy.view.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.example.damian.projektgrupowy.R;

import com.example.damian.projektgrupowy.core.BaseFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends BaseFragment implements
        OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        View.OnClickListener,
        GoogleMap.OnInfoWindowClickListener{

    private static final int MY_LOCATION_REQUEST_CODE = 1;
    private MapView mapView;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Button showPlacesButton;

    private boolean navigateMe;
    private boolean isPlacesVisible;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = (MapView) view.findViewById(R.id.fragment_map_container);
        mapView.onCreate(savedInstanceState);

        showPlacesButton = (Button)view.findViewById(R.id.fragment_map_show_places_button);
        showPlacesButton.setOnClickListener(this);

        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
        showMap();

        return view;
    }

    public void showMap() {
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(getContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        buildGoogleApiClient();
                        googleMap.setMyLocationEnabled(true);
                    }
                } else {
                    buildGoogleApiClient();
                    googleMap.setMyLocationEnabled(true);
                }

                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                googleMap.getUiSettings().setCompassEnabled(true);

                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(googleMap.getCameraPosition()));
                Location locationCt;
                LocationManager locationManagerCt = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                locationCt = locationManagerCt
                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if(locationCt != null) {
                    LatLng latLng = new LatLng(locationCt.getLatitude(),
                            locationCt.getLongitude());
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                } else {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.773960, 19.483048), 17.0f));
                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                }

                if (navigateMe) {
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.773960, 19.483048), 17.0f));
                    MarkerOptions marker = new MarkerOptions().position(new LatLng(51.773960, 19.483048));
                    googleMap.addMarker(marker);
                    showPlacesButton.setVisibility(View.GONE);
                }

                if(isPlacesVisible){
                    MarkerOptions barlickiego = new MarkerOptions().position(new LatLng(51.773960, 19.483048));
                    barlickiego.snippet("Doktora Stefana Barlickiego 22")
                            .title("SPZOZ Uniwesytecki Szpital Kliniczny\n nr 1 im. Norberta Barlickiego");
                    googleMap.addMarker(barlickiego);

                    MarkerOptions pirgowa = new MarkerOptions().position(new LatLng(51.751903, 19.455417));
                    pirgowa.snippet("Wólczańska 191/195")
                            .title("Wojewódzki Specjalistyczny Szpital\n im. Wojciecha Pirogowa");
                    googleMap.addMarker(pirgowa);

                    MarkerOptions wam = new MarkerOptions().position(new LatLng(51.754291, 19.449530));
                    wam.snippet("Stefana Żeromskiego 113")
                            .title("Uniwersytecki Szpital Kliniczny im. Wojskowej \nAkademi Medycznej - Centralny Szpital Weteranów");
                    googleMap.addMarker(wam);

                    MarkerOptions wam2 = new MarkerOptions().position(new LatLng(51.767789, 19.437442));
                    wam2.snippet("plac Hellera 1")
                            .title("Uniwersytecki Szpital Kliniczny im. Wojskowej \nAkademi Medycznej - Centralny Szpital Weteranów");
                    googleMap.addMarker(wam2);

                    MarkerOptions umed = new MarkerOptions().position(new LatLng(51.774300, 19.505822));
                    umed.snippet("Pomorska 251")
                            .title("SPZOZ Centralny Szpital Kliniczny \nUniwersytetu Medycznegoo w łodzi");
                    googleMap.addMarker(umed);

                    MarkerOptions central = new MarkerOptions().position(new LatLng(51.782458, 19.461384));
                    central.snippet("Franciszkańska 17/25")
                            .title("Regionalne Centrum Krwiodactwa i \nKrwiolecznictwa w łodzi");
                    googleMap.addMarker(central);

                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.773960, 19.483048), 12.0f));

                    googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick(Marker marker) {
                            Toast.makeText(getContext(),marker.getTitle()+"\n"+marker.getSnippet() , Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

        });

    }

    public void isFromNotification(boolean isFromNotification) {
        this.navigateMe = isFromNotification;
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (requestCode == MY_LOCATION_REQUEST_CODE) {
                    if (permissions.length == 1 &&
                            permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        checkLocationPermission();
                        mMap.setMyLocationEnabled(true);
                    }

                }
            }
        }
    }

    public boolean checkLocationPermission() {
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = getActivity().checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onClick(View view) {
        isPlacesVisible = true;
        Toast.makeText(getContext(), "klik", Toast.LENGTH_SHORT).show();
        showMap();
        mapView.invalidate();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }
}
