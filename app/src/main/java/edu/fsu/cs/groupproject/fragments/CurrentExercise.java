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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CurrentExercise extends Fragment
{

    Spinner muscle_spin;
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
    //Exercise e;
    //int muscle_lookup = 0;
    int exercise_lookup = 0;
    int prev_exercise = - 1;
    String current_date;
    //public static boolean muscle_chosen = false;
    //SensorManager sensorManager;
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
    //public static String [] exercise_array; // = {"","",""};

    Button home;

    Date currentTime = Calendar.getInstance().getTime();
    public CurrentExercise()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        db = new DatabaseHelper(getContext());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.current_exercise, container, false);//activity_main


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

        //get today's date
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy");
        String changedDate = dateFormat.format(currentTime);
        //set current date from today's date
        current_date = new String(changedDate);
        home = (Button) view.findViewById(R.id.home_button2);

        home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);

            }
        });



       //select muscle group spinner
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

                switch (muscle_spin.getSelectedItemPosition())
                {
                    case 0://choose muscle
                        break;
                    case 1://chest exercises
                        //set exercise spinner according to muscle chosen
                        exercise_spin.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,chest_ex));
                        exercise_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                switch (exercise_spin.getSelectedItemPosition())
                                {
                                    case 0://choose
                                        break;
                                    case 1://bench
                                        exercise_lookup = 1;
                                        break;
                                    case 2://incline
                                        exercise_lookup = 2;
                                        break;
                                    case 3://cable flye
                                        exercise_lookup = 3;
                                        break;
                                    default:
                                        break;
                                }

                                add_set.setVisibility(View.VISIBLE);
                                add_set.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view)
                                    {
                                        //increment set number
                                        setNumber++;
                                        //set set number to visible
                                        set_num.setVisibility(View.VISIBLE);
                                        //set add reps button to visible
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        set_num.setTextSize(25);
                                        String str = String.valueOf(setNumber);
                                        //set text for set number
                                        set_num.setText(str);
                                        //set weight text to visible
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
                        exercise_spin.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,back_ex));
                        exercise_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                switch (exercise_spin.getSelectedItemPosition())
                                {
                                    case 0:
                                        break;
                                    case 1:////lat pulldown
                                        exercise_lookup = 4;
                                        break;
                                    case 2://T bar row
                                        exercise_lookup = 5;
                                        break;
                                    case 3://cable row
                                        exercise_lookup = 6;
                                        break;
                                    default:
                                        break;
                                }
                                add_set.setVisibility(View.VISIBLE);
                                add_set.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view)
                                    {
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
                        exercise_spin.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,quad_ex));
                        exercise_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                switch (exercise_spin.getSelectedItemPosition())
                                {
                                    case 0://choose exercise
                                        break;
                                    case 1:////squat
                                        exercise_lookup = 7;
                                        break;
                                    case 2://leg press
                                        exercise_lookup = 8;
                                        break;
                                    case 3://leg extension
                                        exercise_lookup = 9;
                                        break;
                                    default:
                                        break;
                                }
                                add_set.setVisibility(View.VISIBLE);
                                add_set.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view)
                                    {
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
                        exercise_spin.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,ham_ex));
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
                                        break;
                                    case 2://leg press
                                        exercise_lookup = 11;
                                        break;
                                    case 3://leg extension
                                        exercise_lookup = 12;
                                        break;
                                    default:
                                        break;
                                }
                                add_set.setVisibility(View.VISIBLE);
                                add_set.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view)
                                    {
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
                                    case 0://choose
                                        break;
                                    case 1://calf raise
                                        exercise_lookup = 13;
                                        break;
                                    case 2://seated calf raise
                                        exercise_lookup = 14;
                                        break;
                                    default:
                                        break;
                                }
                                add_set.setVisibility(View.VISIBLE);
                                add_set.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view)
                                    {
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
                    case 6://biceps
                        exercise_spin.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,biceps_ex));//exercise_array
                        exercise_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                switch (exercise_spin.getSelectedItemPosition())
                                {
                                    case 0://choose
                                        break;
                                    case 1://barbell curl
                                        exercise_lookup = 15;
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        add_set.setVisibility(View.VISIBLE);
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        add_set.setOnClickListener(new View.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View view)
                                            {
                                                setNumber++;
                                                set_num.setVisibility(View.VISIBLE);
                                                set_num.setTextSize(25);
                                                String str = String.valueOf(setNumber);
                                                set_num.setText(str);
                                                weight.setVisibility(View.VISIBLE);
                                            }
                                        });

                                        break;
                                    case 2://dumbbell curl
                                        exercise_lookup = 16;
                                        add_set.setVisibility(View.VISIBLE);
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        add_set.setOnClickListener(new View.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View view)
                                            {
                                                setNumber++;
                                                set_num.setVisibility(View.VISIBLE);
                                                set_num.setTextSize(25);
                                                String str = String.valueOf(setNumber);
                                                set_num.setText(str);
                                                weight.setVisibility(View.VISIBLE);
                                            }
                                        });
                                        break;
                                    case 3://cable curl
                                        exercise_lookup = 17;
                                        add_set.setVisibility(View.VISIBLE);
                                        add_reps_man.setVisibility(View.VISIBLE);
                                        add_set.setOnClickListener(new View.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View view)
                                            {
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
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView)
                            {

                            }
                        });
                        break;
                    case 7://triceps
                        exercise_spin.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,triceps_ex));//exercise_array
                        exercise_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                switch (exercise_spin.getSelectedItemPosition())
                                {
                                    case 0://choose exercise
                                        break;
                                    case 1://Barbell extension
                                        exercise_lookup = 18;
                                        break;
                                    case 2://dumbbell extension
                                        exercise_lookup = 19;
                                        break;
                                    case 3://cable push down
                                        exercise_lookup = 20;
                                        break;
                                    default:
                                        break;
                                }

                                add_set.setVisibility(View.VISIBLE);
                                add_set.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view)
                                    {
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
                                    case 0://choose ex
                                        break;
                                    case 1://wrist curl
                                        exercise_lookup = 21;
                                    default:
                                        break;
                                }
                                add_set.setVisibility(View.VISIBLE);
                                add_set.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view)
                                    {
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
                                    case 0://choose exercise
                                        break;
                                    case 1://barbell press
                                        exercise_lookup = 22;
                                        break;
                                    case 2://dumbbell press
                                        exercise_lookup = 23;
                                        break;
                                    case 3://lateral raise
                                        exercise_lookup = 24;
                                        break;
                                    default:
                                        break;
                                }
                                add_set.setVisibility(View.VISIBLE);
                                add_set.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view)
                                    {
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

            }
        });

        //rep spinner
        rep_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                //get selected reps from spinner for manual rep input
                int sel = rep_spin.getSelectedItemPosition();
                //if not set to index zero
                if(sel != 0)
                {
                    //set text reps from selection
                    numReps = sel;
                    reps.setText(String.valueOf(numReps));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

        //manually add reps button without using accelerometer
        add_reps_man.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                reps.setVisibility(View.VISIBLE);
                reps.setTextSize(25);
                reps.setText(String.valueOf(numReps));

                //if
                if(exercise_lookup != prev_exercise)
                {
                    //add data to database
                    db.insertWorkout(exercise_lookup,current_date);

                }

                //one specific exercise
                int workoutID = db.getWorkoutID();
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
                reps.setTextSize(25);
                reps.setText(String.valueOf(numReps));

                MainActivity.calibrate = false;
                MainActivity.stop = false;
                MainActivity.flag = true;

                //if current exercise is not the same as previous exercise
                if(exercise_lookup != prev_exercise)
                {
                    //add new exercise data to database
                    db.insertWorkout(exercise_lookup,current_date);

                }

                //get the workoutID of last exercise inserted
                int workoutID = db.getWorkoutID();
                //insert new set
                db.insertSet(workoutID,setNumber,numReps,weight_amt);

                //set prev = current exercise
                prev_exercise = exercise_lookup;

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



    }

    private void setupMuscleSpinner(View view)
    {

    }


}
