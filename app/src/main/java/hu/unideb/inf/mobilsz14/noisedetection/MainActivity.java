package hu.unideb.inf.mobilsz14.noisedetection;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.lights.Light;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
{
    private Switch runOnBackGroundSwitch;
    private ProgressBar accelerationPb;
    private SensorManager sensorManager;
//    private TextView sensorTextView, accelerationTv;
    private Sensor mLight;
    private AccelerationSensor sel = new AccelerationSensor();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runOnBackGroundSwitch = findViewById(R.id.runOnBackgroundSwitch);
        accelerationPb = findViewById(R.id.accelerationPb);
//        accelerationTv = findViewById(R.id.accelerationTv);



        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(sel, mLight, SensorManager.SENSOR_DELAY_FASTEST);


        sel.setPb(accelerationPb);
        System.out.println(accelerationPb.getProgress());
        //sel.setTv(accelerationTv);
        //sensorTextView.setMovementMethod((new ScrollingMovementMethod()));
        //sensorTextView.setText(sensors.toString());

    }
}