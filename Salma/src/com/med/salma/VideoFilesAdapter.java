package com.med.salma;

import com.med.salma.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class VideoFilesAdapter extends BaseAdapter {

	// Declare variables
	private Activity activity;
	private String[] filepath;
	private String[] filename;

	private static LayoutInflater inflater = null;

	public VideoFilesAdapter(Activity adapter_activity, String[] fpath,
			String[] fname) {
		activity = adapter_activity;
		filepath = fpath;
		filename = fname;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	public int getCount() {
		return filepath.length;

	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.video_files_text_rows, null);
		// Locate the TextView in gridview_item.xml
		TextView text = (TextView) vi.findViewById(R.id.txt_video_name);

		// Set file name to the TextView followed by the position
		text.setText(filename[position]);
		return vi;
	}
}