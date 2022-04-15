package edu.fsu.cs.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.fsu.cs.groupproject.fragments.CurrentExercise;
import edu.fsu.cs.groupproject.fragments.FragmentCommunications;
import edu.fsu.cs.groupproject.fragments.StartPage;

public class MainActivity extends AppCompatActivity implements FragmentCommunications {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        openStart();
    }

    private void openStart() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView2, new StartPage(), "StartPage").commit();
    }

    @Override
    public void onAddWorkoutSelected() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView2, new CurrentExercise(), "CurrentExercise").commit();
    }

    @Override
    public void onViewPreviousSelected() {
        // Calendar needs to be implemented, haven't gotten to it yet.
    }

/*    private void openWorkouts() {
        Intent intent = new Intent(this, GraphActivity.class);
        startActivity(intent);
    }
*/


}