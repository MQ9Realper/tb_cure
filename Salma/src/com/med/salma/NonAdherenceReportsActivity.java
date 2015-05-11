package com.med.salma;


import com.med.salma.R;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class NonAdherenceReportsActivity extends Activity {
	public SalmaDatabaseAdapter database_adapter;
	public ListView reports_list;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_non_adherence_reports);
		database_adapter = new SalmaDatabaseAdapter(getApplicationContext());
		reports_list = (ListView)findViewById(R.id.adherenceReports);
		
		Cursor reports_cursor = database_adapter.listNonAdherenceReports();
		String[] source = new String[]{Table_NonAdherenceTable.NON_ADHERENCE_ID, Table_NonAdherenceTable.NON_ADHERENCE_DATE, Table_NonAdherenceTable.NON_ADHERENCE_TIME};
		int[] destination = new int[]{R.id.txt_non_adherence_report_code,
				R.id.txt_non_adherence_date, R.id.txt_non_adherence_time};
		@SuppressWarnings("deprecation")
		ListAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.non_adherence_report_list_row, reports_cursor, source, destination);
		reports_list.setAdapter(adapter);
	}
	
}
