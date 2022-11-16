package hu.unideb.inf.mobilsz14.noisedetection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.lights.Light;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
{
    private ProgressBar accelerationPb;
    private SensorManager sensorManager;
    private TextView textTextView, accelerationTv;
    private Sensor Acceleration;
    private AccelerationSensor sel = new AccelerationSensor();
    private MediaPlayer WinSound = new MediaPlayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accelerationPb = findViewById(R.id.accelerationPb);
        accelerationTv = findViewById(R.id.accelerationTv);
        textTextView = findViewById(R.id.testTextView);
        WinSound = MediaPlayer.create(this, R.raw.winsound);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        Acceleration = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(sel, Acceleration, SensorManager.SENSOR_DELAY_FASTEST);

        sel.setPb(accelerationPb);
        sel.setTv(accelerationTv);
        sel.setMedia(WinSound);
        sel.setTTv(textTextView);
        //sensorTextView.setMovementMethod((new ScrollingMovementMethod()));
        //sensorTextView.setText(sensors.toString());

    }


    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResumed called", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause called", Toast.LENGTH_LONG).show();
    }



}