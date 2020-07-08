package com.example.bonvoyaide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.Cartesian;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.HoverMode;
import com.anychart.anychart.Pie;
import com.anychart.anychart.Position;
import com.anychart.anychart.TooltipPositionMode;
import com.anychart.anychart.ValueDataEntry;

import java.util.ArrayList;
import java.util.List;

public class Analysis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        AnyChartView pieChart = findViewById(R.id.any_chart_view);
        AnyChartView lineChart = findViewById(R.id.line_chart);
        AnyChartView barChart = findViewById(R.id.column_chart);
        AnyChartView niceChart = findViewById(R.id.any_chart_view);

        Pie pie = AnyChart.pie();

        List<DataEntry> dataPie = new ArrayList<>();
        dataPie.add(new ValueDataEntry("Positive", 10000));
        dataPie.add(new ValueDataEntry("Negative", 3000));
        dataPie.add(new ValueDataEntry("Neutral", 1000));

        pie.setData(dataPie);
        pieChart.setChart(pie);


    }


}
