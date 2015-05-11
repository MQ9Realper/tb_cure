package com.med.salma;

import java.io.File;

import com.med.salma.R;
import com.med.salma.VideoFilesAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.Toast;

public class VideoReportsActivity extends Activity {
	// Declare variables
	private String[] FilePathStrings;
	private String[] FileNameStrings;
	private File[] listFile;
	public File file;
	public File another_file;
	public ListView video_reports_list;
	public VideoFilesAdapter video_files_adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_reports);

		listVideoFiles();

	}

	

	// List video files
	public void listVideoFiles() {

		// Check for SD Card
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Toast.makeText(this, "Error! No SDCARD Found!", Toast.LENGTH_LONG)
					.show();
		} else {
			// Locate the image folder in your SD Card
			file = Environment
					.getExternalStorageDirectory();

			another_file = new File(file + File.separator + "TBCureVideos");
			// Create a new folder if no folder named SDImageTutorial exist
			another_file.mkdirs();
		}

		if (another_file.isDirectory()) {
			listFile = another_file.listFiles();
			// Create a String array for FilePathStrings
			FilePathStrings = new String[listFile.length];
			// Create a String array for FileNameStrings
			FileNameStrings = new String[listFile.length];

			for (int i = 0; i < listFile.length; i++) {
				// Get the path of the image file
				FilePathStrings[i] = listFile[i].getAbsolutePath();
				// Get the name image file
				FileNameStrings[i] = listFile[i].getName();
			}
		}
		video_reports_list = (ListView) findViewById(R.id.videoReports);
		video_files_adapter = new VideoFilesAdapter(this, FileNameStrings,
				FilePathStrings);
		video_reports_list.setAdapter(video_files_adapter);
		registerForContextMenu(video_reports_list);

	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View view,
			ContextMenuInfo menuInfo) {
		if (view.getId() == R.id.videoReports) {
			menu.setHeaderTitle("Video Options");
			menu.setHeaderIcon(R.drawable.ic_launcher);

			menu.add("Play Video");

		}

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo)item.getMenuInfo();
		if (item.getTitle() == "Play Video") {
			Intent videoIntent = new Intent(this, VideoViewActivity.class);
			videoIntent.putExtra("video_name", FileNameStrings[menuInfo.position]);
			startActivity(videoIntent);

		}
		return true;

	}
	

}
