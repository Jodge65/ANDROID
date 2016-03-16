package fr.iutbay.joey.TP1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class TP1Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tp1);
		ImageView barre = (ImageView) findViewById(R.id.separation10dp);
        new Anime(this, barre).start();        
	}
}
