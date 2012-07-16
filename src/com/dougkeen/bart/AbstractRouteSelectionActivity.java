package com.dougkeen.bart;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.dougkeen.bart.model.Station;

public abstract class AbstractRouteSelectionActivity extends Activity {

	public AbstractRouteSelectionActivity() {
		super();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.route_form);

		SpinnerAdapter originSpinnerAdapter = new ArrayAdapter<Station>(this,
				R.layout.simple_spinner_item, Station.getStationList());
		((Spinner) findViewById(R.id.origin_spinner))
				.setAdapter(originSpinnerAdapter);

		SpinnerAdapter destinationSpinnerAdapter = new ArrayAdapter<Station>(
				this, R.layout.simple_spinner_item, Station.getStationList());
		((Spinner) findViewById(R.id.destination_spinner))
				.setAdapter(destinationSpinnerAdapter);

		Button okButton = (Button) findViewById(R.id.okButton);
		okButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Station origin = (Station) ((Spinner) findViewById(R.id.origin_spinner))
						.getSelectedItem();
				Station destination = (Station) ((Spinner) findViewById(R.id.destination_spinner))
						.getSelectedItem();
				if (origin == null) {
					Toast.makeText(v.getContext(),
							com.dougkeen.bart.R.string.error_null_origin,
							Toast.LENGTH_LONG);
					return;
				}
				if (destination == null) {
					Toast.makeText(v.getContext(),
							com.dougkeen.bart.R.string.error_null_destination,
							Toast.LENGTH_LONG);
					return;
				}
				if (origin.equals(destination)) {
					Toast.makeText(
							v.getContext(),
							com.dougkeen.bart.R.string.error_matching_origin_and_destination,
							Toast.LENGTH_LONG);
					return;
				}
				onOkButtonClick(origin, destination);
			}

		});

		Button cancelButton = (Button) findViewById(R.id.cancelButton);
		cancelButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				setResult(RESULT_CANCELED);
				finish();
			}
		});

	}

	abstract protected void onOkButtonClick(Station origin, Station destination);
}