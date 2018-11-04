package com.gonel.oguzhan.android_volley;

import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class ImageActivity extends AppCompatActivity {

    private String url = "http://images.performgroup.com/di/library/Goal_Turkey/a7/80/roberto-carlos-fenerbahce_x6u1g75pznd31jovfm52e8dqt.jpg?t=1037212289&w=320";

    private ImageView ivImageView;

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ivImageView = (ImageView) findViewById(R.id.ivImageView);

        mRequestQueue  = Volley.newRequestQueue(this.getApplicationContext());

    }

    public void loadImageButton(View view){

        Log.d("loadImageButton", "Load Image Button got clicked");

        ImageRequest imageRequest= new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                ivImageView.setImageBitmap(bitmap);
            }
        }, 0, 0, null, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ivImageView.setImageResource(R.drawable.error);
                Log.i("imageLoadError", "Fail Response: " + error.toString());
            }
        });

        mRequestQueue.add(imageRequest);
    }
}
