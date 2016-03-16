package fr.iutbay.joey.TP5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;

public class OrientationActivity extends Activity {

	public Button leave;
	public SeekBar sliderBar;
	public Spinner choixOrientation;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation);
        
        int valueLuminosite = this.getIntent().getExtras().getInt(AccueilActivity.INDEX_LUMINOSITE);
        
        leave = (Button) findViewById(R.id.leave);
        sliderBar = (SeekBar) findViewById(R.id.luminositeBar);
        choixOrientation = (Spinner) findViewById(R.id.orientation);
        
        sliderBar.setProgress(valueLuminosite);
        leave.setOnClickListener(new LeaveButton());

    }
    
    @Override
	public void onBackPressed()
    {
    	// do nothing
    }
    
    public class LeaveButton implements OnClickListener
    {    
		@Override
		public void onClick(View v) 
		{
	        Intent result = new Intent();
	        result.putExtra(AccueilActivity.INDEX_RESULTAT_LUMINOSITE, sliderBar.getProgress());
	        result.putExtra(AccueilActivity.INDEX_RESULTAT_ORIENTATION, (String)choixOrientation.getSelectedItem());
	        setResult(RESULT_OK, result);
	        finish();
		}
	}
}
