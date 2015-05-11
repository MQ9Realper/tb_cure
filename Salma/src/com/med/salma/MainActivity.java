package com.med.salma;

import com.med.salma.R;


import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ActionBar action_bar = getActionBar();
		action_bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.drawable.action_bar)));

		signupButtonListener();
		signinButtonListener();
	}

	// Upgrading Database
	public void upgradeDB() {
		AlertDialog.Builder upgradeBuilder = new AlertDialog.Builder(this);
		upgradeBuilder.setIcon(R.drawable.ic_launcher);
		upgradeBuilder.setTitle("Database Upgrade");
		upgradeBuilder.setMessage("Are You Sure You Want To Upgrade Database?");
		upgradeBuilder.setPositiveButton("Upgrade",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						SalmaDatabaseAdapter db = new SalmaDatabaseAdapter(
								getApplicationContext());
						db.Upgrade();
						Toast.makeText(getApplicationContext(),
								"Upgrade Has Successfully Finished!",
								Toast.LENGTH_LONG).show();
					}
				});
		upgradeBuilder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				});
		AlertDialog upgradeDialog = upgradeBuilder.create();
		upgradeDialog.show();

	}

	// Signup button listener
	public void signupButtonListener() {
		final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		final Button btnSignup = (Button) findViewById(R.id.Signup);

		btnSignup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// check if the patient radio button is checked
				int selectedButton = radioGroup.getCheckedRadioButtonId();

				switch (selectedButton) {
				case R.id.DoctorRadioButton:
					Intent doc = new Intent(getApplicationContext(),
							DoctorSignupActivity.class);
					startActivityForResult(doc, 0);
					Toast.makeText(getApplicationContext(), "Doctor",
							Toast.LENGTH_LONG).show();
					break;

				case R.id.PatientRadioButton:
					Intent pat = new Intent(getApplicationContext(),
							PatientSignupActivity.class);
					startActivity(pat);
					Toast.makeText(getApplicationContext(), "Patient",
							Toast.LENGTH_LONG).show();
					break;

				default:
					Toast.makeText(getApplicationContext(),
							"Please Select A Signup Type", Toast.LENGTH_LONG)
							.show();
				}

			}

		});

	}

	// Signin Button
	public void signinButtonListener() {
		final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		final Button btnSignin = (Button) findViewById(R.id.Signin);
		final EditText txtUsername = (EditText) findViewById(R.id.txtUsername);
		final EditText txtPassword = (EditText) findViewById(R.id.txtPass);

		final SalmaDatabaseAdapter sal = new SalmaDatabaseAdapter(
				getApplicationContext());

		btnSignin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// check if the patient radio button is checked
				int selectedButton = radioGroup.getCheckedRadioButtonId();

				switch (selectedButton) {
				case R.id.DoctorRadioButton: // Doctor's Radio Button
					try {
						String storedPassword = sal.doctorSignin(txtUsername
								.getText().toString());
						if (txtPassword.getText().toString()
								.equals(storedPassword)) {
							Toast.makeText(getApplicationContext(),
									"Login Has Been Successful!",
									Toast.LENGTH_LONG).show();
							Intent doc = new Intent(getApplicationContext(),
									DoctorReportsActivity.class);
							
							// Reset Textboxes
							txtPassword.setText("");
							txtUsername.setText("");
							
							doc.putExtra("Doctor Username", txtUsername
									.getText().toString());
							startActivity(doc);
						} else {
							Toast.makeText(getApplicationContext(),
									"Username or Password Does Not Exist!",
									Toast.LENGTH_LONG).show();
						}

					} catch (Exception e) {
						AlertDialog.Builder error = new AlertDialog.Builder(
								getApplicationContext());
						error.setTitle("Signin Error");
						error.setIcon(R.drawable.ic_launcher);
						error.setMessage(e.getMessage().toString());
						AlertDialog alert = error.create();
						alert.show();
					}

					break;

				case R.id.PatientRadioButton: // Patient's Radio Button
					try {

						String storedPassword = sal.patientSignin(txtUsername
								.getText().toString());
						if (txtPassword.getText().toString()
								.equals(storedPassword)) {
							Toast.makeText(getApplicationContext(),
									"Login Has Been Successful!",
									Toast.LENGTH_LONG).show();
							Intent patient = new Intent(
									getApplicationContext(),
									ReminderActivity.class);

							// Reset Textboxes
							txtPassword.setText("");
							txtUsername.setText("");
							
							patient.putExtra("Patient Username", txtUsername
									.getText().toString());
							startActivity(patient);
						} else {
							Toast.makeText(getApplicationContext(),
									"Username or Password Does Not Exist!",
									Toast.LENGTH_LONG).show();
						}

					} catch (Exception exception) {
						AlertDialog.Builder error = new AlertDialog.Builder(
								getApplicationContext());
						error.setTitle("Signin Error");
						error.setIcon(R.drawable.ic_launcher);
						error.setMessage(exception.getMessage().toString());
						AlertDialog alert = error.create();
						alert.show();
					}

					break;

				default:
					Toast.makeText(getApplicationContext(),
							"Please Select A Signup Type", Toast.LENGTH_LONG)
							.show();
				}

			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.about_menu) {

			// SalmaDatabaseAdapter db = new SalmaDatabaseAdapter(this);
			// db.delete();
			// Toast.makeText(this, "DONE!",Toast.LENGTH_LONG).show();
			Intent aboutIntent = new Intent(getApplicationContext(),
					AboutActivity.class);
			startActivity(aboutIntent);
		} else if (id == R.id.upgrade_menu) {
			upgradeDB();
		}

		return super.onOptionsItemSelected(item);

	}

}
