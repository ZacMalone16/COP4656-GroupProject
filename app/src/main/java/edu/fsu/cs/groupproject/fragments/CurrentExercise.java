package edu.fsu.cs.groupproject.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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
    DatabaseHelper db;
    Exercise e;
    int muscle_sel = 0;
    public static boolean muscle_chosen = false;
    String [] muscles = {"Chest","Back","Quads", "Hamstrings", "Calves", "Biceps", "Triceps", "Forearms", "Shoulders"};
    String[] chest_ex = {"Choose Exercise","Bench Press", "Incline Dumbbell Press", "Cable Flye"};
    String[] back_ex = {"Lat Pulldown", "T Bar Row", "Cable Row"};
    String[] quad_ex = {"Squat", "Leg Press", "Leg Extension"};
    public static String [] exercise_array; // = {"","",""};
    public CurrentExercise()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.current_exercise, container, false);//activity_main
         db = new DatabaseHelper(getContext());

        muscle_spin = (Spinner) view.findViewById(R.id.addWorkout_muscleSpinner);
        exercise_spin = (Spinner) view.findViewById(R.id.addWorkout_exercisesSpinner);
        add_set = (Button) view.findViewById(R.id.add_set_button);
        add_set.setVisibility(View.GONE);
        e = new Exercise();

        muscle_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                switch (muscle_spin.getSelectedItemPosition())
                {
                    case 0:
                        System.out.println("choose muscle");
                        break;
                    case 1:
                        //Exercise e = new Exercise();
                        System.out.println("case 1 chest ");
                        //e.muscle = "Chest";
                        //muscle_sel = 1;
                        //chest exercise spinner
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

                                        add_set.setVisibility(View.VISIBLE);
                                        add_set.setOnClickListener(new View.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View view)
                                            {
                                                System.out.println("add_set");
                                            }
                                        });

                                        break;
                                    case 2://incline
                                        System.out.println("Incline Dumbbell Press");
                                        break;
                                    case 3://cable flye
                                        System.out.println("Cable Flye");
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
                    case 2://back exercise spinner
                        System.out.println("back spinner");
                        exercise_spin.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,back_ex));//exercise_array
                        exercise_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                switch (exercise_spin.getSelectedItemPosition())
                                {
                                    case 0://lat pulldown
                                        System.out.println("Lat pulldown");
                                        break;
                                    case 1://T bar row
                                        System.out.println("t bar");
                                        break;
                                    case 2://cable row
                                        System.out.println("cable row");
                                        break;
                                    case 3:
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
                    case 3:
                        e.muscle = "Quads";
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
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

        if(muscle_chosen)
        {

        }

        setupView(view);

        return view;
    }

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
