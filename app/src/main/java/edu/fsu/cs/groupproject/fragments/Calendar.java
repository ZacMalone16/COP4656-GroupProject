package edu.fsu.cs.groupproject.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.fsu.cs.groupproject.R;

public class Calendar extends Fragment implements Communications {

    CalendarCommunications calendarCommunications;

    public Calendar() {}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        establishCommunications(context);
    }

    @Override
    public void establishCommunications(Context context) {
        if (context instanceof CalendarCommunications) {
            calendarCommunications = (CalendarCommunications) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement CalendarCommunications.");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_page, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

    }

    public interface CalendarCommunications {

    }

}
