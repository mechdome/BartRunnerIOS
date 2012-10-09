package com.dougkeen.bart.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.dougkeen.bart.BartRunnerApplication;
import com.dougkeen.bart.model.Departure;
import com.dougkeen.util.WakeLocker;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		BartRunnerApplication application = (BartRunnerApplication) context
				.getApplicationContext();
		final Departure boardedDeparture = application.getBoardedDeparture();
		if (boardedDeparture == null) {
			// Nothing to notify about
			return;
		}

		WakeLocker.acquire(context);

		application.setPlayAlarmRingtone(true);

		Intent targetIntent = new Intent(Intent.ACTION_VIEW, boardedDeparture
				.getStationPair().getUri());
		targetIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		context.startActivity(targetIntent);

		boardedDeparture.notifyAlarmHasBeenHandled();
	}

}
