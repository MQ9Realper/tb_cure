package com.med.salma;

import com.med.salma.R;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ReminderListActivity extends Activity {
	public final int mId = 1100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reminder_list);
		populateReminderList();

	}

	public void populateReminderList() {
		final ListView list = (ListView) findViewById(R.id.reminderList);
		SalmaDatabaseAdapter salmaDB = new SalmaDatabaseAdapter(
				ReminderListActivity.this);
		Cursor cursor = salmaDB.listReminderNames();
		String[] source = new String[] {
				Table_RemindersTable.REMINDER_MEDICINE_NAME,
				Table_RemindersTable.REMINDER_INSTRUCTIONS,
				Table_RemindersTable.REMINDER_DATE_TIME };
		int[] destination = new int[] { R.id.txt_reminder_name,
				R.id.txt_reminder_instructions, R.id.txt_reminder_datetime };

		@SuppressWarnings("deprecation")
		ListAdapter adapter = new SimpleCursorAdapter(
				ReminderListActivity.this, R.layout.reminder_list_text, cursor,
				source, destination);
		list.setAdapter(adapter);
		registerForContextMenu(list);
	}
}
