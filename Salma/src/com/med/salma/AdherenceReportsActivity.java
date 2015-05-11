package com.med.salma;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class AdherenceReportsActivity extends Activity {
	public SalmaDatabaseAdapter database_adapter;
	public ListView list_view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adherence_reports);
		database_adapter = new SalmaDatabaseAdapter(getApplicationContext());
		list_view = (ListView)findViewById(R.id.adherence_list_view);
		
		Cursor adherences_cursor = database_adapter.listAdherenceReports();
		String[] source = new String[]{Table_AdherenceTable.ID, Table_AdherenceTable.VIDEO_NAME, Table_AdherenceTable.ADHERENCE_DATE};
		int[] destination = new int[]{R.id.txt_adherence_code, R.id.txt_adherence_list_video, R.id.txt_adherence_date};
		@SuppressWarnings("deprecation")
		ListAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.adherence_reports_rows, adherences_cursor, source, destination);
		list_view.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.adherence_reports, menu);
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
