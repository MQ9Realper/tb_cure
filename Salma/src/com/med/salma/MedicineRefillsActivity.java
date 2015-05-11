package com.med.salma;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

public class MedicineRefillsActivity extends Activity {
	private Calendar refill_calendar;
	private Button btn_refill_date, btn_refill_time;
	private EditText refill_name;
	private TimePickerDialog time_picker;
	private DatePickerDialog date_picker;
	public SalmaDatabaseAdapter database_adapter;
	public ListView refills_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medicine_refills);
		listRefills();
		refill_name = (EditText) findViewById(R.id.txtRefillName);
		database_adapter = new SalmaDatabaseAdapter(this);
		refill_calendar = Calendar.getInstance();
		btn_refill_date = (Button) findViewById(R.id.btn_refill_date);
		btn_refill_time = (Button) findViewById(R.id.btn_refill_time);
		btn_refill_time.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setTime();

			}
		});

		btn_refill_date.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setDate();

			}
		});

	}

	// LIST REFILLS
	public void listRefills() {
		refills_list = (ListView) findViewById(R.id.refills_list);
		SalmaDatabaseAdapter db = new SalmaDatabaseAdapter(
				getApplicationContext());
		Cursor refills = db.refillList();
		String[] source = new String[] { Table_RefillTable.REFILL_NAME,
				Table_RefillTable.REFILL_DATE, Table_RefillTable.REFILL_TIME };
		int[] destination = new int[] { R.id.txt_refill_name,
				R.id.txt_refill_date, R.id.txt_refill_time };
		@SuppressWarnings("deprecation")
		ListAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.refills_rows, refills, source, destination);
		refills_list.setAdapter(adapter);
	}

	// SET REFILL TIME
	public void setTime() {

		time_picker = new TimePickerDialog(this,
				new TimePickerDialog.OnTimeSetListener() {

					@Override
					public void onTimeSet(TimePicker view, int hourOfDay,
							int minute) {
						// TODO Auto-generated method stub
						refill_calendar.setTimeInMillis(java.lang.System
								.currentTimeMillis());
						refill_calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
						refill_calendar.set(Calendar.MINUTE, minute);

						SimpleDateFormat time_format = new SimpleDateFormat(
								"hh:mm:ss");
						String time = time_format.format(refill_calendar
								.getTime());
						btn_refill_time.setText(time.toString());
					}
				}, refill_calendar.get(Calendar.HOUR_OF_DAY), refill_calendar
						.get(Calendar.MINUTE), true);
		time_picker.setTitle("Set Time");
		time_picker.setIcon(R.drawable.ic_launcher);
		time_picker.show();

	}

	// SET REFILL DATE
	public void setDate() {
		date_picker = new DatePickerDialog(this,
				new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						refill_calendar.set(Calendar.YEAR, year);
						refill_calendar.set(Calendar.MONTH, monthOfYear);
						refill_calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

						SimpleDateFormat date_format = new SimpleDateFormat(
								"dd-MM-yyyy");
						String date = date_format.format(refill_calendar
								.getTime());
						btn_refill_date.setText(date.toString());

					}
				}, refill_calendar.get(Calendar.YEAR),
				refill_calendar.get(Calendar.MONTH),
				refill_calendar.get(Calendar.DAY_OF_MONTH));
		date_picker.setTitle("Set Date");
		date_picker.setIcon(R.drawable.ic_launcher);
		date_picker.show();
	}
	
	// Method for saving refill details
	public void save(View view){
		database_adapter.addRefill(refill_name
				.getText().toString(),
				btn_refill_date.getText()
						.toString(),
				btn_refill_time.getText()
						.toString());
		Toast.makeText(
				getApplicationContext(),
				"Refill details have been saved!",
				Toast.LENGTH_LONG).show();

		listRefills();

		// Setting alarm
		Log.d("TBCure", "Setting alarm.....");
		Toast.makeText(getApplicationContext(),
				"Setting refill alarm....",
				Toast.LENGTH_LONG).show();
		AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(
				getApplicationContext(),
				RefillAlarmReceiver.class);
		PendingIntent alarmIntent = PendingIntent
				.getBroadcast(
						getApplicationContext(),
						0, intent, 0);
		alarmMgr.set(AlarmManager.RTC_WAKEUP,
				refill_calendar
						.getTimeInMillis(),
				alarmIntent);
		Log.d("TBCure", "Alarm has been set!");
		Toast.makeText(getApplicationContext(),
				"Refill alarm has been set!",
				Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.medicine_refills, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
