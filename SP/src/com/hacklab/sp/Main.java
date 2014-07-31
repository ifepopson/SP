package com.hacklab.sp;

import java.io.IOException;
import java.util.Calendar;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Main extends Activity {
	static int determinant = 0;

	// if 0 then its bus/danfo
	// if 1 its car

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	
		setTitle("Main Menu");
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		LocationListener ll = new mylocationlistener();
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, ll);


	}

	public void car(View v) {
		data.vehicle = v.getTag().toString();


		Calendar c = Calendar.getInstance();
		data.hour = c.get(Calendar.HOUR_OF_DAY) + "";
		data.minute = c.get(Calendar.MINUTE) + "";
		data.date = c.get(Calendar.YEAR) + "/" + (c.get(Calendar.MONTH) + 1)
				+ "/" + c.get(Calendar.DAY_OF_MONTH) + "";

		Intent t = new Intent(this, Survey.class);
		startActivity(t);
		Main.determinant = 1;
	}

	public void danfobus(View v) {

		// Toast.makeText(this, v.getTag().toString(),
		// Toast.LENGTH_LONG).show();
		data.vehicle = v.getTag().toString();
		

		Calendar c = Calendar.getInstance();
		data.hour = c.get(Calendar.HOUR_OF_DAY) + "";
		data.minute = c.get(Calendar.MINUTE) + "";
		data.date = c.get(Calendar.YEAR) + "/" + (c.get(Calendar.MONTH) + 1)
				+ "/" + c.get(Calendar.DAY_OF_MONTH) + "";

		Intent t = new Intent(this, Survey.class);
		startActivity(t);
		Main.determinant = 0;
	}

	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		startActivity(intent);
	}

}
