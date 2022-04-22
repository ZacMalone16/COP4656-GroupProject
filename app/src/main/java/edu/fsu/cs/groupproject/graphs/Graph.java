package edu.fsu.cs.groupproject.graphs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;
import java.util.ArrayList;
import edu.fsu.cs.groupproject.R;


public class Graph extends FrameLayout
{
    static Canvas can;
    ArrayList<Line> data = new ArrayList<>();
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

    //int max_weight;
    //int sets_reps;
    //int layout;
    int width;
    int height;
    int calendar_w;
    int calendar_h;
    int day_w;
    //int y_axis_h;
    int r_margin;
    int l_margin;
    int top_margin;
    float min_y;
    float max_y;
    float min_x;
    float max_x;
    float zero_y;
    float legend_y;
    boolean draw_graph;
    boolean max_graph;
    int g_height = 1040;
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

        //init variables
        draw_graph = false;
        width = w;
        height = h;
        r_margin = 125;
        l_margin = 225;
        top_margin = 125;
        calendar_w = (w - r_margin) - l_margin;
        calendar_h = (h - g_height) - top_margin;
        origin.x = l_margin;
        origin.y = height - g_height;
        min_y = 1000;
        max_y = -1000;
        min_x = 1000;
        max_x = -1000;
        //each day width on graph
        day_w = calendar_w/31;

    }

    void set_layout(int i)
    {
        switch(i)
        {
            case 0:
                inflate(getContext(), R.layout.activity_main_graph,this);//R.layout.choose_graph
                break;
            case 1:
                inflate(getContext(),R.layout.frag_sets_reps,this);
                break;
            case 2:
                inflate(getContext(),R.layout.frag_max_weight ,this);//R.layout.max_weight
                break;
        }
    }

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

        //draw graph axes
        draw_axes(canvas);

        if(draw_graph)
        {
            //draw points on graph
            if(max_graph)//switch axes since reps is smaller range put on x
            {
                draw_marks2("March", "LBS",canvas);
                draw_legend("max",50,330,canvas);

            }
            else
            {
                draw_marks2("Reps","LBS",canvas);
                draw_legend("reps",350,740,canvas);
            }

            //draw lines between points on graph
            draw_lines2(canvas);

            //draw orange data points over lines
            for(int i = 0; i < data.size(); i++)
            {
                for(int j = 0; j < data.get(i).points.size(); j++)
                {
                    paint.setColor(Color.BLACK);
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawCircle(data.get(i).points.get(j).x, data.get(i).points.get(j).y,12,paint);
                    paint.setColor(Color.rgb(255,175,0));
                    canvas.drawCircle(data.get(i).points.get(j).x, data.get(i).points.get(j).y,10,paint);
                }

            }

        }

    }//end onDraw()

    //set exercises by passing in arraylist of points, name of line on graph, index of line in array list
    void set_exercises(ArrayList<int[][]> array_l,String str,int index)
    {

        add_points(str, array_l.get(index));
    }

    void add_points(String str,int [][] array)
    {
        //make new line for graph
        Line the_line = new Line(str,array);
        data.add(the_line);

        /*
        //print out data
        for(int i = 0; i < data.size(); i++)
        {
            System.out.printf("%s:\n", data.get(i).name);
            data.get(i).print();
        }
         */

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

    //remove line from graph
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

    void draw_marks2(String x, String y, Canvas canvas)
    {
        //draw y axis units
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(45);
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
        //draw axis units
        for(int i = 0; i < data.size(); i++)//line1.size()//
        {
            for(int j = 0; j < data.get(i).points.size(); j++)//stagger notches and units up and down to fit
            {
                //draw y axis notches
                canvas.drawLine(l_margin + 20, data.get(i).points.get(j).y,l_margin - 20, data.get(i).points.get(j).y,paint);
                canvas.drawText(String.valueOf((int)data.get(i).points.get(j).lbs),l_margin - 100, data.get(i).points.get(j).y + 15,paint);
                //draw x axis notches
                canvas.drawLine(data.get(i).points.get(j).x,height - g_height, data.get(i).points.get(j).x,height - g_height + 20,paint);
                paint.setTextSize(45);
                canvas.drawText(String.valueOf((int)data.get(i).points.get(j).day), data.get(i).points.get(j).x - 10,height - g_height + 75,paint);

            }

        }

        //draw x axis label
        paint.setColor(Color.GREEN);
        //size of month name text
        int month_size = 150;
        double x_label = (calendar_w/2.0) + l_margin - month_size;
        //draw x axis label
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(25);
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
        paint.setTextSize(70);
        canvas.drawText(x, (float)x_label,height - g_height + 160,paint);

        //draw x label outline
        paint.setColor(Color.BLACK);
        paint.setTextSize(70);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawText(x, (float)x_label,height - g_height + 160,paint);

        //draw y axis label
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        int lbs_size = 75;
        double y_label = ((calendar_h/2.0) + top_margin) + lbs_size;
        paint.setTextSize(70);
        canvas.drawText(y, 50,origin.y + 65,paint);//(float)y_label

        //draw y label outline
        paint.setColor(Color.BLACK);
        paint.setTextSize(70);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawText(y, 50,origin.y + 65,paint);

    }

    //draw lines between points on graph
    void draw_lines2(Canvas canvas)
    {

        paint.setStrokeWidth(10);
        for(int i = 0; i < data.size(); i++)
        {
            paint.setColor(switch_color(i));
            for(int j = 0; j < data.get(i).points.size() - 1; j++)
            {
                canvas.drawLine(data.get(i).points.get(j).x, data.get(i).points.get(j).y, data.get(i).points.get(j + 1).x, data.get(i).points.get(j + 1).y,paint);
            }

        }

    }

    //proportion graph dynamically
    void proportion_graph2()
    {
        //get difference between max and min
        float span_x = find_span_x();
        float span_y = find_span_y();
        //mult every lb gained by this modifier
        float mod_y = calendar_h/span_y;
        float mod_x = calendar_w/span_x;

        int spacer = 25;
        zero_y = (origin.y - spacer) - ((0 - min_y) * mod_y);

        //spread the coordinates out,set the difference in lbs gained from min amount of weight
        for(int i = 0; i < data.size(); i++)
        {
            for(int j = 0; j < data.get(i).points.size(); j++)
            {
                //x coordinates
                data.get(i).points.get(j).diff_x = data.get(i).points.get(j).day - min_x;
                data.get(i).points.get(j).x = (data.get(i).points.get(j).diff_x * mod_x) + l_margin;
                //y coordinates
                data.get(i).points.get(j).diff_y = data.get(i).points.get(j).lbs - min_y;
                data.get(i).points.get(j).y = (origin.y - spacer) - (data.get(i).points.get(j).diff_y * mod_y);
            }

        }

    }//end proportion graph

    //draw the axes lines
    void draw_axes(Canvas canvas)
    {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        //draw y axis
        canvas.drawLine(l_margin,top_margin - 22,l_margin,height - g_height,paint);
        //draw x axis
        canvas.drawLine(l_margin,height - g_height ,width - r_margin,height - g_height,paint);
        paint.setColor(Color.RED);
        canvas.drawPoint(origin.x,origin.y,paint);

    }

    //find span in y axis data
    float find_span_y()
    {
        max_y = -1000;
        min_y = 1000;
        //check 1st line data
        for(int i = 0; i < data.size(); i++)
        {
            //find max
            for(int j = 0; j < data.get(i).points.size(); j++)
            {
                if(data.get(i).points.get(j).lbs > max_y)
                {
                    max_y = data.get(i).points.get(j).lbs;
                }
            }

        }

        //find min
        for(int i = 0; i < data.size(); i++)
        {
            for(int j = 0; j < data.get(i).points.size(); j++)
            {
                if(data.get(i).points.get(j).lbs < min_y)
                {
                    min_y = data.get(i).points.get(j).lbs;
                }
            }

        }

        //return the difference
        return max_y - min_y;
    }

    //find span in x axis data
    float find_span_x()
    {
        max_x = -1000;
        min_x = 1000;

        //find max
        for(int i = 0; i < data.size(); i++)
        {
            for(int j = 0; j < data.get(i).points.size(); j++)//data2.get(i).points.get(j).x
            {
                if(data.get(i).points.get(j).day > max_x)//data.get(i).get(j).y > max
                {
                    max_x = data.get(i).points.get(j).day;//max = data.get(i).get(j).y;
                }
            }

        }

        //find min
        for(int i = 0; i < data.size(); i++)
        {
            for(int j = 0; j < data.get(i).points.size(); j++)
            {
                if(data.get(i).points.get(j).day < min_x)
                {
                    min_x = data.get(i).points.get(j).day;
                }
            }

        }
        //return difference
        return max_x - min_x;
    }

    //draw the color coded legend below the graph
    void draw_legend(String type,int x_val, int y_val,Canvas canvas)
    {
        //draw legend line name with color
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(45);
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);

        int spacer = 45;

        //max weight graph
        if(type.equals("max"))
        {
            //if 6 lines or less put in one column
            if(data.size() <= 6)
            {
                for(int i = 0; i < data.size(); i++)
                {
                    paint.setColor(switch_color(i));
                    canvas.drawText(data.get(i).name,x_val,(height - y_val) + (i * spacer),paint);
                }
            }
            //if 12 lines of less put in two columns
            else if(data.size() <= 12)
            {
                for(int i = 0; i < 6; i++)
                {
                    paint.setColor(switch_color(i));
                    canvas.drawText(data.get(i).name,x_val,(height - y_val) + (i * spacer),paint);
                }

                for(int i = 6; i < data.size(); i++)
                {
                    paint.setColor(switch_color(i));
                    canvas.drawText(data.get(i).name,x_val + 500,(height - y_val) + ((i - 6) * spacer),paint);
                }
            }
            //if more than 12 lines
            else if(data.size() <= 24)
            {
                for(int i = 0; i < 6; i++)
                {
                    paint.setColor(switch_color(i));
                    canvas.drawText(data.get(i).name,x_val,(height - y_val) + (i * spacer),paint);
                }

                for(int i = 6; i < 12; i++)
                {
                    paint.setColor(switch_color(i));
                    canvas.drawText(data.get(i).name,x_val + 500,(height - y_val) + ((i - 6) * spacer),paint);
                }

                for(int i = 12; i < data.size(); i++)
                {
                    paint.setColor(switch_color(i));
                    canvas.drawText(data.get(i).name,x_val + 500,(height - y_val) + ((i - 6) * spacer),paint);
                }



            }

        }
        //type == sets,reps, and weight graph
        else
        {
            //put in one column
            for(int i = 0; i < data.size(); i++)
            {
                paint.setColor(switch_color(i));
                //canvas.drawText(data.get(i).name,50,(height - val) + (i * spacer),paint);//
                canvas.drawText(data.get(i).name,x_val,(height - y_val) + (i * spacer),paint);//

            }
        }


    }

    //alternate colors for lines and legend on the graph
    int switch_color(int i)
    {
        switch(i)
        {
            case 0:
                return Color.BLUE;
            case 1:
                return Color.RED;
            case 2:
                return Color.MAGENTA;
            case 3:
                return Color.GREEN;
            case 4:
                return Color.DKGRAY;
            case 5:
                return Color.CYAN;
            case 6:
                return Color.rgb(128,128,128);
            case 7:
                return Color.rgb(128,0,0);
            case 8:
                return Color.rgb(0,0,128);
            case 9:
                return Color.rgb(128,128,0);
            case 10:
                return Color.rgb(0,128,128);
            case 11:
                return Color.BLUE;
            case 12:
                return Color.RED;
            case 13:
                return Color.MAGENTA;
            case 14:
                return Color.GREEN;
            case 15:
                return Color.DKGRAY;
            case 16:
                return Color.CYAN;

        }
        //add return green color if more lines than 17
        return Color.GREEN;
    }

}//end  class Graph