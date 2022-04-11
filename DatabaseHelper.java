package edu.fsu.cs.groupproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Workouts.db";
    public static final String TABLE_NAME1 = "lifts_table";
    public static final String T1_COL0 = "ExerciseID";
    public static final String T1_COL1 = "Exercise";
    public static final String T1_COL2 = "MuscleGroup";

    public static final String TABLE_NAME2 = "workouts_table";
    public static final String T2_COL0 = "WorkoutID";
    public static final String T2_COL1 = "ExerciseID";
    public static final String T2_COL2 = "Date";
    public static final String T2_COL3 = "Sets";
    public static final String T2_COL4 = "Reps";
    public static final String T2_COL5 = "Weight";


    public DBHelper(@Nullable Context context) { super(context, DATABASE_NAME, null, 1);}
    // SQLiteDatabase db = (this.getWritableDatabase());

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("create table " + TABLE_NAME1 + " (ExerciseID INTEGER PRIMARY KEY AUTOINCREMENT, Exercise TEXT, MuscleGroup TEXT)" );
        sqLiteDatabase.execSQL("create table " + TABLE_NAME2 + " (WorkoutID INTEGER PRIMARY KEY AUTOINCREMENT, ExerciseID INTEGER, Date TEXT, Sets INTEGER, Reps INTEGER, Weight INTEGER)" );

        ContentValues values = new ContentValues();

        //Exercises and their Muscle Groups are Hard Coded:
        values.put(T1_COL1, "Bench Press");
        values.put(T1_COL2, "Chest");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        values.put(T1_COL1, "Squat");
        values.put(T1_COL2, "Legs");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        values.put(T1_COL1, "Flye");
        values.put(T1_COL2, "Chest");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        values.put(T1_COL1, "Dumbbell Curl");
        values.put(T1_COL2, "Biceps");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME2);
        onCreate(sqLiteDatabase);
    }

    //Can easily insert a new workout to the workout table:
    public boolean insertWorkout(int ExerciseID, String Date, int Sets, int Reps, int Weight){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T2_COL1, ExerciseID);
        values.put(T2_COL2, Date);
        values.put(T2_COL3, Sets);
        values.put(T2_COL4, Reps);
        values.put(T2_COL5, Weight);
        long results = db.insert(TABLE_NAME2, null, values);

        if(results == -1)
            return false;
        else
            return true;

    }



    //Test function to check data
    public Cursor getAllDatat1(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur =  db.rawQuery("SELECT * FROM " + TABLE_NAME1, null);
        return cur;
    }

    //Function to retrieve all records a user put in for a certain workout, e.g. bench = ExerciseId 1
    public Cursor getExerciseData(int ExerciseID){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME2 + " WHERE ExerciseID=" + ExerciseID + "";
        Cursor cur =  db.rawQuery(sql, null);
        //Cursor cur =  db.rawQuery("SELECT * FROM " + TABLE_NAME2, null);
        return cur;
    }

}


