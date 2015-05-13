package appewtc.masterung.whereewtcmp;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    //Explicit
    private int intSelectMap = 1;
    private TextView txtShowLat, txtShowLng;
    private LocationManager objLocationManager;
    private Criteria objCriteria;
    private boolean bolGPS, bolNetwork;
    private double douUserLat, douUserLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //Setup Location Manager
        setupLocationManager();

    }   // onCreate

    @Override
    protected void onStart() {
        super.onStart();

        bolGPS = objLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!bolGPS) {

            bolNetwork = objLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!bolNetwork) {

                //Open Setup Network
                Intent objIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(objIntent);
            }   // if2

        } // if1

    }

    @Override
    protected void onResume() {
        super.onResume();

        afterResume();

    }

    private void afterResume() {

        objLocationManager.removeUpdates(objLocationListener);
        String strLat = "Unknow";
        String strLng = "Unknow";

        Location objNetworkLocation = requestUpdateFromProvider(LocationManager.NETWORK_PROVIDER,
                "Network not Connected");
        if (objNetworkLocation != null) {
            strLat = String.format("%.7f", objNetworkLocation.getLatitude());
            strLng = String.format("%.7f", objNetworkLocation.getLongitude());
        }   // if1

        Location objGPSLocation = requestUpdateFromProvider(LocationManager.GPS_PROVIDER, "No Card GPS");
        if (objGPSLocation != null) {
            strLat = String.format("%.7f", objGPSLocation.getLatitude());
            strLng = String.format("%.7f", objGPSLocation.getLongitude());
        }   // if2

        txtShowLat.setText(strLat);
        txtShowLng.setText(strLng);

    }

    @Override
    protected void onStop() {
        super.onStop();

        objLocationManager.removeUpdates(objLocationListener);

    }

    public Location requestUpdateFromProvider(String strPrivider, String strError) {

        Location objLocation = null;
        if (objLocationManager.isProviderEnabled(strPrivider)) {

            objLocationManager.requestLocationUpdates(strPrivider, 1000, 10, objLocationListener);
            objLocation = objLocationManager.getLastKnownLocation(strPrivider);

        } else {

            Toast.makeText(MainActivity.this, strError, Toast.LENGTH_LONG).show();

        }

        return objLocation;
    }



    public final LocationListener objLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            txtShowLat.setText(String.format("%.7f", location.getLatitude()));
            txtShowLng.setText(String.format("%.7f", location.getLongitude()));
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };



    private void setupLocationManager() {
        objLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        objCriteria = new Criteria();
        objCriteria.setAccuracy(Criteria.ACCURACY_FINE);
        objCriteria.setAltitudeRequired(false);
        objCriteria.setBearingRequired(false);
    }

    private void bindWidget() {
        txtShowLat = (TextView) findViewById(R.id.txtShowLat);
        txtShowLng = (TextView) findViewById(R.id.txtShowLng);
    }

    public void clickBangkok(View view) {
        myIntentToMAP(13.667607, 100.621786);
    }

    public void clickChiangMai(View view) {
        myIntentToMAP(18.787598, 98.986089);
    }

    public void clickPhuket(View view) {
        myIntentToMAP(7.880406, 98.392128);
    }

    public void clickUser(View view) {

        douUserLat = Double.parseDouble(txtShowLat.getText().toString().trim());
        douUserLng = Double.parseDouble(txtShowLng.getText().toString().trim());

        myIntentToMAP(douUserLat, douUserLng);
    }

    private void myIntentToMAP(double douLat, double douLng) {

        douUserLat = Double.parseDouble(txtShowLat.getText().toString().trim());
        douUserLng = Double.parseDouble(txtShowLng.getText().toString().trim());

        //Intent to MapsActivity
        Intent objIntent = new Intent(MainActivity.this, MapsActivity.class);
        objIntent.putExtra("Lat", douLat);
        objIntent.putExtra("Lng", douLng);
        objIntent.putExtra("SelectMap", intSelectMap);
        objIntent.putExtra("UserLat", douUserLat);
        objIntent.putExtra("UserLng", douUserLng);
        startActivity(objIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.itemNormal:
                intSelectMap = 1;
                break;
            case R.id.itemSatellite:
                intSelectMap = 2;
                break;
            case R.id.itemTerrain:
                intSelectMap = 3;
                break;
            case R.id.itemHybrid:
                intSelectMap = 4;
                break;
            default:
                intSelectMap = 1;
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class
