package edu.fsu.cs.groupproject.graphs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

//import android.support.v4.app.Fragment;

import java.util.ArrayList;

import edu.fsu.cs.groupproject.R;

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

    //Spinner muscle_spin;
    //Spinner exercise_spin;
    //Spinner date_spin;

    //vectors of exercises added (int index in list)

    ArrayList<Integer> chest_list = new ArrayList<>();//langlist
    ArrayList<Integer> back_list = new ArrayList<>();
    ArrayList<Integer> shoulder_list = new ArrayList<>();
    ArrayList<Integer> quad_list = new ArrayList<>();
    ArrayList<Integer> ham_list = new ArrayList<>();
    ArrayList<Integer> calf_list = new ArrayList<>();
    ArrayList<Integer> bicep_list = new ArrayList<>();
    ArrayList<Integer> tricep_list = new ArrayList<>();
    ArrayList<Integer> forearm_list = new ArrayList<>();

    //exercise lists
    String[] chest_ex = {"Bench Press", "Incline Dumbbell Press", "Cable Flye"};//langarray
    String[] back_ex = {"Lat Pulldown", "T Bar Row", "Cable Row"};
    String[] shoulder_ex = {"Dumbbell Press", "Barbell Press", "Lateral Raise"};
    String[] quad_ex = {"Squat", "Leg Press", "Leg Extension"};
    String[] ham_ex = {"Deadlift", "Leg Curl", "Dumbbell Lunge"};
    String[] calf_ex = {"Leg Press Calf Extension", "Seated Calf Raise", "Standing Calf Raise"};
    String[] bicep_ex = {"Barbell Curl", "Dumbbell Curl", "Barbell Preacher Lunge"};
    String[] tricep_ex = {"Seated Dumbbell Extension", "Overhead Barbell Extension", "Dumbbell Kickback"};
    String[] forearm_ex = {"Standing Barbell Wrist Curl", "Seated Dumbbell Wrist Curl"};

    boolean[] chest_sel = new boolean[3];//selectedLang
    boolean[] back_sel = new boolean[3];
    boolean[] shoulder_sel = new boolean[3];

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
            else if(graph_num == 3)
            {
                choose_graph_sel = 1;

                graph = new Graph(this, width, height, 0);//
                setContentView(graph);
                graph.setBackgroundColor(Color.WHITE);
                for(int i = 0; i < data.size(); i++)
                {
                    graph.set_exercises(data,names.get(i),i);//"BenchPress"
                }

                graph.proportion_graph2();
                graph.draw_graph = true;
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

    public void chest_max(View v)
    {

        if(v == MaxWeightFrag.chest)
        {
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
                    for (int j = 0; j < chest_list.size(); j++) {
                        //if exercise namec is not already in list
                        if (graph.try_add(chest_ex[chest_list.get(j)]))
                        {
                            //pass arraylist for chest1,name & index of exercise to set_exercises
                            graph.set_exercises(graph.chest_exercises, chest_ex[chest_list.get(j)], chest_list.get(j));
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
            builder.setMultiChoiceItems(back_ex, back_sel, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                    if (b) {

                        //vector of ints
                        back_list.add(i);
                        System.out.printf("%d added\n", i);
                        System.out.println("checked musclselist.size = " + back_list.size());

                    }
                    //checkbox is cleared, call remove function
                    else {
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
                public void onClick(DialogInterface dialogInterface, int i) {

                    System.out.println("OK musclselist.size = " + back_list.size());
                    for (int j = 0; j < back_list.size(); j++) {
                        //if exercise is not already in list
                        if (graph.try_add(back_ex[back_list.get(j)])) {
                            //pass name & index of exercise to set_exercises
                            graph.set_exercises(graph.back_exercises, back_ex[back_list.get(j)], back_list.get(j));
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

    /*
    static Graph get_graph()
    {
        return graph;
    }
     */


}