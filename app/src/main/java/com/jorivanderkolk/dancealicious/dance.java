package com.jorivanderkolk.dancealicious;


import android.app.Activity;
import android.content.Context;
import android.hardware.GeomagneticField;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class dance extends AppCompatActivity {
    //MyRotation myRotation;
    MyAccelSensor myAccelSensor;

    private float avgX = 10, avgY = 0, avgZ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dance);
        //myRotation = new MyRotation(this);
        myAccelSensor = new MyAccelSensor(this);

    }

    @Override
    protected void onResume() {
        // implement onResume() and onPause() to take appropriate action when the activity looses focus
        super.onResume();
        // start sensor listener when activity is started
        //myRotation.start();
        myAccelSensor.start();

    }

    @Override
    protected void onPause() {
        // implement onResume() and onPause to take appropriate action when the activity looses focus
        super.onPause();
        // stop sensor listener off when the activity is paused
        //myRotation.stop();
        myAccelSensor.stop();

    }

    class MyAccelSensor implements SensorEventListener {
        // definitions for sensor and manager
        private SensorManager mSensorManager;
        private Sensor mAccelerometer;
        private Context sensorContext = null;

        public MyAccelSensor(Context context) {
            sensorContext = context;
            initSensor();
        }

        public void start() {
            // enable our sensor when the activity is resumed, ask for 100 ms updates.
            mSensorManager.registerListener(this, mAccelerometer,
                    100000);
        }

        public void stop() {
            // turn our sensor off
            mSensorManager.unregisterListener(this);
        }

        public void initSensor() {
            // Get an instance of the SensorManager
            mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
            mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

        public void onSensorChanged(SensorEvent event) {
            // Define display format
            String fmt = "%.2f";
            // check that the received event is the proper one and use event.values[] for sensor values
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                // get the absolute value of each axis
                float x = Math.abs(event.values[0]);
                float y = Math.abs(event.values[1]);
                float z = Math.abs(event.values[2]);
                // get the average of the three axis
                avgX = (999 * avgX + x) / 1000;
                avgY = (999 * avgY + y) / 1000;
                avgZ = (999 * avgZ + z) / 1000;
                //compute one activity level
                float activity = (avgX + avgY + avgZ - 10)/10 * 100;
                int result = Math.round(activity); //get an integer to show on in the app

                ((TextView)dance.this.findViewById(R.id.accelerometerView)).setText(Integer.toString(result));
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // needs to be here because we implement
        }
    }
}
