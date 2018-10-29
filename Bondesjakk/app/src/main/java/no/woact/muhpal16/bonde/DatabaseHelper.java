package no.woact.muhpal16.bonde;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_HIGHSCORE = "Highscore.db";
    public static final String TABLE_HIGHSCORE = "highscore_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "name";
    public static final String COL_3 = "wins";
    public static final String COL_4 = "draws";
    public static final String COL_5 = "losses";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_HIGHSCORE, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_HIGHSCORE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, WINS INTEGER, DRAWS INTEGER, LOSSES INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIGHSCORE);
        onCreate(db);
    }

    public boolean insertPlayer(String name, int wins, int draws, int losses){  // mulig dele denne opp senere?
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3, "Wins: " + wins);
        contentValues.put(COL_4, "Draws: " + draws);
        contentValues.put(COL_5, "Losses " + losses);
        long result = db.insert(TABLE_HIGHSCORE,null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " +  TABLE_HIGHSCORE;
        Cursor data = db.rawQuery(query, null);
        return data;
    }



}
