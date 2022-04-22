package edu.fsu.cs.groupproject.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
//import android.app.Fragment;

import edu.fsu.cs.groupproject.MainActivity;
import edu.fsu.cs.groupproject.R;
import edu.fsu.cs.groupproject.database.DatabaseHelper;
//import edu.fsu.cs.groupproject.graphs.Exercise;
import edu.fsu.cs.groupproject.graphs.Exercise;
import edu.fsu.cs.groupproject.graphs.GraphActivity;

public class CurrentExercise extends Fragment
{

    Spinner muscle_spin;
    //Spinner back_spin;
    Spinner exercise_spin;
    Button add_set;
    Button add_reps_man;
    Spinner rep_spin;
    TextView set_num;
    TextView weight;
    Button add_five;
    TextView reps;
    Button start;
    Button stop;
    int setNumber = 0;
    int weight_amt = 0;
    int numReps;
    DatabaseHelper db;
    Exercise e;
    int muscle_lookup = 0;
    int exercise_lookup = 0;
    int prev_exercise = - 1;
    String current_date = "04.29.2022";
    public static boolean muscle_chosen = false;
    SensorManager sensorManager;
    String [] muscles = {"Chest","Back","Quads", "Hamstrings", "Calves", "Biceps", "Triceps", "Forearms", "Shoulders"};
    String[] chest_ex = {"Choose Exercise","Bench Press", "Incline Dumbbell Press", "Cable Flye"};
    String[] back_ex = {"Choose Exercise","Lat Pulldown", "T Bar Row", "Cable Row"};
    String[] quad_ex = {"Choose Exercise","Squat", "Leg Press", "Leg Extension"};
    String[] ham_ex = {"Choose Exercise","Leg Curl", "Dumbbell Lunge", "Deadlift"};
    String[] biceps_ex = {"Choose Exercise","Dumbbell Curl","Barbell Curl", "Cable Curl"};
    String[] triceps_ex = {"Choose Exercise","Barbell Extension","Dumbbell Extension", "Cable Push Down"};
    String[] calf_ex = {"Choose Exercise","Calf Raise", "Seated Calf Raise"};
    String[] forearms_ex = {"Choose Exercise","Wrist Curl"};
    String[] shoulder_ex = {"Choose Exercise","Barbell Press","Dumbbell Press" , "Lateral Raise"};
    public static String [] exercise_array; // = {"","",""};
    public CurrentExercise()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        db = new DatabaseHelper(getContext());

        /*
        Cursor cur = db.dateQuery("04.01.2022");
        if(cur != null && cur.getCount() > 0)
        {
           return;
        }

        System.out.println("populating db with prev workouts");
        //chest bench
        db.insertWorkout(1,"04.01.2022");
        //one specific exercise
        int workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,95);
        db.insertSet(workoutID,2,10,115);
        db.insertSet(workoutID,3,8,135);
        db.insertSet(workoutID,4,4,145);

        //incline dumbbell press
        db.insertWorkout(2,"04.01.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,45);
        db.insertSet(workoutID,2,10,50);
        db.insertSet(workoutID,3,8,55);
        db.insertSet(workoutID,4,6,60);
         */

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.current_exercise, container, false);//activity_main


        //sensorManager = (SensorManager) getS
        muscle_spin = (Spinner) view.findViewById(R.id.addWorkout_muscleSpinner);
        exercise_spin = (Spinner) view.findViewById(R.id.addWorkout_exercisesSpinner);
        add_set = (Button) view.findViewById(R.id.add_set_button);
        add_set.setVisibility(View.GONE);
        set_num = (TextView) view.findViewById(R.id.set_num);
        set_num.setVisibility(View.GONE);
        weight = view.findViewById(R.id.weight);
        weight.setVisibility(View.GONE);
        add_five = view.findViewById(R.id.addSet_incrementButton);
        reps = (TextView) view.findViewById(R.id.reps);
        reps.setVisibility(View.GONE);
        start = view.findViewById(R.id.addSet_startButton);
        stop = view.findViewById(R.id.addSet_stopButton);
        add_reps_man = view.findViewById(R.id.add_reps_manual);
        rep_spin = (Spinner) view.findViewById(R.id.rep_spinner);

        e = new Exercise();

        //System.out.println("muscle_sel = " + muscle_sel);
        /*
        if(muscle_sel != 0)
        {
            muscle_spin.setSelection(muscle_sel);
        }
        //muscle_spin.setSelection(2);
         */
        muscle_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                reps.setVisibility(View.GONE);
                setNumber = 0;
                set_num.setVisibility(View.GONE);
                weight.setVisibility(View.GONE);
                add_reps_man.setVisibility(View.GONE);
                //set_num.setVisibility(View.VISIBLE);
                //set_num.setTextSize(25);
                //String str = String.valueOf(setNumber);
                //set_num.setText(str);
                switch (muscle_spin.getSelectedItemPosition())
                {
                    case 0:
                        System.out.println("choose muscle");
                        break;
                    case 1:
                        //Exercise e = new Exercise();
                        System.out.println("case 1 chest ");
                        //MainActivity.muscle_sel = 1;
                        exercise_spin.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,chest_ex));//exercise_array
                        exercise_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                switch (exercise_spin.getSelectedItemPosition())
                                {
                                    case 0://choose
                                        System.out.println("choose exercise");
                                        break;
                                    case 1://bench
                                        System.out.println("bench press");
                                        exercise_lookup = 1;
                                        /**
                                         add_set.setVisibility(View.VISIBLE);
                                         add_reps_man.setVisibility(View.VISIBLE);
                                         add_set.setOnClickListener(new View.OnClickListener()
                                         {
                                         @Override
                                         public void onClick(View view)
                                         {
                                         System.out.println("add_set");
                                         setNumber++;
                                         set_num.setVisibility(View.VISIBLE);
                                         set_num.setTextSize(25);
                                         String str = String.valueOf(setNumber);
                                         set_num.setText(str);
                                         weight.setVisibility(View.VISIBLE);
                                         }
                                         });
                                         */
                                        /*
                                        add_reps_man.setOnClickListener(new View.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View view)
                                            {

                                            }
                                        });
                                         */

                                        break;
                                    case 2://incline
                                        System.out.println("Incline Dumbbell Press");
                                        exercise_lookup = 2;
                                        break;
                                    case 3://cable flye
                                        System.out.println("Cable Flye");
                                        exercise_lookup = 3;
                                        break;
                                    default:
                                        System.out.println("deafult case");
                                        break;
                                }

                                add_set.setVisibility(View.VISIBLE);
                                add_set.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view)
                                    {
                                        System.out.println("add_set");
                                        setNumber++;
                                        set_num.setVisibility(View.VISIBLE);
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        set_num.setTextSize(25);
                                        String str = String.valueOf(setNumber);
                                        set_num.setText(str);
                                        weight.setVisibility(View.VISIBLE);
                                    }
                                });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView)
                            {

                            }
                        });
                        break;
                    case 2://back exercise spinner
                        System.out.println("back spinner");
                        //setNumber = 0;
                        exercise_spin.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,back_ex));//exercise_array
                        exercise_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                switch (exercise_spin.getSelectedItemPosition())
                                {
                                    case 0:
                                        System.out.println("choose back exercise");
                                        break;
                                    case 1:////lat pulldown
                                        exercise_lookup = 4;
                                        /*
                                        System.out.println("Lat pulldown");
                                        add_set.setVisibility(View.VISIBLE);
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        add_set.setOnClickListener(new View.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View view)
                                            {
                                                System.out.println("add_set");
                                                setNumber++;
                                                set_num.setVisibility(View.VISIBLE);
                                                set_num.setTextSize(25);
                                                String str = String.valueOf(setNumber);
                                                set_num.setText(str);
                                                weight.setVisibility(View.VISIBLE);
                                            }
                                        });
                                         */

                                        break;
                                    case 2://T bar row
                                        System.out.println("t bar");
                                        exercise_lookup = 5;

                                        break;
                                    case 3://cable row
                                        System.out.println("cable row");
                                        exercise_lookup = 6;
                                        break;
                                    default:
                                        System.out.println("deafult case");
                                        break;
                                }
                                add_set.setVisibility(View.VISIBLE);
                                add_set.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view)
                                    {
                                        System.out.println("add_set");
                                        setNumber++;
                                        set_num.setVisibility(View.VISIBLE);
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        set_num.setTextSize(25);
                                        String str = String.valueOf(setNumber);
                                        set_num.setText(str);
                                        weight.setVisibility(View.VISIBLE);
                                    }
                                });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView)
                            {

                            }
                        });
                        break;
                    case 3://quads
                        exercise_spin.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,quad_ex));//exercise_array
                        exercise_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                switch (exercise_spin.getSelectedItemPosition())
                                {
                                    case 0:
                                        System.out.println("choose back exercise");
                                        break;
                                    case 1:////squat
                                        exercise_lookup = 7;
                                        /*
                                        System.out.println("Lat pulldown");
                                        add_set.setVisibility(View.VISIBLE);
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        add_set.setOnClickListener(new View.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View view)
                                            {
                                                System.out.println("add_set");
                                                setNumber++;
                                                set_num.setVisibility(View.VISIBLE);
                                                set_num.setTextSize(25);
                                                String str = String.valueOf(setNumber);
                                                set_num.setText(str);
                                                weight.setVisibility(View.VISIBLE);
                                            }
                                        });
                                         */
                                        break;
                                    case 2://leg press
                                        exercise_lookup = 8;
                                        break;
                                    case 3://leg extension
                                        exercise_lookup = 9;
                                        break;
                                    default:
                                        System.out.println("deafult case");
                                        break;
                                }
                                add_set.setVisibility(View.VISIBLE);
                                add_set.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view)
                                    {
                                        System.out.println("add_set");
                                        setNumber++;
                                        set_num.setVisibility(View.VISIBLE);
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        set_num.setTextSize(25);
                                        String str = String.valueOf(setNumber);
                                        set_num.setText(str);
                                        weight.setVisibility(View.VISIBLE);
                                    }
                                });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView)
                            {

                            }
                        });

                        break;
                    case 4://hamstring
                        exercise_spin.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,ham_ex));//exercise_array
                        exercise_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                switch (exercise_spin.getSelectedItemPosition())
                                {
                                    case 0:
                                        break;
                                    case 1:////squat
                                        exercise_lookup = 10;
                                        /*
                                        System.out.println("Lat pulldown");
                                        add_set.setVisibility(View.VISIBLE);
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        add_set.setOnClickListener(new View.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View view)
                                            {
                                                System.out.println("add_set");
                                                setNumber++;
                                                set_num.setVisibility(View.VISIBLE);
                                                set_num.setTextSize(25);
                                                String str = String.valueOf(setNumber);
                                                set_num.setText(str);
                                                weight.setVisibility(View.VISIBLE);
                                            }
                                        });
                                         */
                                        break;
                                    case 2://leg press
                                        exercise_lookup = 11;
                                        break;
                                    case 3://leg extension
                                        exercise_lookup = 12;
                                        break;
                                    default:
                                        System.out.println("deafult case");
                                        break;
                                }
                                add_set.setVisibility(View.VISIBLE);
                                add_set.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view)
                                    {
                                        System.out.println("add_set");
                                        setNumber++;
                                        set_num.setVisibility(View.VISIBLE);
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        set_num.setTextSize(25);
                                        String str = String.valueOf(setNumber);
                                        set_num.setText(str);
                                        weight.setVisibility(View.VISIBLE);
                                    }
                                });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView)
                            {

                            }
                        });
                        break;
                    case 5://calf
                        exercise_spin.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,calf_ex));//exercise_array
                        exercise_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                switch (exercise_spin.getSelectedItemPosition())
                                {
                                    case 0:
                                        break;
                                    case 1://calf raise
                                        exercise_lookup = 13;
                                        /*
                                        System.out.println("Lat pulldown");
                                        add_set.setVisibility(View.VISIBLE);
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        add_set.setOnClickListener(new View.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View view)
                                            {
                                                System.out.println("add_set");
                                                setNumber++;
                                                set_num.setVisibility(View.VISIBLE);
                                                set_num.setTextSize(25);
                                                String str = String.valueOf(setNumber);
                                                set_num.setText(str);
                                                weight.setVisibility(View.VISIBLE);
                                            }
                                        });
                                         */
                                        break;
                                    case 2://seated calf raise
                                        exercise_lookup = 14;
                                        break;
                                    default:
                                        System.out.println("deafult case");
                                        break;
                                }
                                add_set.setVisibility(View.VISIBLE);
                                add_set.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view)
                                    {
                                        System.out.println("add_set");
                                        setNumber++;
                                        set_num.setVisibility(View.VISIBLE);
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        set_num.setTextSize(25);
                                        String str = String.valueOf(setNumber);
                                        set_num.setText(str);
                                        weight.setVisibility(View.VISIBLE);
                                    }
                                });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView)
                            {

                            }
                        });
                        break;
                    case 6:
                        exercise_spin.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,biceps_ex));//exercise_array
                        exercise_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                switch (exercise_spin.getSelectedItemPosition())
                                {
                                    case 0://choose
                                        System.out.println("choose exercise");
                                        break;
                                    case 1://bench
                                        System.out.println("dumbbell");
                                        exercise_lookup = 15;
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        add_set.setVisibility(View.VISIBLE);
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        add_set.setOnClickListener(new View.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View view)
                                            {
                                                System.out.println("add_set");
                                                setNumber++;
                                                set_num.setVisibility(View.VISIBLE);
                                                set_num.setTextSize(25);
                                                String str = String.valueOf(setNumber);
                                                set_num.setText(str);
                                                weight.setVisibility(View.VISIBLE);
                                            }
                                        });

                                        break;
                                    case 2://
                                        System.out.println("Barbell Curl");

                                        exercise_lookup = 16;
                                        add_set.setVisibility(View.VISIBLE);
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        add_set.setOnClickListener(new View.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View view)
                                            {
                                                System.out.println("add_set");
                                                setNumber++;
                                                set_num.setVisibility(View.VISIBLE);
                                                set_num.setTextSize(25);
                                                String str = String.valueOf(setNumber);
                                                set_num.setText(str);
                                                weight.setVisibility(View.VISIBLE);
                                            }
                                        });
                                        break;
                                    case 3://cable
                                        System.out.println("Cable Curl");
                                        exercise_lookup = 17;
                                        add_set.setVisibility(View.VISIBLE);
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        add_set.setOnClickListener(new View.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View view)
                                            {
                                                System.out.println("add_set");
                                                setNumber++;
                                                set_num.setVisibility(View.VISIBLE);
                                                set_num.setTextSize(25);
                                                String str = String.valueOf(setNumber);
                                                set_num.setText(str);
                                                weight.setVisibility(View.VISIBLE);
                                            }
                                        });
                                        break;
                                    default:
                                        System.out.println("deafult case");
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView)
                            {

                            }
                        });
                        break;
                    case 7:
                        exercise_spin.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,triceps_ex));//exercise_array
                        exercise_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                switch (exercise_spin.getSelectedItemPosition())
                                {
                                    case 0:
                                        //System.out.println("choose back exercise");
                                        break;
                                    case 1:////lat pulldown
                                        exercise_lookup = 18;
                                        //System.out.println("Lat pulldown");
                                        break;
                                    case 2://T bar row
                                        exercise_lookup = 19;
                                        break;
                                    case 3://cable row
                                        exercise_lookup = 20;
                                        break;
                                    default:
                                        System.out.println("deafult case");
                                        break;
                                }

                                add_set.setVisibility(View.VISIBLE);
                                add_set.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view)
                                    {
                                        System.out.println("add_set");
                                        setNumber++;
                                        set_num.setVisibility(View.VISIBLE);
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        set_num.setTextSize(25);
                                        String str = String.valueOf(setNumber);
                                        set_num.setText(str);
                                        weight.setVisibility(View.VISIBLE);
                                    }
                                });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView)
                            {

                            }
                        });
                        break;
                    case 8://forearms
                        exercise_spin.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,forearms_ex));//exercise_array
                        exercise_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                switch (exercise_spin.getSelectedItemPosition())
                                {
                                    case 0:
                                        break;
                                    case 1://calf raise
                                        exercise_lookup = 21;
                                    default:
                                        System.out.println("deafult case");
                                        break;
                                }
                                add_set.setVisibility(View.VISIBLE);
                                add_set.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view)
                                    {
                                        System.out.println("add_set");
                                        setNumber++;
                                        set_num.setVisibility(View.VISIBLE);
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        set_num.setTextSize(25);
                                        String str = String.valueOf(setNumber);
                                        set_num.setText(str);
                                        weight.setVisibility(View.VISIBLE);
                                    }
                                });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView)
                            {

                            }
                        });
                        break;
                    case 9://shoulders
                        exercise_spin.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,shoulder_ex));//exercise_array
                        exercise_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                switch (exercise_spin.getSelectedItemPosition())
                                {
                                    case 0:
                                        break;
                                    case 1:////squat
                                        exercise_lookup = 22;
                                        /*
                                        System.out.println("Lat pulldown");
                                        add_set.setVisibility(View.VISIBLE);
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        add_set.setOnClickListener(new View.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View view)
                                            {
                                                System.out.println("add_set");
                                                setNumber++;
                                                set_num.setVisibility(View.VISIBLE);
                                                set_num.setTextSize(25);
                                                String str = String.valueOf(setNumber);
                                                set_num.setText(str);
                                                weight.setVisibility(View.VISIBLE);
                                            }
                                        });
                                         */
                                        break;
                                    case 2://leg press
                                        exercise_lookup = 23;
                                        break;
                                    case 3://leg extension
                                        exercise_lookup = 24;
                                        break;
                                    default:
                                        System.out.println("deafult case");
                                        break;
                                }
                                add_set.setVisibility(View.VISIBLE);
                                add_set.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view)
                                    {
                                        System.out.println("add_set");
                                        setNumber++;
                                        set_num.setVisibility(View.VISIBLE);
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        set_num.setTextSize(25);
                                        String str = String.valueOf(setNumber);
                                        set_num.setText(str);
                                        weight.setVisibility(View.VISIBLE);
                                    }
                                });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView)
                            {

                            }
                        });
                        break;
                    default:
                        System.out.println("layout spinner = " + muscle_spin.getSelectedItemPosition());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });


        setupView(view);

        add_five.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                weight_amt += 5;
                String str = String.valueOf(weight_amt);
                str = str.concat("  x ");

                weight.setVisibility(View.VISIBLE);
                weight.setTextSize(25);
                weight.setText(String.valueOf(str));
                System.out.println("weight = " + weight_amt);
            }
        });

        /*
        //start button
        start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                System.out.println("start clicked");

                Intent intent = new Intent(getActivity(), MainActivity.class);
                //Bundle bundle = new Bundle();
                intent.putExtra("start","true");
                startActivity(intent);




            }
        });
         */

        //rep spinner
        rep_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                int sel = rep_spin.getSelectedItemPosition();

                System.out.println("rep spin sel = " + sel);
                if(sel != 0)
                {
                    numReps = sel;
                    reps.setText(String.valueOf(numReps));
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

        //manually add reps without using accelerometer
        add_reps_man.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                reps.setVisibility(View.VISIBLE);
                reps.setTextSize(25);
                System.out.println("before set text");
                reps.setText(String.valueOf(numReps));

                if(exercise_lookup != prev_exercise)
                {
                    System.out.println("insertWorkput called");
                    //add data to database
                    db.insertWorkout(exercise_lookup,current_date);

                }

                //one specific exercise
                int workoutID = db.getWorkoutID();
                System.out.println("workoutID = " + workoutID);
                db.insertSet(workoutID,setNumber,numReps,weight_amt);

                prev_exercise = exercise_lookup;

            }
        });



        //stop button
        stop.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                MainActivity.stop = true;
                reps.setVisibility(View.VISIBLE);

                numReps =  MainActivity.count_reps();
                //bool = funct();
                //while(some)
                reps.setTextSize(25);
                System.out.println("before set text");
                reps.setText(String.valueOf(numReps));

                MainActivity.calibrate = false;
                MainActivity.stop = false;
                MainActivity.flag = true;

                System.out.println("exercise lookup = " + exercise_lookup);
                System.out.println("prev exercise = " + prev_exercise);
                if(exercise_lookup != prev_exercise)
                {
                    System.out.println("insertWorkput called");
                    //add data to database
                    db.insertWorkout(exercise_lookup,current_date);

                }

                //one specific exercise?
                int workoutID = db.getWorkoutID();
                System.out.println("workoutID = " + workoutID);
                db.insertSet(workoutID,setNumber,numReps,weight_amt);

                prev_exercise = exercise_lookup;

                System.out.println("new date query***********");
                System.out.println("04.01.2022");
                Cursor cur = db.dateQuery("04.01.2022");
                if(cur != null && cur.getCount() > 0)
                {
                    cur.moveToFirst();
                    while(!cur.isAfterLast())
                    {

                        System.out.println("cur(0)WorkoutID = " + cur.getString(0));
                        System.out.println("cur(1)Exercise = " + cur.getString(1));
                        System.out.println("cur(2 Set Number = " + cur.getString(2));
                        System.out.println("cur(3 Weight = " + cur.getString(3));
                        System.out.println("cur(4 Reps = " + cur.getString(4));
                        //System.out.println("cur(2) = " + cur.getString(2));
                        cur.moveToNext();
                    }
                }

                System.out.println(current_date);
                cur = db.dateQuery(current_date);
                if(cur != null && cur.getCount() > 0)
                {
                    cur.moveToFirst();
                    while(!cur.isAfterLast())
                    {

                        System.out.println("cur(0)WorkoutID = " + cur.getString(0));
                        System.out.println("cur(1)Exercise = " + cur.getString(1));
                        System.out.println("cur(2 Set Number = " + cur.getString(2));
                        System.out.println("cur(3 Weight = " + cur.getString(3));
                        System.out.println("cur(4 Reps = " + cur.getString(4));
                        //System.out.println("cur(2) = " + cur.getString(2));
                        cur.moveToNext();
                    }
                }


            }//end stop
        });


        return view;
    }//end onCreate View()






    private void setupView(View view)
    {

        setupMuscleSpinner(view);
        setupExerciseSpinner(view);

        setupSetsListView(view);

        setupTextViews(view);
    }

    private void setupTextViews(View view)
    {


    }

    private void setupSetsListView(View view)
    {


    }

    private void setupExerciseSpinner(View view)
    {

        //add muscle spinner

    }

    private void setupMuscleSpinner(View view)
    {

    }


}
