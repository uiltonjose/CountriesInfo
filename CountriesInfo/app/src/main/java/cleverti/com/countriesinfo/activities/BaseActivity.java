package cleverti.com.countriesinfo.activities;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by uiltonsantos on 08/03/2017.
 */

public class BaseActivity extends AppCompatActivity {

    private RequestManager requestManager;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestManager = Glide.with(this);
    }

    public void downloadResourceWithGlide(String url, ImageView imageView) {
        if (requestManager != null) {
            requestManager.load(url)
                    .asBitmap().centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(getBitmapTarget(imageView));
        }
    }

    private BitmapImageViewTarget getBitmapTarget(final ImageView imageView) {
        return new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        };
    }

    public void showProgressDialog() {
        showProgressDialog(null);
    }

    public void showProgressDialog(String message) {

        try {
            if (message == null)
                message = "Loading";

            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(message);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    public void dismissProgressDialog() {
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }

        } catch (Exception e) {
            //just ignore
        }
    }
}
