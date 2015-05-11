package com.med.salma;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends WakefulBroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub 
		Bundle extras = intent.getExtras();
		String name = extras.getString("reminder_name");
		String date = extras.getString("reminder_date");
		String time = extras.getString("reminder_time");
		
		intent.putExtra("name", name);
		intent.putExtra("date", date);
		intent.putExtra("time", time);
		
		
		Log.d("Alarm Receiver", "Alarm has been received!");
		Toast.makeText(context, "It's time to take medicine!",
		        Toast.LENGTH_LONG).show();
		
		    // Vibrate the mobile phone
		    Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		    vibrator.vibrate(2000);
		    
		    ComponentName comp = new ComponentName(context.getPackageName(),
	                AlarmService.class.getName());
	        startWakefulService(context, (intent.setComponent(comp)));
	        setResultCode(Activity.RESULT_OK);
    }
		
	}
