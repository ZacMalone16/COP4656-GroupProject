
//Written by Matthew Kaplan
package edu.fsu.cs.groupproject.graphs;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import edu.fsu.cs.groupproject.R;

public class MaxWeightFrag extends Fragment
{

    static TextView chest;
    static TextView back;
    static TextView shoulder;
    static TextView quads;
    static TextView hams;
    static TextView calf;
    static TextView biceps;
    static TextView triceps;
    static TextView forearms;

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
        chest = view.findViewById(R.id.chest_drop5);
        back = view.findViewById(R.id.back_drop);
        shoulder = view.findViewById(R.id.shoulder_drop);
        quads = view.findViewById(R.id.quads_drop);
        hams = view.findViewById(R.id.hamstrings_drop);
        calf = view.findViewById(R.id.calf_drop);
        biceps = view.findViewById(R.id.biceps_drop);
        triceps = view.findViewById(R.id.triceps_drop);
        forearms = view.findViewById(R.id.forearms_drop);

        return view;
    }
}
