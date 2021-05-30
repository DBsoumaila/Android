package com.example.bricole.view;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bricole.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {
        PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        pieChart = (PieChart) findViewById(R.id.pieChart);
        ArrayList<PieEntry> admins= new ArrayList<>();
        admins.add( new PieEntry(100, "2016"));
        admins.add( new PieEntry(500, "2017"));
        admins.add( new PieEntry(135, "2018"));
        admins.add( new PieEntry(260, "2019"));
        admins.add( new PieEntry(10, "2020"));
        admins.add( new PieEntry(800, "2021"));
        admins.add( new PieEntry(750, "2022"));
        admins.add( new PieEntry(790, "2023"));
        admins.add( new PieEntry(900, "2024"));
        PieDataSet pieDataSet= new PieDataSet(admins,"Adminis");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData= new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Admins");
        pieChart.animate();



    }

}