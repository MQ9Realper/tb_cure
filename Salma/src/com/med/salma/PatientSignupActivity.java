package com.med.salma;

import com.med.salma.R;

import android.app.ActionBar;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class PatientSignupActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
		ActionBar action_bar = getActionBar();
		action_bar.setDisplayHomeAsUpEnabled(true);
		action_bar.setBackgroundDrawable(new ColorDrawable(getResources()
				.getColor(R.drawable.action_bar)));

		TabHost tabHost = getTabHost();

		this.setNewTab(this, tabHost, "new_patient_info_tab",
				R.string.new_patient_info_tab, android.R.drawable.ic_menu_edit,
				new Intent(this, PatientInfoActivity.class));
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
		
	}

	
