package com.example.atto;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Fragment_restaurant_page_Activity extends Fragment implements OnMapReadyCallback {

    GoogleMap gMap;
    public Fragment_restaurant_page_Activity(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.activity_fragment_restaurant_page, container, false);

        //마켓 페이지로 연결해야댐

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return rootview;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setComponent(ComponentName.unflattenFromString("com.google.android.apps.maps/com.google.android.maps.MapsActivity"));
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setData(Uri.parse("https://www.google.com/maps/d/u/0/viewer?ie=UTF8&hl=ko&msa=0&ll=37.80435926012475%2C126.97654766699219&spn=0.379134%2C0.617294&z=9&mid=11rmzgn_-gXKrRKPPVEhJfScfqJ8"));
        startActivity(intent);

        //구글 맵에 직접 마크하는 경우
//        String str_loc_Nm = "충북대학교";
//        double dbl_Lat = 36.6287;
//        double dbl_Lon = 127.4606;
//        String str_loc_Desc = "충북대학교";
//
//        LatLng Location  = new LatLng(dbl_Lat, dbl_Lon);
//
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(Location);
//        markerOptions.position(Location);
//        markerOptions.title(str_loc_Nm);
//        markerOptions.snippet(str_loc_Desc);
//        gMap.addMarker(markerOptions);
//        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Location, 10));
    }

}