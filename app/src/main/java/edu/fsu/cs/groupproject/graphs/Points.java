package edu.fsu.cs.groupproject.graphs;

public class Points
{
    float x;
    float y;
    float diff_x;
    float diff_y;
    float day;
    float lbs;

    //lbs should be value so can change to anything that will be on the y axis
    Points(float d, float l, float x_val, float y_val, float change_x,float change_y)
    {
        //switch axes to accommodate the larger range in weight on the larger y axis
        //day = d;
        //lbs = l;
        day = l;
        lbs = d;
        //x = x_val;
        //y = y_val;
        x = y_val;
        y = x_val;
        //diff_x = change_x;
        //diff_y = change_y;
        diff_x = change_y;
        diff_y = change_x;
    }
}
