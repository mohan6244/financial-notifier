package com.st.fn;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import java.util.Calendar;

import io.fabric.sdk.android.Fabric;

public class FinancialNotifierMenuActivity extends Activity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "QA3OXb9nQQj2rYOmhWPVa7jhk";
    private static final String TWITTER_SECRET = "8wRZeKfW8IDVz0N9FP26Mxxco40empMTIsrll9LwxFSkANE5hm";
    private Button button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
		Fabric.with(this, new Twitter(authConfig));
		setContentView(R.layout.main);
		setAlarm();

			Database.getPreferences(this);

	}

	public void gotoVehiclesMenu(View v) {
		Intent intent = new Intent(this, VehiclesActivity.class);
		startActivity(intent);
	}

	public void gotoLifeInsurancesMenu(View v) {
		Intent intent = new Intent(this, LifeInsurancesActivity.class);
		startActivity(intent);
	}
	
	public void gotoDepositsMenu(View v) {
		Intent intent = new Intent(this, DepositsActivity.class);
		startActivity(intent);
	}

	public void gotoUpcomingEvents(View v) {
		Intent intent = new Intent(this, UpcomingEventsActivity.class);
		startActivity(intent);
	}

	public void testNotifier(View v) {
		Intent intent = new Intent("com.st.fn.NOTIFY");
		this.sendBroadcast(intent);
	}
	
	public void setPreferences(View v) {
		Intent intent = new Intent(this, PrefsActivity.class);
		this.startActivity(intent);
	}
	
	public void backupRestore(View v) {
		Intent intent = new Intent(this, BackupRestoreActivity.class);
		this.startActivity(intent);
	}

    public void goToPurchase(View v)
    {
        Intent intent= new Intent(this, PaymentActivity.class);
        this.startActivity(intent);

    }
    public void logOut(View v)
    {
        Digits.getSessionManager().clearActiveSession();
        Intent i=new Intent(this,LoginActivity.class);
        this.startActivity(i);
    }
	public void setAlarm() {
		Intent intent = new Intent(this, DateChangeBroadcastReceiver.class);
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, 0);
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		// set alarm at 6:00 am and fire it for every day
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, Database.getHourOfDay());
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		am.setRepeating(AlarmManager.RTC, cal.getTimeInMillis(),
				AlarmManager.INTERVAL_DAY, pi);
		Log.d(Database.TAG, "Alarm is set");
	}
}