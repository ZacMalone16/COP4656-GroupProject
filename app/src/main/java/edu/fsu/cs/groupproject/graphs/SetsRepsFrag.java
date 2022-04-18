package edu.fsu.cs.groupproject.graphs;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import edu.fsu.cs.groupproject.R;
import edu.fsu.cs.groupproject.database.DatabaseHelper;
//import androidx.fragment.app.Fragment;

public class SetsRepsFrag extends Fragment
{
    Spinner date_spin;
    DatabaseHelper db;
    ArrayList<int[][]> data = new ArrayList<>();
    int workoutID;
    int [][] points_array;
    int x = 0;
    int current_exercise = -1;

    public SetsRepsFrag()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.frag_sets_reps,container,false);
        date_spin = (Spinner) view.findViewById(R.id.dateSpinner);
        db = new DatabaseHelper(getActivity());

        date_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                switch (date_spin.getSelectedItemPosition())
                {
                    case 0:
                        //chose a date
                        break;
                    case 1://April 01, 2022
                        System.out.println("apr 01 sel");
                        Cursor cur = db.dateQuery("04.01.2022");


                        if(cur != null && cur.getCount() > 0)
                        {
                            cur.moveToFirst();
                        }
                        else
                        {
                            System.out.println("null");
                            return;
                        }
                        while(!cur.isAfterLast())
                        {
                            System.out.println("cur(0) = " + Integer.parseInt(cur.getString(0)));
                            workoutID = Integer.parseInt(cur.getString(0));
                            System.out.println("cur.getcount = " + cur.getCount());
                            //skip to next record if the workout ID is the same
                            if(Integer.parseInt(cur.getString(0)) == current_exercise)
                            {

                                cur.moveToNext();
                                continue;
                            }

                            Cursor cur2 = db.getWorkoutData(workoutID);

                            if(cur2 != null && cur2.getCount() > 0)
                            {
                                points_array = new int[cur2.getCount()][2];//
                                System.out.println("cur2.getCount() = " + cur2.getCount());
                                x = 0;
                                cur2.moveToFirst();
                                while(!cur2.isAfterLast())
                                {
                                    System.out.println("x = " + x);
                                    //reps
                                    points_array[x][0] = Integer.parseInt(cur2.getString(4));
                                    //System.out.println(Integer.parseInt(cur.getString(4)));
                                    //weight
                                    points_array[x][1] = Integer.parseInt(cur2.getString(3));

                                    System.out.println("0 setID = " + cur2.getString(0));
                                    System.out.println("1 workoutID = " + cur2.getString(1));
                                    System.out.println("2 setNum = " + cur2.getString(2));
                                    System.out.println("3 Reps = " + cur2.getString(3));
                                    System.out.println("4 weight = " + cur2.getString(4));
                                    cur2.moveToNext();
                                    x++;
                                }
                                //get name of exercise
                                cur2 = db.getAllDatat1();

                                if(cur2 != null && cur2.getCount() > 0)
                                {
                                    cur2.moveToFirst();
                                    while(!cur2.isAfterLast())//
                                    {
                                        if(Integer.parseInt(cur2.getString(0)) == workoutID)
                                        {
                                            GraphActivity.names.add(cur2.getString(2));

                                        }

                                        System.out.println("0 exercise ID = " + cur2.getString(0));
                                        System.out.println("1 muscleGroup = " + cur2.getString(1));
                                        System.out.println("2 Exercise = " + cur2.getString(2));
                                        cur2.moveToNext();
                                    }

                                }

                                GraphActivity.data.add(points_array);
                            }
                            else
                            {
                                System.out.println("null");
                                return;
                            }


                            System.out.println("points array.length = " + points_array.length);
                            //debug print
                            for(int n = 0; n < points_array.length; n++)
                            {
                                //for(int k = 0; k < 2; k++)
                                //{
                                System.out.printf("%d %d,%d\n", n,points_array[n][0],points_array[n][1]);
                                //}
                            }
                            current_exercise = Integer.parseInt(cur.getString(0));
                            cur.moveToNext();

                        }//end while
                        Intent intent = new Intent(getActivity(), GraphActivity.class);
                        //intent.putExtra("bundle_layout",1);
                        intent.putExtra("graph_num",3);
                        startActivity(intent);

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
