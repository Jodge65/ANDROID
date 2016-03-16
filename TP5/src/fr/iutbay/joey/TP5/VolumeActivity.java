package fr.iutbay.joey.TP5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.CheckBox;

public class VolumeActivity extends Activity {

	public SeekBar sliderBar;
	public CheckBox silencieux;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);
        
        sliderBar = (SeekBar) findViewById(R.id.volumeBar);
        silencieux = (CheckBox) findViewById(R.id.onOffSilencieux);
        
        int valueSound = this.getIntent().getExtras().getInt(AccueilActivity.INDEX_SON);//savedInstanceState.getString("test");
        sliderBar.setProgress(valueSound);

        silencieux.setOnCheckedChangeListener(new ClickSilencieux());
        sliderBar.setOnSeekBarChangeListener(new ChangeValue());
    }
    
    @Override
	public void onBackPressed()
    {
        Intent result = new Intent();
        result.putExtra(AccueilActivity.INDEX_RESULTAT_SON, sliderBar.getProgress());
        setResult(RESULT_OK, result);
        super.onBackPressed();
    }
    
    
    private class ChangeValue implements OnSeekBarChangeListener
    {
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			if(progress == 0)
				silencieux.setChecked(true);
			else
			{
				silencieux.setClickable(true);
				silencieux.setChecked(false);
			}
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {	}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) { }
    	
    }
    
    private class ClickSilencieux implements OnCheckedChangeListener
    {
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
		{
			if(isChecked)
			{
				sliderBar.setProgress(0);
				silencieux.setClickable(false);				
			}
		}
    	
    }
}
