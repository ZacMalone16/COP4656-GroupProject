package edu.fsu.cs.groupproject.graphs;/*

package edu.fsu.cs.graph;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.Nullable;

public class ChooseGraph extends Activity
{
    Graph graph;
    DisplayMetrics displayMetrics = new DisplayMetrics();
    //phone screen height
    int height;
    //phone screen width
    int width;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
        System.out.println("width: " + width);
        System.out.println("height: " + height);

        graph = new Graph(this,width,height,0);//

        setContentView(graph);
        graph.setBackgroundColor(Color.WHITE);

        Spinner choose_graph = (Spinner) findViewById(R.id.spinner);

        choose_graph.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                switch(choose_graph.getSelectedItemPosition())
                {
                    case 0:
                        System.out.println("case 0 selected in spinner");
                        break;
                    case 1:
                        graph = new Graph(getBaseContext(), width, height, 1);
                        setContentView(graph);
                        System.out.println("layout spinner = " + choose_graph.getSelectedItemPosition());
                        layout = 1;
                        //graph.set_layout(1);
                        break;
                    case 2:
                        System.out.println("layout spinner = " + choose_graph.getSelectedItemPosition());
                        //Graph g2 = new Graph(getApplicationContext(),width,height,2);
                        graph = new Graph(getApplicationContext(), width, height, 2);
                        graph.setBackgroundColor(Color.WHITE);
                        //graph.set_layout(2);
                        setContentView(graph);
                        layout = 2;

                        break;
                    default:
                        System.out.println("layout spinner = " + choose_graph.getSelectedItemPosition());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
    }
}


 */