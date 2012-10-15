package com.horizon.AndroidHelloWorld.gcm;
import android.content.Context;

import com.google.android.gcm.GCMBroadcastReceiver;
public class HOPEGCMBroadcastReceiver extends GCMBroadcastReceiver{

	@Override
	protected String getGCMIntentServiceClassName(Context context){
		return "com.horizon.AndroidHelloWorld.gcm.GCMIntentService";
	}
}
