package edu.fsu.cs.groupproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Workouts.db";
    public static final String TABLE_NAME1 = "lifts_table";
    public static final String T1_COL0 = "ExerciseID";
    public static final String T1_COL1 = "MuscleGroup";//"Exercise"
    public static final String T1_COL2 = "Exercise";//"MuscleGroup";

    public static final String TABLE_NAME2 = "workouts_table";
    public static final String T2_COL0 = "WorkoutID";  // Primary Key
    public static final String T2_COL1 = "ExerciseID";  //From Lifts Table
    public static final String T2_COL2 = "Date";

    public static final String TABLE_NAME3 = "sets_table";
    public static final String T3_COL0 = "SetID";       // primary key
    public static final String T3_COL1 = "WorkoutID";  //from workouts table
    public static final String T3_COL2 = "SetNum";
    public static final String T3_COL3 = "Reps";
    public static final String T3_COL4 = "Weight";




    public DatabaseHelper(@Nullable Context context) { super(context, DATABASE_NAME, null, 1);}
    //SQLiteDatabase db = (this.getWritableDatabase());

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("create table " + TABLE_NAME1 + " (ExerciseID INTEGER PRIMARY KEY AUTOINCREMENT, Exercise TEXT, MuscleGroup TEXT)" );
        sqLiteDatabase.execSQL("create table " + TABLE_NAME2 + " (WorkoutID INTEGER PRIMARY KEY AUTOINCREMENT, ExerciseID INTEGER, Date TEXT)" );
        sqLiteDatabase.execSQL("create table " + TABLE_NAME3 + " (SetID INTEGER PRIMARY KEY AUTOINCREMENT, WorkoutID INTEGER, SetNum INTEGER, Reps INTEGER, Weight INTEGER)" );


        ContentValues values = new ContentValues();

        //Exercises and their Muscle Groups are Hard Coded:
        // Will add more as we get sections working together.

        //lifts table
        //1
        values.put(T1_COL1, "Chest");//muscle group
        values.put(T1_COL2, "Bench Press");//exercise
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //2
        values.put(T1_COL1, "Chest");
        values.put(T1_COL2, "Incline Dumbbell Press");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //3

        values.put(T1_COL1, "Chest");
        values.put(T1_COL2, "Cable Flye");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //4

        values.put(T1_COL1, "Back");
        values.put(T1_COL2, "Lat Pulldown");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //5

        values.put(T1_COL1, "Back");
        values.put(T1_COL2, "T Bar Row");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //6

        values.put(T1_COL1, "Back");
        values.put(T1_COL2, "Cable Row");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //7
        values.put(T1_COL1, "Quads");
        values.put(T1_COL2, "Squat");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //8
        values.put(T1_COL1, "Biceps");
        values.put(T1_COL2, "Dumbbell Curl");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME2);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME3);
        onCreate(sqLiteDatabase);
    }

    //Can easily insert a new workout to the workout table:
    public boolean insertWorkout(int ExerciseID, String Date){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T2_COL1, ExerciseID);
        values.put(T2_COL2, Date);
        long results = db.insert(TABLE_NAME2, null, values);

        if(results == -1)
            return false;
        else
            return true;
    }

    // NEED Query by DATE


    // Date -> Muscle Group -> Exercise


    //This is mostly for Collin (By collin) but it might help make sense of this crap
    //When we are inserting sets
    // The workoutID will remain the same
    //So each row in sets that pertain to one workout will have the same workout ID
    // Can select data from rows Where workoutID = 1;

    /*
    User begins logging a particular exercise.
    The "Workout" is added to the workouts table.
    Retrieve the WorkoutID from the workouts table. (It is autoincremented) //This is the weird step
    Until the User is done logging that workout, this workoutID will not change.
   Example call for workout:
   //will need a date variable.
    db.insertWorkout(1, "01-01-2021");
    workoutID = db.getWorkoutID;
    Will Take in input:
    User enters weight.
    weight variable is set.
    Sensor tracks reps - stores to reps variable
    Sensor incremenets hypothetical "set_number" variable
    db.insertSet(workoutid, set_number, reps, weight);
    prompt for next weight or finish workout.
     */
    public boolean insertSet(int WorkoutID, int Set, int Reps, int Weight){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T3_COL1, WorkoutID);
        values.put(T3_COL2, Set);
        values.put(T3_COL3, Reps);
        values.put(T3_COL4, Weight);
        long results = db.insert(TABLE_NAME3, null, values);
        if(results == -1)
            return false;
        else
            return true;

    }

    //This is so we can retrieve workoutId to insert sets into a particular workout.
    public int getWorkoutID(){
        int workoutID = 0;
        String sql = "SELECT MAX(rowid) FROM " + TABLE_NAME2 + "";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if (cursor.moveToNext()) {
            workoutID = cursor.getInt(0);
            return workoutID;
        }
        else return 1;
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

    public Cursor getWorkoutData(int WorkoutID){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME3 + " WHERE WorkoutID=" + WorkoutID + "";
        Cursor cur =  db.rawQuery(sql, null);
        //Cursor cur =  db.rawQuery("SELECT * FROM " + TABLE_NAME2, null);
        return cur;
    }

    public Cursor getDataForDate(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT Date, WorkoutID FROM " + TABLE_NAME2 + " WHERE Date = '" + date + "' ";
        Cursor cur =  db.rawQuery(sql, null);
        return cur;
    }

}
//TODO: Date Query, Date/Muscle/Exercise Queries
// Date -> Muscle Group -> Exercise