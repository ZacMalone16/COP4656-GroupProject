package edu.fsu.cs.groupproject.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    Spinner exercise_spin;
    DatabaseHelper db;
    Exercise e;
    int muscle_sel = 0;
    public static boolean muscle_chosen = false;
    String [] muscles = {"Chest","Back","Quads", "Hamstrings", "Calves", "Biceps", "Triceps", "Forearms", "Shoulders"};
    String[] chest_ex = {"Bench Press", "Incline Dumbbell Press", "Cable Flye"};
    public static String [] exercise_array; // = {"","",""};
    public CurrentExercise()
    {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.current_exercise, container, false);//activity_main
         db = new DatabaseHelper(getContext());

        muscle_spin = (Spinner) view.findViewById(R.id.addWorkout_muscleSpinner);
        exercise_spin = (Spinner) view.findViewById(R.id.addWorkout_exercisesSpinner);
        e = new Exercise();

        muscle_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                switch (muscle_spin.getSelectedItemPosition())
                {
                    case 0:
                        System.out.println("case 0 ");
                        break;
                    case 1:
                        //Exercise e = new Exercise();
                        System.out.println("case 1 chest ");
                        e.muscle = "Chest";
                        muscle_sel = 1;
                        exercise_spin.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,chest_ex));//exercise_array
                        exercise_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                switch (exercise_spin.getSelectedItemPosition())
                                {
                                    case 0://bench press
                                        System.out.println("bench press");
                                        break;
                                    case 1://Incline Dumbbell Press
                                        break;
                                    case 2://cable flye
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
                    case 2:
                        e.muscle = "Back";
                        System.out.println("layout spinner  = " + muscle_spin.getSelectedItemPosition());
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
