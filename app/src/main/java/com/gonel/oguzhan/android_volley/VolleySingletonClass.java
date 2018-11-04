package com.gonel.oguzhan.android_volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/*
The purpose of this class is to provide us with a request queue
 */
public class VolleySingletonClass {

    // Whenever getInstance is called, it creates a new mInstance if its null, it then calls the
    // VolleySingletonClass Constructor which then calls getRequestQueue to create a request queue
    // This way, we only have one Request Queue, allowing us to control how HTTP Requests are made



    private static VolleySingletonClass mInstance;
    private RequestQueue mRequestQueue;


    private VolleySingletonClass(Context context){
        mRequestQueue = getRequestQueue(context);
    }

    // This class returns a VolleySingletonClass instance, so that methds of this class can be called
    public static VolleySingletonClass getInstance(Context context){

        // if mInstance is null (not created an instance yet)
        if (mInstance == null){
            mInstance = new VolleySingletonClass(context);
        }
        return mInstance;
    }


    public RequestQueue getRequestQueue(Context context){
        if (mRequestQueue == null){
            // Don't need to put NEW Volley.newRequestQueue as NEW is in the function
            mRequestQueue = Volley.newRequestQueue(context);
        }
        return mRequestQueue;
    }
}
