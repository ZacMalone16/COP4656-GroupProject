package edu.fsu.cs.groupproject.graphs;

public class Points
{
    float x;
    float y;
    float diff_x;
    float diff_y;
    float day;
    float lbs;

    Points(float d, float l, float x_val, float y_val, float change_x,float change_y)
    {
        //switch axes to accommodate the larger range in weight on the larger y axis
        day = l;
        lbs = d;
        x = y_val;
        y = x_val;
        diff_x = change_y;
        diff_y = change_x;
    }
}
