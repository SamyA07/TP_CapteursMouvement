package com.example.sameur.tp_capteursmouvement;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9;
    SensorManager sensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView7 = (TextView) findViewById(R.id.textView7);
        textView8 = (TextView) findViewById(R.id.textView8);
        textView9 = (TextView) findViewById(R.id.textView9);
        textView1.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
        textView3.setVisibility(View.INVISIBLE);
        textView4.setVisibility(View.INVISIBLE);
        textView5.setVisibility(View.INVISIBLE);
        textView6.setVisibility(View.INVISIBLE);
    }

    public void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorListener, sensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(sensorListener);
    }

    public SensorEventListener sensorListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int acc) {
        }

        public void onSensorChanged(SensorEvent event) {
            //Récupération des valeurs du Sensor
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            int axeX = (int) x;
            int axeY = (int) y;
            int axeZ = (int) z;

            textView7.setText("X : " + axeX);
            textView8.setText("Y : " + axeY);
            textView9.setText("Z : " + axeZ);

            if (axeX>0 && axeY==0){
                textView2.setVisibility(View.VISIBLE);
            }
            else if (axeX<0 && axeY==0){
                textView1.setVisibility(View.VISIBLE);
            }
            else {
                textView1.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.INVISIBLE);
            }

            if (axeY>0 && axeX==0){
                textView3.setVisibility(View.VISIBLE);
            }
            else if (axeY<0 && axeX==0){
                textView4.setVisibility(View.VISIBLE);
            }
            else {
                textView3.setVisibility(View.INVISIBLE);
                textView4.setVisibility(View.INVISIBLE);
            }

            if (axeZ>0 && axeX==0 && axeY ==0){
                textView6.setVisibility(View.VISIBLE);
            }
            else if (axeZ<0 && axeX==0 && axeY ==0){
                textView5.setVisibility(View.VISIBLE);
            }
            else {
                textView5.setVisibility(View.INVISIBLE);
                textView6.setVisibility(View.INVISIBLE);
            }
        }

    };
}
