import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Patients Table
public class PatientsTable {

	private static final String PATIENT_ID = "patient_id";
	private static final String PATIENT_FIRST_NAME = "patient_first_name";
	private static final String PATIENT_SECOND_NAME = "patient_second_name";
	private static final String PATIENT_DOB = "patient_dob";
	private static final String PATIENT_GENDER = "patient_gender";
	private static final String PATIENT_USERNAME = "patient_username";
	private static final String PATIENT_EMAIL_ADDRESS = "patient_email_address";
	private static final String PATIENT_PASSWORD = "patient_password";

}

// Doctors Table
public class DoctorsTable {

	private static final String DOCTOR_ID = "doctor_id";
	private static final String DOCTOR_FIRST_NAME = "doctor_first_name";
	private static final String DOCTOR_SECOND_NAME = "doctor_second_name";
	private static final String DOCTOR_SPECIALTY = "doctor_specialty";
	private static final String DOCTOR_GENDER = "doctor_gender";
	private static final String DOCTOR_USERNAME = "doctor_username";
	private static final String DOCTOR_EMAIL_ADDRESS = "doctor_email_address";
	private static final String DOCTOR_PASSWORD = "doctor_password";

}

// Reminders Table
public class RemindersTable {

	private static final String REMINDER_ID = "reminder_id";
	private static final String REMINDER_MEDICINE_NAME = "reminder_medicine_name";
	private static final String REMINDER_INSTRUCTIONS = "reminder_instructions";
	private static final String REMINDER_DRUG_TYPE = "reminder_drug_type";
	private static final String REMINDER_FREQUENCY = "reminder_frequency";
	private static final String REMINDER_SET_TIME = "reminder_set-time";
	private static final String REMINDER_START_DATE = "reminder_start_date";
	private static final String REMINDER_END_DATE = "reminder_end_date";

}

// Non-adherence Reports Table
public class NonAdherenceTable {
	private static final String ADHERENCE_ID = "adherence_id";
	private static final String REMINDER_ID = "reminder_id";
	private static final String ADHERENCE_DESCRIPTION = "adherence_description";
}

// Database Helper
public class SalmaDB extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "salma.db";
	private static final String DATABASE_VERSION = 1;

	public SalmaDB(Context context) {
		// TODO Auto-generated constructor stub

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db){
		// Create Doctor's Table
		db.execSQL("CREATE TABLE " + DoctorsTable.TABLE_NAME
				+ " (" + DoctorsTable.DOCTOR_ID + " INTEGER PRIMARY KEY
				AUTOINCREMENT,"
				+ DoctorsTable.DOCTOR_FIRST_NAME + " TEXT,"
				+ DoctorsTable.DOCTOR_SECOND_NAME + " TEXT,"
				+ DoctorsTable.DOCTOR_SPECIALTY + " TEXT,"
				+ DoctorsTable.DOCTOR_GENDER + " TEXT,"
				+ DoctorsTable.DOCTOR_USERNAME + " TEXT,"
				+ DoctorsTable.DOCTOR_EMAIL_ADDRESS + " TEXT,"
				+ DoctorsTable.DOCTOR_PASSWORD + " INTEGER);");
		
		// Create Patient's Table
		db.execSQL("CREATE TABLE " + PatientsTable.TABLE_NAME
				+ " (" + PatientsTable.PATIENT_ID + " INTEGER PRIMARY KEY
				AUTOINCREMENT,"
				+ PatientsTable.PATIENT_FIRST_NAME + " TEXT,"
				+ PatientsTable.PATIENT_SECOND_NAME + " TEXT,"
				+ PatientsTable.PATIENT_DOB + " TEXT,"
				+ PatientsTable.PATIENT_GENDER + " TEXT,"
				+ PatientsTable.PATIENT_USERNAME + " TEXT,"
				+ PatientsTable.PATIENT_EMAIL_ADDRESS + " TEXT,"
				+ PatientsTable.PATIENT_PASSWORD + " INTEGER);");
		
		// Create Reminder's Table
		db.execSQL("CREATE TABLE " + RemindersTable.TABLE_NAME
				+ " (" + RemindersTable.REMINDER_ID + " INTEGER PRIMARY KEY
				AUTOINCREMENT,"
				+ RemindersTable.REMINDER_MEDICINE_NAME + " TEXT,"
				+ RemindersTable.REMINDER_INSTRUCTIONS + " TEXT,"
				+ RemindersTable.REMINDER_DRUG_TYPE + " TEXT,"
				+ RemindersTable.REMINDER_FREQUENCY + " TEXT,"
				+ RemindersTable.REMINDER_SET_TIME + " TEXT,"
				+ RemindersTable.REMINDER_START_DATE + " TEXT,"
				+ RemindersTable.REMINDER_END_DATE + " INTEGER);");
		
		// Create Non-Adherence Table
		db.execSQL("CREATE TABLE " + NonAdherenceTable.TABLE_NAME + "(" + NonAdherenceTable.ADHERENCE_ID + 
				"INTEGER PRIMARY KEY AUTOINCREMENT," + NonAdherenceTable.REMINDER_ID + "INTEGER," + 
				NonAdherenceTable.ADHERENCE_DESCRIPTION + "TEXT);");
	}

	// Wrapper method for adding a new Doctor
	public long addDoctor(String first_name, String second_name,
			String specialty, String gender, String username,
			String email_address, String password) {

		ContentValues cv = new ContentValues();
		cv.put(DoctorsTable.DOCTOR_FIRST_NAME, first_name);
		cv.put(DoctorsTable.DOCTOR_SECOND_NAME, second_name);
		cv.put(DoctorsTable.DOCTOR_SPECIALTY, specialty);
		cv.put(DoctorsTable.DOCTOR_GENDER, gender);
		cv.put(DoctorsTable.DOCTOR_USERNAME, username);
		cv.put(DoctorsTable.DOCTOR_EMAIL_ADDRESS, email_address);
		cv.put(DoctorsTable.DOCTOR_PASSWORD, password);

		SQLiteDatabase db = getWritableDatabase();
		long result = db.insert(DoctorsTable.TABLE_NAME,
				DoctorsTable.DOCTOR_FIRST_NAME, cv);
		return result;

	}

	// Wrapper method for adding a new Patient
	public long addPatient(String first_name, String second_name, String dob,
			String gender, String username, String email_address,
			String password) {

		ContentValues cv = new ContentValues();
		cv.put(PatientsTable.PATIENT_FIRST_NAME, first_name);
		cv.put(PatientsTable.PATIENT_SECOND_NAME, second_name);
		cv.put(PatientsTable.PATIENT_DOB, dob);
		cv.put(PatientsTable.PATIENT_GENDER, gender);
		cv.put(PatientsTable.PATIENT_USERNAME, username);
		cv.put(PatientsTable.PATIENT_EMAIL_ADDRESS, email_address);
		cv.put(PatientsTable.PATIENT_PASSWORD, password);

		SQLiteDatabase db = getWritableDatabase();
		long result = db.insert(PatientsTable.TABLE_NAME,
				PatientsTable.PATIENT_FIRST_NAME, cv);
		return result;

	}

	// Wrapper method for adding a new Reminder
	public long addReminder(String medicine_name, String instructions,
			String drug_type, String frequency, String set_time,
			String start_date, String end_date) {

		ContentValues cv = new ContentValues();
		cv.put(RemindersTable.REMINDER_MEDICINE_NAME, medicine_name);
		cv.put(RemindersTable.REMINDER_INSTRUCTIONS, instructions);
		cv.put(RemindersTable.REMINDER_FREQUENCY, frequency);
		cv.put(RemindersTable.REMINDER_DRUG_TYPE, drug_type);
		cv.put(RemindersTable.REMINDER_SET_TIME, set_time);
		cv.put(RemindersTable.REMINDER_START_DATE, start_date);
		cv.put(RemindersTable.REMINDER_END_DATE, end_date);

		SQLiteDatabase db = getWritableDatabase();
		long result = db.insert(RemindersTable.TABLE_NAME,
				RemindersTable.REMINDER_MEDICINE_NAME, cv);
		return result;

	}

	// Wrapper method for adding non-adherence reports
	public long addNonAdherenceReport(String reminder_id,
			String adherence_description) {
		ContentValues cv = new ContentValues();
		cv.put(NonAdherenceTable.REMINDER_ID, reminder_id);
		cv.put(NonAdherenceTable.ADHERENCE_DESCRIPTION, adherence_description);

		SQLiteDatabase db = getWritableDatabase();
		long result = db.insert(NonAdherenceTable.TABLE_NAME,
				NonAdherenceTable.REMINDER_ID, cv);
		return result;
	}

	// Wrapper method for listing all non-adherence reports
	public Cursor listNonAdherenceReports() {
		SQLiteDatabase db = getWritableDatabase();
		String[] columns = new String[]{NonAdherenceTable.REMINDER_ID, NonAdherenceTable.ADHERENCE_DESCRIPTION};
        Cursor cursor = db.query(NonAdherenceTable.TABLE_NAME, columns);
        return cursor;
        
	// Wrapper method for listing all reminders
	public Cursor listReminders() {
		SQLiteDatabase db = getWritableDatabase();
		String[] columns = new String[]{RemindersTable.REMINDER_ID, RemindersTable.REMINDER_MEDICINE_NAME};
        Cursor cursor = db.query(RemindersTable.TABLE_NAME, columns);
        return cursor;
	}

	}
}