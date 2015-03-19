package com.example.duitangtest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	ImageView iv1,iv2,iv3,iv4,iv5,iv6;
	Bitmap bm1 = null ,bm2 = null, bm3 = null ,bm4 = null ,bm5 = null ,bm6 = null;
	boolean b1,b2, b3,b4,b5,b6;
	
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
		b1 = b2 = b3 = b4 = b5 = b6 = false;
		
		new Thread(){
			public void run(){
				
				bm1 = getbitmap("http://cdnq.duitang.com/uploads/item/201410/14/20141014185925_FBQNS.thumb.700_0.jpeg");
				bm2 = getbitmap("http://cdnq.duitang.com/uploads/item/201409/30/20140930170248_GsFv4.thumb.700_0.jpeg");
				b1 = b2 = true;		
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
				b3 = b4 = true ;
				
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
					
				b5 = b6 = true;
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
		
		iv1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(b1 == false ) return ;
				SaveImage(bm1,"pic2.jpg");
			}
		}
		);
		
		iv2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(b2 == false ) return ;
				SaveImage(bm2,"pic1.jpg");
			}
		}
		);
		
		iv3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(b3 == false ) return ;
				SaveImage(bm3,"pic3.jpg");
			}
		}
		);
		
		iv4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(b4 == false ) return ;
				SaveImage(bm4,"pic4.jpg");
			}
		}
		);
		
		iv5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(b5 == false ) return ;
				SaveImage(bm5,"pic5.jpg");
			}
		}
		);
		
		iv6.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(b6 == false ) return ;
				SaveImage(bm6,"pic6.gif");
			}
		}
		);
		
		
		
	}

	private boolean SaveImage(Bitmap bm,String name) {
		if(bm == null) return false;
		else{
			String filepath = Environment.getExternalStorageDirectory() + "/DuitangTest_" + name ;
			File file= new File(filepath);
			try {
				file.createNewFile();
				FileOutputStream FileS = new FileOutputStream(file,false);
				bm.compress(Bitmap.CompressFormat.PNG, 60, FileS);
				FileS.flush();              
	    		FileS.close(); 
	    		
	    		Log.d("LOG","debug10");
	    		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Toast.makeText(this, "存储文件失败", Toast.LENGTH_LONG).show();
				e.printStackTrace();
				Log.e("LOG","error10");
				return false;
			}
			
			Toast.makeText(this, "文件已存储到：" + filepath, Toast.LENGTH_LONG).show();
			Log.d("LOG","debug11");
			return true;
		}
		// TODO Auto-generated method stub
		
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
