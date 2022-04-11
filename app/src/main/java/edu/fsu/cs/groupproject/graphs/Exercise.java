package edu.fsu.cs.groupproject.graphs;

import android.graphics.Point;

import java.util.ArrayList;

public class Exercise
{

    String name;//bench press
    String muscle;
    ArrayList<Point> data = new ArrayList<>();//sets and reps
    //int[][] exercise = new int[][]

    Exercise(String m,String n,int[][] array)
    {
        name = n;
        muscle = m;

        for(int i = 0; i < array.length; i++)
        {

            Point p = new Point();
            p.x = array[i][0];
            p.y = array[i][1];
            data.add(p);

        }
    }


}
