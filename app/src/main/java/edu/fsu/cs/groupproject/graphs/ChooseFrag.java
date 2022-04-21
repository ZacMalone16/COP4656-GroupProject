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
import android.widget.Button;
import android.widget.Spinner;

import java.util.Random;

import edu.fsu.cs.groupproject.R;
import edu.fsu.cs.groupproject.database.DatabaseHelper;


public class ChooseFrag extends Fragment
{

    public Spinner choose_graph;
    DatabaseHelper db;
    Button home;

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

        /*
        Random rand = new Random();
        for(int i = 0; i < 3; i++)
        {
            db.insertWorkout(i + 1,"04.11.2022");
            int workoutID = db.getWorkoutID();
            for(int j = 0; j < 4; j++)
            {
                db.insertSet(workoutID,j + 1, rand.nextInt(16) + 1, rand.nextInt(226) + 1);
            }

        }
         */

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

        //shoulder barbell
        db.insertWorkout(22,"04.01.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,95);
        db.insertSet(workoutID,2,10,105);
        db.insertSet(workoutID,3,8,115);
        db.insertSet(workoutID,4,3,120);
        //shoulder dumbbell
        db.insertWorkout(23,"04.01.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,15,30);
        db.insertSet(workoutID,2,12,35);
        db.insertSet(workoutID,3,9,40);
        db.insertSet(workoutID,4,7,45);
        //shoulder lateral
        db.insertWorkout(24,"04.01.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,15,10);
        db.insertSet(workoutID,2,12,15);
        db.insertSet(workoutID,3,10,15);
        db.insertSet(workoutID,4,9,15);

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
        db.insertWorkout(6,"04.02.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,40);
        db.insertSet(workoutID,2,10,45);
        db.insertSet(workoutID,3,7,50);
        db.insertSet(workoutID,4,5,60);

        //bench
        db.insertWorkout(1,"04.09.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,95);
        db.insertSet(workoutID,2,10,115);
        db.insertSet(workoutID,3,7,135);
        db.insertSet(workoutID,4,5,150);

        //incline dumbbell press
        db.insertWorkout(2,"04.09.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,15,45);
        db.insertSet(workoutID,2,12,55);
        db.insertSet(workoutID,3,8,55);
        db.insertSet(workoutID,4,4,65);

        //cable flye
        db.insertWorkout(3,"04.09.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,15,25);
        db.insertSet(workoutID,2,13,45);
        db.insertSet(workoutID,3,12,55);
        db.insertSet(workoutID,4,8,65);

        //back lat pulldown
        db.insertWorkout(4,"04.09.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,15,75);
        db.insertSet(workoutID,2,11,85);
        db.insertSet(workoutID,3,7,90);
        db.insertSet(workoutID,4,3,95);

        //back T bar row
        db.insertWorkout(5,"04.09.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,4,75);
        db.insertSet(workoutID,2,5,65);
        db.insertSet(workoutID,3,6,55);
        db.insertSet(workoutID,4,7,45);

        //cable row
        db.insertWorkout(6,"04.09.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,40);
        db.insertSet(workoutID,2,10,45);
        db.insertSet(workoutID,3,7,50);
        db.insertSet(workoutID,4,5,60);

        //shoulder barbell
        db.insertWorkout(22,"04.09.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,95);
        db.insertSet(workoutID,2,11,105);
        db.insertSet(workoutID,3,10,115);
        db.insertSet(workoutID,4,3,125);
        //shoulder dumbbell
        db.insertWorkout(23,"04.09.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,15,30);
        db.insertSet(workoutID,2,13,35);
        db.insertSet(workoutID,3,10,40);
        db.insertSet(workoutID,4,8,45);
        //shoulder lateral
        db.insertWorkout(24,"04.09.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,10,15);
        db.insertSet(workoutID,2,9,15);
        db.insertSet(workoutID,3,6,15);
        db.insertSet(workoutID,4,4,15);

        //bench
        db.insertWorkout(1,"04.16.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,135);
        db.insertSet(workoutID,2,10,145);
        db.insertSet(workoutID,3,7,155);
        db.insertSet(workoutID,4,2,160);

        //incline dumbbell press
        db.insertWorkout(2,"04.16.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,15,50);
        db.insertSet(workoutID,2,12,60);
        db.insertSet(workoutID,3,7,70);
        db.insertSet(workoutID,4,4,70);

        //cable flye
        db.insertWorkout(3,"04.16.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,15,25);
        db.insertSet(workoutID,2,15,45);
        db.insertSet(workoutID,3,12,55);
        db.insertSet(workoutID,4,10,65);

        //back lat pulldown
        db.insertWorkout(4,"04.16.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,13,85);
        db.insertSet(workoutID,2,9,95);
        db.insertSet(workoutID,3,5,105);
        db.insertSet(workoutID,4,1,110);

        //back T bar row
        db.insertWorkout(5,"04.16.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,65);
        db.insertSet(workoutID,2,10,75);
        db.insertSet(workoutID,3,7,80);
        db.insertSet(workoutID,4,5,85);

        //cable row
        db.insertWorkout(6,"04.16.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,45);
        db.insertSet(workoutID,2,11,50);
        db.insertSet(workoutID,3,8,55);
        db.insertSet(workoutID,4,3,65);

        //bench
        db.insertWorkout(1,"04.23.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,135);
        db.insertSet(workoutID,2,8,155);
        db.insertSet(workoutID,3,5,165);
        db.insertSet(workoutID,4,3,175);

        //incline dumbbell press
        db.insertWorkout(2,"04.23.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,60);
        db.insertSet(workoutID,2,12,65);
        db.insertSet(workoutID,3,8,70);
        db.insertSet(workoutID,4,6,75);

        //cable flye
        db.insertWorkout(3,"04.23.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,15,35);
        db.insertSet(workoutID,2,12,45);
        db.insertSet(workoutID,3,10,60);
        db.insertSet(workoutID,4,3,70);


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
        home = (Button) view.findViewById(R.id.home_button);


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
                        //set sets reps frag
                        Intent intent = new Intent(getActivity(), GraphActivity.class);
                        //intent.putExtra("bundle_layout",1);
                        intent.putExtra("graph_num",1);
                        startActivity(intent);
                        //graph = new Graph(getBaseContext(), width, height, 1);
                        //setContentView(graph);
                        System.out.println("layout spinner = " + choose_graph.getSelectedItemPosition());
                        //layout = 1;

                        //onStop();
                        //recreate();
                        //graph.set_layout(1);
                        break;
                    case 2:
                        //set int var in main to load max weight frag
                        //Intent intent2 = new Intent(getActivity(),MainActivity.class);
                        Intent intent2 = new Intent(getActivity(), GraphActivity.class);//MaxWeight.class
                        //intent2.putExtra("bundle_layout",2);
                        intent2.putExtra("graph_num",2);
                        startActivity(intent2);
                        //choose_graph.setSelection(0);

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