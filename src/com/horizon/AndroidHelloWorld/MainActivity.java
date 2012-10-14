package com.horizon.AndroidHelloWorld;

import com.google.android.gcm.GCMRegistrar;
import com.horizon.AndroidHelloWorld.R;

import android.os.Bundle;
import android.os.Looper;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("HelloWorld", "MainActivity.onCreate");
        final Activity pInstance = this;
        new Thread(){
        	@Override
			public void run(){
        		 GCMRegistrar.checkDevice(pInstance);
        	     GCMRegistrar.checkManifest(pInstance);
        	     final String regId = GCMRegistrar.getRegistrationId(pInstance);
        	        if (regId.equals("")) {
        	        	  Log.v("gcm", "start register");
        	          GCMRegistrar.register(pInstance, GCMIntentService.GCM_SENDER_ID);
        	        } else {
        	          Log.v("gcm", "Already registered");
        	          Log.d("gcm", "regId:" + regId);
        	        }
        		
        	}
   
        	
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public void onPause(){
    	super.onPause();
    	Log.d("MainActivity", "MainActivity.onPause");
    	
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	Log.d("MainActivity", "MainActivity.onResume");
    	
    }
    
    private native void helloLog(String logThis);
    
    public void onClickButton(View v){
    	helloLog("This will log to LogCat via the native call.");  	
    }
    
    public void helloCPP(){
    	Log.d("hello", "from c to java");
    	
    }
    static {  
       System.loadLibrary("myNative");  
    }  

}
