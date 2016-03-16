package fr.iutbay.joey.TD1;

import android.content.Context;
import android.os.Vibrator;

public class ThreadTimer extends Thread
{
	Vibrator vibreur;
	public ThreadTimer(Vibrator vibreur)
	{
		this.vibreur = vibreur;
	}
	
	
	public synchronized void run()
	{
		
		vibreur.vibrate(1000);
	}
}
