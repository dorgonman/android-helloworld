package com.horizon.AndroidHelloWorld;

import com.horizon.AndroidHelloWorld.R;
import com.horizon.AndroidHelloWorld.gcm.GCMIntentService;

import android.os.Bundle;
import android.os.Looper;
import android.app.Activity;
import android.app.NativeActivity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import java.lang.String;
import com.google.android.gcm.GCMRegistrar;
import com.google.android.gcm.GCMBroadcastReceiver;
//Gcm
//https://code.google.com/apis/console/b/1/#project:209242230890
public class MyMainNativeActivity extends NativeActivity {
	//MyMainNativeActivity(){
		//super();
		//Looper.prepare();
	//}
	public static final String GSF_PACKAGE = 
			"com.google.android.gsf";

	public static final String REQUEST_REGISTRATION_INTENT = 
			"com.google.android.c2dm.intent.REGISTER";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        Log.d("MyMainNativeActivity", "MyMainNativeActivity.onCreate");
        Intent registrationIntent = new Intent(REQUEST_REGISTRATION_INTENT);
        registrationIntent.setPackage(GSF_PACKAGE);

        final Context pInstance = getApplicationContext();
   
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public void onPause(){
    	super.onPause();
    	Log.d("MyMainNativeActivity", "MyMainNativeActivity.onPause");
    	
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	Log.d("MyMainNativeActivity", "MyMainNativeActivity.onResume");
    	
    }
    
    public static void helloStaticCPP(){
    	Log.d("hello", "from c to java helloStaticCPP");	
    }
    public  void helloCPP(){
    	Log.d("hello", "from c to java helloCPP");	
    }
    public void messageMe(String text) {
        System.out.println(text);
    }
}
