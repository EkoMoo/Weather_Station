package com.weatherstation;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

/**
 * Created by RadenMas on 24/04/2022.
 */
public class ShowChart {

    public void chart(LineChart lineChart, ArrayList<Entry> values, int min, int max){

        LineDataSet lineDataSet = new LineDataSet(null, null);
        lineDataSet.setValues(values);

        ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();

        iLineDataSets.add(lineDataSet);
        LineData lineData = new LineData(iLineDataSets);

        YAxis yAxisL = lineChart.getAxis(YAxis.AxisDependency.LEFT);
        yAxisL.setDrawGridLines(false);
        yAxisL.setDrawLabels(true);
        yAxisL.setAxisMinimum(min);
        yAxisL.setAxisMaximum(max);

        lineChart.setData(lineData);
        lineChart.getAxisRight().setEnabled(false);
    }
}
