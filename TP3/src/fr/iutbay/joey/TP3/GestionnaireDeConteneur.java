package fr.iutbay.joey.TP3;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;


public class GestionnaireDeConteneur extends BaseAdapter 
{
	private Context contexte;

	private Integer[] identificateurDImages = { R.drawable.a_1, R.drawable.a_2, R.drawable.a_3, R.drawable.ic_launcher};
	private int largeur, hauteur;
	private int marges;
	private int couleurFond;
	
	public GestionnaireDeConteneur(Context ctx, int l, int h) 
	{
		contexte = ctx;
		largeur = l; hauteur = h; 
		marges = 6; 
		couleurFond = Color.BLACK; 
	}
	
	public int getCount() { return identificateurDImages.length; }
	public Object getItem(int position) { return contexte.getResources().getDrawable(identificateurDImages[position]); }
	public long getItemId(int position) { return identificateurDImages[position]; }
	public View getView(int position, View ancienneVue, ViewGroup vueParente) 
	{
		ImageView vueDImage = new ImageView(contexte);
		vueDImage.setImageResource(identificateurDImages[position]);
		vueDImage.setLayoutParams(new Gallery.LayoutParams(largeur, hauteur));
		vueDImage.setPadding(marges, 0, marges, 0);
		vueDImage.setScaleType(ImageView.ScaleType.FIT_XY);
		vueDImage.setBackgroundColor(couleurFond);
		return vueDImage;
	}
} 