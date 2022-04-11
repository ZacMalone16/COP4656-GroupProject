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

import java.util.ArrayList;

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
    //int [][] accel = new int[][]
    ArrayList<PointF> data_points = new ArrayList<>();
    ArrayList<PointF> reps = new ArrayList<>();
    PointF prev;
    //ArrayList<>

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




       /*
        System.out.println("************raw readings************");
        System.out.println("ax = " + ax);
        System.out.println("ay = " + ay);
        System.out.println("az = " + az);
        */




        if(!stop_sensor)
        {
            //ax - offset_x) != 0.0 || (ay - offset_y) != 0.0
            //(ax - offset_x) != 0.0 && (ay - offset_y) != 0.0 /////ay - offset_y) != 0.0
            //if( !( (ay - offset_y) <= 0.01) && !((ay - offset_y) >= -0.01) )
            //if(  (ay - offset_y) >= 0.02 && (ay - offset_y) <= -0.02 )
            //if(  (ay - offset_y) >= 0.2 || (ay - offset_y) <= -0.2 )
            if(  (ay - offset_y) >= 0.2 || (ay - offset_y) <= -0.2 ) //if((ay - offset_y) != 0.0)//
            {
                if(Math.abs(prev.y) - Math.abs((ay - offset_y)) > .5f)
                {
                    //az -= offset_z;
                    ax -= offset_x;
                    ay -= offset_y;
                    PointF p = new PointF(count,(float)ay);
                    data_points.add(p);
                    count++;
                }

            }
            prev = new PointF(count,(float)ay);


        }
        else
        {
            if(flag)
            {
                //find_next_min_max();

                //int index = 0;
                boolean has_next_rep = get_reps();
                int rep_count = 0;
                while(has_next_rep)
                {

                    rep_count++;
                    System.out.printf("REP %d\n", rep_count);
                    has_next_rep = get_reps();

                }

                flag = false;
                //return;
            }

        }

        //for(int i = 0; i < )
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
            if(next - current >= mag)//data_points.get(i + 1).y - data_points.get(i).y > 3.0
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

    void find_next_min_max()
    {
        int period = 60;
        //PointF start = data_points.get(0);
        float min_y = 1000;
        float min_x = 0;
        float max_y = -1000;
        float max_x = 0;
        int index = 0;
        int new_index = 0;
        boolean check_max = false;
        boolean check_min = false;
        boolean flip = true;

        int size = data_points.size()/period;
        int remainder = data_points.size() % period;
        //size = (size * period) + remainder;

        //find the first 2 mins
       /**
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < period; j++)
            {

                //if on last iteration
                //if(i == (size - 1))
               // {
               //     period = remainder;
               // }

                if(data_points.get(j + (i * period)).y < min_y)
                {
                    min_y = data_points.get(j).y;
                    min_x = data_points.get(j).x;
                    PointF p = new PointF(min_x,min_y);
                    reps.add(0,p);
                    index = (j + (i * period));
                }
                //check max
                if(data_points.get(j + (i * period)).y > max_y)
                {

                }
            }


        }

        //int i = (size * period);
        for(int i = (size * period); i < data_points.size(); i++)
        {


            min_y = data_points.get(i).y;
            min_x = data_points.get(i).x;
        }
        PointF p = new PointF(min_x,min_y);
        reps.add(p);
        */


       /**
        //check first 60 for min
        for(int i = 0; i < period; i++)
        {
            if(data_points.get(i).y < min_y)
            {
                min_y = data_points.get(i).y;
                min_x = data_points.get(i).x;
                index = i;
            }
        }
        //add 1st min
        PointF p = new PointF(min_x,min_y);
        reps.add(p);
        */

        //index = -1;
        boolean has_next_period = check_next_period(index,period);
        System.out.println("has next period = " + has_next_period);


        PointF p = new PointF(data_points.get(0).x,data_points.get(0).y);
        reps.add(p);
        while(has_next_period)
        {
            if(has_next_period)
            {
                //check next 60 starting at index for max
                for(int i = index; i < (index + period); i++ )//index + 2 + period// index + period
                {

                    if(flip)
                    {
                        if(data_points.get(i).y < min_y)
                        {
                            min_y = data_points.get(i).y;
                            min_x = data_points.get(i).x;
                            new_index = i;
                        }
                    }
                    else
                    {
                        if(data_points.get(i).y > max_y)
                        {
                            max_y = data_points.get(i).y;
                            max_x = data_points.get(i).x;
                            new_index = i;
                        }
                    }
                }
                if(flip)
                {
                    p = new PointF(min_x,min_y);
                }
                else
                {
                    p = new PointF(max_x,max_y);
                }

                reps.add(p);
                flip = !flip;
                min_y = 1000;
                max_y = -1000;
            }
            /*
            //else until the end
            else
            {
                //check remaining indices
                for(int i = index + 1; i < data_points.size(); i++)
                {

                    if(flip)
                    {
                        if(data_points.get(i).y < min_y)
                        {
                            min_y = data_points.get(i).y;
                            min_x = data_points.get(i).x;
                            index = i;
                        }
                    }
                    else
                    {
                        if(data_points.get(i).y > max_y)
                        {
                            max_y = data_points.get(i).y;
                            max_x = data_points.get(i).x;
                            index = i;
                        }
                    }

                    if(data_points.get(i).y > max_y)
                    {
                        max_y = data_points.get(i).y;
                        max_x = data_points.get(i).x;
                        index = i;
                    }

                }
                if(flip)
                {
                    p = new PointF(min_x,min_y);
                }
                else
                {
                    p = new PointF(max_x,max_y);
                }

                reps.add(p);
                flip = !flip;
                min_y = 1000;
                max_y = -1000;
            }
             */

            index = new_index;
            //check next 60 exists
            has_next_period = check_next_period(index,period);
            System.out.println("has next period = " + has_next_period);

        }

       boolean has_more_data = has_remaining_data(index);

       while(has_more_data)
       {
           //check remaining indices
           for(int i = index + 1; i < data_points.size(); i++)
           {

               if(flip)
               {
                   if(data_points.get(i).y < min_y)
                   {
                       min_y = data_points.get(i).y;
                       min_x = data_points.get(i).x;
                       index = i;
                   }
               }
               else
               {
                   if(data_points.get(i).y > max_y)
                   {
                       max_y = data_points.get(i).y;
                       max_x = data_points.get(i).x;
                       index = i;
                   }
               }

               if(data_points.get(i).y > max_y)
               {
                   max_y = data_points.get(i).y;
                   max_x = data_points.get(i).x;
                   index = i;
               }

           }
           if(flip)
           {
               p = new PointF(min_x,min_y);
           }
           else
           {
               p = new PointF(max_x,max_y);
           }

           reps.add(p);
           flip = !flip;
           min_y = 1000;
           max_y = -1000;

           has_more_data = has_remaining_data(index);

       }

    }//end find next min max ()

    boolean check_next_period(int index,int period)
    {
        if((index + 1 + period) < data_points.size())//index + 1 + period
        {

            return true;
        }
        return  false;
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

        /**file I/O
        try
        {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("reps.dat"));
            os.writeObject(data_points);
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
         */

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

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }
}