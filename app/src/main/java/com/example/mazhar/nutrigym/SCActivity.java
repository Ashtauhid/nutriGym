package com.example.mazhar.nutrigym;

/**
 * Created by mazhar on 5/6/2017.
 */
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class SCActivity extends AppCompatActivity implements SensorEventListener {

    TextView tv_steps;
    int steps;
    EditText stride;
    Button stridebtn;
    SensorManager sensorManager;
    boolean running = false;
    int length;
    TextView viewthis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sc);


        viewthis = (TextView) findViewById(R.id.viewthis);
        stridebtn = (Button) findViewById(R.id.stridebtn);
        tv_steps = (TextView) findViewById(R.id.tv_steps);
        stride = (EditText) findViewById(R.id.stride);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        stridebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                length = Integer.parseInt(stride.getText().toString());
                double happen = length*steps*0.0254;
                viewthis.setText(String.valueOf(happen));

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if(countSensor!=null){
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        }
        else{
            Toast.makeText(this, "Sensor not found!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(running){
            Calendar c = Calendar.getInstance();

            int Hr24=c.get(Calendar.HOUR_OF_DAY);
            if(Hr24==0){
                event.values[0]=0;
            }
            String t = String.valueOf(event.values[0]);
            tv_steps.setText(String.valueOf(event.values[0]));
            steps = Integer.parseInt(t);

        }
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
