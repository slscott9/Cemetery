package hfad.com.databaserecent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context; //We need a context object so we can get the context for this database open helper
    private static final String DATABASE_NAME = "Cemetery.db";
    private static final int DATABASE_VERSION = 3;

    private static final String TABLE_NAME = "my_cemeteries";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "cemetery_name";
    private static final String COLUMN_LOCATION = "cemetery_location";
    private static final String COLUMN_GRAVE_NUM = "cemetery_grave_num";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {            //Create the table first format the table in a query string variable, then call the exec command with String query

        // _id    COLUMN_NAME    COLUMN_LOCATION    COLUMN_GRAVE_NUM

        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_LOCATION + " TEXT, " +
                        COLUMN_GRAVE_NUM + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addCemetery(String name, String location, int graveNum){
        SQLiteDatabase db = this.getWritableDatabase(); //database object refers to this sqlite database for writable data
        ContentValues cv = new ContentValues(); //We are going to store all our data from our app and pass this to our database table

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_LOCATION, location);
        cv.put(COLUMN_GRAVE_NUM, graveNum);
        long result = db.insert(TABLE_NAME, null, cv); //insert the ContentValues object into our table we put it in a result variable for checking if it was successful
        if(result == -1){
            Toast.makeText(context, "Database unavailable", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;  //Load all the data from the database into the cursor, return the cursor so we can use its data in the recycler view
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String id, String name, String location, String graveNums){ //This will be called inside update activity
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_LOCATION, location);
        cv.put(COLUMN_GRAVE_NUM, graveNums);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{id});
        if(result == -1){
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully ", Toast.LENGTH_SHORT).show();
        }
    }
}
