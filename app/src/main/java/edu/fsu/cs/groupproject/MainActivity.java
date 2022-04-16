package edu.fsu.cs.groupproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;

import edu.fsu.cs.groupproject.fragments.CurrentExercise;
import edu.fsu.cs.groupproject.fragments.FragmentCommunications;
import edu.fsu.cs.groupproject.fragments.StartPage;
import edu.fsu.cs.groupproject.graphs.Graph;

public class MainActivity extends AppCompatActivity implements FragmentCommunications
{
    static FragmentManager manager;
    static FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            String [] values = getIntent().getStringArrayExtra("exercises");
            for(int i = 0; i < values.length; i++)
            {
                System.out.println(values[i]);
            }
            CurrentExercise.exercise_array = values;
            CurrentExercise.muscle_chosen = true;
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView2, new CurrentExercise(), "CurrentExercise").commit();

            /*
            System.out.println("bundle extras != null");
            String zero = (String) extras.get("0");
            System.out.println("main bundle_layout = " + zero);
            //load sets and reps frag
            if (bundle_layout.equals("0"))
            {

                System.out.println("bundle worked");
            }
             */
        }

        init();
    }

    private void init() {
        openStart();
        getSupportActionBar().hide();
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

    public static void reload_frag()
    {

        CurrentExercise frag = new CurrentExercise();
        //manager = get
        /*
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView2, new CurrentExercise(), "CurrentExercise").commit();
         */
    }

}

/*    private void openWorkouts() {
        Intent intent = new Intent(this, GraphActivity.class);
        startActivity(intent);
    }
*/