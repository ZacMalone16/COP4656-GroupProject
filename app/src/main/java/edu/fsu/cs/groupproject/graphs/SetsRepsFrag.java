package edu.fsu.cs.groupproject.graphs;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import edu.fsu.cs.groupproject.R;
//import androidx.fragment.app.Fragment;

public class SetsRepsFrag extends Fragment
{
    static Spinner date_spin;

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

        SetsRepsFrag.date_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                switch (SetsRepsFrag.date_spin.getSelectedItemPosition())
                {
                    case 0:
                        //chose a date
                        break;
                    case 1://April 01, 2022
                        System.out.println("apr 01 sel");
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
