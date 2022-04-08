package edu.fsu.cs.groupproject.graphs;

public class Points
{
    int x;
    int y;
    int diff;
    int day;
    int lbs;

    //lbs should be value so can change to anything that will be on the y axis
    Points(int d, int l, int x_val, int y_val, int change)
    {
        day = d;
        lbs = l;
        x = x_val;
        y = y_val;
        diff = change;
    }
}
