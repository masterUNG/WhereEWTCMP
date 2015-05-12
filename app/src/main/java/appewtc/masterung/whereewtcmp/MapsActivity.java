package appewtc.masterung.whereewtcmp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private LatLng centerLatLng, bangkokLatLng,
            chiangmaiLatLng, phuketLatLng, ewtcLatLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Setup Center Map
        setUpCenterMap();

        //Create LatLng
        createLatLng();



        setUpMapIfNeeded();
    }   // onCreate

    private void createLatLng() {

        bangkokLatLng = new LatLng(13.744465, 100.516982);
        chiangmaiLatLng = new LatLng(18.787598, 98.986089);
        phuketLatLng = new LatLng(7.880406, 98.392128);
        ewtcLatLng = new LatLng(13.667607, 100.621786);

    }

    private void setUpCenterMap() {

        //Receive Value from MainActivity
        double douLat = getIntent().getExtras().getDouble("Lat");
        double douLng = getIntent().getExtras().getDouble("Lng");
        centerLatLng = new LatLng(douLat, douLng);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }   // onResume

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }   // setUpMapIfNeeded

    private void setUpMap() {

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(centerLatLng, 16));

        //Create Maker
        createMaker();

    }   // setUpMap

    private void createMaker() {

        //DownTown
        mMap.addMarker(new MarkerOptions().position(bangkokLatLng));    //default Maker
        mMap.addMarker(new MarkerOptions().position(chiangmaiLatLng)
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                .title("เชียงใหม่").snippet("ใจกลางเชียงใหม่"));   // Orange Maker
        mMap.addMarker(new MarkerOptions().position(phuketLatLng)
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.build3))
                .title("ภูเก็ต").snippet("เมืองท่องเทียวทางใต้"));
        mMap.addMarker(new MarkerOptions().position(ewtcLatLng)
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.build9))
                .title("สถาบัน EWTC")
                .snippet("คือสถานที่ ที่มาสเตอร์ อึ่ง สอนแอนดรอยด์"));

    }

}   // Main Class
