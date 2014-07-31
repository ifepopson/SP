package com.hacklab.sp;

import java.io.UnsupportedEncodingException;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Partd extends Activity {

	AlertDialog d = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		
		setContentView(R.layout.partd);
		setTitle("Part C - PERSONAL DETAILS");

		ActionBar ac = getActionBar();
		ac.setHomeButtonEnabled(true);
		ac.setIcon(R.drawable.abs__ic_ab_back_holo_dark);

	}

	public void save(View v) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Thank You.");
		builder.setMessage("Are you sure you want to submit?");
		builder.setPositiveButton("Yes", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
				RadioButton selected = (RadioButton)findViewById(rg.getCheckedRadioButtonId());
				
				RadioGroup rg2 = (RadioGroup) findViewById(R.id.radioGroup2);
				RadioButton selected2 = (RadioButton)findViewById(rg2.getCheckedRadioButtonId());
				
				if (Main.determinant != 1) {
					//danfo 
					try {
					data.q9_income = selected.getTag().toString();
					data.occupation = selected2.getTag().toString();
					
					EditText tx = (EditText) findViewById(R.id.adult);
					data.adult = tx.getEditableText().toString();
					
					EditText tx2 = (EditText) findViewById(R.id.child);
					data.child = tx2.getEditableText().toString();
					
					Open.createdanfourl(Partd.this);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else{
					try {
					data.carq9_income = selected.getTag().toString();
					
					data.occupation = selected2.getTag().toString();
					
					EditText tx = (EditText) findViewById(R.id.adult);
					data.adult = tx.getEditableText().toString();
					
					EditText tx2 = (EditText) findViewById(R.id.child);
					data.child = tx2.getEditableText().toString();
					
					Open.createcarurl(Partd.this);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				Intent t = new Intent(Partd.this, End.class);
				startActivity(t);
			

			}
		});

		builder.setNegativeButton("No", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				d.dismiss();
			}
		});

		d = builder.create();
		d.show();

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		onBackPressed();
		return true;
	}
}
