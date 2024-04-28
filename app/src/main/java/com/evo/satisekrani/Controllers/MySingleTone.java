package com.evo.satisekrani.Controllers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.evo.satisekrani.Common.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MySingleTone {

    public MySingleTone(Context context,GetDataListener dataListener, Map<String, String> params, String linkSon) {


        RequestQueue queue = Volley.newRequestQueue(context);
        response(Common.linkDemo+linkSon,dataListener,params, queue);
    }
    public MySingleTone() { }


    private void response(String url, GetDataListener dataListener, Map<String,String> params, RequestQueue queue){

        StringRequest request = new StringRequest(Request.Method.POST, url, (Response.Listener<String>) response -> {
            try {
                JSONObject object = new JSONObject(response);
                dataListener.onSuccessData(response);
            } catch (JSONException e) {
                e.printStackTrace();
                dataListener.onErrorData(e.getMessage() +" \n\nSonuç: "+response);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dataListener.onErrorData("Volley Error: "+ error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        queue.add(request);
    }
    public void sendJsonArray(Context context, GetDataListener dataListener, JSONObject obje, String linkSon, String yeniMasa){

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest jsonReq = new StringRequest(Request.Method.POST, Common.linkDemo+linkSon, response -> {
            Toast.makeText(context, ""+response, Toast.LENGTH_SHORT).show();
            Log.d("JOBJE"+" sonuç", "onResponse: "+ response);
            dataListener.onSuccessData(response);
        }, error -> dataListener.onErrorData("sjson hatası: "+error.getMessage())){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("yeniMasa",yeniMasa);
                param.put("siparis",obje.toString());
                return  param;
            }
        }
//        {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> header = new HashMap<>();
//                header.put("Content-Type", "application/json; charset=utf-8");
//                return header;
//            }
//        }
        ;
        Log.d("JOBJE", "yeniMasa:"+ yeniMasa+"  "+obje.toString());


        queue.add(jsonReq);
    }
}