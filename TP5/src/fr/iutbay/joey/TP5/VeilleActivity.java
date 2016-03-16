package fr.iutbay.joey.TP5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class VeilleActivity extends Activity {

	public RadioGroup tpsMiseEnVeille;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veille);
    	String valueSound = this.getIntent().getExtras().getString(AccueilActivity.INDEX_VEILLE);

    	RadioButton temps;
    	if(valueSound.equalsIgnoreCase(getString(R.string.trenteSeconde)))
    		temps = (RadioButton) findViewById(R.id.trenteSecondes);
    	else if (valueSound.equalsIgnoreCase(getString(R.string.uneMinute)))
    		temps = (RadioButton) findViewById(R.id.uneMinute);
    	else if (valueSound.equalsIgnoreCase(getString(R.string.quinzeMinutes)))
    		temps = (RadioButton) findViewById(R.id.quinzeMinutes);
    	else if (valueSound.equalsIgnoreCase(getString(R.string.uneHeure)))
    		temps = (RadioButton) findViewById(R.id.uneHeure);
    	else//if (valueSound.equalsIgnoreCase(getString(R.string.jamais)))
    		temps = (RadioButton) findViewById(R.id.jamais);
    	
    	temps.setChecked(true);
        tpsMiseEnVeille = (RadioGroup) findViewById(R.id.tpsMiseEnVeille);
        tpsMiseEnVeille.setOnCheckedChangeListener(new ChoisEffectuer());
    }
    
    private class ChoisEffectuer implements OnCheckedChangeListener
    {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) 
		{
			if(checkedId != -1)
			{
			    Intent result = new Intent();
			    RadioButton temps = (RadioButton) findViewById(checkedId);
			    //RadioButton temps = (RadioButton) group.getChildAt(checkedId);
		        result.putExtra(AccueilActivity.INDEX_RESULTAT_VEILLE, temps.getText());
		        setResult(RESULT_OK, result);
		        finish();
			}
		}
    }

    @Override
	public void onBackPressed()
    {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
