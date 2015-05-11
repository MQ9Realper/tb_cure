package com.med.salma;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class NonAdherenceReportsListActivity extends Activity {
	public SalmaDatabaseAdapter database_adapter;
	public ListView reports_list;

	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nonadherence_reports_list);
		
		ActionBar action_bar = getActionBar();
		action_bar.setBackgroundDrawable(new ColorDrawable(getResources()
				.getColor(R.drawable.action_bar)));

		database_adapter = new SalmaDatabaseAdapter(getApplicationContext());
		reports_list = (ListView) findViewById(R.id.adherence_list_view);

		SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
		Calendar c = Calendar.getInstance();

		// Saving Report
		database_adapter.addNonAdherenceReport(DateFormat.getDateInstance()
				.format(new Date()), df.format(c.getTime()).toString());

		Toast.makeText(
				getApplicationContext(),
				"A non-adherence report has been saved on "
						+ DateFormat.getDateInstance().format(new Date()),
				Toast.LENGTH_LONG).show();

		// Listing Reports
		Cursor reports_cursor = database_adapter.listNonAdherenceReports();
		String[] source = new String[] {
				Table_NonAdherenceTable.NON_ADHERENCE_ID,
				Table_NonAdherenceTable.NON_ADHERENCE_DATE,
				Table_NonAdherenceTable.NON_ADHERENCE_TIME };
		int[] destination = new int[] { R.id.txt_non_adherence_report_code,
				R.id.txt_non_adherence_date, R.id.txt_non_adherence_time };
		@SuppressWarnings("deprecation")
		ListAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
				R.layout.non_adherence_report_list_row, reports_cursor, source,
				destination);
		reports_list.setAdapter(adapter);

	}
}