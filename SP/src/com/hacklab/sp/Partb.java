package com.hacklab.sp;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Partb extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// 1 means car
		if (Main.determinant == 1) {
			setContentView(R.layout.partbcar);
		} else {
			setContentView(R.layout.partb);
		}

		setTitle("Part B - TRAVEL CHOICE QUESTIONS ");

		ActionBar ac = getActionBar();
		ac.setHomeButtonEnabled(true);
		ac.setIcon(R.drawable.abs__ic_ab_back_holo_dark);

	}

	public void next(View v) {
		// TODO Auto-generated method stub

		if (Main.determinant != 1) {
			//danfo 
			// SP1 - SP4
			RadioButton b = (RadioButton) findViewById(R.id.q1yes);
			if (b.isChecked()) {
				data.sp1 = "YES";
			} else {
				data.sp1 = "NO";
			}

			b = (RadioButton) findViewById(R.id.q2yes);
			if (b.isChecked()) {
				data.sp2 = "YES";
			} else {
				data.sp2 = "NO";
			}

			b = (RadioButton) findViewById(R.id.q3yes);
			if (b.isChecked()) {
				data.sp3 = "YES";
			} else {
				data.sp3 = "NO";
			}

			b = (RadioButton) findViewById(R.id.q4yes);
			if (b.isChecked()) {
				data.sp4 = "YES";
			} else {
				data.sp4 = "NO";
			}
			// End OF SP8

		}else{
			//caar
			// SP1 - SP4
			RadioButton b = (RadioButton) findViewById(R.id.carq1yes);
			if (b.isChecked()) {
				data.carsp1 = "YES";
			} else {
				data.carsp1 = "NO";
			}

			b = (RadioButton) findViewById(R.id.carq2yes);
			if (b.isChecked()) {
				data.carsp2 = "YES";
			} else {
				data.carsp2 = "NO";
			}

			b = (RadioButton) findViewById(R.id.carq3yes);
			if (b.isChecked()) {
				data.carsp3 = "YES";
			} else {
				data.carsp3 = "NO";
			}

			b = (RadioButton) findViewById(R.id.carq4yes);
			if (b.isChecked()) {
				data.carsp4 = "YES";
			} else {
				data.carsp4 = "NO";
			}
			// End OF SP8

		}
		

		if (Main.determinant != 1) {
			//danfo 
			//S5 - S8
			RadioButton b = (RadioButton) findViewById(R.id.q5yes);
			if (b.isChecked()) {
				data.sp5 = "YES";
			} else {
				data.sp5 = "NO";
			}

			b = (RadioButton) findViewById(R.id.q6yes);
			if (b.isChecked()) {
				data.sp6 = "YES";
			} else {
				data.sp6 = "NO";
			}

			b = (RadioButton) findViewById(R.id.q7yes);
			if (b.isChecked()) {
				data.sp7 = "YES";
			} else {
				data.sp7 = "NO";
			}

			b = (RadioButton) findViewById(R.id.q8yes);
			if (b.isChecked()) {
				data.sp8 = "YES";
			} else {
				data.sp8 = "NO";
			}
			// End OF SP8
			
		}else{
			//S5 - S8
			RadioButton b = (RadioButton) findViewById(R.id.carq5yes);
			if (b.isChecked()) {
				data.carsp5 = "YES";
			} else {
				data.carsp5 = "NO";
			}

			b = (RadioButton) findViewById(R.id.carq6yes);
			if (b.isChecked()) {
				data.carsp6 = "YES";
			} else {
				data.carsp6 = "NO";
			}

			b = (RadioButton) findViewById(R.id.carq7yes);
			if (b.isChecked()) {
				data.carsp7 = "YES";
			} else {
				data.carsp7 = "NO";
			}

			b = (RadioButton) findViewById(R.id.carq8yes);
			if (b.isChecked()) {
				data.carsp8 = "YES";
			} else {
				data.carsp8 = "NO";
			}
			// End OF SP8
			
		}
		
		
		Intent t = new Intent(Partb.this, Partd.class);
		startActivity(t);
		
	

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		onBackPressed();
		return true;
	}

	public void close(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		startActivity(intent);
		finish();

	}

}
