package com.med.salma;

import com.med.salma.R;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DoctorsListActivity extends Activity {

	ListView doctors_list;
	SalmaDatabaseAdapter database_adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctors_list);
		ActionBar action_bar = getActionBar();
		action_bar.setBackgroundDrawable(new ColorDrawable(getResources()
				.getColor(R.drawable.action_bar)));

		doctors_list = (ListView) findViewById(R.id.doctors_list);
		database_adapter = new SalmaDatabaseAdapter(getApplicationContext());

		populateDoctorsList();
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

}
