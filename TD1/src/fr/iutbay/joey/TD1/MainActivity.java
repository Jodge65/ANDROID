package fr.iutbay.joey.TD1;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;


@TargetApi(23) 
public class MainActivity extends Activity {

	protected TimePicker choixHeure;
	protected EditText afficherHeure;
	protected Button ok, annuler;
	protected RadioButton sonner, vibrer;
	
	protected boolean choixFait;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        choixHeure = (TimePicker)findViewById(R.id.timer);
        choixHeure.setIs24HourView(true);
        afficherHeure = (EditText)findViewById(R.id.affHeure);
        ok = (Button)findViewById(R.id.ok);
        annuler = (Button)findViewById(R.id.annuler);
        sonner = (RadioButton)findViewById(R.id.sonnerieRadiButton);
        vibrer = (RadioButton)findViewById(R.id.vibreurRadiButton);
        
        
        choixHeure.setOnTimeChangedListener(new ChangerHeure());
        ok.setOnClickListener(new Ok());
        annuler.setOnClickListener(new Annuler());
        choixHeure.setOnTimeChangedListener(new ChangerHeure());
        
        afficherHeure.setOnEditorActionListener(new modifierHeure());
        choixFait = false;
        
        
        ThreadTimer t = new ThreadTimer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private class ChangerHeure implements OnTimeChangedListener
    {


		@Override
		public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
    		afficherHeure.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
    		choixFait = true;			
		}
    }
    
    private class Annuler implements OnClickListener
    {
    	@Override
    	public void onClick(View we)
    	{
    		afficherHeure.setText("");
    		choixFait = false;
    	}
    }
   
    private class Ok implements OnClickListener
    {
		@Override
		public void onClick(View v) {
    		afficherHeure.setText("");
    		choixFait = false;
		}
    }
    
    private class ModifierHeure implements OnEditorActionListener
    {
		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) 
		{
			int heures = -1;
			int minutes = -1;
			if(actionId == EditorInfo.IME_ACTION_DONE)
			{
				String type = afficherHeure.getText().toString();
				String partie[] = type.split(":");
				if(partie.length == 2)
				{
					try
					{
						heures = Integer.parseInt(partie[0]);
						minutes = Integer.parseInt(partie[1]);
					}
					catch (NumberFormatException e)
					{
						//heures = -1;
						//minutes = -1;
					}
				}
			}
			/*else
			{
				heures = -1;
				minutes = -1;
			}*/
			
			if(heures >= 0 && minutes >= 0)
			{
				choixHeure.setHour(heures);
				choixHeure.setMinute(minutes);
				choixFait = true;
			}
			else
			{
				afficherHeure.setText("");
				choixFait = false;
				Toast.makeText(getcontext(), ".......", Toast.LENGTH_LONG).show();
			}
			return false;
		}

    }
    
    public class ThreadTimer extends Thread
    {
    	private long delai;
    	public ThreadTimer()
    	{
    		delai = choixHeure.getHour()*3600 + choixHeure.getMinute();
    		start();
    	}
    	
    	
    	public synchronized void run()
    	{
    		sleep(delai*1000);
    		
    		if(vibrer.isChecked())
    		{
    			Vibrator vibreur = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

    			vibreur.vibrate(3000);
    		}
    		else
    		{
    			MediaPlayer sonner = MediaPlayer.create(/*MainActivity.this.getContext()*/getBaseContext(), R.id.__);
    			sonner.start();
    		}
    	}
    }
    
    
}
