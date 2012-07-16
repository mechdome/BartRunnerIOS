package com.dougkeen.bart;

import android.content.Intent;

import com.dougkeen.bart.model.Constants;
import com.dougkeen.bart.model.Station;

public class QuickRouteActivity extends AbstractRouteSelectionActivity {

	@Override
	protected void onOkButtonClick(Station origin, Station destination) {
		startActivity(new Intent(Intent.ACTION_VIEW,
				Constants.ARBITRARY_ROUTE_CONTENT_URI_ROOT.buildUpon()
						.appendPath(origin.abbreviation)
						.appendPath(destination.abbreviation).build()));
	}
}
