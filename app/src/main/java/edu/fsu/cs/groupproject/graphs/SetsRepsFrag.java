package edu.fsu.cs.groupproject.graphs;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import edu.fsu.cs.groupproject.R;
//import androidx.fragment.app.Fragment;

public class SetsRepsFrag extends Fragment
{

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





        return view;

    }
}
