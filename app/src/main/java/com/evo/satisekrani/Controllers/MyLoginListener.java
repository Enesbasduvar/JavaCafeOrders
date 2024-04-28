package com.evo.satisekrani.Controllers;

import org.json.JSONObject;

public interface MyLoginListener {
    void onSuccessLogin(String response, String user,String pass);
    void onErrorLogin(String error);
}
