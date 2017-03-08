package cleverti.com.countriesinfo.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import cleverti.com.countriesinfo.R;

import static android.view.WindowManager.LayoutParams.TYPE_APPLICATION_PANEL;

/**
 * Created by uiltonsantos on 08/03/2017.
 */

public class AndroidUtil {

    public static boolean hasConnectivity(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getApplicationContext().getSystemService(
                            Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager
                    .getActiveNetworkInfo();

            return networkInfo != null && networkInfo.isAvailable()
                    && networkInfo.isConnected();
        } else {
            return false;
        }
    }


    public static void showMessageOK(Context context, String title, String message, DialogInterface.OnClickListener okListener) {
        showMessageOK(context, title, -1, message, okListener);
    }

    public static void showMessageOK(Context context, String title, int labelBtn, String message, DialogInterface.OnClickListener okListener) {
        try {
            if (okListener == null) {
                okListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                };
            }

            if (labelBtn == -1) {
                labelBtn = R.string.ok;
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(false);
            builder.setMessage(message);
            builder.setPositiveButton(labelBtn, okListener);
            if (title != null) {
                builder.setTitle(title);
            }

            AlertDialog dialog = builder.create();
            if (dialog != null) {
                dialog.setCanceledOnTouchOutside(false);
                dialog.getWindow().setType(TYPE_APPLICATION_PANEL);
                dialog.show();
            }

        } catch (Exception e) {
            if (context != null)
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}
