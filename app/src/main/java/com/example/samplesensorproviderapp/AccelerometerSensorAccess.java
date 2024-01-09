package com.example.samplesensorproviderapp;

import static com.example.samplesensorproviderapp.MainActivity.brokerURI;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client;

import java.util.UUID;

public class AccelerometerSensorAccess implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor mAccelerometer;
    private TextView sensor_field;

    public AccelerometerSensorAccess(SensorManager sm, TextView tv) {
        sensorManager = sm;
        sensor_field = tv;
        mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void publishMessage(String value) {
        Mqtt5BlockingClient client = Mqtt5Client.builder()
                .identifier(UUID.randomUUID().toString())
                .serverHost(brokerURI)
                .buildBlocking();

        client.connect();
        client.publishWith()
                .topic("Accelerometer")
                .qos(MqttQos.AT_LEAST_ONCE)
                .payload(value.getBytes())
                .send();
        client.disconnect();
    }
    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        // The light sensor returns a single value.
        float lux = event.values[0];
        // Show luminosity value on the text field
        sensor_field.setText(String.valueOf(lux));
        publishMessage(String.valueOf(lux));
    }

    @Override
    protected void finalize() {
        sensorManager.unregisterListener(this);
    }
}