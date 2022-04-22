package edu.fsu.cs.groupproject.fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.fsu.cs.groupproject.R;
import edu.fsu.cs.groupproject.database.DatabaseHelper;



public class DailyView extends Fragment {
    ArrayList<String> workout_list;
    ListView dailyview;
    DatabaseHelper db;

    public DailyView(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(getContext());

/*
        Cursor cur = db.todaysWorkout();

        while(cur.moveToNext()) {
            if(cur.getInt(1) != 1){
                workout_list.add("Set: " + cur.getString(1) + "\n");
                workout_list.add("Reps: " + cur.getString(2) + "\n");
                workout_list.add("Weight: " + cur.getString(3) + "\n");
            }
            else {
                workout_list.add("Exercise: " + cur.getString(0) + "\n");
                workout_list.add("Set: " + cur.getString(1) + "\n");
                workout_list.add("Reps: " + cur.getString(2) + "\n");
                workout_list.add("Weight: " + cur.getString(3) + "\n");
            }
        }*/

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_daily_view, container, false);

        ListView dailyview = view.findViewById(R.id.dailylistview);

        workout_list = new ArrayList<String>(); // ##########################################
        Cursor cur = db.todaysWorkout();

        while(cur.moveToNext()) {
            if(cur.getInt(1) != 1){
                workout_list.add("Set: " + cur.getString(1));
                workout_list.add("Reps: " + cur.getString(2));
                workout_list.add("Weight: " + cur.getString(3));
            }
            else {
                workout_list.add("Exercise: " + cur.getString(0));
                workout_list.add("Set: " + cur.getString(1));
                workout_list.add("Reps: " + cur.getString(2));
                workout_list.add("Weight: " + cur.getString(3));
            }
        }

        CustomListAdapter listAdapter = new CustomListAdapter(workout_list);// #####################
        dailyview.setAdapter(listAdapter);
        return view;
    }

    class CustomListAdapter extends BaseAdapter {
        List<String> items;
        public CustomListAdapter(List<String> items){
            super();
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }
        @Override
        public Object getItem(int i) {
            return items.get(i);
        }
        @Override
        public long getItemId(int i) {
            return items.get(i).hashCode();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(getContext());
            textView.setText(items.get(i));
            return textView;
        }
    }
}
