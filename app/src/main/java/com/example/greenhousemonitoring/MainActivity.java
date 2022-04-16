package com.example.greenhousemonitoring;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final String MSG = "com.example.greenhouse.msg";

    public void login_refresh(View view) {
        String tag = "Refresh", msg = "checking for refresh button running";
        Log.i(tag, msg);
        Intent intent = new Intent(this, SensorData.class);
        EditText editText1 = findViewById(R.id.login_username);
        //String message = "Sensor Data";
        String message = "Sensor Data" + editText1.getText().toString();
        intent.putExtra(MSG, message);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide(); // this will remove action bar
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.black));
    }
}