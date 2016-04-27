package com.example.yurab.converterlab.fragments.map;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yurab.converterlab.R;
import com.example.yurab.converterlab.constants.Constants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Created by Yura Breza
 * Date  27.04.2016.
 */
public class MapFragment extends Fragment {

    private SupportMapFragment fragment;
    private GoogleMap map;
    private String city;
    private String addressGeo;
    private String title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        city = getArguments().getString(Constants.MAP_KEY_CITY);
        addressGeo = getArguments().getString(Constants.MAP_KEY_ADDRESS);
        title = getArguments().getString(Constants.MAP_KEY_TITLE);
        addressGeo =
                "Украина, " + getArguments().getString(Constants.MAP_KEY_REGION) + ", " + city + ", " + addressGeo;
        return inflater.inflate(R.layout.map_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fm = getChildFragmentManager();
        fragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (fragment == null) {
            fragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.main_activity, fragment).commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (map == null) {

            map = fragment.getMap();
            try {
                map.setMyLocationEnabled(true);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
            map.getUiSettings().setZoomControlsEnabled(true);
            map.getUiSettings().setCompassEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(true);
            Toast.makeText(getContext(), addressGeo, Toast.LENGTH_SHORT).show();
            Geocoder geocoder = new Geocoder(getContext());
            List<Address> addresses;
            try {
                addresses = geocoder.getFromLocationName(addressGeo, 1);
                Address address = addresses.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                Toast.makeText(getContext(), String.valueOf(latLng), Toast.LENGTH_SHORT).show();
                map.addMarker(new MarkerOptions().position(latLng).title(title));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}
