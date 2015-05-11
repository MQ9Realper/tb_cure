package com.med.salma;

import com.med.salma.Table_DoctorsTable;
import com.med.salma.Table_NonAdherenceTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// Database Helper
public class SalmaDatabaseAdapter extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "salma.db";
	private static final int DATABASE_VERSION = 1;

	public SalmaDatabaseAdapter(Context context) {
		// TODO Auto-generated constructor stub

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		// UPGRADE STATEMENTS

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create Doctor's Table
		db.execSQL("CREATE TABLE " + Table_DoctorsTable.TABLE_NAME + "("
				+ Table_DoctorsTable.DOCTOR_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ Table_DoctorsTable.DOCTOR_FIRST_NAME + " TEXT,"
				+ Table_DoctorsTable.DOCTOR_SECOND_NAME + " TEXT,"
				+ Table_DoctorsTable.DOCTOR_FULL_NAME + " TEXT,"
				+ Table_DoctorsTable.DOCTOR_SPECIALTY + " TEXT,"
				+ Table_DoctorsTable.DOCTOR_GENDER + " TEXT,"
				+ Table_DoctorsTable.DOCTOR_USERNAME + " TEXT,"
				+ Table_DoctorsTable.DOCTOR_EMAIL_ADDRESS + " TEXT,"
				+ Table_DoctorsTable.DOCTOR_PASSWORD + " INTEGER);");

		// Create Patient's Table
		db.execSQL("CREATE TABLE " + Table_PatientsTable.TABLE_NAME + "("
				+ Table_PatientsTable.PATIENT_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ Table_PatientsTable.PATIENT_FIRST_NAME + " TEXT,"
				+ Table_PatientsTable.PATIENT_SECOND_NAME + " TEXT,"
				+ Table_PatientsTable.PATIENT_DOB + " TEXT,"
				+ Table_PatientsTable.PATIENT_GENDER + " TEXT,"
				+ Table_PatientsTable.PATIENT_USERNAME + " TEXT,"
				+ Table_PatientsTable.PATIENT_EMAIL_ADDRESS + " TEXT,"
				+ Table_PatientsTable.PATIENT_PASSWORD + " INTEGER);");

		// Create Reminder's Table
		db.execSQL("CREATE TABLE " + Table_RemindersTable.TABLE_NAME + "("
				+ Table_RemindersTable.REMINDER_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ Table_RemindersTable.REMINDER_MEDICINE_NAME + " TEXT,"
				+ Table_RemindersTable.REMINDER_INSTRUCTIONS + " TEXT,"
				+ Table_RemindersTable.REMINDER_DRUG_TYPE + " TEXT,"
				+ Table_RemindersTable.REMINDER_FREQUENCY + " TEXT,"
				+ Table_RemindersTable.REMINDER_DATE_TIME + " TEXT,"
				+ Table_RemindersTable.REMINDER_END_DATE + " INTEGER);");

		// Create Non-Adherence Table
		db.execSQL("CREATE TABLE " + Table_NonAdherenceTable.TABLE_NAME + "("
				+ Table_NonAdherenceTable.NON_ADHERENCE_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ Table_NonAdherenceTable.NON_ADHERENCE_DATE + " TEXT,"
				+ Table_NonAdherenceTable.NON_ADHERENCE_TIME + " TEXT" + ");");

		// Create Adherence Table
		db.execSQL("CREATE TABLE " + Table_AdherenceTable.TABLE_NAME + "("
				+ Table_AdherenceTable.ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ Table_AdherenceTable.VIDEO_NAME + " TEXT,"
				+ Table_AdherenceTable.ADHERENCE_DATE + " TEXT" + ");");

		// Create Refills Table
		db.execSQL("CREATE TABLE " + Table_RefillTable.TABLE_NAME + "("
				+ Table_RefillTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ Table_RefillTable.REFILL_NAME + " TEXT,"
				+ Table_RefillTable.REFILL_DATE + " TEXT,"
				+ Table_RefillTable.REFILL_TIME + " TEXT" + ");");
	}

	public long addAdherenceReport(String video_name, String adherence_date) {
		ContentValues cv = new ContentValues();
		cv.put(Table_AdherenceTable.VIDEO_NAME, video_name);
		cv.put(Table_AdherenceTable.ADHERENCE_DATE, adherence_date);

		SQLiteDatabase db = getWritableDatabase();
		long result = db.insert(Table_AdherenceTable.TABLE_NAME,
				Table_AdherenceTable.VIDEO_NAME, cv);
		return result;
	}

	public Cursor listAdherenceReports() {
		SQLiteDatabase db = getWritableDatabase();
		Cursor adherence_reports = db.query(Table_AdherenceTable.TABLE_NAME,
				new String[] { Table_AdherenceTable.ID,
						Table_AdherenceTable.VIDEO_NAME,
						Table_AdherenceTable.ADHERENCE_DATE }, null, null,
				null, null, null);
		return adherence_reports;
	}

	// Wrapper method for a new refill
	public long addRefill(String refill_name, String refill_date,
			String refill_time) {
		ContentValues refill_contents = new ContentValues();
		refill_contents.put(Table_RefillTable.REFILL_NAME, refill_name);
		refill_contents.put(Table_RefillTable.REFILL_DATE, refill_date);
		refill_contents.put(Table_RefillTable.REFILL_TIME, refill_time);
		SQLiteDatabase db = getWritableDatabase();
		long result = db.insert(Table_RefillTable.TABLE_NAME,
				Table_RefillTable.REFILL_NAME, refill_contents);
		return result;
	}

	// Wrapper method for listing all Refills
	public Cursor refillList() {
		SQLiteDatabase db = getWritableDatabase();
		Cursor refills_list = db.query(Table_RefillTable.TABLE_NAME,
				new String[] { Table_RefillTable.ID,
						Table_RefillTable.REFILL_NAME,
						Table_RefillTable.REFILL_DATE,
						Table_RefillTable.REFILL_TIME }, null, null, null,
				null, null);
		return refills_list;
	}

	// Wrapper method for adding a new Doctor
	public long addDoctor(String first_name, String second_name,
			String full_name, String specialty, String gender, String username,
			String email_address, String password) {

		ContentValues cv = new ContentValues();
		cv.put(Table_DoctorsTable.DOCTOR_FIRST_NAME, first_name);
		cv.put(Table_DoctorsTable.DOCTOR_SECOND_NAME, second_name);
		cv.put(Table_DoctorsTable.DOCTOR_FULL_NAME, full_name);
		cv.put(Table_DoctorsTable.DOCTOR_SPECIALTY, specialty);
		cv.put(Table_DoctorsTable.DOCTOR_GENDER, gender);
		cv.put(Table_DoctorsTable.DOCTOR_USERNAME, username);
		cv.put(Table_DoctorsTable.DOCTOR_EMAIL_ADDRESS, email_address);
		cv.put(Table_DoctorsTable.DOCTOR_PASSWORD, password);

		SQLiteDatabase db = getWritableDatabase();
		long result = db.insert(Table_DoctorsTable.TABLE_NAME,
				Table_DoctorsTable.DOCTOR_FIRST_NAME, cv);
		return result;

	}

	// Wrapper method for adding a new Patient
	public long addPatient(String first_name, String second_name, String dob,
			String gender, String username, String email_address,
			String password) {

		ContentValues cv = new ContentValues();
		cv.put(Table_PatientsTable.PATIENT_FIRST_NAME, first_name);
		cv.put(Table_PatientsTable.PATIENT_SECOND_NAME, second_name);
		cv.put(Table_PatientsTable.PATIENT_DOB, dob);
		cv.put(Table_PatientsTable.PATIENT_GENDER, gender);
		cv.put(Table_PatientsTable.PATIENT_USERNAME, username);
		cv.put(Table_PatientsTable.PATIENT_EMAIL_ADDRESS, email_address);
		cv.put(Table_PatientsTable.PATIENT_PASSWORD, password);

		SQLiteDatabase db = getWritableDatabase();
		long result = db.insert(Table_PatientsTable.TABLE_NAME,
				Table_PatientsTable.PATIENT_FIRST_NAME, cv);
		return result;

	}

	// Wrapper method for adding a new Reminder
	public long addReminder(String medicine_name, String instructions,
			String drug_type, String frequency, String date_time,
			String end_date) {

		ContentValues cv = new ContentValues();
		cv.put(Table_RemindersTable.REMINDER_MEDICINE_NAME, medicine_name);
		cv.put(Table_RemindersTable.REMINDER_INSTRUCTIONS, instructions);
		cv.put(Table_RemindersTable.REMINDER_FREQUENCY, frequency);
		cv.put(Table_RemindersTable.REMINDER_DRUG_TYPE, drug_type);
		cv.put(Table_RemindersTable.REMINDER_DATE_TIME, date_time);
		cv.put(Table_RemindersTable.REMINDER_END_DATE, end_date);

		SQLiteDatabase db = getWritableDatabase();
		long result = db.insert(Table_RemindersTable.TABLE_NAME,
				Table_RemindersTable.REMINDER_MEDICINE_NAME, cv);
		return result;

	}

	// Wrapper method for adding non-adherence reports
	public long addNonAdherenceReport(String non_adherence_date,
			String non_adhrence_time) {
		ContentValues cv = new ContentValues();
		cv.put(Table_NonAdherenceTable.NON_ADHERENCE_DATE, non_adherence_date);
		cv.put(Table_NonAdherenceTable.NON_ADHERENCE_TIME, non_adhrence_time);

		SQLiteDatabase db = getWritableDatabase();
		long result = db.insert(Table_NonAdherenceTable.TABLE_NAME,
				Table_NonAdherenceTable.NON_ADHERENCE_DATE, cv);
		return result;
	}

	// Wrapper method for listing all non-adherence reports
	public Cursor listNonAdherenceReports() {
		SQLiteDatabase db = getWritableDatabase();
		Cursor non_adherence_reports = db.query(
				Table_NonAdherenceTable.TABLE_NAME, new String[] {
						Table_NonAdherenceTable.NON_ADHERENCE_ID,
						Table_NonAdherenceTable.NON_ADHERENCE_DATE,
						Table_NonAdherenceTable.NON_ADHERENCE_TIME }, null,
				null, null, null, null);
		return non_adherence_reports;
	}

	// Wrapper method for listing all reminder names
	public Cursor listReminderNames() {
		SQLiteDatabase db = getWritableDatabase();
		Cursor reminders = db.query(Table_RemindersTable.TABLE_NAME,
				new String[] { Table_RemindersTable.REMINDER_ID,
						Table_RemindersTable.REMINDER_MEDICINE_NAME,
						Table_RemindersTable.REMINDER_INSTRUCTIONS,
						Table_RemindersTable.REMINDER_DATE_TIME }, null, null,
				null, null, null);
		return reminders;

	}

	// Wrapper method for listing Reminders
	public Cursor listReminders() {
		SQLiteDatabase db = getWritableDatabase();
		Cursor reminders = db.query(Table_RemindersTable.TABLE_NAME,
				new String[] { Table_RemindersTable.REMINDER_ID,
						Table_RemindersTable.REMINDER_DATE_TIME }, null, null,
				null, null, null);
		return reminders;

	}

	// Wrapper method for listing Doctors
	public Cursor listDoctors() {
		SQLiteDatabase db = getWritableDatabase();
		Cursor doctors = db.query(Table_DoctorsTable.TABLE_NAME, new String[] {
				Table_DoctorsTable.DOCTOR_ID,
				Table_DoctorsTable.DOCTOR_FULL_NAME,
				Table_DoctorsTable.DOCTOR_SPECIALTY,
				Table_DoctorsTable.DOCTOR_EMAIL_ADDRESS }, null, null, null,
				null, null);
		return doctors;
	}

	// Doctor Signin
	public String doctorSignin(String username) {
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(Table_DoctorsTable.TABLE_NAME, null,
				Table_DoctorsTable.DOCTOR_USERNAME + "=?",
				new String[] { username }, null, null, null);
		if (cursor.getCount() < 1) {
			cursor.close();
			return "User Doesn't Exist";
		}
		cursor.moveToFirst();
		String password = cursor.getString(cursor
				.getColumnIndex(Table_DoctorsTable.DOCTOR_PASSWORD));
		return password;
	}

	// Patient Signin
	public String patientSignin(String username) {
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(Table_PatientsTable.TABLE_NAME, null,
				Table_PatientsTable.PATIENT_USERNAME + "=?",
				new String[] { username }, null, null, null);
		if (cursor.getCount() < 1) {
			cursor.close();
			return "User Doesn't Exist";
		}
		cursor.moveToFirst();
		String password = cursor.getString(cursor
				.getColumnIndex(Table_PatientsTable.PATIENT_PASSWORD));
		return password;

	}

	public void Upgrade() {
		SQLiteDatabase db = getWritableDatabase();
		Log.w("LOG_TAG", "Upgrading database");
		// KILL PREVIOUS
		db.execSQL("DROP TABLE IF EXISTS " + Table_RemindersTable.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + Table_DoctorsTable.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + Table_PatientsTable.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + Table_NonAdherenceTable.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + Table_AdherenceTable.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + Table_RefillTable.TABLE_NAME);
		onCreate(db);
	}
}