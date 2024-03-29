package edu.fsu.cs.groupproject.graphs;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import edu.fsu.cs.groupproject.R;
import edu.fsu.cs.groupproject.database.DatabaseHelper;
//import androidx.fragment.app.Fragment;

public class SetsRepsFrag extends Fragment
{
    Spinner date_spin;
    ListView simpleListView;
    TextView days_list;
    LinearLayout linear;
    DatabaseHelper db;
    ArrayList<int[][]> data = new ArrayList<>();
    ArrayList<String> date_list = new ArrayList<>();
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

        db = new DatabaseHelper(getActivity());
        System.out.println("get all dates");
        date_list.clear();
        Cursor cur = db.getDates();
        if(cur != null && cur.getCount() > 0)
        {
            cur.moveToFirst();
            while(!cur.isAfterLast())
            {

                date_list.add(cur.getString(0));
                System.out.println("cur(0)dates = " + cur.getString(0));

                //System.out.println("cur(2) = " + cur.getString(2));
                cur.moveToNext();
            }
        }

        cur = db.dateQuery("04.08.2022");
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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.frag_sets_reps,container,false);

        days_list = (TextView) view.findViewById(R.id.itemTextView);
        days_list.setTextColor(Color.BLACK);
        simpleListView = (ListView) view.findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(),R.layout.frag_sets_reps,R.id.itemTextView,date_list);
        simpleListView.setAdapter(arrayAdapter);
        simpleListView.setClickable(true);

        simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                //clear the current list before adding anything
                GraphActivity.data.clear();
                GraphActivity.names.clear();
                String clicked_date = ((TextView) view.findViewById(R.id.itemTextView)).getText().toString();

                System.out.println(clicked_date);
                Cursor cur = db.dateQuery(clicked_date);

                if(cur != null && cur.getCount() > 0)
                {
                    cur.moveToFirst();
                }
                else
                {
                    return;
                }
                while(!cur.isAfterLast())
                {
                    //get workoutID
                    workoutID = Integer.parseInt(cur.getString(0));//was 5
                    //skip to next record if the workout ID is the same
                    if(workoutID == current_exercise)
                    {
                        cur.moveToNext();
                        continue;
                    }

                    //get workout data from ID
                    Cursor cur2 = db.getWorkoutData(workoutID);

                    if(cur2 != null && cur2.getCount() > 0)
                    {
                        points_array = new int[cur2.getCount()][2];
                        x = 0;
                        cur2.moveToFirst();
                        while(!cur2.isAfterLast())
                        {
                            //reps
                            points_array[x][0] = Integer.parseInt(cur2.getString(4));
                            //weight
                            points_array[x][1] = Integer.parseInt(cur2.getString(3));
                            cur2.moveToNext();
                            x++;
                        }
                        //get name of exercise
                        cur2 = db.getAllDatat1();
                        if(cur2 != null && cur2.getCount() > 0)
                        {
                            cur2.moveToFirst();
                            while(!cur2.isAfterLast())
                            {
                                //if workout ID is equal to exerciseID
                                if(Integer.parseInt(cur2.getString(0)) == Integer.parseInt(cur.getString(5)))
                                {
                                    GraphActivity.names.add(cur2.getString(2));

                                }
                                cur2.moveToNext();
                            }

                        }

                        //add data to graph
                        GraphActivity.data.add(points_array);
                    }
                    else
                    {
                        return;
                    }

                    current_exercise = Integer.parseInt(cur.getString(0));

                    Cursor cur3 = db.maxWeight(current_exercise);
                    if(cur3 != null && cur3.getCount() > 0)
                    {
                        cur3.moveToFirst();
                        while(!cur3.isAfterLast())
                        {


                            cur3.moveToNext();
                        }
                    }

                    cur.moveToNext();

                }//end while

                Intent intent = new Intent(getActivity(), GraphActivity.class);
                intent.putExtra("graph_num",3);
                startActivity(intent);
            }
        });

        return view;

    }
}
