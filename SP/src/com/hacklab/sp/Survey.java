package com.hacklab.sp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.Vector;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.AssetManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Survey extends Activity {
	static String[] tripps = { "To/From Work",
			"In course of work Employers business", "To/from school/college",
			"Shopping", "Personal business ", "Visiting friends/relatives",
			"Recreation/leisure", "Others" };

	static Vector areas = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// 1 means car
		if (Main.determinant == 1) {
			setContentView(R.layout.startsurveycar);
			LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			LocationListener ll = new mylocationlistener();
			lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
					ll);

		} else {
			setContentView(R.layout.startsurvey);
			LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			LocationListener ll = new mylocationlistener();
			lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
					ll);

		}

		setTitle("LAST JOURNEY INFORMATION");

		ActionBar ac = getActionBar();
		ac.setHomeButtonEnabled(true);
		ac.setIcon(R.drawable.abs__ic_ab_back_holo_dark);

		AssetManager ass = getAssets();
		try {
			String[] files = ass.list("");
			InputStream input = ass.open(files[0]);
			String text = convertstreamtostring(input);

			char[] alltyped = text.toCharArray(); // convert the ish yu type to
													// a char array
			String curr = "";
			areas = new Vector();
			for (int i = 0; i < text.length(); i++) {

				if (alltyped[i] != ',' || i == 0) {
					curr += alltyped[i];
				} else {
					areas.add(curr);
					curr = "";
				}

			}

			Object[] ar = areas.toArray();
			if (Main.determinant == 1) {
				ArrayAdapter adapter = new ArrayAdapter(this,
						android.R.layout.simple_list_item_1, ar);

				AutoCompleteTextView begin = (AutoCompleteTextView) findViewById(R.id.carjourneybegin);
				begin.setAdapter(adapter);

				AutoCompleteTextView end = (AutoCompleteTextView) findViewById(R.id.carjourneyend);
				end.setAdapter(adapter);
				
				AutoCompleteTextView myloc = (AutoCompleteTextView) findViewById(R.id.myloc);
				myloc.setAdapter(adapter);
				
			} else {
				ArrayAdapter adapter = new ArrayAdapter(this,
						android.R.layout.simple_list_item_1, ar);

				AutoCompleteTextView begin = (AutoCompleteTextView) findViewById(R.id.journeybegin);
				begin.setAdapter(adapter);

				AutoCompleteTextView end = (AutoCompleteTextView) findViewById(R.id.journeyend);
				end.setAdapter(adapter);

				AutoCompleteTextView myloc = (AutoCompleteTextView) findViewById(R.id.myloc);
				myloc.setAdapter(adapter);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Spinner trips = (Spinner) findViewById(R.id.trips);
		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_spinner_item, tripps);
		// adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// trips.setAdapter(adapter);

	}

	public static String convertstreamtostring(InputStream is)
			throws IOException {
		Writer writer = new StringWriter();
		char[] buffer = new char[2048];
		try {
			Reader r = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			int n;
			while ((n = r.read(buffer)) != -1) {
				writer.write(buffer, 0, n);
			}
		} catch (Exception e) {

		} finally {
			is.close();
		}

		String text = writer.toString();

		return text;

	}

	public void next(View v) {
		// TODO Auto-generated method stub

		if (Main.determinant != 1) {

			RadioButton male = (RadioButton) findViewById(R.id.radio0);

			EditText u = (EditText) findViewById(R.id.myloc);

			data.userlocation = u.getText().toString();
			
			if (male.isChecked()) {
				data.gender = "Male";
			} else {
				data.gender = "Female";
			}

			// Q1
			RadioButton c1 = (RadioButton) findViewById(R.id.toworkcar);
			RadioButton c2 = (RadioButton) findViewById(R.id.empbiz);
			RadioButton c3 = (RadioButton) findViewById(R.id.sch);
			RadioButton c4 = (RadioButton) findViewById(R.id.shop);
			RadioButton c5 = (RadioButton) findViewById(R.id.personalbiz);
			RadioButton c6 = (RadioButton) findViewById(R.id.visitfriend);
			RadioButton c7 = (RadioButton) findViewById(R.id.rec);
			RadioButton c8 = (RadioButton) findViewById(R.id.other);

			if (c1.isChecked()) {
				data.q1 = c1.getText().toString();
			} else if (c2.isChecked()) {
				data.q1 = c2.getText().toString();
			} else if (c3.isChecked()) {
				data.q1 = c3.getText().toString();
			} else if (c4.isChecked()) {
				data.q1 = c4.getText().toString();
			} else if (c5.isChecked()) {
				data.q1 = c5.getText().toString();
			} else if (c6.isChecked()) {
				data.q1 = c6.getText().toString();
			} else if (c7.isChecked()) {
				data.q1 = c7.getText().toString();
			} else if (c8.isChecked()) {
				data.q1 = c8.getText().toString();
			}
			// END OF Q1

			// Q2
			CheckBox d1 = (CheckBox) findViewById(R.id.danfo);
			CheckBox d2 = (CheckBox) findViewById(R.id.bigbus);
			CheckBox d3 = (CheckBox) findViewById(R.id.brt);
			CheckBox d4 = (CheckBox) findViewById(R.id.okada);
			CheckBox d5 = (CheckBox) findViewById(R.id.train);
			CheckBox d6 = (CheckBox) findViewById(R.id.ferry);
			CheckBox d7 = (CheckBox) findViewById(R.id.keke);
			CheckBox d8 = (CheckBox) findViewById(R.id.othermodes);

			if (c1.isChecked()) {
				data.q2_1 = d1.getText().toString();
			} else if (c2.isChecked()) {
				data.q2_2 = d2.getText().toString();
			} else if (c3.isChecked()) {
				data.q2_3 = d3.getText().toString();
			} else if (c4.isChecked()) {
				data.q2_4 = d4.getText().toString();
			} else if (c5.isChecked()) {
				data.q2_1 = d5.getText().toString();
			} else if (c6.isChecked()) {
				data.q2_2 = d6.getText().toString();
			} else if (c7.isChecked()) {
				data.q2_3 = d7.getText().toString();
			} else if (c8.isChecked()) {
				data.q2_4 = d8.getText().toString();
			}
			// End OF Q2

			// Q3 hr and minute
			EditText hr = (EditText) findViewById(R.id.totalhour);
			EditText min = (EditText) findViewById(R.id.totalmin);

			if (hr.getText().toString().equals("")) {
				data.q3hour = "0";
			} else {
				data.q3hour = hr.getText().toString();
			}

			if (min.getText().toString().equals("")) {
				data.q3minute = "0";
			} else {
				data.q3minute = min.getText().toString();
			}

			// end of Q3

			// Q4
			// Danfo
			hr = (EditText) findViewById(R.id.danfohour);
			min = (EditText) findViewById(R.id.danfomin);

			if (hr.getText().toString().equals("")) {
				data.q4_Danfo_Hour = "0";
			} else {
				data.q4_Danfo_Hour = hr.getText().toString();
			}

			if (min.getText().toString().equals("")) {
				data.q4_Danfo_minute = "0";
			} else {
				data.q4_Danfo_minute = min.getText().toString();
			}

			// BRT
			hr = (EditText) findViewById(R.id.brthour);
			min = (EditText) findViewById(R.id.brtminute);

			if (hr.getText().toString().equals("")) {
				data.q4_brt_Hour = "0";
			} else {
				data.q4_brt_Hour = hr.getText().toString();
			}

			if (min.getText().toString().equals("")) {
				data.q4_brt_minute = "0";
			} else {
				data.q4_brt_minute = min.getText().toString();
			}

			// BigBus
			hr = (EditText) findViewById(R.id.bigbushour);
			min = (EditText) findViewById(R.id.bigbusminute);

			if (hr.getText().toString().equals("")) {
				data.q4_bigbus_Hour = "0";
			} else {
				data.q4_bigbus_Hour = hr.getText().toString();
			}

			if (min.getText().toString().equals("")) {
				data.q4_bigbus_minute = "0";
			} else {
				data.q4_bigbus_minute = min.getText().toString();
			}

			// waiting
			hr = (EditText) findViewById(R.id.waitinghour);
			min = (EditText) findViewById(R.id.waitingminute);

			if (hr.getText().toString().equals("")) {
				data.q4_waiting_Hour = "0";
			} else {
				data.q4_waiting_Hour = hr.getText().toString();
			}

			if (min.getText().toString().equals("")) {
				data.q4_waiting_minute = "0";
			} else {
				data.q4_waiting_minute = min.getText().toString();
			}

			// WALKING
			hr = (EditText) findViewById(R.id.walkinghour);
			min = (EditText) findViewById(R.id.walkingminute);

			if (hr.getText().toString().equals("")) {
				data.q4_walking_Hour = "0";
			} else {
				data.q4_walking_Hour = hr.getText().toString();
			}

			if (min.getText().toString().equals("")) {
				data.q4_walking_minute = "0";
			} else {
				data.q4_walking_minute = min.getText().toString();
			}

			// WALKING
			hr = (EditText) findViewById(R.id.otherhour);
			min = (EditText) findViewById(R.id.danfomin);

			if (hr.getText().toString().equals("")) {
				data.q4_other_hour = "0";
			} else {
				data.q4_other_hour = hr.getText().toString();
			}

			if (min.getText().toString().equals("")) {
				data.q4_other_minute = "0";
			} else {
				data.q4_other_minute = min.getText().toString();
			}

			// End of Q4

			// Q5
			hr = (EditText) findViewById(R.id.journeybegin);
			data.q5_origin = hr.getText().toString();

			// End of Q5

			// Q6
			hr = (EditText) findViewById(R.id.journeyend);
			data.q6_destination = hr.getText().toString();

			// End of Q6

			// Q7
			hr = (EditText) findViewById(R.id.faredanfo);
			data.q7_fareforfuel = hr.getText().toString();
			// End of Q7

			// Q7-2
			// hr = (EditText) findViewById(R.id.fareforparking);
			// data.q7_fareforparking = hr.getText().toString();
			// End of Q7-2

			String text = "";
			// 1 means car
			if (Main.determinant == 1) {
				// text =
				// "Suppose there was an alternative route for your last journey, in which you spend less (or more) time due to congestion.  In each of the following situations the travel time and cost of this alternative route is different from your last journey.  I would like you to say which option, your current route or the alternative route, you would use in each situation. ";
				text = "Suppose there was an alternative service for your last journey.  In each of the following situations the travel time and fare of the alternative is different from your last journey.  I would like you to say which option you would use in each situation. \nPlease assume that all other aspects of the trip, such as waiting time at bus stops, are the same for each option.\nPlease say whether you would choose the service you used or the alternative in each of the following situations? (TICK CHOICE)";
			} else {
				// text =
				// "Suppose there was an alternative service for your last journey.\nIn each of the following situations the travel time and fare of the alternative is different from your last journey.  I would like you to say whether which option you would prefer to use in each situation bus or taxi. Please assume that all other aspects of the trip, such as waiting time at bus stops, are the same for each option.between the two alternatives.  Please say whether you would choose the service you used or the alternative prefer to use the bus or taxi in each of the following situations? \nThe journeys presented may not the same as for your current journey, but please say which journey you would prefer if this was the choice you were faced with.";

				text = "Suppose there was an alternative service for your last journey.  In each of the following situations the travel time and fare of the alternative is different from your last journey.  I would like you to say which option you would use in each situation. \nPlease assume that all other aspects of the trip, such as waiting time at bus stops, are the same for each option.\nPlease say whether you would choose the service you used or the alternative in each of the following situations? (TICK CHOICE)";

			}
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setCancelable(true);
			builder.setTitle("Please Read.");
			builder.setMessage(text);
			builder.setPositiveButton("OK", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent t = new Intent(Survey.this, Partb.class);
					startActivity(t);

				}
			});

			AlertDialog d = builder.create();

			d.show();
		} else {
			EditText u = (EditText) findViewById(R.id.myloc);

			data.userlocation = u.getText().toString();

			nextcar();

		}

	}

	public void nextcar() {

		RadioButton male = (RadioButton) findViewById(R.id.radio0);

		if (male.isChecked()) {
			data.cargender = "Male";
		} else {
			data.cargender = "Female";
		}

		// Q1
		RadioButton c1 = (RadioButton) findViewById(R.id.toworkcar);
		RadioButton c2 = (RadioButton) findViewById(R.id.empbiz);
		RadioButton c3 = (RadioButton) findViewById(R.id.sch);
		RadioButton c4 = (RadioButton) findViewById(R.id.shop);
		RadioButton c5 = (RadioButton) findViewById(R.id.personalbiz);
		RadioButton c6 = (RadioButton) findViewById(R.id.visitfriend);
		RadioButton c7 = (RadioButton) findViewById(R.id.rec);
		RadioButton c8 = (RadioButton) findViewById(R.id.other);

		if (c1.isChecked()) {
			data.carq1 = c1.getText().toString();
		} else if (c2.isChecked()) {
			data.carq1 = c2.getText().toString();
		} else if (c3.isChecked()) {
			data.carq1 = c3.getText().toString();
		} else if (c4.isChecked()) {
			data.carq1 = c4.getText().toString();
		} else if (c5.isChecked()) {
			data.carq1 = c5.getText().toString();
		} else if (c6.isChecked()) {
			data.carq1 = c6.getText().toString();
		} else if (c7.isChecked()) {
			data.carq1 = c7.getText().toString();
		} else if (c8.isChecked()) {
			data.carq1 = c8.getText().toString();
		}
		// END OF Q1

		// Q2
		CheckBox d1 = (CheckBox) findViewById(R.id.danfo);
		CheckBox d3 = (CheckBox) findViewById(R.id.brt);
		CheckBox d4 = (CheckBox) findViewById(R.id.okada);
		CheckBox d5 = (CheckBox) findViewById(R.id.train);
		CheckBox d6 = (CheckBox) findViewById(R.id.ferry);
		CheckBox d7 = (CheckBox) findViewById(R.id.keke);

		if (c1.isChecked()) {
			data.carq2_1 = d1.getText().toString();
		} else if (c3.isChecked()) {
			data.carq2_3 = d3.getText().toString();
		} else if (c4.isChecked()) {
			data.carq2_4 = d4.getText().toString();
		} else if (c5.isChecked()) {
			data.carq2_1 = d5.getText().toString();
		} else if (c6.isChecked()) {
			data.carq2_2 = d6.getText().toString();
		} else if (c7.isChecked()) {
			data.carq2_3 = d7.getText().toString();
		}
		// End OF Q2

		// Q3 hr and minute
		EditText hr = (EditText) findViewById(R.id.totalhourcar);
		EditText min = (EditText) findViewById(R.id.totalmincar);

		if (hr.getText().toString().equals("")) {
			data.carq3_hour = "0";
		} else {
			data.carq3_hour = hr.getText().toString();
		}

		if (min.getText().toString().equals("")) {
			data.carq3_minute = "0";
		} else {
			data.carq3_minute = min.getText().toString();
		}

		// end of Q3

		// Q4
		// car
		hr = (EditText) findViewById(R.id.carhour);
		min = (EditText) findViewById(R.id.carmin);

		if (hr.getText().toString().equals("")) {
			data.carq4car_hour = "0";
		} else {
			data.carq4car_hour = hr.getText().toString();
		}

		if (min.getText().toString().equals("")) {
			data.carq4car_minute = "0";
		} else {
			data.carq4car_minute = min.getText().toString();
		}

		// BRT
		hr = (EditText) findViewById(R.id.taxihour);
		min = (EditText) findViewById(R.id.taximinute);

		if (hr.getText().toString().equals("")) {
			data.carq4_taxi_Hour = "0";
		} else {
			data.carq4_taxi_Hour = hr.getText().toString();
		}

		if (min.getText().toString().equals("")) {
			data.carq4_taxi_minute = "0";
		} else {
			data.carq4_taxi_minute = min.getText().toString();
		}

		// BigBus
		hr = (EditText) findViewById(R.id.bigbushourcar);
		min = (EditText) findViewById(R.id.bigbusminutecar);

		if (hr.getText().toString().equals("")) {
			data.carq4_bigbus_Hour = "0";
		} else {
			data.carq4_bigbus_Hour = hr.getText().toString();
		}

		if (min.getText().toString().equals("")) {
			data.carq4_bigbus_minute = "0";
		} else {
			data.carq4_bigbus_minute = min.getText().toString();
		}

		// keknap
		hr = (EditText) findViewById(R.id.kekehour);
		min = (EditText) findViewById(R.id.kekemin);

		if (hr.getText().toString().equals("")) {
			data.carq4_napep_Hour = "0";
		} else {
			data.carq4_napep_Hour = hr.getText().toString();
		}

		if (min.getText().toString().equals("")) {
			data.carq4_napep_minute = "0";
		} else {
			data.carq4_napep_minute = min.getText().toString();
		}

		// WALKING
		hr = (EditText) findViewById(R.id.carwalkinghour);
		min = (EditText) findViewById(R.id.carwalkingminute);

		if (hr.getText().toString().equals("")) {
			data.carq4_walking_Hour = "0";
		} else {
			data.carq4_walking_Hour = hr.getText().toString();
		}

		if (min.getText().toString().equals("")) {
			data.carq4_walking_minute = "0";
		} else {
			data.carq4_walking_minute = min.getText().toString();
		}

		// Other
		hr = (EditText) findViewById(R.id.otherhourcar);
		min = (EditText) findViewById(R.id.othermincar);

		if (hr.getText().toString().equals("")) {
			data.carq4_other_hour = "0";
		} else {
			data.carq4_other_hour = hr.getText().toString();
		}

		if (min.getText().toString().equals("")) {
			data.carq4_other_minute = "0";
		} else {
			data.carq4_other_minute = min.getText().toString();
		}

		// End of Q4

		// Q5
		hr = (EditText) findViewById(R.id.carjourneybegin);
		data.carq5_origin = hr.getText().toString();

		// End of Q5

		// Q6
		hr = (EditText) findViewById(R.id.carjourneyend);
		data.carq6_destination = hr.getText().toString();

		// End of Q6

		// Q7
		hr = (EditText) findViewById(R.id.fareforfuelcar);
		data.carq7_fareforfuel = hr.getText().toString();
		// End of Q7

		// Q7-2
		hr = (EditText) findViewById(R.id.fareforparkingcar);
		data.carq7_fareforparking = hr.getText().toString();
		// End of Q7-2

		String text = "";
		// 1 means car

		text = "Suppose there was an alternative service for your last journey.  In each of the following situations the travel time and fare of the alternative is different from your last journey.  I would like you to say which option you would use in each situation. \nPlease assume that all other aspects of the trip, such as waiting time at bus stops, are the same for each option.\nPlease say whether you would choose the service you used or the alternative in each of the following situations? (TICK CHOICE)";

		// if (Main.determinant == 1) {
		// text =
		// "Suppose there was an alternative route for your last journey, in which you spend less (or more) time due to congestion.  In each of the following situations the travel time and cost of this alternative route is different from your last journey.  I would like you to say which option, your current route or the alternative route, you would use in each situation. ";
		// } else {
		// text =
		// "Suppose there was an alternative service for your last journey.\nIn each of the following situations the travel time and fare of the alternative is different from your last journey.  I would like you to say whether which option you would prefer to use in each situation bus or taxi. Please assume that all other aspects of the trip, such as waiting time at bus stops, are the same for each option.between the two alternatives.  Please say whether you would choose the service you used or the alternative prefer to use the bus or taxi in each of the following situations? \nThe journeys presented may not the same as for your current journey, but please say which journey you would prefer if this was the choice you were faced with.";
		// }

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle("Please Read.");
		builder.setMessage(text);
		builder.setPositiveButton("OK", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent t = new Intent(Survey.this, Partb.class);
				startActivity(t);

			}
		});

		AlertDialog d = builder.create();

		d.show();
	}

	public void close(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		startActivity(intent);
		finish();

	}

	public void save(View v) {
		Calendar c = Calendar.getInstance();

		//
		// String now = c.get(Calendar.YEAR) + "_" + c.get(Calendar.MONTH) + "_"
		// + c.get(Calendar.DAY_OF_MONTH) + "_" + c.get(Calendar.HOUR)
		// + "_" + c.get(Calendar.MINUTE) + "_" + c.get(Calendar.SECOND);
		//
		//
		// AsyncHttpClient t = new AsyncHttpClient();
		// t.get(url, new AsyncHttpResponseHandler() {
		// @Override
		// public void onSuccess(String Response) {
		// // TODO Auto-generated method stub
		// Toast.makeText(Survey.this, "Saved Successfully",
		// Toast.LENGTH_SHORT).show();
		//
		// // Intent t = new Intent(Survey.this, Survey.class);
		// // startActivity(t);
		//
		// }
		//
		// @Override
		// @Deprecated
		// public void onFailure(Throwable error) {
		// // TODO Auto-generated method stub
		// Toast.makeText(Survey.this, "Network Error", Toast.LENGTH_SHORT)
		// .show();
		//
		// }
		// });
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		onBackPressed();
		return true;
	}
	// public void onBackPressed() {
	// // TODO Auto-generated method stub
	// super.onBackPressed();
	//
	// Intent intent = new Intent(Intent.ACTION_MAIN);
	// intent.addCategory(Intent.CATEGORY_HOME);
	// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	//
	// startActivity(intent);
	// }
}
