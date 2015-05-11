package com.med.salma;

import com.med.salma.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class DoctorBioInfoActivity extends Activity {
	// Editables Declaration
	EditText txtFirstName;
	EditText txtSecondName;
	EditText txtSpecialty;
	EditText txtUserName;
	EditText txtEmailAddress;
	EditText txtPassword;
	RadioGroup rdGender;
	public ListView doctors_list;
	SalmaDatabaseAdapter database_adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_bio_info);

//		ActionBar action_bar = getActionBar();
//		action_bar.setBackgroundDrawable(new ColorDrawable(getResources()
//				.getColor(R.drawable.action_bar)));

		txtFirstName = (EditText) findViewById(R.id.txtDoctorFirstName);
		txtSecondName = (EditText) findViewById(R.id.txtDoctorSecondName);
		txtSpecialty = (EditText) findViewById(R.id.txtDoctorPractice);
		txtUserName = (EditText) findViewById(R.id.txtDoctorUsername);
		txtEmailAddress = (EditText) findViewById(R.id.txtDoctorEmail);
		txtPassword = (EditText) findViewById(R.id.txtDoctorPassword);
		rdGender = (RadioGroup) findViewById(R.id.radioGroup);

		doctors_list = (ListView) findViewById(R.id.doctors_list);
		database_adapter = new SalmaDatabaseAdapter(getApplicationContext());

	}

	// Method for populating the doctors list
	public void populateDoctorsList() {
		Cursor doctors_list_cursor = database_adapter.listDoctors();
		String[] source_columns = new String[] { Table_DoctorsTable.DOCTOR_FULL_NAME,
				Table_DoctorsTable.DOCTOR_SPECIALTY,
				Table_DoctorsTable.DOCTOR_EMAIL_ADDRESS };
		int[] destination_columns = new int[] { R.id.txt_doctor_first_name,
				R.id.txt_doctor_specialty, R.id.txt_doctor_email };
		@SuppressWarnings("deprecation")
		ListAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
				R.layout.doctors_list_row, doctors_list_cursor, source_columns,
				destination_columns);
		doctors_list.setAdapter(adapter);

	}

	// Method for adding new doctors
	public void addDoctor(View view) {
		// toString conversions
		String first_name = txtFirstName.getText().toString();
		String second_name = txtSecondName.getText().toString();
		String specialty = txtSpecialty.getText().toString();
		String username = txtUserName.getText().toString();
		String email_address = txtEmailAddress.getText().toString();
		String password = txtPassword.getText().toString();
		int gender = rdGender.getCheckedRadioButtonId();
		String full_name = "Dr. " + first_name + " " + second_name;

		SalmaDatabaseAdapter salmaDB = new SalmaDatabaseAdapter(this);
		salmaDB.addDoctor(first_name, second_name, full_name, specialty,
				String.valueOf(gender), username, email_address, password);
		Toast.makeText(this,
				"Dr. " + first_name + " Has Been Successfully Registered!",
				Toast.LENGTH_LONG).show();

		// Reset Textboxes
		txtFirstName.setText("");
		txtSecondName.setText("");
		txtSpecialty.setText("");
		txtEmailAddress.setText("");
		txtUserName.setText("");
		txtPassword.setText("");

		showDialog();

	}

	public void showDialog() {

		AlertDialog.Builder doctors_alert_builder = new AlertDialog.Builder(
				this);
		doctors_alert_builder.setTitle("Doctor Action");
		doctors_alert_builder.setIcon(getResources().getDrawable(
				R.drawable.ic_launcher));
		doctors_alert_builder.setMessage("Do you want to view reports?");
		doctors_alert_builder.setNegativeButton("Not Now",
				new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent doctors_list_intent = new Intent(
								getApplicationContext(),
								DoctorsListActivity.class);
						startActivity(doctors_list_intent);
					}
				});
		doctors_alert_builder.setPositiveButton("Yes", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// Move to doctors reports
				Intent doctor_reports = new Intent(getApplicationContext(),
						DoctorReportsActivity.class);
				startActivity(doctor_reports);

			}
		});
		AlertDialog doctor_dialog = doctors_alert_builder.create();
		doctor_dialog.show();

	}

}
