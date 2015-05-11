package com.med.salma;

import com.med.salma.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;


public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);

		ActionBar actionBar = getActionBar();
		actionBar.setTitle(" ");

	}

	// More Info Display method
	public void moreInfo(View view) {

		final CharSequence[] developerInfo = {
				"Developer: Abdalla Salma Mukhtasim",
				"Email: salma.mukhtasim@gmail.com", "Phone: +254 707 071349" };

		AlertDialog.Builder appInfo = new AlertDialog.Builder(this);
		appInfo.setIcon(R.drawable.ic_launcher);
		appInfo.setTitle("More Info");
		appInfo.setItems(developerInfo, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		appInfo.setNegativeButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});

		appInfo.setPositiveButton("Cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				});

		AlertDialog drugDialog = appInfo.create();
		drugDialog.show();

	}

}
