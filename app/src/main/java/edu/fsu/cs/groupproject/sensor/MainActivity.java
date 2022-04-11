package edu.fsu.cs.groupproject.sensor;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import edu.fsu.cs.groupproject.R;

public class MainActivity extends Activity implements SensorEventListener //AppCompatActivity
{

    Graph graph;
    Graph reps_graph;
    SensorManager sensorManager;
    double ax,ay,az, offset_x, offset_y, offset_z;
    boolean once = true;
    boolean flag = true;
    Button stop;
    Button graph_button;
    boolean stop_sensor = false;
    ArrayList<PointF> data_points = new ArrayList<>();
    ArrayList<PointF> reps = new ArrayList<>();
    ArrayList<PointF> raw_data = new ArrayList<>();
    PointF prev;

    DisplayMetrics displayMetrics = new DisplayMetrics();
    //phone screen height
    int height;
    //phone screen width
    int width;
    int count = 0;
    //int graph_num = 1;
    boolean do_switch = true;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        stop = (Button) findViewById(R.id.stop_button);
        graph_button = (Button) findViewById(R.id.graph_button);
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;

        graph = new Graph(this,width,height,0);
        reps_graph = new Graph(this,width,height,0);
        setContentView(graph);
        graph.setBackgroundColor(Color.WHITE);
        reps_graph.setBackgroundColor(Color.WHITE);

        prev = new PointF(0,0.0f);
        System.out.println("width: " + width);
        System.out.println("height: " + height);

    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {

        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            ax = event.values[0];
            ay = event.values[1];
            ax = event.values[2];
        }

        if(once)
        {
            offset_x = ax;
            offset_y = ay;
            offset_z = az;
            once = false;
            System.out.println("************after offest************");
            System.out.println("ax = " + (ax -= offset_x));
            System.out.println("ay = " + (ay -= offset_y));
            System.out.println("az = " + (az -= offset_z));

        }

       /**
        System.out.println("************raw readings************");
        System.out.println("ax = " + ax);
        System.out.println("ay = " + ay);
        System.out.println("az = " + az);
        */

        if(!stop_sensor)
        {
            //if((ay - offset_y) != 0.0)
            if(  (ay - offset_y) >= 0.2 || (ay - offset_y) <= -0.2 )
            {
                //add raw data outside -.2 to .2
                PointF p = new PointF(count,(float)ay);
                raw_data.add(p);
                //if change in magnitude is larger than .5f add to data points
                if(Math.abs(prev.y) - Math.abs((ay - offset_y)) > .5f)
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
        //else stop pressed
        else
        {
            if(flag)
            {
                boolean has_next_rep = get_reps();
                int rep_count = 0;
                while(has_next_rep)
                {

                    rep_count++;
                    System.out.printf("REP %d\n", rep_count);
                    has_next_rep = get_reps();
                }

                flag = false;
            }
        }
    }//end onSensorChanged()

    boolean get_reps()
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

    boolean check_is_large(int i,String str)
    {
        float next = data_points.get(i + 1).y;////should this be absolute value?
        float current = data_points.get(i).y;//should this be absolute value?
        float mag = 3.0f;

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

    boolean has_next_index(int index)
    {
        if(index + 1 > data_points.size() - 1)
        {
            return false;
        }
        return true;
    }

    boolean has_remaining_data(int index)
    {

        //if there is still data to check
        if(index + 1 < data_points.size())
        {
            return true;
        }
        return false;
    }
    public void stop(View v)
    {
        //if(v == stop)
        //{
            System.out.println("stop pressed");

            stop_sensor = true;
        //}
    }

    public void graph(View v)
    {
       //set up raw accel data graph
        System.out.println("graph pressed");
        float[][] array = new float[data_points.size()][2];
        for (int i = 0; i < data_points.size(); i++)
        {
            array[i][0] = data_points.get(i).x;
            array[i][1] = data_points.get(i).y;

        }
        graph.add_pointsF(String.valueOf("accel"), array);
        graph.proportion_graph2();
        graph.draw_graph = true;
        setContentView(graph);
        //graph_num++;

        //set up the rep counter graph
        float[][] array2 = new float[reps.size()][2];
        for(int i = 0; i < reps.size(); i++)
        {
            array2[i][0] = reps.get(i).x;
            array2[i][1] = reps.get(i).y;

        }
        reps_graph.add_pointsF(String.valueOf("reps"),array2);
        reps_graph.proportion_graph2();
        reps_graph.draw_graph = true;

        //file I/O
        try
        {
            //write data_points
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("data_points.dat"));
            os.writeObject(data_points);
            //write raw data outside error margin
            os = new ObjectOutputStream(new FileOutputStream("raw_data.dat"));
            os.writeObject(raw_data);
            //write reps data
            os = new ObjectOutputStream(new FileOutputStream("reps.dat"));
            os.writeObject(reps);

            os.close();
            //FileInputStream fis = new FileInputStream()

            FileOutputStream fos = new FileOutputStream("accel.dat");
            int x = 0;
            while(x != -1)
            {

                fos.write(x);
            }

        }
        catch (Exception e)
        {
            System.out.println("error: " + e);
        }


    }

    public void switch_graph(View v)
    {
        System.out.println("switch graph pressed");

        if(do_switch)
        {
            //graph = reps_graph;
            setContentView(reps_graph);
            do_switch = !do_switch;
        }
        else
        {
            setContentView(graph);
            do_switch = !do_switch;
        }
    }

    public void load(View v)
    {
        try
        {

            FileInputStream fs = new FileInputStream("raw_data.dat");
            ObjectInputStream is = new ObjectInputStream(fs);

            while(fs.available() != 0)
            {

            }
        }
        catch (Exception e)
        {
            System.out.println("error: " + e);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }
}