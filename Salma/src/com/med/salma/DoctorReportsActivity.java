package com.med.salma;

import com.med.salma.R;

import android.app.ActionBar;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class DoctorReportsActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_reports);

		ActionBar action_bar = getActionBar();
		action_bar.setBackgroundDrawable(new ColorDrawable(getResources()
				.getColor(R.drawable.action_bar)));

		TabHost tabHost = getTabHost();

		this.setNewTab(this, tabHost, "video_reports_tab",
				R.string.video_reports_tab, R.drawable.ic_action_video,
				new Intent(this, VideoReportsActivity.class));
		this.setNewTab(this, tabHost, "non_adherence_report_tab",
				R.string.non_adherence_reports_tab,
				R.drawable.ic_action_dontlike, new Intent(this,
						NonAdherenceReportsActivity.class));

		this.setNewTab(this, tabHost, "adherence_report_tab",
				R.string.adherence_reports_tab, R.drawable.ic_action_like,
				new Intent(this, AdherenceReportsActivity.class));
	}

	private void setNewTab(Context context, TabHost tabHost, String tag,
			int title, int icon, Intent contentID) {
		TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag);
		tabSpec.setIndicator(getTabIndicator(tabHost.getContext(), title, icon));
		tabSpec.setContent(contentID);
		tabHost.addTab(tabSpec);
	}

	private View getTabIndicator(Context context, int title, int icon) {
		View view = LayoutInflater.from(context).inflate(
				R.layout.tab_new_reminder_layout, null);
		ImageView iv = (ImageView) view.findViewById(R.id.reminder_image);
		iv.setImageResource(icon);
		TextView tv = (TextView) view.findViewById(R.id.reminder_text);
		tv.setText(title);
		return view;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater menu_inflater = getMenuInflater();
		menu_inflater.inflate(R.menu.doctors_reports_menu, menu);
		return super.onCreateOptionsMenu(menu);
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case R.id.action_show_doctors_list:
			Intent doctors_list_intent = new Intent(this, DoctorsListActivity.class);
			startActivity(doctors_list_intent);
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
