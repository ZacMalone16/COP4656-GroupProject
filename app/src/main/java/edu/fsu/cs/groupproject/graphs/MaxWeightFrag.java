package edu.fsu.cs.groupproject.graphs;

//import androidx.fragment.app.Fragment;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import edu.fsu.cs.groupproject.R;

public class MaxWeightFrag extends Fragment
{

    static TextView chest;
    static TextView back;
    static TextView shoulder;
    TextView quads;
    TextView hams;
    TextView calf;
    TextView bicep;
    TextView tricep;
    TextView forearms;

    public MaxWeightFrag()
    {

    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.frag_max_weight,container,false);

        //Spinner choose_graph = (Spinner) view.findViewById(R.id.spinner);

        chest = view.findViewById(R.id.chest_drop5);
        back = view.findViewById(R.id.back_drop);
        shoulder = view.findViewById(R.id.shoulder_drop);
        quads = view.findViewById(R.id.quads_drop);
        hams = view.findViewById(R.id.hamstrings_drop);
        calf = view.findViewById(R.id.calf_drop);
        bicep = view.findViewById(R.id.biceps_drop);
        tricep = view.findViewById(R.id.triceps_drop);
        forearms = view.findViewById(R.id.forearms_drop);

        return view;
    }
}
