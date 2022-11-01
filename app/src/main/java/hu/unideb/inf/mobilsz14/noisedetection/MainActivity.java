package hu.unideb.inf.mobilsz14.noisedetection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.lights.Light;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.method.ScrollingMovementMethod;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
{
    private Vibrator vibrator;
    private Switch runOnBackGroundSwitch;
    private ProgressBar accelerationPb;
    private SensorManager sensorManager;
    private CameraManager cameraManager;
    private TextView sensorTextView, accelerationTv;
    private Sensor mLight;
    private AccelerationSensor sel = new AccelerationSensor();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runOnBackGroundSwitch = findViewById(R.id.runOnBackgroundSwitch);
        accelerationPb = findViewById(R.id.accelerationPb);
        accelerationTv = findViewById(R.id.accelerationTv);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//        accelerationTv = findViewById(R.id.accelerationTv);



        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(sel, mLight, SensorManager.SENSOR_DELAY_FASTEST);
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        sel.setPb(accelerationPb);
        sel.setTv(accelerationTv);
        sel.SetVibrate(vibrator);
        sel.SetTorch(cameraManager);
        //sensorTextView.setMovementMethod((new ScrollingMovementMethod()));
        //sensorTextView.setText(sensors.toString());

    }

}