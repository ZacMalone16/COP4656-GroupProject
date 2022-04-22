package edu.fsu.cs.groupproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Workouts.db";
    public static final String TABLE_NAME1 = "lifts_table";
    public static final String T1_COL0 = "ExerciseID";
    public static final String T1_COL1 = "MuscleGroup";
    public static final String T1_COL2 = "Exercise";

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
        sqLiteDatabase.execSQL("create table " + TABLE_NAME1 + " (ExerciseID INTEGER PRIMARY KEY AUTOINCREMENT, MuscleGroup TEXT, Exercise TEXT)" );
        sqLiteDatabase.execSQL("create table " + TABLE_NAME2 + " (WorkoutID INTEGER PRIMARY KEY AUTOINCREMENT, ExerciseID INTEGER, Date TEXT)" );
        sqLiteDatabase.execSQL("create table " + TABLE_NAME3 + " (SetID INTEGER PRIMARY KEY AUTOINCREMENT, WorkoutID INTEGER, SetNum INTEGER, Reps INTEGER, Weight INTEGER)" );


        ContentValues values = new ContentValues();



        //Exercises and their Muscle Groups are Hard Coded:
        // Will add more as we get sections working together.
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
        values.put(T1_COL1, "Quads");
        values.put(T1_COL2, "Leg Press");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //9
        values.put(T1_COL1, "Quads");
        values.put(T1_COL2, "Leg Extension");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //10
        values.put(T1_COL1, "Hamstrings");
        values.put(T1_COL2, "Leg Curl");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //11
        values.put(T1_COL1, "Hamstrings");
        values.put(T1_COL2, "Dumbbell Lunge");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //12
        values.put(T1_COL1, "Hamstrings");
        values.put(T1_COL2, "Deadlift");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //13
        values.put(T1_COL1, "Calves");
        values.put(T1_COL2, "Calf Raise");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //14
        values.put(T1_COL1, "Calves");
        values.put(T1_COL2, "Seated Calf Raise");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //15 //was 10
        values.put(T1_COL1, "Biceps");
        values.put(T1_COL2, "Barbell Curl");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //16 was 11
        values.put(T1_COL1, "Biceps");
        values.put(T1_COL2, "Dumbbell Curl");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //17 was 12
        values.put(T1_COL1, "Biceps");
        values.put(T1_COL2, "Cable Curl");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //18
        values.put(T1_COL1, "Triceps");
        values.put(T1_COL2, "Barbell Extension");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //19
        values.put(T1_COL1, "Triceps");
        values.put(T1_COL2, "Dumbbell Extension");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //20
        values.put(T1_COL1, "Triceps");
        values.put(T1_COL2, "Cable Push Down");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //21
        values.put(T1_COL1, "Forearms");
        values.put(T1_COL2, "Wrist Curl");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //22
        values.put(T1_COL1, "Shoulders");
        values.put(T1_COL2, "Barbell Press");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //23
        values.put(T1_COL1, "Shoulders");
        values.put(T1_COL2, "Dumbbell Press");
        sqLiteDatabase.insert(TABLE_NAME1, null, values);
        //24
        values.put(T1_COL1, "Shoulders");
        values.put(T1_COL2, "Lateral Raise");
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

    public Cursor dateQuery(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT workouts_table.WorkoutID, lifts_table.Exercise, sets_table.SetNum, sets_table.Weight, sets_table.Reps, lifts_table.ExerciseID FROM lifts_table, workouts_table, sets_table ON lifts_table.ExerciseID = workouts_table.ExerciseID AND workouts_table.WorkoutID = sets_table.WorkoutID WHERE workouts_table.Date = '" + date + "' ORDER BY workouts_table.WorkoutID";


        Cursor cur =  db.rawQuery(sql, null);
        return cur;
        //cur.getString(0) //WorkoutID
        //cur.getString(1) //Exercise
        //cur.getString(2) // Set Number
        //cur.getString(3) // Weight
        //cur.getString(4) // Reps
        //cur.getString(5) // ExerciseID
    }

    /*
    public Cursor getAllDates()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur =  db.rawQuery("SELECT * FROM " + TABLE_NAME1, null);
        return cur;
    }
     */

    public Cursor maxWeight(int ExerciseID){
        SQLiteDatabase db = this.getReadableDatabase();
        //date and max weight for that specific exercise on each day.
        // select max(weight), date
        String sql = "SELECT  lifts_table.Exercise, max(sets_table.Weight), Date FROM lifts_table, workouts_table, sets_table ON workouts_table.WorkoutID = sets_table.WorkoutID WHERE workouts_table.ExerciseID = '" + ExerciseID + "'";
        Cursor cur =  db.rawQuery(sql, null);
        return cur;
        //cur.getString(0) //Exercise Name
        //cur.getString(1) //Max Weight
        //cur.getString(2) //Date of Max
    }

    public Cursor getDates(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT DISTINCT Date FROM " + TABLE_NAME2 + "";
        Cursor cur =  db.rawQuery(sql, null);
        return cur;
        //cur.getString(0) //Date
    }

    public ArrayList<String> getDatesList() {
        ArrayList<String> dates = new ArrayList<>();
        Cursor cursor = getDates();

        if (cursor == null) {
            return dates;
        }

        while (cursor.moveToNext()) {
            System.out.println(cursor.getString(0));
            dates.add(cursor.getString(0));
        }

        return dates;
    }

    public Cursor dailyMax(int ExerciseID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        //date and max for that exercise on that day
        String sql = "SELECT  Date, Max(Weight) FROM workouts_table, sets_table ON workouts_table.WorkoutID = sets_table.WorkoutID WHERE workouts_table.ExerciseID = '" + ExerciseID + "' GROUP BY Date";
        Cursor cur = db.rawQuery(sql, null);
        return cur;
    }

    public Cursor todaysWorkout(String current_date){
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT Exercise, SetNum, Reps, Weight FROM lifts_table, sets_table, workouts_table WHERE lifts_table.ExerciseID = workouts_table.ExerciseID AND workouts_table.Date = '" + current_date + "' GROUP BY Exercise, Date, SetNum, workouts_table.ExerciseID";
        Cursor cur =  db.rawQuery(sql, null);
        return cur;
    }

}
//TODO: Date Query, Date/Muscle/Exercise Queries
// Date -> Muscle Group -> Exercise