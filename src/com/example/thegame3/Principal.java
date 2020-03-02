package com.example.thegame3;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;


public class Principal extends Activity {
	Bitmap camino, auto;
	int x=0, y=0;//rut
	int x2=0 , y2=0;//car
	long delay = 30 ;
	Rect inicio, destino;
	DisplayMetrics pantalla;
	int h,w;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pantalla= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(pantalla);
        w = pantalla.widthPixels;
        h = pantalla.heightPixels;
        game g = new game(this);
        setContentView(g);
        getActionBar().hide();
    }

    class game extends View{

		public game(Context context) {
			super(context);
			
			camino= BitmapFactory.decodeResource(getResources(),R.drawable.carretera);
			auto= BitmapFactory.decodeResource(getResources(),R.drawable.auto);
			y = camino.getHeight();
			x2=(w-auto.getWidth())/2;
			y2= h - auto.getHeight() - 50;
			
			inicio = new Rect(x,y -300 , x + camino.getWidth(),y);
		}
		
		protected void Ondraw(Canvas canvas) {
			up();
			destino = new Rect(0,0, w,h);
			canvas.drawBitmap(camino, inicio, destino, null);
			canvas.drawBitmap(auto, x2, y2, null);
			postInvalidateDelayed(delay);
		}
		
		private void up(){
			y= y-2;
			if(y-300 < 0) y =camino.getHeight();
			inicio = new Rect(x,y-300,x +camino.getWidth(),y);
			
		}
		
		int getx, gety;
		
		public boolean onTouchEvent(MotionEvent Evento){
			int ac= Evento.getAction();
			getx=(int)Evento.getX();
			gety=(int)Evento.getY();
			if(ac == MotionEvent.ACTION_MOVE){
				x2 = getx-auto.getWidth()/2;
				y2 = getx-auto.getWidth()/2;
				
				
				
			}
			return true;
		}
    	
    }
}