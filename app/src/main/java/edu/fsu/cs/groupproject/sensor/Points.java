package edu.fsu.cs.groupproject.sensor;

public class Points
{
    float x;
    float y;
    float diff_x;
    float diff_y;
    float day;
    float lbs;

    //lbs should be value so can change to anything that will be on the y axis
    Points(float d, float l, float x_val, float y_val, float change_x,float change_y)//Points(float d, float l, float x_val, float y_val, float change)
    {
        day = d;
        lbs = l;
        x = x_val;
        y = y_val;
        diff_x = change_x;
        diff_y = change_y;
    }
}