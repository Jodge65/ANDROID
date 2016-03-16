package fr.iutbay.joey.TP1;

import android.app.Activity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Anime extends Thread {
	private ImageView barre;
	private Activity activite;
	
	public Anime(Activity a, ImageView b) { activite = a; barre = b; }
	
	public void run() 
	{
		while(true) 
		{
	        Animation animEfface = AnimationUtils.loadAnimation(activite, R.anim.efface);
	        activite.runOnUiThread(new JoueAnimation(animEfface));
	        try{ sleep(2000); } 
			catch (InterruptedException e){}
		}
	}
	
	private class JoueAnimation implements Runnable 
	{
		private Animation animation;
		
		public JoueAnimation(Animation a) 
		{ 
			animation = a; 
		}
		
		public void run() 
		{ 
			barre.startAnimation(animation); 
		}
	}

}

