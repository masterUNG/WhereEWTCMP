package appewtc.masterung.whereewtcmp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }   // onCreate

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
        //myIntentToMAP();
    }

    private void myIntentToMAP(double douLat, double douLng) {
        //Intent to MapsActivity
        Intent objIntent = new Intent(MainActivity.this, MapsActivity.class);
        objIntent.putExtra("Lat", douLat);
        objIntent.putExtra("Lng", douLng);
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



        return super.onOptionsItemSelected(item);
    }
}   // Main Class
