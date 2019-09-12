package com.example.volleyrequestt01.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppClient extends Application {
    public static RequestQueue mQueue;

    public void onCreate() {
        super.onCreate();
        mQueue = Volley.newRequestQueue(this);

    }

    public static RequestQueue getRequestmQueue() {
        return mQueue;
    }
}
