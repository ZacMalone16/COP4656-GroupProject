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

public class CalendarFragment extends Fragment {

    CalendarCommunication commsListener;

    public CalendarFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_fragment, container, false);
        setupView(view);
        return view;
    }

    private void setupView(View view) {
        Button backButton = (Button) view.findViewById(R.id.calendar_returnButton);
        backButton.setOnClickListener(v -> commsListener.onReturn());
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof CalendarCommunication) {
            commsListener = (CalendarCommunication) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement CalendarCommunication");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        commsListener = null;
    }

    public interface CalendarCommunication {
        void onReturn();
    }

}
