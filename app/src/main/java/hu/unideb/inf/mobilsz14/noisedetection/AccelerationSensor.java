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

public class AccelerationSensor implements SensorEventListener
{



    private ProgressBar pb;
    private TextView tv;
    private Vibrator v;
    private CameraManager t;

    public void setPb(ProgressBar pb)
    {
        this.pb = pb;
    }

    public void setTv(TextView tv)
    {
        this.tv = tv;
    }

    public void SetVibrate(Vibrator v)
    {
        this.v = v;
    }

    public void SetTorch(CameraManager t)
    {
        this.t = t;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        pb.setProgress(Math.abs((int)sensorEvent.values[0]*5));
        //tv.setText("" + sensorEvent.values[0]);
        //System.out.println(sensorEvent.values[0]);
        //elágazások:
        float AccelerationValues = Math.abs(sensorEvent.values[0]);
        if(AccelerationValues == 0)
        {
            v.cancel();
            tv.setText("It's steady.");
        }
        else if(AccelerationValues <= 2 && AccelerationValues >= 0.1)
        {
            v.cancel();
            tv.setText("Well, it moves... Slowly");
        }
        else if(AccelerationValues <= 6 && AccelerationValues > 2)
        {
            v.cancel();
            tv.setText("It's a great acceleration");
            LightTorch(3);
            //villog pl háromszor 2sec alatt
        }
        else if(AccelerationValues <= 12 && AccelerationValues > 6)
        {
            LightTorch(0);
            v.cancel();
            tv.setText("Woah, it's fast..");
            v.vibrate(100);
            LightTorch(8);
            //gyorsabban villog és rezeg
        }
        else if (AccelerationValues > 12)
        {
            v.cancel();
            tv.setText("Almost the speed of the light... :O");
            //folyamatosan világít és statikusan rezeg
            v.vibrate(10000);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private String getCameraID;

    public void LightTorch(int times) //torch flashes up count/second times
    {
        try {
            getCameraID = t.getCameraIdList()[0];
            t.setTorchMode(getCameraID, false);

            for(int i = 0; i < times; i++)
            {
                //flash & sleep
                t.setTorchMode(getCameraID, true);
                Thread.sleep(10);
                t.setTorchMode(getCameraID, false);
                Thread.sleep((int)1000/times);

            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
