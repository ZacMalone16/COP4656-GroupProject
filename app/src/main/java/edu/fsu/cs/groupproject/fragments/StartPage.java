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

public class StartPage extends Fragment implements Communications {

    private StartPageCommunications startPageCommunications;

    public StartPage() {}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        establishCommunications(context);
    }

    @Override
    public void establishCommunications(Context context) {
        if (context instanceof StartPageCommunications) {
            startPageCommunications = (StartPageCommunications) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement StartPageCommunications");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        setupView(view);

        return view;
    }

    private void setupView(View view) {
        setupButtons(view);
    }

    private void setupButtons(View view) {
        Button addNewWorkout = (Button) view.findViewById(R.id.startPage_addNewWorkout);
        addNewWorkout.setOnClickListener(v -> addWorkoutHandler());

        Button viewPreviousWorkouts = (Button) view.findViewById(R.id.startPage_viewPreviousWorkouts);
        viewPreviousWorkouts.setOnClickListener(v -> addViewPreviousWorkoutsHandler());
    }

    private void addViewPreviousWorkoutsHandler() {
        startPageCommunications.onViewPreviousSelected();
    }

    private void addWorkoutHandler() {
        startPageCommunications.onAddWorkoutSelected();
    }

    public interface StartPageCommunications {
        void onAddWorkoutSelected();
        void onViewPreviousSelected();
    }
}
