package com.med.salma;

import java.util.Calendar;

import com.med.salma.R;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PatientInfoActivity extends Activity {
	// Editables Declaration
	EditText txtFirstName;
	EditText txtSecondName;
	EditText txtDOB;
	EditText txtUserName;
	EditText txtEmailAddress;
	EditText txtPassword;
	RadioGroup rdGender;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_info);

		txtFirstName = (EditText) findViewById(R.id.txtPatientFirstName);
		txtSecondName = (EditText) findViewById(R.id.txtPatientSecondName);
		txtDOB = (EditText) findViewById(R.id.txtPatientDOB);
		txtUserName = (EditText) findViewById(R.id.txtPatientUsername);
		txtEmailAddress = (EditText) findViewById(R.id.txtPatientEmail);
		txtPassword = (EditText) findViewById(R.id.txtPatientPassword);
		rdGender = (RadioGroup) findViewById(R.id.radioGroup);
	}

	// Adding new patient (s)
	public void addPatient(View view) {
		// toString conversions
		String first_name = txtFirstName.getText().toString();
		String second_name = txtSecondName.getText().toString();
		String date_of_birth = txtDOB.getText().toString();
		String username = txtUserName.getText().toString();
		String email_address = txtEmailAddress.getText().toString();
		String password = txtPassword.getText().toString();
		int gender = rdGender.getCheckedRadioButtonId();

		SalmaDatabaseAdapter salmaDB = new SalmaDatabaseAdapter(this);
		salmaDB.addPatient(first_name, second_name, date_of_birth,
				String.valueOf(gender), username, email_address, password);
		Toast.makeText(this,first_name + " " + second_name + " Has Been Successfully Registered!",
				Toast.LENGTH_LONG).show();
		
		// Intent's Extra value
		String patient_name = first_name + " " + second_name;
		
		Intent reminders = new Intent(this, ReminderActivity.class);
		reminders.putExtra("Welcome Patient", patient_name);
		startActivity(reminders);
		}

	// Patient DOB
	public void patientDOB(View view) {
		final Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_YEAR);

		final EditText txtPatientDOB = (EditText) findViewById(R.id.txtPatientDOB);
		DatePickerDialog datePicker = new DatePickerDialog(this,
				new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// TODO Auto-generated method stub
						txtPatientDOB.setText(dayOfMonth + "/"
								+ (monthOfYear + 1) + "/" + year);

					}
				}, day, month, year);
		datePicker.setTitle("Date of Birth");
		datePicker.setIcon(R.drawable.ic_launcher);
		datePicker.show();

	}

}
