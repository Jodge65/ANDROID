package fr.iutbay.joey.TD5;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends Activity {

	public ScrollView view;
	public ImageView image;
	public Sensor capteur;
	public SensorManager manager;
	public OnRotationPhone ecouteur;
	public boolean posIni;
	public float positionIni;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        view = (ScrollView) findViewById(R.id.view);
        image = (ImageView) findViewById(R.id.image);
        
        posIni = false;
        manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        capteur = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        ecouteur = new OnRotationPhone();
        manager.registerListener(new OnRotationPhone(), Sensor.TYPE_ORIENTATION);
    }
    
    @Override
    protected void onPause()
    {
    	if(capteur != null)
    	{
    		manager.unregisterListener(ecouteur);
    	}
    }
    
    @Override
    protected void onResume()
    {
    	if(capteur != null)
    	{
            manager.registerListener(new OnRotationPhone(), Sensor.TYPE_ORIENTATION);
    	}
    }
    private class OnRotationPhone implements SensorListener
    {
    	private float[] oldRotation;
		@Override
		public void onSensorChanged(int sensor, float[] values) 
		{
			float[] rotation = values.clone();

			if(oldRotation == null)
				oldRotation = rotation.clone();
			int scrollValueZ = 0;
			int scrollValueY = 0;
			if(rotation[2] > 10.0F || rotation[2] < 10.0F )
			{
				scrollValueZ = (int) (rotation[2]);
				if(scrollValueZ < 0)
					scrollValueZ *= -1;	
			}
			if(rotation[1] > 10.0F || rotation[1] < 10.0F )
			{
				scrollValueY = (int) (oldRotation[1]);
				if(scrollValueY < 0)	
					scrollValueY *= -1;
			}
			view.smoothScrollBy(scrollValueZ, scrollValueY);
			
			if(!posIni)
			{
				posIni = true;
				positionIni = rotation[0];
			}
			float angleZ = rotation[0] - positionIni;
			image.setPivotX(image.getWidth() / 2);
			image.setPivotY(image.getHeight() / 2);
			image.setRotation(-angleZ);
			float scale;
			if(angleZ > 0)
			{
				scale = angleZ / 20 + 1;
			}
			else
			{
				scale = angleZ / 200 + 1;
			}

			image.setScaleX(scale);
			image.setScaleY(scale);
			
		}

		@Override
		public void onAccuracyChanged(int sensor, int accuracy) 
		{

		}
    	
    }
}
