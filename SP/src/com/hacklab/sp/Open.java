package com.hacklab.sp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class Open {
	// http://faoconsulting.com/app/index1.php?vehicle=Danfo == baseurlshii
	static Activity v = null;
	static String no = "";
	static AlertDialog.Builder builder = null;

	public static void login(final Activity v, String username, String password) {
		String url = "http://faoconsulting.com/app/login.php?username="
				+ username + "&password=" + password;

		// initialize dialog
		builder = new AlertDialog.Builder(v);
		builder.setTitle("Logging In");
		builder.setMessage("Please Wait........");

		final AlertDialog d = builder.create();
		d.show();
		// End of initialize dialog

		AsyncHttpClient client = new AsyncHttpClient();
		client.get(url, new AsyncHttpResponseHandler() {

			public void onSuccess(String content) {
				// TODO Auto-generated method stub
				Open.no = content;
				if (Open.no.equals("-1")) {
					d.dismiss();
					Toast.makeText(v, "Invalid Login Details",
							Toast.LENGTH_LONG).show();
				} else {
					d.dismiss();
					Intent t = new Intent(v, Main.class);
					v.startActivity(t);
				}

			}

			public void onFailure(Throwable error) {
				d.dismiss();
				Toast.makeText(v, "Network Error ", Toast.LENGTH_LONG).show();

			}
		});

	}

	public static void postdanfodata(String url) {

		AsyncHttpClient client = new AsyncHttpClient();
		client.get(url, new AsyncHttpResponseHandler() {

			public void onSuccess(String content) {
				// TODO Auto-generated method stub

				Toast.makeText(v, "Survey Posted Successfully",
						Toast.LENGTH_LONG).show();
				try {
					Open.danfosavetodevice();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			public void onFailure(Throwable error) {
				// TODO Auto-generated method stub
				// super.onFailure(error);
				// Log.d("error", error.getMessage());
				Toast.makeText(v, "Survey Didnt Post Successfully",
						Toast.LENGTH_LONG).show();
				try {
					Open.danfosavetodevice();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

	public static void createdanfourl(Activity cv)
			throws UnsupportedEncodingException {
		v = cv;
		data.q9_income = replaceAll(data.q9_income, "-", "%20");
		Log.d("URLdd", data.q9_income);

		String url = "http://faoconsulting.com/app/index1.php?" + "vehicle="
				+ data.vehicle + "&ul=" + data.userlocation + "&intno=" + no + "&location=" + data.location
				+ "&hour=" + data.hour + "&minute=" + data.minute + "&date="
				+ data.date + "&gender=" + data.gender + "&q1=" + data.q1
				+ "&q2_1=" + data.q2_1 + "&q2_2=" + data.q2_2 + "&q2_3="
				+ data.q2_3 + "&q2_4=" + data.q2_4 + "&q3hour=" + data.q3hour
				+ "&q3minute=" + data.q3minute + "&q4_Danfo_Hour="
				+ data.q4_Danfo_Hour + "&q4_Danfo_minute="
				+ data.q4_Danfo_minute + "&q4_brt_Hour=" + data.q4_brt_Hour
				+ "&q4_brt_minute=" + data.q4_brt_minute + "&q4_bigbus_Hour="
				+ data.q4_bigbus_Hour + "&q4_bigbus_minute="
				+ data.q4_bigbus_minute + "&q4_waiting_Hour="
				+ data.q4_waiting_Hour + "&q4_waiting_minute="
				+ data.q4_waiting_minute + "&q4_walking_Hour="
				+ data.q4_walking_Hour + "&q4_walking_minute="
				+ data.q4_walking_minute + "&q4_other_hour="
				+ data.q4_other_hour + "&q4_other_minute="
				+ data.q4_other_minute + "&q5_origin=" + data.q5_origin
				+ "&q6_destination=" + data.q6_destination + "&q7_fareforfuel="
				+ data.q7_fareforfuel + "&q7_fareforparking="
				+ data.q7_fareforparking + "&sp1=" + data.sp1 + "&sp2="
				+ data.sp2 + "&sp3=" + data.sp3 + "&sp4=" + data.sp4 + "&sp5="
				+ data.sp5 + "&sp6=" + data.sp6 + "&sp7=" + data.sp7 + "&sp8="
				+ data.sp8 + "&q9_income=" + data.q9_income;

		url = replaceAll(url, " ", "%20");
		postdanfodata(url);
		Log.d("URL", url);
	}

	public static void createcarurl(Activity cv)
			throws UnsupportedEncodingException {
		v = cv;
		data.carq9_income = replaceAll(data.carq9_income, "-", " ");
		String url = "http://faoconsulting.com/app/index2.php?" + "vehicle="
				+ data.vehicle + "&ul=" + data.userlocation + "&intno=" + no.trim() + "&location="
				+ data.carlocation + "&hour=" + data.hour + "&minute="
				+ data.minute + "&date=" + data.date + "&gender="
				+ data.cargender + "&q1=" + data.carq1 + "&q2_1="
				+ data.carq2_1 + "&q2_2=" + data.carq2_2 + "&q2_3="
				+ data.carq2_3 + "&q2_4=" + data.carq2_4 + "&q3hour="
				+ data.carq3_hour + "&q3minute=" + data.carq3_minute
				+ "&q4_car_Hour=" + data.carq4car_hour + "&q4_car_minute="
				+ data.carq4car_minute + "&q4_bus_Hour="
				+ data.carq4_bigbus_Hour + "&q4_bus_minute="
				+ data.carq4_bigbus_minute + "&q4_taxi_Hour="
				+ data.carq4_taxi_Hour + "&q4_taxi_minute="
				+ data.carq4_taxi_minute + "&q4_napep_Hour="
				+ data.carq4_napep_Hour + "&q4_napep_minute="
				+ data.carq4_napep_minute + "&q4_walking_Hour="
				+ data.carq4_walking_Hour + "&q4_walking_minute="
				+ data.carq4_walking_minute + "&q4_other_hour="
				+ data.carq4_other_hour + "&q4_other_minute="
				+ data.carq4_other_minute + "&q5_origin=" + data.carq5_origin
				+ "&q6_destination=" + data.carq6_destination
				+ "&q7_fareforfuel=" + data.carq7_fareforfuel
				+ "&q7_fareforparking=" + data.carq7_fareforparking + "&sp1="
				+ data.carsp1 + "&sp2=" + data.carsp2 + "&sp3=" + data.carsp3
				+ "&sp4=" + data.carsp4 + "&sp5=" + data.carsp5 + "&sp6="
				+ data.carsp6 + "&sp7=" + data.carsp7 + "&sp8=" + data.carsp8
				+ "&q9_income=" + data.carq9_income;

		url = replaceAll(url, " ", "%20");
		postcardata(url);

		Log.d("CARURL", url);

	}

	public static void postcardata(String url) {

		AsyncHttpClient client = new AsyncHttpClient();
		client.get(url, new AsyncHttpResponseHandler() {

			public void onSuccess(String content) {
				// TODO Auto-generated method stub

				Toast.makeText(v, "Survey Posted Successfully",
						Toast.LENGTH_LONG).show();
				try {
					Open.carsavetodevice();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			public void onFailure(Throwable error) {
				// TODO Auto-generated method stub
				// super.onFailure(error);
				// Log.d("error", error.getMessage());
				Toast.makeText(v, "Survey Didnt Post Successfully",
						Toast.LENGTH_LONG).show();
				try {
					Open.carsavetodevice();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	public static void danfosavetodevice() throws IOException {

		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			// We can read and write the media

			File f = Environment.getExternalStorageDirectory();
			String u = f.getPath();

			f = new File(u + "/SP");
			boolean b = f.mkdir();

			f = new File(f, data.vehicle + ".csv");
			if (!f.exists()) {

			 String w = "\"" + data.location + "\","
					    + "\"" + data.userlocation + "\","
						+ "\"" + no.trim()+ "\","
						+ "\"" + data.hour + "\"," 
						+ "\"" + data.minute+ "\"," 
						+ "\"" + data.date + "\"," 
						+ "\"" + "" + "\","
						+ "\"" + data.gender + "\"," 
						+ "\"" + data.q1 + "\","
						+ "\"" + data.q2_1 + "\","
						+ "\"" + data.q2_2 + "\","
						+ "\"" + data.q2_3 + "\"," 
						+ "\"" + data.q2_4 + "\","
						+ "\"" + data.q3hour + "\"," 
						+ "\"" + data.q3minute+ "\"," 
						+ "\"" + data.q4_Danfo_Hour + "\","
						+ "\"" + data.q4_Danfo_minute + "\"," 
						+ "\"" + data.q4_brt_Hour + "\"," 
						+ "\"" + data.q4_brt_minute+ "\"," 
						+ "\"" + data.q4_bigbus_Hour + "\"," 
						+ "\"" + data.q4_bigbus_minute + "\"," 
						+ "\"" + data.q4_waiting_Hour + "\"," 
						+ "\"" + data.q4_waiting_minute + "\","
						+ "\"" + data.q4_walking_Hour + "\"," 
						+ "\"" + data.q4_walking_minute + "\"," 
						+ "\"" + data.q4_other_hour + "\"," 
						+ "\"" + data.q4_other_minute + "\"," 
						+ "\"" + data.q5_origin + "\"," 
						+ "\"" + data.q6_destination + "\"," 
						+ "\"" + data.q7_fareforfuel + "\"," 
						+ "\"" + data.q7_fareforparking + "\"," 
						+ "\"" + "" + "\","
						+ "\"" + data.sp1 + "\"," 
						+ "\"" + data.sp2 + "\","
						+ "\"" + data.sp3 + "\"," 
						+ "\"" + data.sp4 + "\","
						+ "\"" + data.sp5 + "\"," 
						+ "\"" + data.sp6 + "\","
						+ "\"" + data.sp7 + "\"," 
						+ "\"" + data.sp8 + "\","
						+ "\"" + data.q9_income + "\","
						+ "\"" + data.occupation + "\","
						+ "\"" + data.adult + "\","
						+ "\"" + data.child + "\"\n";

				BufferedWriter writer = new BufferedWriter(new FileWriter(f));
				String total = data.columnhead + w;
				writer.write(total);
				writer.close();

				Log.d("SAvetofile", total);

			} else {
				// get old file first
				String head = "";
				FileInputStream fis = new FileInputStream(f);
				byte[] da = new byte[fis.available()];
				while (fis.read(da) != -1) {
					head = new String(da);
				}
				fis.close();

				String w = "\"" + data.location + "\"," 
						+ "\"" + no.trim()+ "\","
						+ "\"" + data.hour + "\"," 
						+ "\"" + data.minute+ "\"," 
						+ "\"" + data.date + "\"," 
						+ "\"" + "" + "\","
						+ "\"" + data.gender + "\"," 
						+ "\"" + data.q1 + "\","
						+ "\"" + data.q2_1 + "\","
						+ "\"" + data.q2_2 + "\","
						+ "\"" + data.q2_3 + "\"," 
						+ "\"" + data.q2_4 + "\","
						+ "\"" + data.q3hour + "\"," 
						+ "\"" + data.q3minute+ "\"," 
						+ "\"" + data.q4_Danfo_Hour + "\","
						+ "\"" + data.q4_Danfo_minute + "\"," 
						+ "\"" + data.q4_brt_Hour + "\"," 
						+ "\"" + data.q4_brt_minute+ "\"," 
						+ "\"" + data.q4_bigbus_Hour + "\"," 
						+ "\"" + data.q4_bigbus_minute + "\"," 
						+ "\"" + data.q4_waiting_Hour + "\"," 
						+ "\"" + data.q4_waiting_minute + "\","
						+ "\"" + data.q4_walking_Hour + "\"," 
						+ "\"" + data.q4_walking_minute + "\"," 
						+ "\"" + data.q4_other_hour + "\"," 
						+ "\"" + data.q4_other_minute + "\"," 
						+ "\"" + data.q5_origin + "\"," 
						+ "\"" + data.q6_destination + "\"," 
						+ "\"" + data.q7_fareforfuel + "\"," 
						+ "\"" + data.q7_fareforparking + "\"," 
						+ "\"" + "" + "\","
						+ "\"" + data.sp1 + "\"," 
						+ "\"" + data.sp2 + "\","
						+ "\"" + data.sp3 + "\"," 
						+ "\"" + data.sp4 + "\","
						+ "\"" + data.sp5 + "\"," 
						+ "\"" + data.sp6 + "\","
						+ "\"" + data.sp7 + "\"," 
						+ "\"" + data.sp8 + "\","
						+ "\"" + data.q9_income + "\"\n";

				String total = head + w;
				BufferedWriter writer = new BufferedWriter(new FileWriter(f));
				writer.write(total);
				writer.close();

				Log.d("SAvetofile", "Hopeful completion");

			}

		} else {
		
		}

	}

	public static void carsavetodevice() throws IOException {

		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			// We can read and write the media

			File f = Environment.getExternalStorageDirectory();
			String u = f.getPath();

			f = new File(u + "/SP");
			boolean b = f.mkdir();

			f = new File(f, data.vehicle + ".csv");
			
			if (!f.exists()) {

			 String w = "\"" + data.location + "\","
					 + "\"" + data.userlocation + "\","
						+ "\"" + no.trim()+ "\","
						+ "\"" + data.hour + "\"," 
						+ "\"" + data.minute+ "\"," 
						+ "\"" + data.date + "\"," 
						+ "\"" + "" + "\","
						+ "\"" + data.cargender + "\"," 
						+ "\"" + data.carq1 + "\","
						+ "\"" + data.carq2_1 + "\","
						+ "\"" + data.carq2_2 + "\","
						+ "\"" + data.carq2_3 + "\"," 
						+ "\"" + data.carq2_4 + "\","
						+ "\"" + data.carq3_hour + "\"," 
						+ "\"" + data.carq3_minute+ "\"," 
						+ "\"" + data.carq4car_hour + "\","
						+ "\"" + data.carq4car_hour + "\"," 
						+ "\"" + data.carq4_bigbus_Hour + "\"," 
						+ "\"" + data.carq4_bigbus_minute+ "\"," 
						+ "\"" + data.carq4_taxi_Hour + "\"," 
						+ "\"" + data.carq4_taxi_minute + "\"," 
						+ "\"" + data.carq4_napep_Hour + "\"," 
						+ "\"" + data.carq4_napep_minute + "\","
						+ "\"" + data.carq4_walking_Hour + "\"," 
						+ "\"" + data.carq4_walking_minute + "\"," 
						+ "\"" + data.carq4_other_hour + "\"," 
						+ "\"" + data.carq4_other_minute + "\"," 
						+ "\"" + data.carq5_origin + "\"," 
						+ "\"" + data.carq6_destination + "\"," 
						+ "\"" + data.carq7_fareforfuel + "\"," 
						+ "\"" + data.carq7_fareforparking + "\"," 
						+ "\"" + "" + "\","
						+ "\"" + data.carsp1 + "\"," 
						+ "\"" + data.carsp2 + "\","
						+ "\"" + data.carsp3 + "\"," 
						+ "\"" + data.carsp4 + "\","
						+ "\"" + data.carsp5 + "\"," 
						+ "\"" + data.carsp6 + "\","
						+ "\"" + data.carsp7 + "\"," 
						+ "\"" + data.carsp8 + "\","
						+ "\"" + data.carq9_income + "\"\n";

				BufferedWriter writer = new BufferedWriter(new FileWriter(f));
				String total = data.columnheadcar + w;
				writer.write(total);
				writer.close();

				

			} else {
				// get old file first
				String head = "";
				FileInputStream fis = new FileInputStream(f);
				byte[] da = new byte[fis.available()];
				while (fis.read(da) != -1) {
					head = new String(da);
				}
				fis.close();

				String w = "\"" + data.location + "\"," 
						+ "\"" + no.trim()+ "\","
						+ "\"" + data.hour + "\"," 
						+ "\"" + data.minute+ "\"," 
						+ "\"" + data.date + "\"," 
						+ "\"" + "" + "\","
						+ "\"" + data.cargender + "\"," 
						+ "\"" + data.carq1 + "\","
						+ "\"" + data.carq2_1 + "\","
						+ "\"" + data.carq2_2 + "\","
						+ "\"" + data.carq2_3 + "\"," 
						+ "\"" + data.carq2_4 + "\","
						+ "\"" + data.carq3_hour + "\"," 
						+ "\"" + data.carq3_minute+ "\"," 
						+ "\"" + data.carq4car_hour + "\","
						+ "\"" + data.carq4car_hour + "\"," 
						+ "\"" + data.carq4_bigbus_Hour + "\"," 
						+ "\"" + data.carq4_bigbus_minute+ "\"," 
						+ "\"" + data.carq4_taxi_Hour + "\"," 
						+ "\"" + data.carq4_taxi_minute + "\"," 
						+ "\"" + data.carq4_napep_Hour + "\"," 
						+ "\"" + data.carq4_napep_minute + "\","
						+ "\"" + data.carq4_walking_Hour + "\"," 
						+ "\"" + data.carq4_walking_minute + "\"," 
						+ "\"" + data.carq4_other_hour + "\"," 
						+ "\"" + data.carq4_other_minute + "\"," 
						+ "\"" + data.carq5_origin + "\"," 
						+ "\"" + data.carq6_destination + "\"," 
						+ "\"" + data.carq7_fareforfuel + "\"," 
						+ "\"" + data.carq7_fareforparking + "\"," 
						+ "\"" + "" + "\","
						+ "\"" + data.carsp1 + "\"," 
						+ "\"" + data.carsp2 + "\","
						+ "\"" + data.carsp3 + "\"," 
						+ "\"" + data.carsp4 + "\","
						+ "\"" + data.carsp5 + "\"," 
						+ "\"" + data.carsp6 + "\","
						+ "\"" + data.carsp7 + "\"," 
						+ "\"" + data.carsp8 + "\","
						+ "\"" + data.carq9_income + "\"\n";

				String total = head + w;
				BufferedWriter writer = new BufferedWriter(new FileWriter(f));
				writer.write(total);
				writer.close();

				
			}

		} else {
		
		}

	}
	public static String replaceAll(String text, String searchString,
			String replacementString) {
		StringBuffer sBuffer = new StringBuffer();
		int pos = 0;
		while ((pos = text.indexOf(searchString)) != -1) {
			sBuffer.append(text.substring(0, pos) + replacementString);
			text = text.substring(pos + searchString.length());
		}
		sBuffer.append(text);
		return sBuffer.toString();

	}

}
