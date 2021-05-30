package com.example.bricole.view;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bricole.R;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class RadarChartActivity extends AppCompatActivity {

    RadarChart radarChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_chart);
        radarChart= (RadarChart) findViewById(R.id.radarChart);
        ArrayList<RadarEntry> adminsForFirstVisit= new ArrayList<>();
        adminsForFirstVisit.add(new RadarEntry(200));
        adminsForFirstVisit.add(new RadarEntry(300));
        adminsForFirstVisit.add(new RadarEntry(100));
        adminsForFirstVisit.add(new RadarEntry(400));
        adminsForFirstVisit.add(new RadarEntry(260));
        adminsForFirstVisit.add(new RadarEntry(500));
        adminsForFirstVisit.add(new RadarEntry(600));

        RadarDataSet radarDataSetForfistVisit= new RadarDataSet(adminsForFirstVisit,"Admins");
        radarDataSetForfistVisit.setColor(Color.RED);
        radarDataSetForfistVisit.setLineWidth(2f);
        radarDataSetForfistVisit.setValueTextColor(Color.RED);
        radarDataSetForfistVisit.setValueTextSize(14f);

        RadarData radarData= new RadarData();
        radarData.addDataSet(radarDataSetForfistVisit);
        String[] labels= {"2015","2016","2017","2018","2019","2020","2021"};

        XAxis xAxis= radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        radarChart.setData(radarData);

    }
}