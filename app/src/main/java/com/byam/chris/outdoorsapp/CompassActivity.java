package com.byam.chris.outdoorsapp;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Chris on 4/30/2016.
 */

//use geomagnetic field sensor and accelerometer to report compass bearing
//copied and pasted HRM code for now (going to be a little more complex)
public class CompassActivity extends AppCompatActivity implements SensorEventListener {

    Sensor s;
    SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.content_compass);

        sm = ((SensorManager)getSystemService(SENSOR_SERVICE));
        s = sm.getDefaultSensor(Sensor.TYPE_HEART_RATE);

        //restart activity if try again button is clicked
        Button tryAgain = (Button)findViewById(R.id.button);
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompassActivity.this, HRMActivity.class);
                startActivity(intent);
            }
        });
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
                TextView hrDisplay = (TextView)findViewById(R.id.textView2);
                hrDisplay.setText("" + (int) event.values[0] + " bpm");

                //pause sensor
                onPause();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){
    }

}
