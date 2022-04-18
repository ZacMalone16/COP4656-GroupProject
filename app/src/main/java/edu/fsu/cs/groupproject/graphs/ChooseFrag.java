package edu.fsu.cs.groupproject.graphs;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

//import androidx.fragment.app.Fragment;
//import android.support.v4.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import edu.fsu.cs.groupproject.R;


public class ChooseFrag extends Fragment
{

    public Spinner choose_graph;

    public ChooseFrag()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


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