package edu.fsu.cs.groupproject.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.fsu.cs.groupproject.R;

public class MainFragment extends Fragment {

    private FragmentCommunicationListener commsListener;

    public MainFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        //setupView(view);
        return view;
    }

    private void setupView(View view) {
        Button main_calendarButton = view.findViewById(R.id.main_calendarButton);
        main_calendarButton.setOnClickListener(v -> commsListener.openCalendar());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof FragmentCommunicationListener) {
            commsListener = (FragmentCommunicationListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentCommunicationListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        commsListener = null;
    }

    public interface FragmentCommunicationListener {
        void openCalendar();
    }

}
