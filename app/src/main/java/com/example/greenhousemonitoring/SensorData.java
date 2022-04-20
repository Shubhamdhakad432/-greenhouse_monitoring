package com.example.greenhousemonitoring;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
                    LineChart barChart_temp;
                    LineData barData_temp;
                    LineDataSet barDataSet_temp;
                    JSONArray result = response.getJSONArray("results");
                    JSONObject json_array_0 = result.getJSONObject(0);
                    double value0 = json_array_0.getDouble("value");
                    JSONObject json_array_1 = result.getJSONObject(1);
                    double value1 = json_array_1.getDouble("value");
                    JSONObject json_array_2 = result.getJSONObject(2);
                    double value2 = json_array_2.getDouble("value");
                    JSONObject json_array_3 = result.getJSONObject(3);
                    double value3 = json_array_3.getDouble("value");
                    JSONObject json_array_4 = result.getJSONObject(4);
                    double value4 = json_array_4.getDouble("value");
                    JSONObject json_array_5 = result.getJSONObject(5);
                    double value5 = json_array_5.getDouble("value");
                    JSONObject json_array_6 = result.getJSONObject(6);
                    double value6 = json_array_6.getDouble("value");
                    JSONObject json_array_7 = result.getJSONObject(7);
                    double value7 = json_array_7.getDouble("value");
                    barChart_temp=findViewById(R.id.temp_BarChart);
                    List<Entry> barEntries_temp = new ArrayList<>();
                    barEntries_temp.add(new Entry(0.9999999999f,0f));
                    barEntries_temp.add(new Entry(1,(float)value7));
                    barEntries_temp.add(new Entry(2,(float)value6));
                    barEntries_temp.add(new Entry(3,(float)value5));
                    barEntries_temp.add(new Entry(4,(float)value4));
                    barEntries_temp.add(new Entry(5,(float)value3));
                    barEntries_temp.add(new Entry(6,(float)value2));
                    barEntries_temp.add(new Entry(7,(float)value1));
                    barEntries_temp.add(new Entry(8,(float)value0));

                    barDataSet_temp=new LineDataSet(barEntries_temp,"Temperature");
                    barData_temp=new LineData(barDataSet_temp);
                    barChart_temp.setData(barData_temp);
//                    barDataSet_temp.setColors(ColorTemplate.JOYFUL_COLORS);
                    barDataSet_temp.setColors(ColorTemplate.rgb("#179D9A"));
                    barDataSet_temp.setValueTextColor(Color.BLACK);
                    barDataSet_temp.setValueTextSize(8f);

                    TextView textView = (TextView) findViewById(R.id.temp_display);
                    textView.setText(value0 + " ℃");

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
                    LineChart barChart_humid;
                    LineData barData_humid;
                    LineDataSet barDataSet_humid;
                    JSONArray result = response.getJSONArray("results");
                    JSONObject json_array_0 = result.getJSONObject(0);
                    double value0 = json_array_0.getDouble("value");
                    JSONObject json_array_1 = result.getJSONObject(1);
                    double value1 = json_array_1.getDouble("value");
                    JSONObject json_array_2 = result.getJSONObject(2);
                    double value2 = json_array_2.getDouble("value");
                    JSONObject json_array_3 = result.getJSONObject(3);
                    double value3 = json_array_3.getDouble("value");
                    JSONObject json_array_4 = result.getJSONObject(4);
                    double value4 = json_array_4.getDouble("value");
                    JSONObject json_array_5 = result.getJSONObject(5);
                    double value5 = json_array_5.getDouble("value");
                    JSONObject json_array_6 = result.getJSONObject(6);
                    double value6 = json_array_6.getDouble("value");
                    JSONObject json_array_7 = result.getJSONObject(7);
                    double value7 = json_array_7.getDouble("value");
                    barChart_humid=findViewById(R.id.humid_BarChart);
                    List<Entry> barEntries_humid = new ArrayList<>();
                    barEntries_humid.add(new Entry(0.9999999999f,0f));
                    barEntries_humid.add(new Entry(1,(float)value7));
                    barEntries_humid.add(new Entry(2,(float)value6));
                    barEntries_humid.add(new Entry(3,(float)value5));
                    barEntries_humid.add(new Entry(4,(float)value4));
                    barEntries_humid.add(new Entry(5,(float)value3));
                    barEntries_humid.add(new Entry(6,(float)value2));
                    barEntries_humid.add(new Entry(7,(float)value1));
                    barEntries_humid.add(new Entry(8,(float)value0));
                    barDataSet_humid=new LineDataSet(barEntries_humid,"Humidity");
                    barData_humid=new LineData(barDataSet_humid);
                    barChart_humid.setData(barData_humid);
                    barDataSet_humid.setColors(ColorTemplate.rgb("#179D9A"));
                    barDataSet_humid.setValueTextColor(Color.BLACK);
                    barDataSet_humid.setValueTextSize(8f);

                    TextView textView = (TextView) findViewById(R.id.hum_display);
                    textView.setText(value0 + " %RH");
//                    Log.d("volley", "The response is getting as : " + value);

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


        // light bar
        LineChart barChart_light;
        LineData barData_light;
        LineDataSet barDataSet_light;

        barChart_light=findViewById(R.id.light_BarChart);
        List<Entry> barEntries_light = new ArrayList<>();
        barEntries_light.add(new Entry(0.9999999999f,0f));
        barEntries_light.add(new Entry(1,501f));
        barEntries_light.add(new Entry(2,504.2f));
        barEntries_light.add(new Entry(3,508f));
        barEntries_light.add(new Entry(4,499.2f));
        barEntries_light.add(new Entry(5,505.5f));
        barEntries_light.add(new Entry(6,500f));
        barEntries_light.add(new Entry(7,502.8f));
        barEntries_light.add(new Entry(8,503.8f));
        barDataSet_light=new LineDataSet(barEntries_light,"Light");
        barData_light=new LineData(barDataSet_light);
        barChart_light.setData(barData_light);
        barDataSet_light.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet_light.setValueTextColor(Color.BLACK);
        barDataSet_light.setValueTextSize(8f);




        // soil bar
        LineChart barChart_soil;
        LineData barData_soil;
        LineDataSet barDataSet_soil;

        barChart_soil=findViewById(R.id.soil_BarChart);
        List<Entry> barEntries_soil = new ArrayList<>();
        barEntries_soil.add(new Entry(0.99999999f,0f));
        barEntries_soil.add(new Entry(1,33.5f));
        barEntries_soil.add(new Entry(2,35f));
        barEntries_soil.add(new Entry(3,32f));
        barEntries_soil.add(new Entry(4,30.2f));
        barEntries_soil.add(new Entry(5,45.5f));
        barEntries_soil.add(new Entry(6,28f));
        barEntries_soil.add(new Entry(7,31f));
        barEntries_soil.add(new Entry(8,30f));
        barDataSet_soil=new LineDataSet(barEntries_soil,"Soil moisture");
        barData_soil=new LineData(barDataSet_soil);
        barChart_soil.setData(barData_soil);
        barDataSet_soil.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet_soil.setValueTextColor(Color.BLACK);
        barDataSet_soil.setValueTextSize(8f);
    }

    public void login_actuator(View view) {
        Intent intent = new Intent(this, Actuator.class);
        startActivity(intent);
    }
}