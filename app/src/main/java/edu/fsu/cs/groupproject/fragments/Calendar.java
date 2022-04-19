package edu.fsu.cs.groupproject.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.time.LocalDate;
import java.util.ArrayList;

import edu.fsu.cs.groupproject.R;
import edu.fsu.cs.groupproject.database.DatabaseHelper;
import edu.fsu.cs.groupproject.graphs.GraphActivity;

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
        init_calendar(view, databaseHelper);
    }

    private void init_calendar(View view, DatabaseHelper databaseHelper) {
        MaterialCalendarView materialCalendarView = view.findViewById(R.id.calendarView);
        ArrayList<String> dates = databaseHelper.getDatesList();

        populateCalendar(materialCalendarView, dates);
        init_listeners(materialCalendarView, dates, databaseHelper, view);
    }

    private void populateCalendar(MaterialCalendarView materialCalendarView, ArrayList<String> dates) {
        for (String s : dates) {
            String[] dayMonthYear;
            dayMonthYear = s.split("\\.");
            int month = Integer.parseInt(dayMonthYear[0]);
            int day = Integer.parseInt(dayMonthYear[1]);
            int year = Integer.parseInt(dayMonthYear[2]);
            CalendarDay selectedDay = CalendarDay.from(year, month, day);
            materialCalendarView.setDateSelected(selectedDay, true);
        }
    }

    private void init_listeners(MaterialCalendarView materialCalendarView, ArrayList<String> arrayList, DatabaseHelper databaseHelper, View view) {
        materialCalendarView.setOnDateChangedListener((widget, date, selected) -> {

            String dateAsString = formatDate(date);

            Cursor cursor = databaseHelper.dateQuery(dateAsString);

            if (cursor.getCount() == 0) {
                return;
            }

            calendarCommunications.onDateSelected(view);
        });
    }

    private String formatDate(CalendarDay date) {
        String day = String.valueOf(date.getDay());
        String month = String.valueOf(date.getMonth());
        String year = String.valueOf(date.getYear());

        if (day.length() == 1) {
            day = "0" + day;
        }

        if (month.length() == 1) {
            month = "0" + month;
        }

        return month + "." + day + "." + year;
    }

    public interface CalendarCommunications {
        void onDateSelected(View view);
    }

}
