package com.example.volleyrequestt01.network;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.volleyrequestt01.app.AppClient;
import com.example.volleyrequestt01.app.AppConfig;
import com.example.volleyrequestt01.inter.NetWorkOnResult;

import org.json.JSONException;
import org.json.JSONObject;

public class NetRequest extends Thread {
    private final String TAG = "NetRequest";
    private JSONObject jsonBody;
    private String action;
    private NetWorkOnResult netWorkOnResult;
    private boolean isLoop = false;
    private long loopTime = 1000;


    public NetRequest(String action) {
        this.action = action;
    }

    public NetRequest setjsonBody(JSONObject jsonBody) {
        this.jsonBody = jsonBody;
        return this;
    }

    public NetRequest setNetRequest(NetWorkOnResult netWorkOnResult) {
        this.netWorkOnResult = netWorkOnResult;
        start();
        return this;
    }

    public NetRequest setLoop(boolean loop) {
        this.isLoop = loop;
        return this;
    }

    public NetRequest setLoopTime(long loopTime) {
        this.loopTime = loopTime;
        return this;
    }

    public void request() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                AppConfig.URL + action, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    netWorkOnResult.onSuccess(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                netWorkOnResult.onError();
            }
        });
        AppClient.getRequestmQueue().add(jsonObjectRequest);

    }

    @Override
    public void run() {
        super.run();
        do {
            Log.i(TAG, "run()运行了！");
            request();
            try {
                Thread.sleep(loopTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (isLoop);
    }

    public void clean() {
        isLoop = false;

    }

}
