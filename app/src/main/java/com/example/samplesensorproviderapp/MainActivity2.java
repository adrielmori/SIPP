package com.example.samplesensorproviderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private SensorManager sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        sensor = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        TextView textView = (TextView) findViewById(R.id.textView2);

        AccelerometerSensorAccess accAcess = new AccelerometerSensorAccess(sensor, textView);
    }
}