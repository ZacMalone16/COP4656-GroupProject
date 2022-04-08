package edu.fsu.cs.groupproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Workouts.db";
    public static final String TABLE_NAME = "lifts_table";
    public static final String COL_1 = "ID";
    // public static final String COL_X = "DATE";
    // public static final String COL_X = "MuscleGroup";    // exercise within musclegroup
    public static final String COL_2 = "Exercise";
    public static final String COL_3 = "Sets";      // multiple sets? Object that stores set data? Object that stores Date?
    public static final String COL_4 = "Reps";
    public static final String COL_5 = "Weight";
    public static final String COL_6 = "Workout_Time";
    public static final String COL_7 = "Duration";
    public static final String COL_8 = "Average_HR";
    public static final String COL_9 = "Calorie";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = (this.getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Exercise TEXT, Sets INTEGER, Reps INTEGER, Weight INTEGER, " +
                "Workout_Time TIME, Duration INTEGER, Average_HR INTEGER, Calories INTEGER)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String Exercise, String Sets, String Reps,
                              String Weight, String Workout_Time, String Duration, String Average_HR,
                              String Calories)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_2, Exercise);
        values.put(COL_3, Sets);
        values.put(COL_4, Reps);
        values.put(COL_5, Weight);
        values.put(COL_6, Workout_Time);
        values.put(COL_7, Duration);
        values.put(COL_8, Average_HR);
        values.put(COL_9, Calories);

        long results = db.insert(TABLE_NAME, null, values);

        return results != -1;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur =  db.rawQuery("select * from " + TABLE_NAME, null);
        return cur;
    }
}
