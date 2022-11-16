package hu.unideb.inf.mobilsz14.noisedetection;

import android.annotation.SuppressLint;
import android.graphics.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Vibrator;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

public class AccelerationSensor implements SensorEventListener {


    private ProgressBar pb;
    private TextView tv, tTv;
    private MediaPlayer ws;

    public void setPb(ProgressBar pb) {
        this.pb = pb;
    }

    public void setTv(TextView tv) {
        this.tv = tv;
    }

    public void setTTv(TextView tTv){this.tTv = tTv;}

    public void setMedia(MediaPlayer winSound){ this.ws = winSound;}

    public float maxAcceleration = 0;




    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        try {


            //tv.setText("" + sensorEvent.values[0]);
            //System.out.println(sensorEvent.values[0]);
            //elágazások:
            float AccelerationValues = Math.abs(sensorEvent.values[0]);
            pb.setProgress((int)Math.abs(AccelerationValues / 78 * 100));
            if(maxAcceleration<AccelerationValues)
            {
                maxAcceleration = AccelerationValues;
                tTv.setText("");
            }
            tv.setText((int)(AccelerationValues/78*100)+"%");


            if(maxAcceleration >= 78)
            {
                tTv.setText("You win.");
                ws.start();
                Thread.sleep(1000);
                maxAcceleration = 0;
//                tTv.setText(" ");

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}

