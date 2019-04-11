package aiactr.archit.decode;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class EPICSQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "android_api";

    // Login table name
    private static final String TABLE_EPIC = "table_epic";

    // Login Table Columns names
    private static final String KEY_NAME = "name";
    private static final String KEY_POLLING_STATION = "polling_station";
    private static final String KEY_LAT = "lat";
    private static final String KEY_LNG = "lng";
    private static final String KEY_EPIC = "epic_no";
    private static final String KEY_IMAGE= "image";
    private static final String KEY_QUEUE = "queue";

    public EPICSQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_EPIC + "("
                +KEY_NAME + " VARCHAR,"+ KEY_POLLING_STATION + " VARCHAR,"+  KEY_LAT + " DOUBLE,"+ KEY_LNG + " DOUBLE,"+
                KEY_EPIC + " VARCHAR PRIMARY KEY,"
                +  KEY_IMAGE + "VARCHAR," +  KEY_QUEUE+ "INTEGER" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EPIC);

        // Create tables again
        onCreate(db);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails(String epic_no) {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_EPIC + " where " + KEY_EPIC + " = " + epic_no + "; ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("name", cursor.getString(1));
            user.put("polling_station", cursor.getString(2));
            user.put("lat", cursor.getString(3));
            user.put("lng", cursor.getString(4));
            user.put("epic_no", cursor.getString(5));
            user.put("image", cursor.getString(6));
            user.put("queue", cursor.getString(7));

        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    public void updateUserDetails(String polling_station,String q)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE TABLE_EPIC SET KEY_QUEUE=q WHERE KEY_POLLING_STATION=polling_station");
        db.close();
    }

}
