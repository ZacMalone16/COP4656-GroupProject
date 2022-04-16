package edu.fsu.cs.groupproject.graphs;

import android.graphics.Point;

import java.util.ArrayList;

public class Exercise
{

    public String name;//bench press
    public String muscle;
    public ArrayList<Point> data = new ArrayList<>();//index is set #, each row,col is weight and reps
    //int[][] exercise = new int[][]

    public Exercise()
    {

    }

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
