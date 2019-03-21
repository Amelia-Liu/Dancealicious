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

    /*
    class MyRotation implements SensorEventListener {
        // definitions for sensor and manager
        private SensorManager SensorManager;
        private Sensor sensor;
        private Context sensorContext = null;

        public MyRotation(Context context) {
            sensorContext = context;
            initSensor();
        }

        public void start() {
            // enable our sensor when the activity is resumed, ask for 100 ms updates.
            SensorManager.registerListener(this, sensor,
                    100000);
        }

        public void stop() {
            // turn our sensor off
            SensorManager.unregisterListener(this);
        }

        public void initSensor() {
            // Get an instance of the SensorManager
            SensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
            sensor = SensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
        }

        public void onSensorChanged(SensorEvent event) {
            // Define display format
            String fmt = "X:%.2f Y:%.2f Z:%.2f";
            // check that the received event is the proper one and use event.values[] for sensor values
            if (event.sensor.getType() == Sensor.TYPE_GAME_ROTATION_VECTOR) {
                   float x = Math.abs(event.values[0]);
                   float y = Math.abs(event.values[1]);
                   float z = Math.abs(event.values[2]);

                ((TextView)dance.this.findViewById(R.id.rotationView)).setText(String.format(fmt,x,y,z));
                // ((TextView)NextActivity.this.findViewById(R.id.textView)).setText(String.format(fmt,event.values[0],event.values[1],event.values[2]));
                //Snackbar.make(findViewById(R.id.nextLayout),"Yeey...",Snackbar.LENGTH_LONG).show();
                //Toast.makeText(sensorContext,"Yeey...", Toast.LENGTH_SHORT).show();
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // needs to be here because we implement SensorEventListener
        }
    }
    */

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
                float x = Math.abs(event.values[0]);
                float y = Math.abs(event.values[1]);
                float z = Math.abs(event.values[2]);
                int total = Math.round(x + y + z);

                ((TextView)dance.this.findViewById(R.id.accelerometerView)).setText(Integer.toString(total));
                // ((TextView)NextActivity.this.findViewById(R.id.textView)).setText(String.format(fmt,event.values[0],event.values[1],event.values[2]));
                //Snackbar.make(findViewById(R.id.nextLayout),"Yeey...",Snackbar.LENGTH_LONG).show();
                //Toast.makeText(sensorContext,"Yeey...", Toast.LENGTH_SHORT).show();
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // needs to be here because we implement SensorEventListener

            //hoii
        }
    }
}
