package com.horizon.AndroidHelloWorld.gcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;
import com.horizon.AndroidHelloWorld.MainActivity;
import com.horizon.AndroidHelloWorld.R;

public class GCMIntentService extends GCMBaseIntentService{
	
	public final static String GCM_SENDER_ID = "209242230890";
	
	public GCMIntentService(){
		super(GCM_SENDER_ID);

		Log.d("gcm", "GCMIntentService()");
	}
	


	@Override
	protected void onError(Context arg0, String arg1) {
		
		Log.d("gcm", "GCMIntentService.onError:" + arg1);
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		Log.d("gcm", "GCMIntentService.onMessage" +  intent);
		
		String ns = Context.NOTIFICATION_SERVICE;
	
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
	
		//�ŧi�ϥ�
	
		 int icon = R.drawable.ic_launcher;

		//�q�����D
	
		 CharSequence tickerText = "Message from GCM server(tickerText)";
	
		 long when = System.currentTimeMillis();
	
		 CharSequence contentTitle = "your content title";

		//���o�q�����e

		 CharSequence contentText = intent.getStringExtra("message");

		//�I��q������X��activity

		 Intent notificationIntent = new Intent(this, MainActivity.class);

		 PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

		 Notification notification = new Notification(icon, tickerText, when);

		//�I���۰ʲ����ӳq��

		 notification.flags = Notification.FLAG_AUTO_CANCEL;

		 notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
	
		 mNotificationManager.notify(1, notification);
		 Log.d("gcm", "gcm message:" + contentText.toString() + context.getClass());

		
	}

	@Override
	protected void onRegistered(Context arg0, String arg1) {
		Log.d("gcm", "GCMIntentService.onRegistered:" + arg1);
		
	}

	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		Log.d("gcm", "GCMIntentService.onUnregistered" + arg1);
		
	}

}
