package fr.iutbay.joey.TP5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AccueilActivity extends Activity 
{
	public TextView rubriqueSon, rubriqueEcran, rubriqueVeille, 
					subtitleSon, subtitleEcran, subtitleEcranBis, subtitleVeille;
	public int valueSound, valueLuminosity;
	public String valueVeille;
	
	public static final String INDEX_SON = "fr.iutbay.joey.TP5.volumeSon";
	public static final String INDEX_LUMINOSITE = "fr.iutbay.joey.TP5.luminosite";
	public static final String INDEX_ORIENTATION = "fr.iutbay.joey.TP5.orientation";
	public static final String INDEX_VEILLE = "fr.iutbay.joey.TP5.veille";

	public static final String INDEX_RESULTAT_SON = "fr.iutbay.joey.TP5.volumeSon_resultat";
	public static final String INDEX_RESULTAT_LUMINOSITE = "fr.iutbay.joey.TP5.luminosite_resultat";
	public static final String INDEX_RESULTAT_ORIENTATION = "fr.iutbay.joey.TP5.orientation_resultat";
	public static final String INDEX_RESULTAT_VEILLE = "fr.iutbay.joey.TP5.veille_resultat";
	
	public static final int ID_INTENT_SON = 1;
	public static final int ID_INTENT_ECRAN = 2;
	public static final int ID_INTENT_VEILLE = 3;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        
        rubriqueSon = (TextView) findViewById(R.id.reglageSon);
        rubriqueEcran = (TextView) findViewById(R.id.reglageEcran);
        rubriqueVeille = (TextView) findViewById(R.id.reglageVeille);

        rubriqueSon.setOnClickListener(new gotoSoundSetting());
        rubriqueEcran.setOnClickListener(new gotoScreenSetting());
        rubriqueVeille.setOnClickListener(new gotoVeilleSetting());
        
        subtitleSon = (TextView) findViewById(R.id.subtitleSon);
        subtitleEcran = (TextView) findViewById(R.id.subtitleEcran);
        subtitleEcranBis = (TextView) findViewById(R.id.subtitleEcranBis);
        subtitleVeille = (TextView) findViewById(R.id.subtitleVeille);
        
        valueSound = 50;
        valueLuminosity = 60;
		valueVeille = getString(R.string.uneHeure); 
		
        String textSon = this.getString(R.string.subtitleSon) + valueSound;
        String textEcran = this.getString(R.string.subtitleOneEcran);
        String textEcranBis = this.getString(R.string.subtitleTwoEcran) + valueLuminosity + "%";
        String textVeille = getString(R.string.subtitleVeille) + " " + valueVeille;
                
        subtitleSon.setText(textSon);
        subtitleEcran.setText(textEcran);
        subtitleEcranBis.setText(textEcranBis);
        subtitleVeille.setText(textVeille);
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    	switch(requestCode)
    	{
    		case ID_INTENT_SON:
    			valueSound = data.getExtras().getInt(INDEX_RESULTAT_SON);
    			String textSon = this.getString(R.string.subtitleSon) + valueSound;
    	        subtitleSon.setText(textSon);
    			break;
    		case ID_INTENT_ECRAN:
    			valueLuminosity = data.getExtras().getInt(INDEX_RESULTAT_LUMINOSITE);
    	        String textEcran = data.getExtras().getString(INDEX_RESULTAT_ORIENTATION);
    	        String textEcranBis = this.getString(R.string.subtitleTwoEcran) + valueLuminosity + "%";
    	        subtitleEcran.setText(textEcran);
    	        subtitleEcranBis.setText(textEcranBis);
    			break;
    		case ID_INTENT_VEILLE:
    			if(resultCode == RESULT_OK)
    			{
    				valueVeille = data.getExtras().getString(INDEX_RESULTAT_VEILLE); 
    				subtitleVeille.setText(getString(R.string.subtitleVeille) + " " + valueVeille);
    			}
    			break;
    	}
    }
    
    private class gotoSoundSetting implements OnClickListener
    {
		@Override
		public void onClick(View v) 
		{
			Intent menuSon = new Intent(AccueilActivity.this, VolumeActivity.class);
			menuSon.putExtra(INDEX_SON, valueSound);
			
			startActivityForResult(menuSon, ID_INTENT_SON);
		}
    }
    
    private class gotoScreenSetting implements OnClickListener
    {
		@Override
		public void onClick(View v) 
		{
			Intent menuEcran = new Intent(AccueilActivity.this, OrientationActivity.class);
			menuEcran.putExtra(INDEX_LUMINOSITE, valueLuminosity);
			menuEcran.putExtra(INDEX_ORIENTATION, getString(R.string.subtitleOneEcran));
			startActivityForResult(menuEcran, ID_INTENT_ECRAN);
		}
    }
    
    private class gotoVeilleSetting implements OnClickListener
    {
		@Override
		public void onClick(View v) 
		{
			Intent menuVeille = new Intent(AccueilActivity.this, VeilleActivity.class);
			menuVeille.putExtra(INDEX_VEILLE, valueVeille);
			startActivityForResult(menuVeille, ID_INTENT_VEILLE);
		}
    }
}
