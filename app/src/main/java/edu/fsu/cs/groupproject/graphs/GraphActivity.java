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

import java.util.ArrayList;

import edu.fsu.cs.groupproject.MainActivity;
import edu.fsu.cs.groupproject.R;
import edu.fsu.cs.groupproject.database.DatabaseHelper;

//import android.support.v4.app.Fragment;

public class GraphActivity extends Activity //Activity //AppCompatActivity
{
    Graph graph;
    //used to get phone screen dimensions
    DisplayMetrics displayMetrics = new DisplayMetrics();
    //phone screen height
    int height;
    //phone screen width
    int width;

    FragmentManager manager;
    FragmentTransaction ft;
    static int choose_graph_sel;
    static ArrayList<int[][]> data = new ArrayList<>();
    static ArrayList<String> names = new ArrayList<>();
    int [][] max_by_date;
    //int workoutID;
    int current_exercise;
    String name;

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
    String[] hams_ex = {"Leg Curl","Dumbbell Lunge","Deadlift"};
    String[] calf_ex = {"Calf Raise", "Seated Calf Raise"};
    String[] biceps_ex = {"Barbell Curl", "Dumbbell Curl", "Cable Curl"};
    String[] triceps_ex = {"Barbell Extension", "Dumbbell Extension", "Cable Push Down"};
    String[] forearms_ex = {"Wrist Curl"};

    //array of booleans that refer to if the exercise at this index was added
    boolean[] chest_sel = new boolean[3];//selectedLang
    boolean[] back_sel = new boolean[3];
    boolean[] shoulder_sel = new boolean[3];
    boolean[] biceps_sel = new boolean[3];
    boolean[] triceps_sel = new boolean[3];
    boolean[] forearms_sel = new boolean[3];//size should be 1
    boolean[] quads_sel = new boolean[3];
    boolean[] hams_sel = new boolean[3];
    boolean[] calf_sel = new boolean[2];
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //get width and height of phone screen
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
        db = new DatabaseHelper(getBaseContext());


        //check if bundle is not null
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            //get bundle extras
            int graph_num = extras.getInt("graph_num");

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

            }
            //load max weight frag
            else if(graph_num == 2)
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
                graph.setBackgroundColor(Color.WHITE);
                for(int i = 0; i < data.size(); i++)
                {
                    graph.set_exercises(data,names.get(i),i);
                }

                //proportion new graph
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
        //extras == null load choose graph frag
        else
        {
            graph = new Graph(this, width, height, 0);//
            setContentView(graph);
            graph.setBackgroundColor(Color.WHITE);

            manager = getFragmentManager();
            ft = manager.beginTransaction();
            ChooseFrag chooseFrag = new ChooseFrag();
            ft.replace(R.id.spinner_frag,chooseFrag);
            ft.commit();
        }

    }//end OnCreate()


    public void daily_max(int sel,int eID)
    {

        //exercise ID in database
        int exerciseID = sel + 1 + eID;
        //get daily max
        Cursor cur = db.dailyMax(exerciseID);

        //if the cursor wasn't null and has data
        if(cur != null && cur.getCount() > 0)
        {
            //set up array to size of cur.getCount()
            max_by_date = new int[cur.getCount()][2];
            //move to first row
            cur.moveToFirst();
            int x = 0;
            //while there is another row
            while(!cur.isAfterLast())
            {
                //parse the date
                String day = cur.getString(0);
                day = day.substring(3,5);
                //put into an 2d array of ints, x,y is date, max weight
                max_by_date[x][1] = Integer.parseInt(day);
                max_by_date[x][0] = Integer.parseInt(cur.getString(1));
                //increment index
                x++;
                //move to next row
                cur.moveToNext();
            }
            //add data to arraylist
            data.add(max_by_date);

            //get name of exercise
            Cursor cur2 = db.getAllDatat1();
            if(cur2 != null && cur2.getCount() > 0)
            {
                cur2.moveToFirst();
                while(!cur2.isAfterLast())
                {
                    //if workout ID is equal to exerciseID
                    if(Integer.parseInt(cur2.getString(0)) == exerciseID)
                    {
                        //get name of exercise
                        name = cur2.getString(2);
                        break;

                    }
                    //move to next row
                    cur2.moveToNext();
                }

            }

            //set exercises on graph to be displayed
            graph.set_exercises(data,name,data.size() - 1);//chest_ex[chest_list.get(j)]
        }

    }



    //chest drop down
    public void chest_max(View v)
    {
        if(v == MaxWeightFrag.chest)
        {

            //set up dialog popup
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
                        //vector of ints refers to what was selected
                        chest_list.add(i);

                    }
                    //checkbox is cleared, call remove function
                    else
                    {
                        //remove exercise from graph if check box cleared
                        graph.remove(chest_ex[i]);
                        chest_list.remove(Integer.valueOf(i));
                        graph.proportion_graph2();
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

                    for (int j = 0; j < chest_list.size(); j++)
                    {

                        //if exercise name is not already in list
                        if (graph.try_add(chest_ex[chest_list.get(j)]))
                        {
                            daily_max(chest_list.get(j),0);
                            graph.proportion_graph2();

                        }

                    }

                    graph.draw_graph = true;
                    setContentView(graph);


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

                    }
                    //checkbox is cleared, call remove function
                    else
                    {

                        graph.remove(back_ex[i]);//muscle_list.get(i)
                        back_list.remove(Integer.valueOf(i));
                        graph.proportion_graph2();
                        setContentView(graph);

                    }

                }
            });

            //add exercises to graph if OK clicked
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {

                    for (int j = 0; j < back_list.size(); j++)
                    {
                        //if exercise is not already in list
                        if (graph.try_add(back_ex[back_list.get(j)]))
                        {
                            //add to list
                            daily_max(back_list.get(j),3);
                            graph.proportion_graph2();

                        }

                    }

                    graph.draw_graph = true;
                    setContentView(graph);

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
                    }
                    //checkbox is cleared, call remove function
                    else
                    {
                        graph.remove(shoulder_ex[i]);
                        shoulder_list.remove(Integer.valueOf(i));
                        graph.proportion_graph2();
                        setContentView(graph);

                    }

                }
            });

            //add exercises to graph if OK clicked
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {

                    for (int j = 0; j < shoulder_list.size(); j++)
                    {
                        //if exercise is not already in list add
                        if (graph.try_add(shoulder_ex[shoulder_list.get(j)]))
                        {
                            daily_max(shoulder_list.get(j),21);
                            graph.proportion_graph2();

                        }

                    }

                    graph.draw_graph = true;
                    setContentView(graph);


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
            builder.setTitle("Choose Biceps Exercises");
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

                    }
                    //checkbox is cleared, call remove function
                    else
                    {
                        graph.remove(biceps_ex[i]);//muscle_list.get(i)
                        biceps_list.remove(Integer.valueOf(i));
                        graph.proportion_graph2();
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

                    for (int j = 0; j < biceps_list.size(); j++)
                    {
                        //if exercise is not already in list add
                        if (graph.try_add(biceps_ex[biceps_list.get(j)]))
                        {
                            daily_max(biceps_list.get(j),14);
                            graph.proportion_graph2();

                        }

                    }

                    graph.draw_graph = true;
                    setContentView(graph);


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

                    }
                    //checkbox is cleared, call remove function
                    else
                    {
                        graph.remove(triceps_ex[i]);//muscle_list.get(i)
                        triceps_list.remove(Integer.valueOf(i));
                        graph.proportion_graph2();

                        setContentView(graph);

                    }

                }
            });

            //add exercises to graph if OK clicked
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {

                    for (int j = 0; j < triceps_list.size(); j++)
                    {
                        //if exercise is not already in list
                        if (graph.try_add(triceps_ex[triceps_list.get(j)]))
                        {
                            daily_max(triceps_list.get(j),17);
                            graph.proportion_graph2();

                        }

                    }

                    graph.draw_graph = true;
                    setContentView(graph);

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
        }
    }//end triceps_max()

    //forearms drop down onclick
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
                    }
                    //checkbox is cleared, call remove function
                    else
                    {
                        graph.remove(forearms_ex[i]);
                        forearms_list.remove(Integer.valueOf(i));
                        graph.proportion_graph2();
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

                    for (int j = 0; j < forearms_list.size(); j++) {
                        //if exercise is not already in list add
                        if (graph.try_add(forearms_ex[forearms_list.get(j)]))
                        {
                            daily_max(forearms_list.get(j),20);
                            graph.proportion_graph2();
                        }

                    }

                    graph.draw_graph = true;
                    setContentView(graph);

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

        }
    }//end forearms

    //quads drop down onclick
    public void quads_max(View v)
    {
        if(v == MaxWeightFrag.quads)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(GraphActivity.this);
            builder.setTitle("Choose Quadriceps Exercises");
            builder.setCancelable(false);
            builder.setMultiChoiceItems(quads_ex, quads_sel, new DialogInterface.OnMultiChoiceClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean b)
                {

                    if (b)
                    {
                        //vector of ints
                        quads_list.add(i);
                    }
                    //checkbox is cleared, call remove function
                    else
                    {
                        graph.remove(quads_ex[i]);
                        quads_list.remove(Integer.valueOf(i));
                        graph.proportion_graph2();
                        setContentView(graph);

                    }

                }
            });

            //add exercises to graph if OK clicked
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {

                    for (int j = 0; j < quads_list.size(); j++)
                    {
                        //if exercise is not already in list add
                        if (graph.try_add(quads_ex[quads_list.get(j)]))
                        {
                            daily_max(quads_list.get(j),6);
                            graph.proportion_graph2();
                        }

                    }

                    graph.draw_graph = true;
                    setContentView(graph);

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
                    quads_list.clear();
                    //clear all checks
                    for (int j = 0; j < quads_sel.length; j++) {
                        quads_sel[j] = false;
                    }
                    setContentView(graph);

                }
            });
            builder.show();

        }
    }//end quads

    //hams drop down onclick
    public void hams_max(View v)
    {
        if(v == MaxWeightFrag.hams)
        {

            AlertDialog.Builder builder = new AlertDialog.Builder(GraphActivity.this);
            builder.setTitle("Choose Hamstring Exercises");
            builder.setCancelable(false);
            builder.setMultiChoiceItems(hams_ex, hams_sel, new DialogInterface.OnMultiChoiceClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean b)
                {

                    if (b)
                    {
                        //vector of ints
                        hams_list.add(i);
                    }
                    //checkbox is cleared, call remove function
                    else
                    {
                        graph.remove(hams_ex[i]);
                        hams_list.remove(Integer.valueOf(i));
                        graph.proportion_graph2();
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

                    for (int j = 0; j < hams_list.size(); j++)
                    {
                        //if exercise is not already in list add
                        if (graph.try_add(hams_ex[hams_list.get(j)]))
                        {
                            daily_max(hams_list.get(j),9);
                            graph.proportion_graph2();

                        }

                    }

                    graph.draw_graph = true;
                    setContentView(graph);

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
                    hams_list.clear();
                    //clear all checks
                    for (int j = 0; j < hams_sel.length; j++) {
                        hams_sel[j] = false;
                    }
                    setContentView(graph);

                }
            });
            builder.show();

        }
    }//end hams

    //calf drop down onclick
    public void calf_max(View v)
    {
        if(v == MaxWeightFrag.calf)
        {

            AlertDialog.Builder builder = new AlertDialog.Builder(GraphActivity.this);
            builder.setTitle("Choose Calf Exercises");
            builder.setCancelable(false);
            builder.setMultiChoiceItems(calf_ex, calf_sel, new DialogInterface.OnMultiChoiceClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean b)
                {

                    if (b)
                    {
                        //vector of ints
                        calf_list.add(i);
                    }
                    //checkbox is cleared, call remove function
                    else
                    {
                        graph.remove(calf_ex[i]);
                        calf_list.remove(Integer.valueOf(i));
                        graph.proportion_graph2();
                        setContentView(graph);

                    }

                }
            });

            //add exercises to graph if OK clicked
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    for (int j = 0; j < calf_list.size(); j++) {
                        //if exercise is not already in list add
                        if (graph.try_add(calf_ex[calf_list.get(j)]))
                        {
                            daily_max(calf_list.get(j),12);
                            graph.proportion_graph2();

                        }

                    }

                    graph.draw_graph = true;
                    setContentView(graph);

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
                    calf_list.clear();
                    //clear all checks
                    for (int j = 0; j < calf_sel.length; j++) {
                        calf_sel[j] = false;
                    }
                    setContentView(graph);

                }
            });
            builder.show();

        }
    }//end calf

    //go back to main button
    public void home(View v)
    {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }

    public void backToMain(View v) {
        home(v);
    }


}