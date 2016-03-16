package fr.iutbay.joey.TD4;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	public Button testAudio, testVideo;
	public TextView urlText;
	protected MediaPlayer jouerSon;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        testAudio = (Button)findViewById(R.id.testAudio);
        testAudio.setOnClickListener(new onTestAudioClick());

        testVideo = (Button)findViewById(R.id.testVideo);
        testVideo.setOnClickListener(new onTestVideoClick());
        
        urlText = (TextView)findViewById(R.id.urlText);

    }
    
    private class onTestAudioClick implements OnClickListener
    {
		@Override
		public void onClick(View v) 
		{
			String saisie = (String) urlText.getText();
			if(saisie.length() == 0)
			{
				jouerSon = MediaPlayer.create(MainActivity.this, R.row.son_de_test);

			}
			else
			{
				try
				{
			        Uri chemin = Uri.parse("android.ressources://itubayonne.univ-pau"+R.row.son_de_test);
					jouerSon = MediaPlayer.create(MainActivity.this, chemin);	
					/*jouerSon = new MediaPlayer();
					jouerSon.setDataSource(chemin);
					jouerSon.prepare();*/
				}
				catch(Exception e)
				{
					jouerSon = null;
				}

			}
			
			if(jouerSon != null)
			{
				jouerSon.setOnCompletionListener(new FinSon());
				jouerSon.start();
			}
		}
    }
    private class FinSon implements OnCompletionListener
    {

		@Override
		public void onCompletion(MediaPlayer mp) 
		{
			jouerSon.stop();
			jouerSon.release();
		}
    	
    }
    
    private class onTestVideoClick implements OnClickListener
    {
		@Override
		public void onClick(View v) 
		{
			Intent video = new Intent(MainActivity.this, VoirVideo.class);
			String saisie = (String) urlText.getText();
			if(saisie.length() == 0)
			{
				video.putExtra("video", saisie);
			}
			startActivity(video);
		}
    }
}
