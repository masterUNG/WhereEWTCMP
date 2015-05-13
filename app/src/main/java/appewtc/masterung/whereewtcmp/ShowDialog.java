package appewtc.masterung.whereewtcmp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by masterUNG on 5/13/15 AD.
 */
public class ShowDialog {

    public void myDialog(Context context, String strLat, String strLng) {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(context);
        objBuilder.setIcon(R.drawable.friend);
        objBuilder.setTitle("My Location");
        objBuilder.setMessage("Lat = " + strLat + "\n" + "Lng = " + strLng);
        objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        objBuilder.show();

    }

}   // Main Class
