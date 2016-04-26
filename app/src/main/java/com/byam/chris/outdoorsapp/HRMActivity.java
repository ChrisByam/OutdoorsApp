package com.byam.chris.outdoorsapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class HRMActivity extends AppCompatActivity implements SensorEventListener {

    //initialize any UI elements

    Sensor s;
    SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_hrm);

        //initialize Sensor and SensorManager
        sm = ((SensorManager)getSystemService(SENSOR_SERVICE));
        s = sm.getDefaultSensor(Sensor.TYPE_HEART_RATE);

        //interact with view here...setOnInflatedListener(...)
    }

    @Override
    protected void onResume(){
        super.onResume();
        //register listener
        if (sm != null){
            sm.registerListener(this, s, sm.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        //unregister listener
        if (sm != null){
            sm.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event){

        //check that sensor type is correct
        if (event.sensor.getType() == Sensor.TYPE_HEART_RATE){
            //check that value was registered...what to do if it's not?
            if ((int)event.values[0] > 0){
                //set views in activity to result
            }
        }
    }

}
