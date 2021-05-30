package com.example.bricole.view;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bricole.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {
    BarChart barChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        barChat= (BarChart) findViewById(R.id.BarChart);
        ArrayList<BarEntry> visitors= new ArrayList<>();
        visitors.add(new BarEntry(2014, 100));
        visitors.add(new BarEntry(2015, 156));
        visitors.add(new BarEntry(2016, 200));
        visitors.add(new BarEntry(2017, 17));
        visitors.add(new BarEntry(2018, 49));
        visitors.add(new BarEntry(2019, 300));
        visitors.add(new BarEntry(2020, 380));
        visitors.add(new BarEntry(2021, 500));

        BarDataSet barDataSet= new BarDataSet(visitors,"Admins");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData= new BarData(barDataSet);
        barChat.setFitBars(true);
        barChat.setData(barData);
        barChat.getDescription().setText("Bar Chart Admin");
        barChat.animateY(2000);





    }
}