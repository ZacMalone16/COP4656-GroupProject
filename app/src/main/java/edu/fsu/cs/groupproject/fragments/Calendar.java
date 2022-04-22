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
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;

import edu.fsu.cs.groupproject.R;
import edu.fsu.cs.groupproject.database.DatabaseHelper;
import edu.fsu.cs.groupproject.graphs.CalendarGraphActivity;

public class Calendar extends Fragment {


    DatabaseHelper databaseHelper;

    public Calendar() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

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

            //calendarCommunications.onDateSelected(view);
            onItemClick(dateAsString);
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

    public void onItemClick(String date) {

        int workoutID, x, current_exercise = -1;
        int[][] points_array;

        CalendarGraphActivity.clear();

        String clicked_date = date;

        System.out.println(clicked_date);
        Cursor cur = databaseHelper.dateQuery(clicked_date);

        if (cur != null && cur.getCount() > 0) {
            cur.moveToFirst();
        } else {
            System.out.println("null");
            return;
        }
        while (!cur.isAfterLast()) {
            //get workoutID
            System.out.println("cur(0) = " + Integer.parseInt(cur.getString(0)));
            //
            workoutID = Integer.parseInt(cur.getString(0));//was 5
            System.out.println("cur.getcount = " + cur.getCount());
            //skip to next record if the workout ID is the same
            if (workoutID == current_exercise)//if(Integer.parseInt(cur.getString(0)) == current_exercise)
            {
                cur.moveToNext();
                continue;
            }

            Cursor cur2 = databaseHelper.getWorkoutData(workoutID);

            if (cur2 != null && cur2.getCount() > 0) {
                points_array = new int[cur2.getCount()][2];//
                System.out.println("cur2.getCount() = " + cur2.getCount());
                x = 0;
                cur2.moveToFirst();
                while (!cur2.isAfterLast()) {
                    System.out.println("x = " + x);
                    //reps
                    points_array[x][0] = Integer.parseInt(cur2.getString(4));
                    //System.out.println(Integer.parseInt(cur.getString(4)));
                    //weight
                    points_array[x][1] = Integer.parseInt(cur2.getString(3));

                    System.out.println("0 setID = " + cur2.getString(0));
                    System.out.println("1 workoutID = " + cur2.getString(1));
                    System.out.println("2 setNum = " + cur2.getString(2));
                    System.out.println("3 Reps = " + cur2.getString(3));
                    System.out.println("4 weight = " + cur2.getString(4));
                    cur2.moveToNext();
                    x++;
                }
                //get name of exercise
                cur2 = databaseHelper.getAllDatat1();

                if (cur2 != null && cur2.getCount() > 0) {
                    cur2.moveToFirst();
                    while (!cur2.isAfterLast())//
                    {
                        if (Integer.parseInt(cur2.getString(0)) == Integer.parseInt(cur.getString(5)))//==workoutid
                        {
                            CalendarGraphActivity.addName(cur2.getString(2));
                            //GraphActivity.names.add(cur2.getString(2));

                        }

                        System.out.println("0 exercise ID = " + cur2.getString(0));
                        System.out.println("1 muscleGroup = " + cur2.getString(1));
                        System.out.println("2 Exercise = " + cur2.getString(2));
                        cur2.moveToNext();
                    }

                }

                CalendarGraphActivity.addData(points_array);
                //GraphActivity.data.add(points_array);
            } else {
                System.out.println("null");
                return;
            }


            System.out.println("points array.length = " + points_array.length);
            //debug print
            for (int n = 0; n < points_array.length; n++) {
                //for(int k = 0; k < 2; k++)
                //{
                System.out.printf("%d %d,%d\n", n, points_array[n][0], points_array[n][1]);
                //}
            }
            current_exercise = Integer.parseInt(cur.getString(0));
            System.out.println("max weight");
            System.out.println("current exercise = " + current_exercise);
            Cursor cur3 = databaseHelper.maxWeight(current_exercise);
            if (cur3 != null && cur3.getCount() > 0) {
                cur3.moveToFirst();
                while (!cur3.isAfterLast()) {
                    System.out.println("cur(0) Name = " + cur3.getString(0));
                    System.out.println("cur(1) max weight = " + cur3.getString(1));

                    //System.out.println("cur(2) = " + cur.getString(2));
                    cur3.moveToNext();
                }
            }

            cur.moveToNext();

        }//end while
        Intent intent = new Intent(getActivity(), CalendarGraphActivity.class);
        //intent.putExtra("bundle_layout",1);
        intent.putExtra("graph_num", 3);
        startActivity(intent);
    }



}
