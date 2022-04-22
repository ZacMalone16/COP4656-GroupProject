package edu.fsu.cs.groupproject;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import edu.fsu.cs.groupproject.fragments.Calendar;
import edu.fsu.cs.groupproject.fragments.CurrentExercise;
import edu.fsu.cs.groupproject.fragments.FragmentCommunications;
import edu.fsu.cs.groupproject.fragments.StartPage;
import edu.fsu.cs.groupproject.graphs.Graph;
import edu.fsu.cs.groupproject.graphs.GraphActivity;

public class MainActivity extends AppCompatActivity implements FragmentCommunications, SensorEventListener
{
    static FragmentManager manager;
    static FragmentTransaction ft;

    static ArrayList<PointF> data_points = new ArrayList<>();
    static ArrayList<PointF> reps = new ArrayList<>();
    static ArrayList<PointF> raw_data = new ArrayList<>();

    SensorManager sensorManager;
    CurrentExercise currentExercise = new CurrentExercise();
    public static int muscle_sel = 0;
    PointF prev;
    double ax,ay,az, offset_x, offset_y, offset_z;
    public static boolean calibrate = false;
    public static boolean stop = false;
    int count = 0;
    public static int index = 0;
    public static boolean flag  = true;
    public static int rep_count;
    boolean complete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);

        prev = new PointF(0,0.0f);

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            onAddWorkoutSelected();

        }
        else
        {

            init();

        }


    }

    private void init() {
        openStart();
        getSupportActionBar().hide();
    }

    public void graph_frag(View v)
    {
        Intent intent = new Intent(this, GraphActivity.class);
        startActivity(intent);
    }

    //start page frag
    private void openStart() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView2, new StartPage(), "StartPage").commit();
    }

    @Override
    //current exercise frag
    public void onAddWorkoutSelected() {
        //currentExercise = new CurrentExercise();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView2, currentExercise, "CurrentExercise").commit();
    }

    @Override
    public void onViewPreviousSelected() {
        // Calendar needs to be implemented, haven't gotten to it yet.
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView2, new Calendar(), "Calendar").commit();
    }

    @Override
    public void onGraphSelected() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        //phone screen height
        int height = displayMetrics.heightPixels;
        //phone screen width
        int width = displayMetrics.widthPixels;

        Graph graph = new Graph(this, width, height, 0);



    }

    @Override
    public void onCurrentExercisesSelected() {



    }


    @Override
    public void onSensorChanged(SensorEvent event)
    {
        //get acceleration in each axis
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            ax = event.values[0];
            ay = event.values[1];
            ax = event.values[2];
        }


        //if start button has not been pressed
        if(!calibrate)
        {
            //get current acceleration to use as offset
            offset_x = ax;
            offset_y = ay;
            offset_z = az;
        }

        //if start was pressed and not stop
        if(calibrate && !stop)
        {
            //filter number one
            //subtract the offset from the current acceleration values, and only add if this value is outside the .2 to -.2 range
            if(  (ay - offset_y) >= 0.2 || (ay - offset_y) <= -0.2 )
            {
                //add raw data outside -.2 to .2
                PointF p = new PointF(count,(float)ay);
                raw_data.add(p);
                //if change in magnitude is larger than .5f add to data points
                if(Math.abs(prev.y) - Math.abs((ay - offset_y)) > .5f || Math.abs(prev.y) - Math.abs((ay - offset_y)) < -.5f )
                {
                    //z axis not used
                    //az -= offset_z;

                    //subtract the offset
                    ax -= offset_x;
                    ay -= offset_y;
                    //add point
                    p = new PointF(count,(float)ay);
                    data_points.add(p);
                    //increment count
                    count++;
                }

            }
            //save prev point
            prev = new PointF(count,(float)ay);
        }
        //only call count reps once after set is completed
        else if(stop && flag)
        {
            count_reps();
            flag = false;

        }
    }

    //count reps
    public static int count_reps()
    {
        //call get reps, return false if a pair of points wasn't found, return false if there are no more data points
        boolean has_next_rep = get_reps();
        rep_count = 0;
        //while get reps returns true
        while(has_next_rep)
        {
            //increment rep count
            rep_count++;
            //call get reps again
            has_next_rep = get_reps();
        }
        System.out.println(" ");

        //return the number of reps counted
        return rep_count;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }


    //get the number of reps
    public static boolean get_reps()
    {

        PointF p;
        boolean is_large;

        if(!has_next_index(index))
        {
            //failure no next rep
            return false;
        }
        //check if magnitude of change was large enough to count
        is_large = check_is_large(index,"up");
        while(!is_large)
        {
            //increment index
            index++;
            //check if next index exists
            if(!has_next_index(index))
            {

                return false;
            }
            //check next index
            is_large = check_is_large(index, "up");
        }

        //add start point to reps
        p = new PointF(data_points.get(index).x,data_points.get(index).y);
        reps.add(p);

        //check if next index exists
        if(!has_next_index(index))
        {
            return false;
        }

        //check if magnitude of change was large enough to count
        is_large = check_is_large(index,"down");

        //while false continue checking next index
        while(!is_large)
        {
            //increment index
            index++;
            //check if next index exists
            if(!has_next_index(index))
            {
                //remove the last point if a complete rep wasn't found (only found min no max)
                reps.remove(reps.size() - 1);
                return false;
            }
            //check next index
            is_large = check_is_large(index, "down");

        }

        //add end point to reps
        p = new PointF(data_points.get(index).x,data_points.get(index).y);////index + 1
        reps.add(p);

        //success next rep found
        return true;
    }

    public static boolean check_is_large(int i,String str)
    {
       //check the index passed in, and the next index for change in magnitude that equals mag
        float next = data_points.get(i + 1).y;
        float current = data_points.get(i).y;

        //magnitude difference to check for
        float mag = (float)2.0;

        //finds min
        if(str.equals("up"))
        {

            if(next - current >= mag)
            {

                return true;
            }
        }
        //finds max
        else if(str.equals("down"))
        {

            if(next - current <= -mag)
            {

                return true;
            }
        }
        return false;
    }

    //check if the next index exists
    public static boolean has_next_index(int index) //
    {
        if(index + 1 > data_points.size() - 1)
        {
            return false;
        }
        return true;
    }

    //start button, sets calibrate to true
    public void start(View v)
    {
        calibrate = true;

    }


}//end MainActivity class

