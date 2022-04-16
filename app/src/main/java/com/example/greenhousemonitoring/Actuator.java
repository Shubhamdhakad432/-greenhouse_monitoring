package com.example.greenhousemonitoring;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import javax.net.ssl.SSLSocketFactory;
import java.util.Arrays;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Actuator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actuator);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.MSG);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Actuator Module");;
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        getWindow().setStatusBarColor(ContextCompat.getColor(Actuator.this, R.color.black));
    }

    public static void main(String[] args) throws MqttException {
        MqttClient client = new MqttClient(
                "mqtt://localhost:5167", // serverURI in format: "protocol://name:port"
                MqttClient.generateClientId(), // ClientId
                new MemoryPersistence()); // Persistence

        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        // mqttConnectOptions.setUserName("mqtt");
        // mqttConnectOptions.setPassword("ShubhamDhakad".toCharArray());
        mqttConnectOptions.setSocketFactory(SSLSocketFactory.getDefault()); // using the default socket factory
        client.connect(mqttConnectOptions);

        client.setCallback(new MqttCallback() {

            @Override
            // Called when the client lost the connection to the broker
            public void connectionLost(Throwable cause) {
                System.out.println("We have lost the client connection ^_^ " + cause);
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                System.out.println("tempAct" + ": " + Arrays.toString(message.getPayload()));
            }

            @Override
            // Called when an outgoing publish is complete
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("delivery complete " + token);
            }
        });

        client.subscribe("#", 1); // subscribe to everything with QoS = 1

        client.publish(
                "topic",
                "payload".getBytes(UTF_8),
                2, // QoS = 2
                false);

        client.disconnect();
    }
}