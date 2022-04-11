
package edu.fsu.cs.groupproject.graphs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/*
public class MaxWeight extends Activity
{
    FragmentManager manager;
    FragmentTransaction ft;
    Graph graph6;
    DisplayMetrics displayMetrics = new DisplayMetrics();
    //phone screen height
    int height;
    //phone screen width
    int width;
    int layout = -1;


    TextView textview;
    TextView quads;

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

    boolean[] chest_sel;//selectedLang
    boolean[] back_sel;
    boolean[] shoulder_sel;


    //int set_
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.max_weight);
        System.out.println("MaxWeight oncreate()");

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
        System.out.println("width: " + width);
        System.out.println("height: " + height);
    }
}
 */
        //graph6 = new Graph(this, width, height, 2);//

        //setContentView(graph6);

       /*
        manager = getFragmentManager();
        ft = manager.beginTransaction();
        //Graph g2 = new Graph(this, width, height, 0);
        ChooseFrag chooseFrag = new ChooseFrag();
        ft.replace(R.id.graph99,graph);//R.id.graph_frag
        ft.commit();
        */

        //setContentView(graph);
        //graph.setBackgroundColor(Color.WHITE);

        /*
        TextView chest;
        TextView back;
        TextView shoulder;
        //TextView quads;
        TextView hams;
        TextView calf;
        TextView bicep;
        TextView tricep;
        TextView forearms;

        Spinner choose_graph = (Spinner) findViewById(R.id.spinner);
         */

        /*
        //textview = findViewById(R.id.chest_drop);
        chest = findViewById(R.id.chest_drop99);

        back = findViewById(R.id.back_drop);
        shoulder = findViewById(R.id.shoulder_drop);
        quads = findViewById(R.id.quads_drop);
        hams = findViewById(R.id.hamstrings_drop);
        calf = findViewById(R.id.calf_drop);
        bicep = findViewById(R.id.biceps_drop);
        tricep = findViewById(R.id.triceps_drop);
        forearms = findViewById(R.id.forearms_drop);


        chest_sel = new boolean[chest_ex.length];
        back_sel = new boolean[back_ex.length];
        shoulder_sel = new boolean[back_ex.length];
         */

        //set position of legend y
        //graph.set_legend_y(quads.getY());

        /*
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
                        graph = new Graph(getBaseContext(), width, height, 1);
                        setContentView(graph);
                        System.out.println("layout spinner = " + choose_graph.getSelectedItemPosition());
                        layout = 1;
                        //graph.set_layout(1);
                        break;
                    case 2:
                        System.out.println("layout spinner = " + choose_graph.getSelectedItemPosition());
                        //Graph g2 = new Graph(getApplicationContext(),width,height,2);
                        graph = new Graph(getApplicationContext(), width, height, 2);
                        graph.setBackgroundColor(Color.WHITE);
                        //graph.set_layout(2);
                        setContentView(graph);
                        layout = 2;
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
         */

       /*
        chest.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(MaxWeight.this);
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
                            graph6.remove(chest_ex[i]);//muscle_list.get(i)
                            chest_list.remove(Integer.valueOf(i));
                            graph6.proportion_graph2();
                            System.out.println("unchecked musclselist.size = " + chest_list.size());

                            setContentView(graph6);

                        }

                    }
                });

                //add exercises to graph if OK clicked
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        System.out.println("OK musclselist.size = " + chest_list.size());
                        for (int j = 0; j < chest_list.size(); j++) {
                            //if exercise namec is not already in list
                            if (graph6.try_add(chest_ex[chest_list.get(j)])) {
                                //pass arraylist for chest1,name & index of exercise to set_exercises
                                graph6.set_exercises(graph6.chest_exercises, chest_ex[chest_list.get(j)], chest_list.get(j));
                                graph6.proportion_graph2();
                                System.out.printf("****%d: %s\n", j, chest_ex[chest_list.get(j)]);
                            }

                        }

                        graph6.draw_graph = true;
                        setContentView(graph6);

                        System.out.println("***************debug***************");
                        System.out.println("graph.data2.size() = " + graph6.data.size());
                        for (int k = 0; k < graph6.data.size(); k++) {
                            System.out.printf("%d:\n", k);
                            graph6.data.get(k).print();

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
                        graph6.data.clear();
                        //clear the list of ints
                        chest_list.clear();
                        //clear all checks
                        for (int j = 0; j < chest_sel.length; j++) {
                            chest_sel[j] = false;
                        }
                        setContentView(graph6);

                    }
                });
                builder.show();
            }
        });//end chest1

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MaxWeight.this);
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
                            graph6.remove(back_ex[i]);//muscle_list.get(i)
                            back_list.remove(Integer.valueOf(i));
                            graph6.proportion_graph2();
                            System.out.println("unchecked musclselist.size = " + back_list.size());

                            setContentView(graph6);

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
                            if (graph6.try_add(back_ex[back_list.get(j)])) {
                                //pass name & index of exercise to set_exercises
                                graph6.set_exercises(graph6.back_exercises, back_ex[back_list.get(j)], back_list.get(j));
                                graph6.proportion_graph2();
                                System.out.printf("****%d: %s\n", j, back_ex[back_list.get(j)]);
                            }

                        }

                        graph6.draw_graph = true;
                        setContentView(graph6);

                        System.out.println("***************debug***************");
                        System.out.println("graph.data2.size() = " + graph6.data.size());
                        for (int k = 0; k < graph6.data.size(); k++) {
                            System.out.printf("%d:\n", k);
                            graph6.data.get(k).print();

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
                        graph6.data.clear();
                        //clear the list of ints
                        back_list.clear();
                        //clear all checks
                        for (int j = 0; j < back_sel.length; j++) {
                            back_sel[j] = false;
                        }
                        setContentView(graph6);

                    }
                });
                builder.show();
            }
        });//end back

        shoulder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MaxWeight.this);
                builder.setTitle("Choose Shoulder Exercises");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(shoulder_ex, shoulder_sel, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                        if (b) {

                            //vector of ints
                            shoulder_list.add(i);
                            System.out.printf("%d added\n", i);
                            System.out.println("checked musclselist.size = " + shoulder_list.size());

                        }
                        //checkbox is cleared, call remove function
                        else {
                            System.out.printf("****removed %d: %s\n", i, shoulder_ex[i]);//error this line //muscle_list.get(i)
                            graph6.remove(shoulder_ex[i]);//muscle_list.get(i)
                            shoulder_list.remove(Integer.valueOf(i));
                            graph6.proportion_graph2();
                            System.out.println("unchecked musclselist.size = " + shoulder_list.size());

                            setContentView(graph6);

                        }

                    }
                });

                //add exercises to graph if OK clicked
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        System.out.println("OK musclselist.size = " + shoulder_list.size());
                        for (int j = 0; j < shoulder_list.size(); j++) {
                            //if exercise is not already in list
                            if (graph6.try_add(shoulder_ex[shoulder_list.get(j)])) {
                                //pass name & index of exercise to set_exercises
                                graph6.set_exercises(graph6.shoulder_exercises, shoulder_ex[shoulder_list.get(j)], shoulder_list.get(j));
                                graph6.proportion_graph2();
                                System.out.printf("****%d: %s\n", j, shoulder_ex[shoulder_list.get(j)]);
                            }

                        }

                        graph6.draw_graph = true;
                        setContentView(graph6);

                        System.out.println("***************debug***************");
                        System.out.println("graph.data2.size() = " + graph6.data.size());
                        for (int k = 0; k < graph6.data.size(); k++) {
                            System.out.printf("%d:\n", k);
                            graph6.data.get(k).print();

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
                        graph6.data.clear();
                        //clear the list of ints
                        shoulder_list.clear();
                        //clear all checks
                        for (int j = 0; j < shoulder_sel.length; j++) {
                            shoulder_sel[j] = false;
                        }
                        setContentView(graph6);

                    }
                });
                builder.show();
            }
        });//end shoulder

        */

/*
        switch (layout)
        {
            case 1:
                //sets and reps
                break;
            case 2:
                //max weight
                chest1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {

                        AlertDialog.Builder builder = new AlertDialog.Builder(MaxWeight.this);
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
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                System.out.println("OK musclselist.size = " + chest_list.size());
                                for (int j = 0; j < chest_list.size(); j++) {
                                    //if exercise namec is not already in list
                                    if (graph.try_add(chest_ex[chest_list.get(j)])) {
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
                                chest_list.clear();
                                //clear all checks
                                for (int j = 0; j < chest_sel.length; j++) {
                                    chest_sel[j] = false;
                                }
                                setContentView(graph);

                            }
                        });
                        builder.show();
                    }
                });//end chest1

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(MaxWeight.this);
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
                    }
                });//end back

                shoulder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(MaxWeight.this);
                        builder.setTitle("Choose Shoulder Exercises");
                        builder.setCancelable(false);
                        builder.setMultiChoiceItems(shoulder_ex, shoulder_sel, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                                if (b) {

                                    //vector of ints
                                    shoulder_list.add(i);
                                    System.out.printf("%d added\n", i);
                                    System.out.println("checked musclselist.size = " + shoulder_list.size());

                                }
                                //checkbox is cleared, call remove function
                                else {
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
                            public void onClick(DialogInterface dialogInterface, int i) {

                                System.out.println("OK musclselist.size = " + shoulder_list.size());
                                for (int j = 0; j < shoulder_list.size(); j++) {
                                    //if exercise is not already in list
                                    if (graph.try_add(shoulder_ex[shoulder_list.get(j)])) {
                                        //pass name & index of exercise to set_exercises
                                        graph.set_exercises(graph.shoulder_exercises, shoulder_ex[shoulder_list.get(j)], shoulder_list.get(j));
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
                    }
                });//end shoulder


                //setContentView(R.layout.activity_main);

        }//end switch
 */


