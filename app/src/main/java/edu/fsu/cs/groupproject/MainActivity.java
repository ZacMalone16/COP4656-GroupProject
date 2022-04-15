package edu.fsu.cs.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        setupButtons();
    }

    private void setupButtons() {}

/*    private void openWorkouts() {
        Intent intent = new Intent(this, GraphActivity.class);
        startActivity(intent);
    }

    private void openCurrentWorkout() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView2, new CurrentWorkoutFragment(), CurrentWorkoutFragment.class.getCanonicalName()).commit();
    }*/

}