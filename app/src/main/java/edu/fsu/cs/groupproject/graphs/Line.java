package edu.fsu.cs.groupproject.graphs;

import java.util.ArrayList;

public class Line
{
    ArrayList<Points> points = new ArrayList<>();
    String name;

    Line(String str, int[][] array)
    {

        name = str;

        for(int i = 0; i < array.length; i++)
        {
            Points p = new Points(array[i][0],array[i][1],array[i][0],array[i][1],0,0);
            points.add(p);
        }
    }

    void print()
    {
        for(int i = 0; i < points.size(); i++)
        {
            System.out.printf("%d: %f,%f\n", i,points.get(i).day, points.get(i).lbs);
        }
    }
}
