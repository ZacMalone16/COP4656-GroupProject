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

   /*
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    */
    public ChooseFrag()
    {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    /*
    public static ChooseFrag newInstance(String param1, String param2)
    {
        ChooseFrag fragment = new ChooseFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
     */

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

       /*
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        */

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

        Spinner choose_graph = (Spinner) view.findViewById(R.id.spinner);
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
                        intent.putExtra("bundle_layout",1);
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
                        intent2.putExtra("bundle_layout",2);
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