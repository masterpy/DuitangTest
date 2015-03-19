package com.example.duitangtest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	ImageView iv1,iv2,iv3,iv4,iv5,iv6;
	Bitmap bm1 = null ,bm2 = null, bm3 = null ,bm4 = null ,bm5 = null ,bm6 = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv1 = (ImageView) findViewById(R.id.imageView1);
		iv2 = (ImageView) findViewById(R.id.imageView2);
		iv3 = (ImageView) findViewById(R.id.imageView3);
		iv4 = (ImageView) findViewById(R.id.imageView4);
		iv5 = (ImageView) findViewById(R.id.imageView5);
		iv6 = (ImageView) findViewById(R.id.imageView6);
		
		
		new Thread(){
			public void run(){
				
				bm1 = getbitmap("http://cdnq.duitang.com/uploads/item/201410/14/20141014185925_FBQNS.thumb.700_0.jpeg");
				bm2 = getbitmap("http://cdnq.duitang.com/uploads/item/201409/30/20140930170248_GsFv4.thumb.700_0.jpeg");

						
                Log.v("SCAN","Thread2");
                iv1.post(new Runnable() {  
                            @Override  
                            public void run() { 
                            	iv1.setImageBitmap(bm1);
                            	iv2.setImageBitmap(bm2);
                            }
                        });
				}
		}.start();
		
		new Thread(){
			public void run(){
				bm3 = getbitmap("http://img5q.duitang.com/uploads/item/201408/13/20140813190048_jRSdB.thumb.700_0.png");
				bm4 = getbitmap("http://img5q.duitang.com/uploads/item/201411/06/20141106161920_MMNVv.thumb.700_0.jpeg");
						
                Log.v("SCAN","Thread2");
                iv3.post(new Runnable() {  
                            @Override  
                            public void run() {
                            	iv3.setImageBitmap(bm3);
                            	iv4.setImageBitmap(bm4);
                            	
                            }
                        });
				}
		}.start();
		
		new Thread(){
			public void run(){
				bm5 = getbitmap("http://img5q.duitang.com/uploads/item/201407/03/20140703170553_wsF5a.thumb.700_0.png");
				bm6 = getbitmap("http://cdnq.duitang.com/uploads/blog/201411/03/20141103203721_mXhuR.thumb.700_0.gif");
						
                Log.v("SCAN","Thread2");
                iv5.post(new Runnable() {  
                            @Override  
                            public void run() { 
                            	iv5.setImageBitmap(bm5);
                            	iv6.setImageBitmap(bm6);
                            }
                        });
				}
		}.start();
		
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
	
	Bitmap getbitmap(String urlstr){
		Log.v("LOG","begin");
		Bitmap bm = null ;
		try {
			Log.d("LOG","debug1");
			 URL url = new URL(urlstr);
			 URLConnection con = (URLConnection) url.openConnection();
	         InputStream stream = con.getInputStream();     
	         bm = BitmapFactory.decodeStream(stream);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			Log.e("LOG","error1");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("LOG","error2");
			e.printStackTrace();
		}
		
		return bm;
	}
}
