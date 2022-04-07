package edu.fsu.cs.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import edu.fsu.cs.groupproject.fragments.BlankFragment;
import edu.fsu.cs.groupproject.fragments.CalendarFragment;
import edu.fsu.cs.groupproject.fragments.Comms;

public class MainActivity extends AppCompatActivity implements Comms {

    // add button to add a workout
    //     will open new gui
    //              custom workout
    //                   scan barcode, or choose muscle workout from list
    //              choose from our recommended list
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        setupButtons();
    }

    private void setupButtons() {
        Button calendarButton = findViewById(R.id.main_calendarButton);
        calendarButton.setOnClickListener(v -> openCalendar());
    }

    private void openCalendar() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView2, new CalendarFragment(), CalendarFragment.class.getCanonicalName()).commit();
    }

    @Override
    public void onReturn() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView2, new BlankFragment(), CalendarFragment.class.getCanonicalName()).commit();
    }
}