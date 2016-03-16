package fr.iutbya.joey.TP2;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class MainActivity extends Activity 
{
	public TimePicker heureLocal, heureDistante;
	public Spinner listePays;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        heureLocal = (TimePicker) findViewById(R.id.heureAConvertir);
        heureDistante = (TimePicker) findViewById(R.id.heureConvertie);
        listePays = (Spinner) findViewById(R.id.choixPays);
        
        Calendar date = Calendar.getInstance();
        changeTime(heureLocal, date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE));
        
        listePays.setOnItemSelectedListener(new choixFuseau());
        heureLocal.setOnTimeChangedListener(new changerHeureAConvertir());
    }
    
    public void changeTime(TimePicker t, int hours, int minutes)
    {
        t.setCurrentHour(hours);
        t.setCurrentMinute(minutes);
    }   
    
    public void changeTime()
    {
    	int hours = heureLocal.getCurrentHour();
		int minutes = heureLocal.getCurrentMinute();

		String index = (String) listePays.getSelectedItem();
		if(index.equalsIgnoreCase("Chili"))
			hours-=5;
		else if(index.equalsIgnoreCase("Inde"))
		{
			minutes+=30;
			if(minutes >= 60)
			{
				hours++;
				minutes-=60;
			}
			hours+=5;
		}
		else if(index.equalsIgnoreCase("Japon"))
			hours+=8;
		else if(index.equalsIgnoreCase("Sénégal"))
			hours-=1;
		
		if (hours < 0)
			hours+=24;
		else if (hours > 24)
			hours-=24;
        changeTime(heureDistante, hours, minutes);	
    }
    
    private class changerHeureAConvertir implements OnTimeChangedListener
    {

		@Override
		public void onTimeChanged(TimePicker view, int hourOfDay, int minute) 
		{
			changeTime();			
		}
    	
    }
    
    private class choixFuseau implements OnItemSelectedListener
    {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) 
		{
			changeTime();
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent){}
    }
    
}
