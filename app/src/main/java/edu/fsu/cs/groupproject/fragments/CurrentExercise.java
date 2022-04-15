package edu.fsu.cs.groupproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.fsu.cs.groupproject.R;

public class CurrentExercise extends Fragment {

    public CurrentExercise() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.current_exercise, container, false);
        setupView(view);
        return view;
    }

    private void setupView(View view) {
        setupMuscleSpinner(view);
        setupExerciseSpinner(view);
        setupSetsListView(view);
        setupTextViews(view);
    }

    private void setupTextViews(View view) {
    }

    private void setupSetsListView(View view) {
    }

    private void setupExerciseSpinner(View view) {
    }

    private void setupMuscleSpinner(View view) {
    }
}
