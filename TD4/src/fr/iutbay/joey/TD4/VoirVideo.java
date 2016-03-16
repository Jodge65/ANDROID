package fr.iutbay.joey.TD4;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VoirVideo  extends Activity 
{
	protected VideoView ecran;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ecran = (VideoView) findById(R.id.ecran);
        
        Uri chemin = Uri.parse("android.ressources://itubayonne.univ-pau"+R.row.video_de_test);
        ecran.setVideoURI(chemin);
        ecran.setMediaController(new MediaController(this));
        ecran.setOnCompletionListener(new FinVideo());
        ecran.requestFocus();
        ecran.start();
    }
    
    private class FinVideo implements OnCompletionListener
    {
		@Override
		public void onCompletion(MediaPlayer mp) 
		{
			ecran.stopPlayback();
			ecran.clearFocus();
			finish();
		}
    	
    }
}
