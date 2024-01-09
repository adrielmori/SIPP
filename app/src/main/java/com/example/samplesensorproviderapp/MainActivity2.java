package com.example.samplesensorproviderapp;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SensorManager sensor = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        TextView textView = (TextView) findViewById(R.id.textView2);

        new AccelerometerSensorAccess(sensor, textView);
    }
}