package com.example.volleyrequestt01.inter;

import org.json.JSONException;
import org.json.JSONObject;

public interface NetWorkOnResult {

    void onSuccess(JSONObject jsonObject) throws JSONException;

    void onError();
}
