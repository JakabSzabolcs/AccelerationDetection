package hu.unideb.inf.mobilsz14.noisedetection;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AccelerationSensor implements SensorEventListener
{

    public void setPb(ProgressBar pb)
    {
        this.pb = pb;
    }

    public void setTv(TextView tv)
    {
        this.tv = tv;
    }

    private ProgressBar pb;
    private TextView tv;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        pb.setProgress(Math.abs((int)sensorEvent.values[0]*5));
//        tv.setText("" + sensorEvent.values[0]);
        System.out.println(sensorEvent.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
