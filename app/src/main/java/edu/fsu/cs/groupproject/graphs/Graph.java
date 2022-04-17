package edu.fsu.cs.groupproject.graphs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

import edu.fsu.cs.groupproject.R;

//missing 2 other constructors
public class Graph extends FrameLayout
{
    static Canvas can;
    //ArrayList< ArrayList<Points> > data = new ArrayList<>();
    ArrayList<Line> data = new ArrayList<>();//data
    //ArrayList<Routine> day = new ArrayList<>();
    ArrayList<Day> day = new ArrayList<>();

    ArrayList<int[][]> chest_exercises = new ArrayList<>();
    ArrayList<int[][]> back_exercises = new ArrayList<>();
    ArrayList<int[][]> shoulder_exercises = new ArrayList<>();

    int [][] bench_press_01 = new int[5][2];
    int [][] bench_press = new int [6][2];
    int [][] incline_dumbell = new int[5][2];
    int [][] incline_dumbell_01 = new int[5][2];
    int [][] cable_flyes = new int[5][2];
    int [][] lat_pulldown = new int [4][2];
    int [][] t_bar_row = new int[4][2];
    int [][] cable_row = new int[4][2];
    int [][] dumbbell_press_01 = new int[5][2];

    int max_weight;
    int sets_reps;
    //TextView chest1 = findViewById(R.id.chest_drop);

    int layout;
    int width;
    int height;
    int calendar_w;
    int calendar_h;
    int day_w;
    int y_axis_h;
    int r_margin;
    int l_margin;
    int top_margin;
    int min;
    int max;
    float legend_y;
    boolean draw_graph;
    //int mod;
    Point origin =  new Point();

    Paint paint = new Paint();

    DisplayMetrics displayMetrics = new DisplayMetrics();


    public Graph(Context context)
    {
        super(context);
    }

    public Graph(Context context, AttributeSet attrs)
    {
        super(context,attrs);
    }

    public Graph(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public Graph(Context context, int w, int h, int layout)
    {
        super(context);

        set_layout(layout);

        System.out.println("graph constructor ()");
        //inflate main layout
        //inflate(getContext(),R.layout.choose_graph ,this);//choose_graph //max_weight

        //max_weight = R.layout.max_weight;
        //sets_reps = R.layout.sets_reps;
        //inflate(getContext(),R.layout.activity_main ,this);
        //init();

        draw_graph = false;
        width = w;
        height = h;
        r_margin = 125;
        l_margin = 225;
        top_margin = 125;
        calendar_w = (w - r_margin) - l_margin;//was w - 100 - 100
        calendar_h = (h - 1000) - top_margin;
        origin.x = l_margin;
        origin.y = height - 1000;
        min = 1000;
        max = -1;
        System.out.printf("origin x,y: %d,%d\n", origin.x,origin.y);
        //each day width on graph
        day_w = calendar_w/31;

        System.out.println("day w: " + day_w);
        System.out.println("cal w: " + calendar_w);
        System.out.println("cal h: " + calendar_h);
        //populate data array with some workout data
        set_data();
        //populate workouts by date vector
        set_days();
        //set up exercises
        init_exercises();

        //add data to points vector
        //add_points(bench_press);
        //add_points(incline_dumbell);
        //add_points(cable_flyes);
        //proportion_graph();

    }

    void set_layout(int i)
    {
        switch(i)
        {
            case 0:
                System.out.println("set_layout case 0 choose_graph");
                inflate(getContext(), R.layout.activity_main_graph,this);//R.layout.choose_graph
                //layout = -1;
                break;
            case 1:
                System.out.println("set_layout case 1 sets_reps");
                inflate(getContext(),R.layout.frag_sets_reps,this);
                //layout = 1;
                break;
            case 2:
                System.out.println("set_layout case 2 max_weight");
                inflate(getContext(),R.layout.frag_max_weight ,this);//R.layout.max_weight
                //layout = 2;
                break;
        }
    }
    /**
    void init()
    {
        inflate(getContext(),R.layout.activity_main ,this);//R.layout.graph_view
        //getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        //width = displayMetrics.widthPixels;
        //height = displayMetrics.heightPixels;
    }
     */


    void set_canvas(Canvas canvas)
    {
        can = canvas;
    }

    Canvas get_canvas()
    {
        return can;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        set_canvas(canvas);
        System.out.println("onDraw()");
        //draw graph axes
        draw_axes(canvas);

        if(draw_graph)
        {
            //draw points on graph
            draw_marks2(canvas);
            draw_lines2(canvas);
            draw_legend(canvas);

            //draw orange data points over lines
            for(int i = 0; i < data.size(); i++)
            {
                for(int j = 0; j < data.get(i).points.size(); j++)
                {
                    //canvas.drawPoint(points.get(i).x,points.get(i).y,paint);
                    paint.setColor(Color.BLACK);
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawCircle(data.get(i).points.get(j).x, data.get(i).points.get(j).y,12,paint);
                    //paint.setStrokeWidth(10);
                    paint.setColor(Color.rgb(255,175,0));
                    canvas.drawCircle(data.get(i).points.get(j).x, data.get(i).points.get(j).y,10,paint);
                }

            }

           /**
            //old draw orange data points over lines
            for(int i = 0; i < data.size(); i++)
            {
                for(int j = 0; j < data.get(i).size(); j++)
                {
                    //canvas.drawPoint(points.get(i).x,points.get(i).y,paint);
                    paint.setColor(Color.BLACK);
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawCircle(data.get(i).get(j).x, data.get(i).get(j).y,12,paint);
                    //paint.setStrokeWidth(10);
                    paint.setColor(Color.rgb(255,175,0));
                    canvas.drawCircle(data.get(i).get(j).x, data.get(i).get(j).y,10,paint);
                }

            }
            */
        }

    }//end onDraw()

    //add exercises to exercise vector
    void init_exercises()
    {
        chest_exercises.add(bench_press);
        chest_exercises.add(incline_dumbell);
        chest_exercises.add(cable_flyes);

        back_exercises.add(lat_pulldown);
        back_exercises.add(t_bar_row);
        back_exercises.add(cable_row);
    }

    //array (chest exercises),get name(Bench press) and index of exercise(bench press) in exercises vector and pass to add_points
    void set_exercises(ArrayList<int[][]> array_l,String str,int index)
    {

        //Bench press, chest_exercises[0]
        add_points(str, array_l.get(index));
    }

    void add_points(String str,int [][] array)
    {
        ArrayList<Points> line = new ArrayList<>();

        Line the_line = new Line(str,array);
        data.add(the_line);

        //print out data
        for(int i = 0; i < data.size(); i++)
        {
            System.out.printf("%s:\n", data.get(i).name);
            data.get(i).print();
        }

    }

    //check if exercise is already in data vector (drawn on the graph)
    boolean try_add(String str)
    {
        for(int i = 0; i < data.size(); i++)
        {
            //if the name already exists
            if(str.equals(data.get(i).name))
            {
                return false;
            }
        }
        return true;
    }

    void remove(String str)
    {
        for(int i = 0; i < data.size(); i++)
        {
            //if the name already exists
            if(str.equals(data.get(i).name))
            {
                data.remove(i);
            }
        }
    }

    /**
    //add routines w/exercises
    void set_data2()
    {
        //add new chest routine March 1
        day.add(0,new Routine("chest"));
        //add chest exercises from march 1
        day.get(0).exercises.add(new Exercise("Bench Press",bench_press));
        day.get(0).exercises.add(new Exercise("Incline Dumbbell Press",incline_dumbell));
        day.get(0).exercises.add(new Exercise("Cable Flye",cable_flyes));
        day.add(1,new Routine("Back"));
        day.get(1).exercises.add(new Exercise("Lat Pulldown",lat_pulldown));
        day.get(1).exercises.add(new Exercise("T Bar Row",t_bar_row));
        day.get(1).exercises.add(new Exercise("Cable Row",cable_row));
        //routine.get(0).exercises.get(0).data.add()

    }
     */

    void set_days()
    {
        day.add(new Day("March 1"));
        Exercise e = new Exercise("Chest","Bench Press",bench_press_01);
        day.get(0).exercises.add(e);
        e = new Exercise("Chest","Incline Dumbbell Press",incline_dumbell_01);
        day.get(0).exercises.add(e);
        e = new Exercise("Shoulders","Dumbbell Press", dumbbell_press_01);
        day.get(0).exercises.add(e);
        //day.add(new Day("March"))

    }

    //set up chest data max weight by day
    void set_data()
    {
        //march 1, 95 lbs
        bench_press[0][0] = 1;//-1 to identify skip this index
        bench_press[0][1] = 55;//1 to identify the exercise
        //march 9, 95 lbs
        bench_press[1][0] = 9;
        bench_press[1][1] = 105;
        //march 15, 95 lbs
        bench_press[2][0] = 15;
        bench_press[2][1] = 125;
        //march 20, 95 lbs
        bench_press[3][0] = 20;
        bench_press[3][1] = 135;
        //march 28, 95 lbs
        bench_press[4][0] = 28;
        bench_press[4][1] = 225;
        //march 31, 95 lbs
        bench_press[5][0] = 31;
        bench_press[5][1] = 235;

        //each index is a set, 0,0 is lbs 0,1 is reps
        //set 1
        bench_press_01[0][0] = 95;
        bench_press_01[0][1] = 12;
        //set 2
        bench_press_01[1][0] = 115;
        bench_press_01[1][1] = 12;
        //set 3
        bench_press_01[2][0] = 135;
        bench_press_01[2][1] = 7;
        //set 4
        bench_press_01[3][0] = 145;
        bench_press_01[3][1] = 2;
        //set 5
        bench_press_01[4][0] = 125;
        bench_press_01[4][1] = 4;

        //dumbell incline data
        incline_dumbell[0][0] = 2;//2 to identify the exercise
        incline_dumbell[0][1] = 50;
        //
        incline_dumbell[1][0] = 9;
        incline_dumbell[1][1] = 55;
        //
        incline_dumbell[2][0] = 15;
        incline_dumbell[2][1] = 140;
        //
        incline_dumbell[3][0] = 20;
        incline_dumbell[3][1] = 60;
        //
        incline_dumbell[4][0] = 31;
        incline_dumbell[4][1] = 215;

        //set 1
        incline_dumbell_01[0][0] = 55;
        incline_dumbell_01[0][1] = 10;
        //set 2
        incline_dumbell_01[1][0] = 50;
        incline_dumbell_01[1][1] = 8;
        //set 3
        incline_dumbell_01[2][0] = 45;
        incline_dumbell_01[2][1] = 6;
        //set 4
        incline_dumbell_01[3][0] = 40;
        incline_dumbell_01[3][1] = 5;
        //set 5
        incline_dumbell_01[4][0] = 40;
        incline_dumbell_01[4][1] = 4;

        //pulley flyes data
        cable_flyes[0][0] = 5;//3 to identify
        cable_flyes[0][1] = 35;
        //
        cable_flyes[1][0] = 13;
        cable_flyes[1][1] = 45;
        //
        cable_flyes[2][0] = 20;
        cable_flyes[2][1] = 50;
        //
        cable_flyes[3][0] = 22;
        cable_flyes[3][1] = 55;
        //
        cable_flyes[4][0] = 27;
        cable_flyes[4][1] = 35;

        lat_pulldown[0][0] = 7;
        lat_pulldown[0][1] = 55;
        //
        lat_pulldown[1][0] = 14;
        lat_pulldown[1][1] = 65;
        //
        lat_pulldown[2][0] = 20;
        lat_pulldown[2][1] = 85;
        //
        lat_pulldown[3][0] = 27;
        lat_pulldown[3][1] = 90;

        t_bar_row[0][0] = 7;
        t_bar_row[0][1] = 75;
        //
        t_bar_row[1][0] = 14;
        t_bar_row[1][1] = 85;
        //
        t_bar_row[2][0] = 20;
        t_bar_row[2][1] = 55;
        //
        t_bar_row[3][0] = 27;
        t_bar_row[3][1] = 90;

        cable_row[0][0] = 7;
        cable_row[0][1] = 50;
        //
        cable_row[1][0] = 14;
        cable_row[1][1] = 60;
        //
        cable_row[2][0] = 20;
        cable_row[2][1] = 60;
        //
        cable_row[3][0] = 27;
        cable_row[3][1] = 70;

        //shoulders
        //set 1
        dumbbell_press_01[0][0] = 35;
        dumbbell_press_01[0][1] = 12;
        //set 2
        dumbbell_press_01[1][0] = 40;
        dumbbell_press_01[1][1] = 10;
        //set 2
        dumbbell_press_01[2][0] = 45;
        dumbbell_press_01[2][1] = 7;
        //set 2
        dumbbell_press_01[3][0] = 50;
        dumbbell_press_01[3][1] = 4;
        //set 2
        dumbbell_press_01[4][0] = 50;
        dumbbell_press_01[4][1] = 2;

    }



    void draw_marks2(Canvas canvas)
    {
        //draw y axis units
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(45);
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
        //draw y axis units weight in lbs
        for(int i = 0; i < data.size(); i++)//line1.size()
        {
            for(int j = 0; j < data.get(i).points.size(); j++)
            {
                //draw y axis notches
                canvas.drawLine(l_margin + 20, data.get(i).points.get(j).y,l_margin - 20, data.get(i).points.get(j).y,paint);//line1.get(i).y
                canvas.drawText(String.valueOf(data.get(i).points.get(j).lbs),l_margin - 100, data.get(i).points.get(j).y + 15,paint);//line1.get(i).lbs

                //
                //draw x axis notches
                canvas.drawLine(data.get(i).points.get(j).x,height - 1000, data.get(i).points.get(j).x,height - 1000 + 20,paint);//line1.get(i).x
                paint.setTextSize(45);
                //next to point

                //on x axis
                canvas.drawText(String.valueOf(data.get(i).points.get(j).day), data.get(i).points.get(j).x - 10,height - 1000 + 75,paint);//bench_press[i][0] // line1.get(i).day
            }

        }

        //draw x axis label
        paint.setColor(Color.GREEN);
        //size of month name text
        int month_size = 150;
        double x_label = (calendar_w/2.0) + l_margin - month_size; // - size// cal_w- l_margin
        System.out.println("cal w - lmargin" + (calendar_w - l_margin));

        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(25);
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
        paint.setTextSize(70);
        canvas.drawText("March", (float)x_label,height - 1000 + 160,paint);

        //draw outline
        paint.setColor(Color.BLACK);
        paint.setTextSize(70);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawText("March", (float)x_label,height - 1000 + 160,paint);


        //draw y axis label
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        int lbs_size = 75;
        double y_label = ((calendar_h/2.0) + top_margin) + lbs_size;
        paint.setTextSize(70);
        canvas.drawText("LBS", 50,origin.y + 65,paint);//(float)y_label

        //draw label outline
        paint.setColor(Color.BLACK);
        paint.setTextSize(70);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawText("LBS", 50,origin.y + 65,paint);

    }

    void draw_lines2(Canvas canvas)
    {

        paint.setStrokeWidth(10);
        System.out.println("drawLines2() data.size() = " + data.size());
        for(int i = 0; i < data.size(); i++)//line1.size() - 1 //one less point
        {
            paint.setColor(switch_color(i));
            for(int j = 0; j < data.get(i).points.size() - 1; j++)
            {
                canvas.drawLine(data.get(i).points.get(j).x, data.get(i).points.get(j).y, data.get(i).points.get(j + 1).x, data.get(i).points.get(j + 1).y,paint); //line1.get(i).x //last point line1.get(i + 1).x
            }

        }

    }

    void proportion_graph2()
    {
        //get difference between max and min
        int span = find_span2();
        System.out.println("span = " + span);
        //mult every lb gained by this modifier, should be a double
        int mod = calendar_h/span;
        System.out.println("mod = " + mod);

        int spacer = 25;
        //spread the x coordinates out
        for(int i = 0; i < data.size(); i++)
        {
            for(int j = 0; j < data.get(i).points.size(); j++)
            {
                data.get(i).points.get(j).x = (data.get(i).points.get(j).day * day_w) + l_margin;//data2.get(i).points.get(j).x
                //spread the y coordinates out, set the difference in lbs gained from min amount of weight
                data.get(i).points.get(j).diff = data.get(i).points.get(j).lbs - min;//data2.get(i).points.get(j).y
                data.get(i).points.get(j).y = (origin.y - spacer) - (data.get(i).points.get(j).diff * mod);
            }

        }

    }//end proportion graph

    void draw_axes(Canvas canvas)
    {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        //draw y axis
        canvas.drawLine(l_margin,top_margin,l_margin,height - 1000,paint);
        //draw x axis
        canvas.drawLine(l_margin,height - 1000 ,width - r_margin,height - 1000,paint);
        paint.setColor(Color.RED);
        canvas.drawPoint(origin.x,origin.y,paint);

    }

    int find_span2()
    {
        max = -1;
        min = 1000;
        //check 1st line data
        for(int i = 0; i < data.size(); i++)
        {
            for(int j = 0; j < data.get(i).points.size(); j++)//data2.get(i).points.get(j).x
            {
                if(data.get(i).points.get(j).lbs > max)//data.get(i).get(j).y > max
                {
                    max = data.get(i).points.get(j).lbs;//max = data.get(i).get(j).y;
                }
            }

        }

        for(int i = 0; i < data.size(); i++)
        {
            for(int j = 0; j < data.get(i).points.size(); j++)
            {
                if(data.get(i).points.get(j).lbs < min)
                {
                    min = data.get(i).points.get(j).lbs;
                }
            }

        }

        System.out.printf("min,max: %d,%d\n", min,max);

        return max - min;
    }

    void set_legend_y(float y)
    {

        legend_y = y;
        System.out.println("legend y = " + legend_y);

    }
    void draw_legend(Canvas canvas)
    {
        //draw y axis units
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(45);
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);

        int spacer = 45;

        for(int i = 0; i < data.size(); i++)
        {
            paint.setColor(switch_color(i));
            canvas.drawText(data.get(i).name,50,(height - 420) + (i * spacer),paint);
            //canvas.drawLine();

        }
    }

    int switch_color(int i)
    {
        switch(i)
        {
            case 0:
                //paint.setColor(Color.BLUE);
                return Color.BLUE;
                //break;
            case 1:
                //paint.setColor(Color.RED);
                return Color.RED;
                //break;
            case 2:
                //paint.setColor(Color.MAGENTA);
                return Color.MAGENTA;
                //break;
            case 3:
                //paint.setColor(Color.YELLOW);
                return Color.GREEN;
                //break;
            case 4:
                //paint.setColor(Color.LTGRAY);
                return Color.DKGRAY;
                //break;

        }
        return Color.GREEN;
    }

}//end  class Graph