package appewtc.masterung.whereewtcmp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private LatLng centerLatLng, bangkokLatLng,
            chiangmaiLatLng, phuketLatLng, ewtcLatLng, userLatLng,
            btsBangnaLatLng, btnUdomsukLatLng, sectionBangnaLatLng,
            sectionSieumLatLng, bangna14LatLng,
            ch1LatLng, ch2LatLng, ch3LatLng, ch4LatLng;
    private PolylineOptions radPolylineOptions, bluePolylineOptions;
    private PolygonOptions myPolygonOptions;

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

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        createLatLng();
        createMaker();
    }

    private void createLatLng() {

        bangkokLatLng = new LatLng(13.744465, 100.516982);
        chiangmaiLatLng = new LatLng(18.787598, 98.986089);
        phuketLatLng = new LatLng(7.880406, 98.392128);
        ewtcLatLng = new LatLng(13.667607, 100.621786);

        //For Location of User
        double douLat = getIntent().getExtras().getDouble("UserLat");
        double douLng = getIntent().getExtras().getDouble("UserLng");
        userLatLng = new LatLng(douLat, douLng);

        btsBangnaLatLng = new LatLng(13.668256, 100.604831);
        btnUdomsukLatLng = new LatLng(13.679869, 100.609594);
        sectionBangnaLatLng = new LatLng(13.673301, 100.606976);
        sectionSieumLatLng = new LatLng(13.665795, 100.644377);
        bangna14LatLng = new LatLng(13.669444, 100.623906);

        ch1LatLng = new LatLng(18.795350, 98.978941);
        ch2LatLng = new LatLng(18.794883, 98.993167);
        ch3LatLng = new LatLng(18.781679, 98.992073);
        ch4LatLng = new LatLng(18.782044, 98.978276);

    }   // createLatLng

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

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(centerLatLng, 15));

        //Create Maker
        createMaker();

        //Select Type Map
        selectTypeMap();

        //Create Polyline
        createPolyLine();

        //Create Polygon
        createPolygon();

        //Get Event from Click Map
        getEventFromClick();

        //Get Event from Click Maker
        getEventFromClickMaker();

    }   // setUpMap

    private void getEventFromClickMaker() {

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                double douLat = marker.getPosition().latitude;
                double douLng = marker.getPosition().longitude;
                callDialog(douLat, douLng);

                return true;
            }
        });

    }

    private void callDialog(double douLat, double douLng) {

        String strLat = String.format("%.7f", douLat);
        String strLng = String.format("%.7f", douLng);

        ShowDialog objShowDialog = new ShowDialog();
          objShowDialog.myDialog(MapsActivity.this, strLat, strLng);
    }

    private void getEventFromClick() {

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                 mMap.addMarker(new MarkerOptions().position(latLng));

               // onOffPolygon(latLng.latitude, latLng.longitude);


            }   // event
        });

    }

    private void onOffPolygon(double douLat, double douLng) {

        if (true && true) {
            if (true && true) {
                mMap.addPolygon(myPolygonOptions);
            }
        }   // if1

    }

    private void createPolygon() {

        myPolygonOptions = new PolygonOptions();
        myPolygonOptions.add(ch1LatLng)
                .add(ch2LatLng)
                .add(ch3LatLng)
                .add(ch4LatLng)
                .add(ch1LatLng)
                .strokeWidth(5)
                .strokeColor(Color.MAGENTA)
                .fillColor(Color.argb(50, 167, 236, 64));
        mMap.addPolygon(myPolygonOptions);

    }

    private void createPolyLine() {

        radPolylineOptions = new PolylineOptions();
        radPolylineOptions.add(userLatLng)
                .add(btsBangnaLatLng)
                .add(sectionBangnaLatLng)
                .add(sectionSieumLatLng)
                .add(bangna14LatLng)
                .add(ewtcLatLng)
                .width(10)
                .color(Color.RED);

       // mMap.addPolyline(radPolylineOptions);

        bluePolylineOptions = new PolylineOptions();
        bluePolylineOptions.add(userLatLng)
                .add(btnUdomsukLatLng)
                .add(sectionBangnaLatLng)
                .add(sectionSieumLatLng)
                .add(bangna14LatLng)
                .add(ewtcLatLng)
                .width(10)
                .color(Color.BLUE);

       // mMap.addPolyline(bluePolylineOptions);

        chooseStation();

    }   // createPolyLine

    private void chooseStation() {

        double douUserLat = getIntent().getExtras().getDouble("UserLat");
        double douUserLng = getIntent().getExtras().getDouble("UserLng");
        double douDistant1 = Math.pow((douUserLat - 13.668256), 2) + Math.pow((douUserLng - 100.604831), 2);
        double douDistant2 = Math.pow((douUserLat - 13.679869), 2) + Math.pow((douUserLng - 100.609594), 2);

        if ((douDistant1 - douDistant2) <= 0) {
            mMap.addPolyline(radPolylineOptions);
        } else {
            mMap.addPolyline(bluePolylineOptions);
        }

    }   // chooseStation

    private void selectTypeMap() {

        //Receive Value from MainActivity
        int intMyMap = getIntent().getExtras().getInt("SelectMap");
        switch (intMyMap) {
            case 1:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case 2:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case 3:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case 4:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            default:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
        }

    }

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

        mMap.addMarker(new MarkerOptions().position(userLatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.friend)));

    }   // createMaker

}   // Main Class
