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

                            workoutID = Integer.parseInt(cur.getString(0));
                            System.out.println("cur.getcount = " + cur.getCount());

                            //points_array = new int[4][2];//cur.getCount()

                            /*
                            while(!cur.isAfterLast())
                            {

                                System.out.println("x = " + x);
                                if(Integer.parseInt(cur.getString(0)) == workoutID)
                                {
                                    //reps
                                    points_array[x][0] = Integer.parseInt(cur.getString(4));
                                    //weight
                                    points_array[x][1] = Integer.parseInt(cur.getString(3));

                                }


                                //GraphActivity.graph.set_exercises();
                                //put this in an aray and pass to graph.set_exercise() then call proportion_graph
                                System.out.println("cur(0)WorkoutID = " + cur.getString(0));//unique exercise
                                System.out.println("cur(1)Exercise = " + cur.getString(1));//exercise name
                                System.out.println("cur(2 Set Number = " + cur.getString(2));
                                System.out.println("cur(3 Weight = " + cur.getString(3));
                                System.out.println("cur(4 Reps = " + cur.getString(4));

                                cur.moveToNext();
                                x++;
                            }
                            System.out.println("points array.length = " + points_array.length);
                             */


                        }

                        cur = db.getWorkoutData(workoutID);

                        if(cur != null && cur.getCount() > 0)
                        {
                            points_array = new int[4][2];//cur.getCount()
                            x = 0;
                            cur.moveToFirst();
                            while(!cur.isAfterLast())
                            {
                                System.out.println("x = " + x);
                                //reps
                                points_array[x][0] = Integer.parseInt(cur.getString(4));
                                //System.out.println(Integer.parseInt(cur.getString(4)));
                                //weight
                                points_array[x][1] = Integer.parseInt(cur.getString(3));

                                System.out.println("0 setID = " + cur.getString(0));
                                System.out.println("1 workoutID = " + cur.getString(1));
                                System.out.println("2 setNum = " + cur.getString(2));
                                System.out.println("3 Reps = " + cur.getString(3));
                                System.out.println("4 weight = " + cur.getString(4));
                                cur.moveToNext();
                                x++;
                            }
                        }
                        else
                        {
                            System.out.println("null");
                        }
                        System.out.println("points array.length = " + points_array.length);

                        data.add(points_array);
                        GraphActivity.data.add(points_array);
                        //System.out.println("0,0");
                        //GraphActivity.graph.set_exercises(data,"BenchPress",0);
                        //GraphActivity.graph.proportion_graph2();


                        for(int n = 0; n < points_array.length; n++)
                        {
                            //for(int k = 0; k < 2; k++)
                            //{
                            System.out.printf("%d %d,%d\n", n,points_array[n][0],points_array[n][1]);
                            //}
                        }
                        Intent intent = new Intent(getActivity(), GraphActivity.class);
                        //intent.putExtra("bundle_layout",1);
                        intent.putExtra("graph_num",3);
                        startActivity(intent);
                        //GraphActivity.set_content();
                        //System.out.println(" ");

                        //System.out.println(points_array[0][0]);

                        //graph.set_exercises(graph.chest_exercises, chest_ex[chest_list.get(j)], chest_list.get(j));
                        //graph.proportion_graph2();
                        //System.out.printf("****%d: %s\n", j, chest_ex[chest_list.get(j)]);

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
