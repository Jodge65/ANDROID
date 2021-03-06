package fr.iutbay.joey.TP3;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Spinner;


public class MainActivity extends Activity {

	public ImageView preview;
	public Gallery gallery;
	public Spinner typeImageSelector;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        preview = (ImageView) findViewById(R.id.image);
        gallery = (Gallery) findViewById(R.id.gallery);
        typeImageSelector = (Spinner) findViewById(R.id.modeSelector);
        
        gallery.setAdapter(new GestionnaireDeConteneur(this, 150, 150)); 
        gallery.setOnItemSelectedListener(new SelectionImage());
        typeImageSelector.setOnItemSelectedListener(new SelectImageType());
    }

    private class SelectImageType implements OnItemSelectedListener
    {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) 
		{
			Object typeScale = typeImageSelector.getSelectedItem();
			ScaleType scale = ScaleType.valueOf((String) typeScale);
			preview.setScaleType(scale);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) 
		{
			
		}
    }
    
    private class SelectionImage implements OnItemSelectedListener
    {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) 
		{
			Object image = gallery.getSelectedItem();
			preview.setScaleType(ScaleType.MATRIX);
			preview.setImageDrawable((Drawable) image);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) 
		{
			
		}
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
