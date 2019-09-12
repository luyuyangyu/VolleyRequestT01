package com.example.volleyrequestt01;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.volleyrequestt01.inter.NetWorkOnResult;
import com.example.volleyrequestt01.network.NetRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login();
    }

    private void login() {

        String username = "admin";
        String password = "123";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("account", username);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new NetRequest("LoginPostServlet")
                .setjsonBody(jsonObject)
                .setNetRequest(new NetWorkOnResult() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) throws JSONException {
                        try {
                            String code = jsonObject.getString("resCode");
                            Log.d(TAG, "##code##" + code);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });

    }
}
