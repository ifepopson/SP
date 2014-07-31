package com.hacklab.sp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Loginactivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		Toast.makeText(Loginactivity.this, "Please Make Sure Your GPS Is On", Toast.LENGTH_LONG).show();
		

		
	}

	public void Login(View v) {

		EditText u = (EditText) findViewById(R.id.username);
//		EditText p = (EditText) findViewById(R.id.password);
//
//		Open.login(this, u.getText().toString(), p.getText().toString());
		
		Open.no = u.getText().toString();  
		 Intent t = new Intent(Loginactivity.this,Main.class);
		 startActivity(t);

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

class mylocationlistener implements LocationListener {
	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		if (location != null) {
			if (Main.determinant != 1) {
				//danfo 
				data.location = "LAT_"+location.getLatitude() + " _LONG_ "  + location.getLongitude();
				
			}else{
				data.carlocation = "LAT_"+location.getLatitude() + " _LONG_ "  + location.getLongitude();
				
			}
			
			Log.d("LOCATION CHANGED", location.getLatitude() + "");
			Log.d("LOCATION CHANGED", location.getLongitude() + "");

			// Toast.makeText(this,
			// "f",
			// Toast.LENGTH_LONG).show();
		}

	}
}