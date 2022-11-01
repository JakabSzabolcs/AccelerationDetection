package hu.unideb.inf.mobilsz14.noisedetection;

import android.graphics.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Vibrator;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

public class AccelerationSensor implements SensorEventListener {


    private ProgressBar pb;
    private TextView tv;
    private Vibrator v;
    private CameraManager t;

    public void setPb(ProgressBar pb) {
        this.pb = pb;
    }

    public void setTv(TextView tv) {
        this.tv = tv;
    }

    public void SetVibrate(Vibrator v) {
        this.v = v;
    }

    public void SetTorch(CameraManager t) {
        this.t = t;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        try {

            getCameraID = t.getCameraIdList()[0];
            pb.setProgress(Math.abs((int) sensorEvent.values[0] * 5));
            //tv.setText("" + sensorEvent.values[0]);
            //System.out.println(sensorEvent.values[0]);
            //elágazások:
            float AccelerationValues = Math.abs(sensorEvent.values[0]);
            if (AccelerationValues <= 2) {
                t.setTorchMode(getCameraID, false);
                v.cancel();
                tv.setText("It's steady.");
            }
            else if (AccelerationValues <= 4 && AccelerationValues > 2)
            {
                t.setTorchMode(getCameraID, false);
                v.cancel();
                tv.setText("Well, it moves... Slowly");
            }
            else if (AccelerationValues <= 8 && AccelerationValues > 4)
            {
                t.setTorchMode(getCameraID, false);
                Thread.sleep(10);
                v.cancel();
                tv.setText("It's a great acceleration");
                t.setTorchMode(getCameraID, true);

                //villog pl háromszor 2sec alatt
            } else if (AccelerationValues <= 16 && AccelerationValues > 8) {
                t.setTorchMode(getCameraID, false);
                Thread.sleep(10);
                v.cancel();
                tv.setText("Woah, it's fast..");
                v.vibrate(100);
                t.setTorchMode(getCameraID, true);
                //gyorsabban villog és rezeg
            } else if (AccelerationValues > 16) {
                t.setTorchMode(getCameraID, false);
                Thread.sleep(10);
                v.cancel();
                tv.setText("Almost the speed of the light... :O");
                //folyamatosan világít és statikusan rezeg
                v.vibrate(200);
                t.setTorchMode(getCameraID, true);
            }
        } catch (CameraAccessException | InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private String getCameraID;
}

