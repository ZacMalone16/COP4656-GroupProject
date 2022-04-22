package edu.fsu.cs.groupproject.graphs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

//import android.support.v4.app.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.fsu.cs.groupproject.MainActivity;
import edu.fsu.cs.groupproject.R;
import edu.fsu.cs.groupproject.database.DatabaseHelper;

public class GraphActivity extends Activity //Activity //AppCompatActivity
{
    Graph graph;
    DisplayMetrics displayMetrics = new DisplayMetrics();
    //phone screen height
    int height;
    //phone screen width
    int width;
    //int layout = -1;//-1

    TextView textview;
    //TextView chest;
    TextView quads;

    FragmentManager manager;
    FragmentTransaction ft;
    static int choose_graph_sel;
    static ArrayList<int[][]> data = new ArrayList<>();
    static ArrayList<String> names = new ArrayList<>();
    int [][] max_by_date;
    int workoutID;
    int current_exercise;
    String name;

    //Spinner muscle_spin;
    //Spinner exercise_spin;
    //Spinner date_spin;

    //vectors of exercises added (int index in list)

    ArrayList<Integer> chest_list = new ArrayList<>();//langlist
    ArrayList<Integer> back_list = new ArrayList<>();
    ArrayList<Integer> shoulder_list = new ArrayList<>();
    ArrayList<Integer> quads_list = new ArrayList<>();
    ArrayList<Integer> hams_list = new ArrayList<>();
    ArrayList<Integer> calf_list = new ArrayList<>();
    ArrayList<Integer> biceps_list = new ArrayList<>();
    ArrayList<Integer> triceps_list = new ArrayList<>();
    ArrayList<Integer> forearms_list = new ArrayList<>();

    //exercise lists
    String[] chest_ex = {"Bench Press", "Incline Dumbbell Press", "Cable Flye"};//langarray
    String[] back_ex = {"Lat Pulldown", "T Bar Row", "Cable Row"};
    String[] shoulder_ex = {"Barbell Press","Dumbbell Press" , "Lateral Raise"};
    String[] quads_ex = {"Squat", "Leg Press", "Leg Extension"};
    String[] hams_ex = {"Deadlift", "Leg Curl", "Dumbbell Lunge"};
    String[] calf_ex = {"Leg Press Calf Extension", "Seated Calf Raise", "Standing Calf Raise"};
    String[] biceps_ex = {"Barbell Curl", "Dumbbell Curl", "Cable Curl"};
    String[] triceps_ex = {"Barbell Extension", "Dumbbell Extension", "Cable Push Down"};
    String[] forearms_ex = {"Wrist Curl"};

    boolean[] chest_sel = new boolean[3];//selectedLang
    boolean[] back_sel = new boolean[3];
    boolean[] shoulder_sel = new boolean[3];
    boolean[] biceps_sel = new boolean[3];
    boolean[] triceps_sel = new boolean[3];
    boolean[] forearms_sel = new boolean[3];
    DatabaseHelper db;

    //int set_
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //System.out.println("main activiy oncreate(), layout = " + layout);

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
        db = new DatabaseHelper(getBaseContext());

        //muscle_spin = (Spinner) findViewById(R.id.muscleSpinner);
        //exercise_spin = (Spinner) findViewById(R.id.exerciseSpinner);
        //date_spin = (Spinner) findViewById(R.id.dateSpinner);
        System.out.println("width: " + width);
        System.out.println("height: " + height);


        //check if bundle is null
        //if nundle is null load choose frag
        //if not null load frag from the bundle

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {

            System.out.println("bundle extras != null");
            //int bundle_layout = extras.getInt("bundle_layout");
            int graph_num = extras.getInt("graph_num");
            //System.out.println("main bundle_layout = " + bundle_layout);
            System.out.println("main graphnum = " + graph_num);
            //load sets and reps frag
            if(graph_num == 1)//if(bundle_layout == 1)
            {
                choose_graph_sel = 1;

                graph = new Graph(this, width, height, 0);//
                setContentView(graph);
                graph.setBackgroundColor(Color.WHITE);


                //clear the data before adding to graph
                data.clear();
                names.clear();

                //sets and reps frag
                manager = getFragmentManager();
                FragmentTransaction ft2 = manager.beginTransaction();
                SetsRepsFrag setsRepsFrag = new SetsRepsFrag();
                ft2.replace(R.id.buttons_frag, setsRepsFrag);
                ft2.commit();

                //choose graph frag
                manager = getFragmentManager();
                ft = manager.beginTransaction();
                ChooseFrag chooseFrag = new ChooseFrag();
                ft.replace(R.id.spinner_frag,chooseFrag);
                ft.commit();
                //chooseFrag.choose_graph.setSelection(1);


            }
            //load max weight frag
            else if(graph_num == 2)//else if(bundle_layout == 2)
            {
                graph = new Graph(this, width, height, 0);//2
                setContentView(graph);
                graph.setBackgroundColor(Color.WHITE);
                graph.max_graph = true;

                //max weight frag
                manager = getFragmentManager();
                FragmentTransaction ft2 = manager.beginTransaction();
                MaxWeightFrag maxWeightFrag = new MaxWeightFrag();
                ft2.replace(R.id.buttons_frag, maxWeightFrag);
                ft2.commit();

                //choose graph frag
                manager = getFragmentManager();
                ft = manager.beginTransaction();
                ChooseFrag chooseFrag = new ChooseFrag();
                ft.replace(R.id.spinner_frag,chooseFrag);
                ft.commit();
            }
            //after clicked a day in SetsRepsFrag
            else if(graph_num == 3)
            {
                choose_graph_sel = 1;

                graph = new Graph(this, width, height, 0);//
                //setContentView(graph);
                graph.setBackgroundColor(Color.WHITE);
                for(int i = 0; i < data.size(); i++)
                {
                    graph.set_exercises(data,names.get(i),i);//"BenchPress"
                }

                graph.proportion_graph2();
                graph.draw_graph = true;
                graph.max_graph = false;
                setContentView(graph);

                //sets and reps frag
                manager = getFragmentManager();
                FragmentTransaction ft2 = manager.beginTransaction();
                SetsRepsFrag setsRepsFrag = new SetsRepsFrag();
                ft2.replace(R.id.buttons_frag, setsRepsFrag);
                ft2.commit();

                //choose graph frag
                manager = getFragmentManager();
                ft = manager.beginTransaction();
                ChooseFrag chooseFrag = new ChooseFrag();
                ft.replace(R.id.spinner_frag,chooseFrag);
                ft.commit();
            }

        }
        //extras == null
        else
        {
            System.out.println("bundle extras = null");
            graph = new Graph(this, width, height, 0);//
            setContentView(graph);
            graph.setBackgroundColor(Color.WHITE);

            manager = getFragmentManager();
            ft = manager.beginTransaction();
            //Graph g2 = new Graph(this, width, height, 0);
            ChooseFrag chooseFrag = new ChooseFrag();
            //ft.replace(R.id.buttons_frag,chooseFrag);//
            ft.replace(R.id.spinner_frag,chooseFrag);
            ft.commit();
        }

        //set position of legend y
        //graph.set_legend_y(quads.getY());

        //setContentView(R.layout.activity_main);

    }//end OnCreate()

    public void set_content()
    {
        setContentView(graph);
    }

    public void daily_max(int sel,int eID)
    {

        //chest bench
        int exerciseID = sel + 1 + eID;
        //data.clear();
        Cursor cur = db.dailyMax(exerciseID);

        if(cur != null && cur.getCount() > 0)
        {
            //set up array to size of cur.getCount()
            max_by_date = new int[cur.getCount()][2];
            cur.moveToFirst();
            int x = 0;
            //while there is another row
            while(!cur.isAfterLast())
            {
                //Cursor cur2 = db.dailyMax()
                System.out.println("cur(0)date = " + cur.getString(0));
                System.out.println("cur(1)max weight = " + cur.getString(1));
                String day = cur.getString(0);
                day = day.substring(3,5);
                System.out.println("day = " + day);
                //put into an 2d array of ints, x,y is date, weight
                max_by_date[x][1] = Integer.parseInt(day);//date //Integer.parseInt(cur.getString(0)
                max_by_date[x][0] = Integer.parseInt(cur.getString(1));//max for this exercise

                //System.out.println("cur(2) = " + cur.getString(2));
                x++;
                cur.moveToNext();
            }
            data.add(max_by_date);

            Cursor cur2 = db.getAllDatat1();

            if(cur2 != null && cur2.getCount() > 0)
            {
                cur2.moveToFirst();
                while(!cur2.isAfterLast())//
                {
                    if(Integer.parseInt(cur2.getString(0)) == exerciseID)//==workoutid
                    {
                        //GraphActivity.names.add(cur2.getString(2));
                        name = cur2.getString(2);
                        break;

                    }

                    //System.out.println("0 exercise ID = " + cur2.getString(0));
                    //System.out.println("1 muscleGroup = " + cur2.getString(1));
                    //System.out.println("2 Exercise = " + cur2.getString(2));
                    cur2.moveToNext();
                }

            }


            //need to add index by checking what the size is
            graph.set_exercises(data,name,data.size() - 1);//chest_ex[chest_list.get(j)]
        }

    }

    public void pop_max_data()
    {
        //start dynamic pop//////////////////////////
        //clear list of exercises
        data.clear();
        names.clear();
        //get name of exercise
        Cursor cur2 = db.getAllDatat1();

        if(cur2 != null && cur2.getCount() > 0)
        {
            cur2.moveToFirst();
            while(!cur2.isAfterLast())//
            {
                //loop through exercises in db and check if the name equals the item checked

                //get all chest exercises //Integer.parseInt(cur2.getString(2)) == workoutID
                if(cur2.getString(1).equals("Chest"))//cur2.getString(2).equals(chest_ex[chest_list.get(j)])
                {
                    names.add(cur2.getString(2));
                    //System.out.println("0 exercise ID = " + cur2.getString(0));
                    //System.out.println("1 muscleGroup = " + cur2.getString(1));
                    System.out.println("2 Exercise = " + cur2.getString(2));

                }

                cur2.moveToNext();
                //workoutID++;
            }

        }

        Cursor cur = db.getDates();
        if(cur != null && cur.getCount() > 0)
        {
            cur.moveToFirst();
            while(!cur.isAfterLast())
            {
                //for every date in date query
                cur2 = db.dateQuery(cur.getString(0));
                int max = 0;

                if(cur2 != null && cur2.getCount() > 0)
                {
                    cur2.moveToFirst();
                    //set current exercise
                    current_exercise = Integer.parseInt(cur.getString(0));
                    while(!cur2.isAfterLast())//
                    {
                        if(Integer.parseInt(cur.getString(0)) == current_exercise)
                        {

                            cur.moveToNext();
                            continue;
                        }

                        //if the weight on this set is greater than max
                        if(Integer.parseInt(cur2.getString(3)) > max)
                        {
                            max = Integer.parseInt(cur2.getString(3));

                        }
                        //date query
                        System.out.println("cur(0)WorkoutID = " + cur2.getString(0));
                        System.out.println("cur(1)Exercise = " + cur2.getString(1));
                        System.out.println("cur(2 Set Number = " + cur2.getString(2));
                        System.out.println("cur(3 Weight = " + cur2.getString(3));
                        System.out.println("cur(4 Reps = " + cur2.getString(4));
                        cur2.moveToNext();
                        //workoutID++;
                    }

                }

                //go to next date
                cur.moveToNext();
            }
        }




        cur2 = db.maxWeight(1);
        System.out.println("exerciseID = " + 1);
        if(cur2 != null && cur2.getCount() > 0)
        {
            cur2.moveToFirst();
            while(!cur2.isAfterLast())//
            {
                System.out.printf("0 Exercise Name %s\n",cur2.getString(0));
                System.out.printf("1 Max weight %s\n",cur2.getString(1));
                System.out.printf("2 date of max %s\n",cur2.getString(2));
                //cur.getString(0) //Exercise Name
                //cur.getString(1) //Max Weight
                //cur.getString(2) //Date of Max
                cur2.moveToNext();
                //workoutID++;
            }

        }

       /*
        cur2 = db.getDates();
        if(cur2 != null && cur2.getCount() > 0)
        {
            cur2.moveToFirst();
            while(!cur2.isAfterLast())//
            {
                System.out.println("");
                cur2.moveToNext();
                //workoutID++;
            }

        }
        */

        /*
        workoutID = 1;
        //cur2 = db.getWorkoutData(workoutID);
        cur2 = db.maxWeight(workoutID);
        while(cur2 != null && cur2.getCount() > 0)
        {
            cur2.moveToFirst();

        }
         */



        //end dynamic pop//////////////////////////

    }//end pop_max_data

    public void chest_max(View v)
    {

        if(v == MaxWeightFrag.chest)
        {
            //pop_max_data();
            System.out.println("chestlsit.size = " + chest_list.size());
            System.out.println("chest-sel.size = " + chest_sel.length);

            for(int j = 0; j < chest_sel.length; j++)
            {
                //chest_sel[chest_list.get(i)] = true;
                //System.out.println("chestlist i = " + chest_list.get(i));
                //chest_sel[chest_list.get(i)] = true;
                System.out.println("chest_sel i = " + chest_sel[j]);
            }


            AlertDialog.Builder builder = new AlertDialog.Builder(GraphActivity.this);
            builder.setTitle("Choose Chest Exercises");
            builder.setCancelable(false);
            builder.setMultiChoiceItems(chest_ex, chest_sel, new DialogInterface.OnMultiChoiceClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean b)
                {

                    if (b)
                    {

                        //vector of ints
                        chest_list.add(i);
                        System.out.printf("%d added\n", i);
                        System.out.println("checked musclselist.size = " + chest_list.size());

                    }
                    //checkbox is cleared, call remove function
                    else
                    {
                        System.out.printf("****removed %d: %s\n", i, chest_ex[i]);//error this line //muscle_list.get(i)
                        graph.remove(chest_ex[i]);//muscle_list.get(i)
                        chest_list.remove(Integer.valueOf(i));
                        graph.proportion_graph2();
                        System.out.println("unchecked musclselist.size = " + chest_list.size());

                        setContentView(graph);

                    }

                }
            });

            //add exercises to graph if OK clicked
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {


                    System.out.println("OK musclselist.size = " + chest_list.size());
                    for (int j = 0; j < chest_list.size(); j++)
                    {


                        //if exercise name is not already in list
                        if (graph.try_add(chest_ex[chest_list.get(j)]))
                        {
                            daily_max(chest_list.get(j),0);

                            //pass arraylist for chest1,name & index of exercise to set_exercises
                            //graph.set_exercises(graph.chest_exercises, chest_ex[chest_list.get(j)], chest_list.get(j));
                            graph.proportion_graph2();
                            System.out.printf("****%d: %s\n", j, chest_ex[chest_list.get(j)]);
                        }

                    }

                    graph.draw_graph = true;
                    setContentView(graph);

                    System.out.println("***************debug***************");
                    System.out.println("graph.data2.size() = " + graph.data.size());
                    for (int k = 0; k < graph.data.size(); k++) {
                        System.out.printf("%d:\n", k);
                        graph.data.get(k).print();

                    }

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    dialogInterface.dismiss();
                }
            });
            builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {

                    // remove all items from graph
                    graph.data.clear();
                    //clear the list of ints
                    chest_list.clear();
                    //clear all checks
                    for (int j = 0; j < chest_sel.length; j++)
                    {
                        chest_sel[j] = false;
                    }
                    setContentView(graph);

                }
            });
            builder.show();



        }//end if v == chest
    }//end chest_max()

    //back drop down onclick
    public void back_max(View v)
    {
        if(v == MaxWeightFrag.back)
        {

            AlertDialog.Builder builder = new AlertDialog.Builder(GraphActivity.this);
            builder.setTitle("Choose Back Exercises");
            builder.setCancelable(false);
            builder.setMultiChoiceItems(back_ex, back_sel, new DialogInterface.OnMultiChoiceClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean b)
                {

                    if (b)
                    {

                        //vector of ints
                        back_list.add(i);
                        System.out.printf("%d added\n", i);
                        System.out.println("checked musclselist.size = " + back_list.size());

                    }
                    //checkbox is cleared, call remove function
                    else
                    {
                        System.out.printf("****removed %d: %s\n", i, back_ex[i]);//error this line //muscle_list.get(i)
                        graph.remove(back_ex[i]);//muscle_list.get(i)
                        back_list.remove(Integer.valueOf(i));
                        graph.proportion_graph2();
                        System.out.println("unchecked musclselist.size = " + back_list.size());

                        setContentView(graph);

                    }

                }
            });

            //add exercises to graph if OK clicked
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {

                    System.out.println("OK musclselist.size = " + back_list.size());
                    for (int j = 0; j < back_list.size(); j++) {
                        //if exercise is not already in list
                        if (graph.try_add(back_ex[back_list.get(j)]))
                        {
                            daily_max(back_list.get(j),3);
                            //pass name & index of exercise to set_exercises
                            //graph.set_exercises(graph.back_exercises, back_ex[back_list.get(j)], back_list.get(j));
                            graph.proportion_graph2();
                            System.out.printf("****%d: %s\n", j, back_ex[back_list.get(j)]);
                        }

                    }

                    graph.draw_graph = true;
                    setContentView(graph);

                    System.out.println("***************debug***************");
                    System.out.println("graph.data2.size() = " + graph.data.size());
                    for (int k = 0; k < graph.data.size(); k++) {
                        System.out.printf("%d:\n", k);
                        graph.data.get(k).print();

                    }

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();
                }
            });
            builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    // remove all items from graph
                    graph.data.clear();
                    //clear the list of ints
                    back_list.clear();
                    //clear all checks
                    for (int j = 0; j < back_sel.length; j++) {
                        back_sel[j] = false;
                    }
                    setContentView(graph);

                }
            });
            builder.show();
            //end back
        }//end v == MaxWeightFrag.back
    }//end back_max()

    //shoulder drop down onclick
    public void shoulder_max(View v)
    {
        if(v == MaxWeightFrag.shoulder)
        {

            AlertDialog.Builder builder = new AlertDialog.Builder(GraphActivity.this);
            builder.setTitle("Choose Shoulder Exercises");
            builder.setCancelable(false);
            builder.setMultiChoiceItems(shoulder_ex, shoulder_sel, new DialogInterface.OnMultiChoiceClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean b)
                {

                    if (b)
                    {

                        //vector of ints
                        shoulder_list.add(i);
                        System.out.printf("%d added\n", i);
                        System.out.println("checked musclselist.size = " + shoulder_list.size());

                    }
                    //checkbox is cleared, call remove function
                    else
                    {
                        System.out.printf("****removed %d: %s\n", i, shoulder_ex[i]);//error this line //muscle_list.get(i)
                        graph.remove(shoulder_ex[i]);//muscle_list.get(i)
                        shoulder_list.remove(Integer.valueOf(i));
                        graph.proportion_graph2();
                        System.out.println("unchecked musclselist.size = " + shoulder_list.size());

                        setContentView(graph);

                    }

                }
            });

            //add exercises to graph if OK clicked
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {

                    System.out.println("OK musclselist.size = " + shoulder_list.size());
                    for (int j = 0; j < shoulder_list.size(); j++) {
                        //if exercise is not already in list
                        if (graph.try_add(shoulder_ex[shoulder_list.get(j)]))
                        {
                            daily_max(shoulder_list.get(j),21);
                            //pass name & index of exercise to set_exercises
                            //graph.set_exercises(graph.back_exercises, back_ex[back_list.get(j)], back_list.get(j));
                            graph.proportion_graph2();
                            System.out.printf("****%d: %s\n", j, shoulder_ex[shoulder_list.get(j)]);
                        }

                    }

                    graph.draw_graph = true;
                    setContentView(graph);

                    System.out.println("***************debug***************");
                    System.out.println("graph.data2.size() = " + graph.data.size());
                    for (int k = 0; k < graph.data.size(); k++) {
                        System.out.printf("%d:\n", k);
                        graph.data.get(k).print();

                    }

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();
                }
            });
            builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    // remove all items from graph
                    graph.data.clear();
                    //clear the list of ints
                    shoulder_list.clear();
                    //clear all checks
                    for (int j = 0; j < shoulder_sel.length; j++) {
                        shoulder_sel[j] = false;
                    }
                    setContentView(graph);

                }
            });
            builder.show();
            //end shoulder
        }//end v == MaxWeightFrag.back
    }//end shoulder_max()

    //biceps drop down onclick
    public void biceps_max(View v)
    {
        if(v == MaxWeightFrag.biceps)
        {

            AlertDialog.Builder builder = new AlertDialog.Builder(GraphActivity.this);
            builder.setTitle("Choose Shoulder Exercises");
            builder.setCancelable(false);
            builder.setMultiChoiceItems(biceps_ex, biceps_sel, new DialogInterface.OnMultiChoiceClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean b)
                {

                    if (b)
                    {

                        //vector of ints
                        biceps_list.add(i);
                        System.out.printf("%d added\n", i);
                        System.out.println("checked musclselist.size = " + biceps_list.size());

                    }
                    //checkbox is cleared, call remove function
                    else
                    {
                        System.out.printf("****removed %d: %s\n", i, biceps_ex[i]);//error this line //muscle_list.get(i)
                        graph.remove(biceps_ex[i]);//muscle_list.get(i)
                        biceps_list.remove(Integer.valueOf(i));
                        graph.proportion_graph2();
                        System.out.println("unchecked musclselist.size = " + biceps_list.size());

                        setContentView(graph);

                    }

                }
            });

            //add exercises to graph if OK clicked
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {

                    System.out.println("OK musclselist.size = " + biceps_list.size());
                    for (int j = 0; j < biceps_list.size(); j++) {
                        //if exercise is not already in list
                        if (graph.try_add(biceps_ex[biceps_list.get(j)]))
                        {
                            daily_max(biceps_list.get(j),14);
                            //pass name & index of exercise to set_exercises
                            //graph.set_exercises(graph.back_exercises, back_ex[back_list.get(j)], back_list.get(j));
                            graph.proportion_graph2();
                            System.out.printf("****%d: %s\n", j, biceps_ex[biceps_list.get(j)]);
                        }

                    }

                    graph.draw_graph = true;
                    setContentView(graph);

                    System.out.println("***************debug***************");
                    System.out.println("graph.data2.size() = " + graph.data.size());
                    for (int k = 0; k < graph.data.size(); k++) {
                        System.out.printf("%d:\n", k);
                        graph.data.get(k).print();

                    }

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();
                }
            });
            builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    // remove all items from graph
                    graph.data.clear();
                    //clear the list of ints
                    biceps_list.clear();
                    //clear all checks
                    for (int j = 0; j < biceps_sel.length; j++) {
                        biceps_sel[j] = false;
                    }
                    setContentView(graph);

                }
            });
            builder.show();
            //end biceps
        }//end v == MaxWeightFrag.back
    }//end biceps_max()

    //triceps drop down onclick
    public void triceps_max(View v)
    {
        if(v == MaxWeightFrag.triceps)
        {

            AlertDialog.Builder builder = new AlertDialog.Builder(GraphActivity.this);
            builder.setTitle("Choose Triceps Exercises");
            builder.setCancelable(false);
            builder.setMultiChoiceItems(triceps_ex, triceps_sel, new DialogInterface.OnMultiChoiceClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean b)
                {

                    if (b)
                    {

                        //vector of ints
                        triceps_list.add(i);
                        System.out.printf("%d added\n", i);
                        System.out.println("checked musclselist.size = " + triceps_list.size());

                    }
                    //checkbox is cleared, call remove function
                    else
                    {
                        System.out.printf("****removed %d: %s\n", i, triceps_ex[i]);//error this line //muscle_list.get(i)
                        graph.remove(triceps_ex[i]);//muscle_list.get(i)
                        triceps_list.remove(Integer.valueOf(i));
                        graph.proportion_graph2();
                        System.out.println("unchecked musclselist.size = " + triceps_list.size());

                        setContentView(graph);

                    }

                }
            });

            //add exercises to graph if OK clicked
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {

                    System.out.println("OK musclselist.size = " + triceps_list.size());
                    for (int j = 0; j < triceps_list.size(); j++) {
                        //if exercise is not already in list
                        if (graph.try_add(triceps_ex[triceps_list.get(j)]))
                        {
                            daily_max(triceps_list.get(j),17);
                            //pass name & index of exercise to set_exercises
                            //graph.set_exercises(graph.back_exercises, back_ex[back_list.get(j)], back_list.get(j));
                            graph.proportion_graph2();
                            System.out.printf("****%d: %s\n", j, triceps_ex[triceps_list.get(j)]);
                        }

                    }

                    graph.draw_graph = true;
                    setContentView(graph);

                    System.out.println("***************debug***************");
                    System.out.println("graph.data2.size() = " + graph.data.size());
                    for (int k = 0; k < graph.data.size(); k++) {
                        System.out.printf("%d:\n", k);
                        graph.data.get(k).print();

                    }

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();
                }
            });
            builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    // remove all items from graph
                    graph.data.clear();
                    //clear the list of ints
                    triceps_list.clear();
                    //clear all checks
                    for (int j = 0; j < triceps_sel.length; j++) {
                        triceps_sel[j] = false;
                    }
                    setContentView(graph);

                }
            });
            builder.show();
            //end biceps
        }//end v == MaxWeightFrag.back
    }//end triceps_max()

    //triceps drop down onclick
    public void forearms_max(View v)
    {
        if(v == MaxWeightFrag.forearms)
        {

            AlertDialog.Builder builder = new AlertDialog.Builder(GraphActivity.this);
            builder.setTitle("Choose Forearm Exercises");
            builder.setCancelable(false);
            builder.setMultiChoiceItems(forearms_ex, forearms_sel, new DialogInterface.OnMultiChoiceClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean b)
                {

                    if (b)
                    {

                        //vector of ints
                        forearms_list.add(i);
                        System.out.printf("%d added\n", i);
                        System.out.println("checked musclselist.size = " + forearms_list.size());

                    }
                    //checkbox is cleared, call remove function
                    else
                    {
                        System.out.printf("****removed %d: %s\n", i, forearms_ex[i]);//error this line //muscle_list.get(i)
                        graph.remove(forearms_ex[i]);//muscle_list.get(i)
                        forearms_list.remove(Integer.valueOf(i));
                        graph.proportion_graph2();
                        System.out.println("unchecked musclselist.size = " + forearms_list.size());

                        setContentView(graph);

                    }

                }
            });

            //add exercises to graph if OK clicked
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {

                    System.out.println("OK musclselist.size = " + forearms_list.size());
                    for (int j = 0; j < forearms_list.size(); j++) {
                        //if exercise is not already in list
                        if (graph.try_add(forearms_ex[forearms_list.get(j)]))
                        {
                            daily_max(forearms_list.get(j),17);
                            //pass name & index of exercise to set_exercises
                            //graph.set_exercises(graph.back_exercises, back_ex[back_list.get(j)], back_list.get(j));
                            graph.proportion_graph2();
                            System.out.printf("****%d: %s\n", j, forearms_ex[forearms_list.get(j)]);
                        }

                    }

                    graph.draw_graph = true;
                    setContentView(graph);

                    System.out.println("***************debug***************");
                    System.out.println("graph.data2.size() = " + graph.data.size());
                    for (int k = 0; k < graph.data.size(); k++) {
                        System.out.printf("%d:\n", k);
                        graph.data.get(k).print();

                    }

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();
                }
            });
            builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    // remove all items from graph
                    graph.data.clear();
                    //clear the list of ints
                    forearms_list.clear();
                    //clear all checks
                    for (int j = 0; j < forearms_sel.length; j++) {
                        forearms_sel[j] = false;
                    }
                    setContentView(graph);

                }
            });
            builder.show();
            //end biceps
        }//end v == MaxWeightFrag.back
    }//end biceps_max()


    //go back to main button
    public void home(View v)
    {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }


}