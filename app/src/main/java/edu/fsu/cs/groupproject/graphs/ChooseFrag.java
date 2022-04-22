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

        //check if database is already populated with previous data
        //this would normally be added by the user by the app if they had added a bunch of workout data
        Cursor cur = db.dateQuery("04.01.2022");
        if(cur != null && cur.getCount() > 0)
        {
            return;
        }

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

        //hams leg curl
        db.insertWorkout(10,"04.02.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,25);
        db.insertSet(workoutID,2,10,30);
        db.insertSet(workoutID,3,8,35);
        db.insertSet(workoutID,4,2,40);
        //hams lunge
        db.insertWorkout(11,"04.02.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,35);
        db.insertSet(workoutID,2,9,40);
        db.insertSet(workoutID,3,6,40);
        db.insertSet(workoutID,4,5,40);
        //hams dead lift
        db.insertWorkout(12,"04.02.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,15,95);
        db.insertSet(workoutID,2,12,115);
        db.insertSet(workoutID,3,9,125);
        db.insertSet(workoutID,4,4,135);

        //calves calf raise
        db.insertWorkout(13,"04.02.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,15,55);
        db.insertSet(workoutID,2,12,65);
        db.insertSet(workoutID,3,10,70);
        db.insertSet(workoutID,4,6,70);
        //calves seated calf raise
        db.insertWorkout(14,"04.02.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,10,25);
        db.insertSet(workoutID,2,8,25);
        db.insertSet(workoutID,3,6,35);
        db.insertSet(workoutID,4,5,40);


        //
        //tricep barbell ext
        db.insertWorkout(18,"04.08.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,45);
        db.insertSet(workoutID,2,11,55);
        db.insertSet(workoutID,3,6,65);
        db.insertSet(workoutID,4,4,65);
        //tricep dumbbell ext
        db.insertWorkout(19,"04.08.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,30);
        db.insertSet(workoutID,2,9,35);
        db.insertSet(workoutID,3,5,40);
        db.insertSet(workoutID,4,3,45);
        //tricep cable push down
        db.insertWorkout(20,"04.08.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,40);
        db.insertSet(workoutID,2,8,45);
        db.insertSet(workoutID,3,6,45);
        db.insertSet(workoutID,4,3,50);

        //squats quads
        db.insertWorkout(7,"04.08.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,95);
        db.insertSet(workoutID,2,10,115);
        db.insertSet(workoutID,3,7,125);
        db.insertSet(workoutID,4,5,135);
        //leg press quads
        db.insertWorkout(8,"04.08.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,15,140);
        db.insertSet(workoutID,2,12,160);
        db.insertSet(workoutID,3,10,180);
        db.insertSet(workoutID,4,8,180);
        //leg ext quads
        db.insertWorkout(9,"04.08.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,30);
        db.insertSet(workoutID,2,11,35);
        db.insertSet(workoutID,3,7,40);
        db.insertSet(workoutID,4,4,45);

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

        //biceps barbell
        db.insertWorkout(15,"04.09.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,40);
        db.insertSet(workoutID,2,9,50);
        db.insertSet(workoutID,3,7,55);
        db.insertSet(workoutID,4,3,55);
        //biceps dumbbell
        db.insertWorkout(16,"04.09.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,25);
        db.insertSet(workoutID,2,10,25);
        db.insertSet(workoutID,3,7,25);
        db.insertSet(workoutID,4,4,25);
        //biceps cable
        db.insertWorkout(17,"04.09.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,10,40);
        db.insertSet(workoutID,2,9,35);
        db.insertSet(workoutID,3,7,30);
        db.insertSet(workoutID,4,3,25);
        //forearms wrist
        db.insertWorkout(21,"04.09.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,20,25);
        db.insertSet(workoutID,2,19,30);
        db.insertSet(workoutID,3,15,35);
        db.insertSet(workoutID,3,8,40);
        db.insertSet(workoutID,3,7,40);
        db.insertSet(workoutID,3,2,45);

        //
        //tricep barbell ext
        db.insertWorkout(18,"04.15.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,10,45);
        db.insertSet(workoutID,2,9,50);
        db.insertSet(workoutID,3,6,55);
        db.insertSet(workoutID,4,2,70);
        //tricep dumbbell ext
        db.insertWorkout(19,"04.15.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,30);
        db.insertSet(workoutID,2,9,35);
        db.insertSet(workoutID,3,5,40);
        db.insertSet(workoutID,4,3,50);
        //tricep cable push down
        db.insertWorkout(20,"04.15.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,40);
        db.insertSet(workoutID,2,8,45);
        db.insertSet(workoutID,3,6,45);
        db.insertSet(workoutID,4,3,45);
        //squats quads
        db.insertWorkout(7,"04.15.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,15,105);
        db.insertSet(workoutID,2,12,115);
        db.insertSet(workoutID,3,8,135);
        db.insertSet(workoutID,4,3,140);
        //leg press quads
        db.insertWorkout(8,"04.15.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,160);
        db.insertSet(workoutID,2,10,180);
        db.insertSet(workoutID,3,7,190);
        db.insertSet(workoutID,4,5,200);
        //leg ext quads
        db.insertWorkout(9,"04.15.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,30);
        db.insertSet(workoutID,2,12,35);
        db.insertSet(workoutID,3,8,45);
        db.insertSet(workoutID,4,3,50);

        //calves calf raise
        db.insertWorkout(13,"04.15.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,15,60);
        db.insertSet(workoutID,2,11,65);
        db.insertSet(workoutID,3,7,75);
        db.insertSet(workoutID,4,4,75);
        //calves seated calf raise
        db.insertWorkout(14,"04.15.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,10,30);
        db.insertSet(workoutID,2,7,35);
        db.insertSet(workoutID,3,6,40);
        db.insertSet(workoutID,4,3,45);

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

        //biceps barbell
        db.insertWorkout(15,"04.17.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,40);
        db.insertSet(workoutID,2,9,50);
        db.insertSet(workoutID,3,7,55);
        db.insertSet(workoutID,4,3,65);
        //biceps dumbbell
        db.insertWorkout(16,"04.17.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,25);
        db.insertSet(workoutID,2,10,25);
        db.insertSet(workoutID,3,7,25);
        db.insertSet(workoutID,4,4,25);
        //biceps cable
        db.insertWorkout(17,"04.17.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,10,40);
        db.insertSet(workoutID,2,9,35);
        db.insertSet(workoutID,3,7,30);
        db.insertSet(workoutID,4,3,25);
        //forearms wrist
        db.insertWorkout(21,"04.17.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,17,30);
        db.insertSet(workoutID,2,15,35);
        db.insertSet(workoutID,3,11,35);
        db.insertSet(workoutID,3,8,40);
        db.insertSet(workoutID,3,6,45);
        db.insertSet(workoutID,3,1,55);

        //hams leg curl
        db.insertWorkout(10,"04.17.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,35);
        db.insertSet(workoutID,2,11,45);
        db.insertSet(workoutID,3,7,50);
        db.insertSet(workoutID,4,4,55);
        //hams lunge
        db.insertWorkout(11,"04.17.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,35);
        db.insertSet(workoutID,2,8,40);
        db.insertSet(workoutID,3,5,45);
        db.insertSet(workoutID,4,3,50);
        //hams dead lift
        db.insertWorkout(12,"04.17.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,105);
        db.insertSet(workoutID,2,10,125);
        db.insertSet(workoutID,3,6,135);
        db.insertSet(workoutID,4,5,145);

        //bench
        db.insertWorkout(1,"04.21.2022");
        //one specific exercise
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,135);
        db.insertSet(workoutID,2,8,155);
        db.insertSet(workoutID,3,5,165);
        db.insertSet(workoutID,4,3,175);

        //incline dumbbell press
        db.insertWorkout(2,"04.21.2022");
        workoutID = db.getWorkoutID();
        System.out.println("workoutID = " + workoutID);
        db.insertSet(workoutID,1,12,60);
        db.insertSet(workoutID,2,12,65);
        db.insertSet(workoutID,3,8,70);
        db.insertSet(workoutID,4,6,75);

        //cable flye
        db.insertWorkout(3,"04.21.2022");
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
                        break;
                    case 1:
                        //load sets reps frag
                        Intent intent = new Intent(getActivity(), GraphActivity.class);
                        intent.putExtra("graph_num",1);
                        startActivity(intent);
                        break;
                    case 2:
                        //load max weight frag
                        Intent intent2 = new Intent(getActivity(), GraphActivity.class);
                        intent2.putExtra("graph_num",2);
                        startActivity(intent2);
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



        return view;
    }
}