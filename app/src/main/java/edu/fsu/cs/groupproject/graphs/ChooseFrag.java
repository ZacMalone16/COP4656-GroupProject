package edu.fsu.cs.groupproject.graphs;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

//import androidx.fragment.app.Fragment;
//import android.support.v4.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import edu.fsu.cs.groupproject.R;
import edu.fsu.cs.groupproject.database.DatabaseHelper;


public class ChooseFrag extends Fragment
{

    public Spinner choose_graph;
    DatabaseHelper db;

    public ChooseFrag()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        db = new DatabaseHelper(getActivity());

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

        //cable flye
        db.insertWorkout(3,"04.01.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,15,25);
        db.insertSet(workoutID,2,15,35);
        db.insertSet(workoutID,3,12,45);
        db.insertSet(workoutID,4,10,50);

        //back lat pulldown
        db.insertWorkout(4,"04.02.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,15,70);
        db.insertSet(workoutID,2,12,80);
        db.insertSet(workoutID,3,7,85);
        db.insertSet(workoutID,4,3,85);

        //back T bar row
        db.insertWorkout(5,"04.02.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,4,75);
        db.insertSet(workoutID,2,5,65);
        db.insertSet(workoutID,3,6,55);
        db.insertSet(workoutID,4,7,45);

        //cable row
        db.insertWorkout(7,"04.02.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,40);
        db.insertSet(workoutID,2,10,45);
        db.insertSet(workoutID,3,7,50);
        db.insertSet(workoutID,4,5,60);

        //bench
        db.insertWorkout(1,"04.08.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,95);
        db.insertSet(workoutID,2,10,115);
        db.insertSet(workoutID,3,7,135);
        db.insertSet(workoutID,2,5,150);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.choose_graph,container,false);
        //Spinner spin = (Spinner) view.findViewById(R.id.spinner);
        //Ar
        //SpinnerAdapter spinnerAdapter = new SpinnerAdapter() {
        //}

        choose_graph = (Spinner) view.findViewById(R.id.spinner);


        choose_graph.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                switch (choose_graph.getSelectedItemPosition())
                {
                    case 0:
                        System.out.println("case 0 selected in spinner");
                        break;
                    case 1:
                        Intent intent = new Intent(getActivity(), GraphActivity.class);
                        //intent.putExtra("bundle_layout",1);
                        intent.putExtra("graph_num",1);
                        startActivity(intent);
                        //set int var in main to load the sets reps frag
                        //graph = new Graph(getBaseContext(), width, height, 1);
                        //setContentView(graph);
                        System.out.println("layout spinner = " + choose_graph.getSelectedItemPosition());
                        //layout = 1;

                        //onStop();
                        //recreate();
                        //graph.set_layout(1);
                        break;
                    case 2:
                        //Intent intent2 = new Intent(getActivity(),MainActivity.class);
                        Intent intent2 = new Intent(getActivity(), GraphActivity.class);//MaxWeight.class
                        //intent2.putExtra("bundle_layout",2);
                        intent2.putExtra("graph_num",2);
                        startActivity(intent2);
                        //choose_graph.setSelection(0);
                        //set int var in main to load max weight frag
                        System.out.println("layout spinner choose frag = " + choose_graph.getSelectedItemPosition());
                        //Graph g2 = new Graph(getApplicationContext(),width,height,2);
                        //graph = new Graph(getApplicationContext(), width, height, 2);
                        //graph.setBackgroundColor(Color.WHITE);
                        //graph.set_layout(2);

                        //layout = 2;

                        break;
                    default:
                        System.out.println("layout spinner = " + choose_graph.getSelectedItemPosition());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });



        return view;
    }
}