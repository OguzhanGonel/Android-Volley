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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private static final String REQUESTTAG = "string request first";
    private Button btnSendRequest;

    private RequestQueue mRequestQueue;

    private StringRequest stringRequest;

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

        mRequestQueue = Volley.newRequestQueue(this);

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
