package edu.fsu.cs.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.fsu.cs.groupproject.fragments.CalendarFragment;
import edu.fsu.cs.groupproject.fragments.Comms;
import edu.fsu.cs.groupproject.fragments.MainFragment;

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

        openMain();
    }

    private void openMain() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView2, new MainFragment(), MainFragment.class.getCanonicalName()).commit();
    }

    @Override
    public void openCalendar() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView2, new CalendarFragment(), CalendarFragment.class.getCanonicalName()).commit();
    }

    @Override
    public void onReturn() {
        openMain();
    }


}