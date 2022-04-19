package edu.fsu.cs.groupproject.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.time.LocalDate;
import java.util.ArrayList;

import edu.fsu.cs.groupproject.R;
import edu.fsu.cs.groupproject.database.DatabaseHelper;

public class Calendar extends Fragment implements Communications {

    CalendarCommunications calendarCommunications;
    DatabaseHelper databaseHelper;

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
        databaseHelper = new DatabaseHelper(getActivity());

        MaterialCalendarView materialCalendarView = view.findViewById(R.id.calendarView);

        ArrayList<String> dates = databaseHelper.getDatesList();



        for (String s : dates) {
            String[] dayMonthYear;
            dayMonthYear = s.split("\\.");
            int month = Integer.parseInt(dayMonthYear[0]);
            int day = Integer.parseInt(dayMonthYear[1]);
            int year = Integer.parseInt(dayMonthYear[2]);
            CalendarDay selectedDay = CalendarDay.from(year, month, day);
            System.out.println(selectedDay.toString());
            materialCalendarView.setDateSelected(selectedDay, true);
        }


    }

    public interface CalendarCommunications {

    }

}
