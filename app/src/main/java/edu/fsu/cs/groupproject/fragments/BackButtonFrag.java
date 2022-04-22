package edu.fsu.cs.groupproject.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import edu.fsu.cs.groupproject.MainActivity;
import edu.fsu.cs.groupproject.R;

public class BackButtonFrag extends Fragment {

    BackButtonCommunications backButtonCommunications;
    Button backButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.back_button_frag, container, false);

        init(view);

        return view;

    }

    private void init(View view) {

        backButton = view.findViewById(R.id.backButtonFrag_BackButton);

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(view.getContext(),  MainActivity.class);
            startActivity(intent);
        });


    }


    public interface BackButtonCommunications {
        void onBackButton();
    }

}
