package fr.iutbay.joey.TD3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.view.View.OnClickListener;

public class MenuActivity extends Activity {

	public Button nextActivity;
	public RadioButton racine, sdCard;
	public static final String NOM_PARENT = "depart";
	public EditText chemin, size;
	public CheckBox r, w, e, h;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        
        chemin = (EditText)findViewById(R.id.chemin);
        size = (EditText)findViewById(R.id.size);

        r = (CheckBox)findViewById(R.id.readable);
        w = (CheckBox)findViewById(R.id.writable);
        e = (CheckBox)findViewById(R.id.executable);
        h = (CheckBox)findViewById(R.id.hidden);

        racine = (RadioButton)findViewById(R.id.root);
        sdCard = (RadioButton)findViewById(R.id.sdCard);
        nextActivity = (Button)findViewById(R.id.parcourir);
        nextActivity.setOnClickListener( new goToListFile());

    }
  
    @Override
    protected void onActivityResult(int id, int codeRetour, Intent result) 
    {
    	switch(id)
    	{
    		case 3:
    			if(codeRetour==RESULT_OK && result != null)
    			{
    				chemin.setText(result.getStringExtra("chemin"));
    				size.setText(String.valueOf(result.getLongExtra("taille", 0L)));
    				
    				r.setChecked(result.getBooleanExtra("r", false));
    				w.setChecked(result.getBooleanExtra("w", false));
    				e.setChecked(result.getBooleanExtra("x", false));
    				h.setChecked(result.getBooleanExtra("cache", false));
    			}
    		
    	}
    }
    
    private class goToListFile implements OnClickListener	
    {
		@Override
		public void onClick(View v) 
		{
			Intent demarre = new Intent(MenuActivity.this, MainActivity.class);
			String chemin;
			if(racine.isChecked())
				chemin = "/";
			else
				chemin = Environment.getExternalStorageDirectory().getAbsolutePath();
			demarre.putExtra(NOM_PARENT, chemin);
			startActivity(demarre);
		}
    }
    
}
