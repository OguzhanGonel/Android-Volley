package com.gonel.oguzhan.android_volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private static final String REQUESTTAG = "string request first";
    private Button btnSendRequest;

    private RequestQueue mRequestQueue;

    private StringRequest stringRequest;

    private DiskBasedCache mCache;
    private com.android.volley.Network mNetwork;

    private String url = "http://www.mocky.io/v2/5bdf32a33100007a009e3ffc";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickFunction(View view){

        Log.d("clickFunction", "Button got clicked");

        sendRequestAndPrintResponse();
    }

    private void sendRequestAndPrintResponse(){

        // Uses the Request Queue provided by Volley
        //mRequestQueue = Volley.newRequestQueue(this);

        // Making my own Request Queue
        //mCache = new DiskBasedCache(getCacheDir(), 4*1024*1024);
        //mNetwork = new BasicNetwork(new HurlStack());
        //mRequestQueue = new RequestQueue(mCache, mNetwork);
        //mRequestQueue.start();

        mRequestQueue = VolleySingletonClass.getInstance(this.getApplicationContext()).getRequestQueue(this.getApplicationContext());

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "Success Response: " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Fail Response: " + error.toString());
            }
        });

        stringRequest.setTag(REQUESTTAG);

        mRequestQueue.add(stringRequest);

    }

    @Override
    protected void onStop(){

        super.onStop();

        if(mRequestQueue != null){
            // Cancel a request
            mRequestQueue.cancelAll(REQUESTTAG);
        }
    }
}
