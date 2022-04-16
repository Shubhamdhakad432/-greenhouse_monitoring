package com.example.greenhousemonitoring;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SensorData extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_data);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.MSG);
        //TextView textView = findViewById(R.id.display);
        //textView.setText(message);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Sensor Module");
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        getWindow().setStatusBarColor(ContextCompat.getColor(SensorData.this, R.color.black));

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest_temp = new JsonObjectRequest(Request.Method.GET,
                "https://industrial.api.ubidots.com/api/v1.6/devices/arduino-test-01/temp/values", null, new Response.Listener<JSONObject>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray result = response.getJSONArray("results");
                    JSONObject json_array_0 = result.getJSONObject(0);
                    String value = json_array_0.getString("value");
                    TextView textView = (TextView) findViewById(R.id.temp_display);
                    textView.setText(value + " ℃");
                    Log.d("volley", "The response is getting as : " + value);
                } catch (JSONException e) {
                    Log.d("volley", "Something went wrong while get string");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley", "Something went wrong");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headerMap = new HashMap<String, String>();
                String token = "BBFF-Aj5j6gFsJdYsEbArY01ArjHFDhsuSp";
                headerMap.put("X-Auth-Token", token);
                return headerMap;
            }
        };
        requestQueue.add(jsonObjectRequest_temp);


        JsonObjectRequest jsonObjectRequest_humid = new JsonObjectRequest(Request.Method.GET,
                "https://industrial.api.ubidots.com/api/v1.6/devices/arduino-test-01/humid/values", null, new Response.Listener<JSONObject>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray result = response.getJSONArray("results");
                    JSONObject json_array_0 = result.getJSONObject(0);
                    String value = json_array_0.getString("value");
                    TextView textView = (TextView) findViewById(R.id.hum_display);
                    textView.setText(value + " %RH");
                    Log.d("volley", "The response is getting as : " + value);
                } catch (JSONException e) {
                    Log.d("volley", "Something went wrong while get string");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley", "Something went wrong");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headerMap = new HashMap<String, String>();
                String token = "BBFF-Aj5j6gFsJdYsEbArY01ArjHFDhsuSp";
                headerMap.put("X-Auth-Token", token);
                return headerMap;
            }
        };
        requestQueue.add(jsonObjectRequest_humid);
    }

    public void login_actuator(View view) {
        Intent intent = new Intent(this, Actuator.class);
        startActivity(intent);
    }
}