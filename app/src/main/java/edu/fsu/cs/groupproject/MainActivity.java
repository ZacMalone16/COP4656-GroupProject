package edu.fsu.cs.groupproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import edu.fsu.cs.groupproject.fragments.CurrentExercise;
import edu.fsu.cs.groupproject.fragments.FragmentCommunications;
import edu.fsu.cs.groupproject.fragments.StartPage;
import edu.fsu.cs.groupproject.graphs.GraphActivity;
import edu.fsu.cs.groupproject.graphs.Graph;

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
            System.out.println("bundle extras != null");
            String start = (String) extras.get("start");
            System.out.println("start = " + start);
            //load sets and reps frag
            if (start.equals("true"))
            {

                System.out.println("bundle worked");
            }

        }
        else
        {
            System.out.println("bundle == null");
            init();
            //onAddWorkoutSelected();
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
    }


    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)//Sensor.TYPE_ACCELEROMETER
        {
            ax = event.values[0];
            ay = event.values[1];
            ax = event.values[2];
        }


        if(!calibrate)
        {
            offset_x = ax;
            offset_y = ay;
            offset_z = az;
        }

        if(calibrate && !stop)
        {
            //System.out.println("calibrate && !stop");
            if(  (ay - offset_y) >= 0.2 || (ay - offset_y) <= -0.2 )//if(  (ay - offset_y) >= 0.2 || (ay - offset_y) <= -0.2 )
            {
                //add raw data outside -.2 to .2
                PointF p = new PointF(count,(float)ay);
                raw_data.add(p);
                //if change in magnitude is larger than .5f add to data points
                if(Math.abs(prev.y) - Math.abs((ay - offset_y)) > .5f || Math.abs(prev.y) - Math.abs((ay - offset_y)) < -.5f )
                {
                    //az -= offset_z;
                    ax -= offset_x;
                    ay -= offset_y;
                    p = new PointF(count,(float)ay);
                    data_points.add(p);
                    count++;
                }

            }
            //save prev point
            prev = new PointF(count,(float)ay);
        }
        else if(stop && flag)
        {
            count_reps();
            System.out.println("get reps called =============================");
            flag = false;
            //if(flag)
            //{
                /*
                //new VanityTask().execute();

                boolean has_next_rep = get_reps();
                rep_count = 0;
                while(has_next_rep)
                {

                    rep_count++;
                    System.out.printf("REP %d\n", rep_count);
                    has_next_rep = get_reps();
                }
                 */


                //rep_count_num.setText(rep_count);
               // flag = false;


            //}
        }
    }
    public static int count_reps()
    {
        boolean has_next_rep = get_reps();
        rep_count = 0;
        while(has_next_rep)
        {

            rep_count++;
            System.out.printf("REP %d\n", rep_count);
            has_next_rep = get_reps();
        }
        System.out.println("");

        return rep_count;


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }

    public static boolean get_reps()
    {
        //float error_margin = .5f;
        PointF p;

        boolean is_large;
        //int index = 0;

        if(!has_next_index(index))
        {
            //failure no next rep
            return false;
        }
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
        p = new PointF(data_points.get(index).x,data_points.get(index).y);//index + 1
        reps.add(p);

        if(!has_next_index(index))
        {
            return false;
        }

        is_large = check_is_large(index,"down");

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
        //float next = Math.abs(data_points.get(i + 1).y);////should this be absolute value?
        //float current = Math.abs(data_points.get(i).y);//should this be absolute value?
        float next = data_points.get(i + 1).y;////should this be absolute value?
        float current = data_points.get(i).y;//should this be absolute value?

        float mag = (float)2.0;

        //finds min
        if(str.equals("up"))
        {
            //System.out.printf("up: %f - %f = %f\n", next,current, next - current);
            if(next - current >= mag)
            {
                //System.out.println("min found$$$$$$$$$$$$$$$$$");
                return true;
            }
        }
        //finds max
        else if(str.equals("down"))
        {
            //System.out.printf("down: %f - %f = %f\n", next,current, next - current);
            if(next - current <= -mag)
            {
                //System.out.println("max found$$$$$$$$$$$$$$$$$");

                return true;
            }
        }
        return false;
    }

    public static boolean has_next_index(int index)
    {
        if(index + 1 > data_points.size() - 1)
        {
            return false;
        }
        return true;
    }

    public void start(View v)
    {
        calibrate = true;

    }

    public void man_add_reps(View v)
    {


    }
}//end MainActivity class

/*    private void openWorkouts() {
        Intent intent = new Intent(this, GraphActivity.class);
        startActivity(intent);
    }
*/