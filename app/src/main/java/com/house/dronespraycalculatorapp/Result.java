package com.house.dronespraycalculatorapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    TextView totalRows;
    TextView totalDistance;
    TextView totalTime;
    TextView dischagreOne;
    TextView timeOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initGUI();
        setValues();
    }

    private void initGUI() {
        totalRows = (TextView) findViewById(R.id.tvTotalRowsValue);
        totalDistance = (TextView) findViewById(R.id.tvTotalDistantValue);
        totalTime = (TextView) findViewById(R.id.tvTotalTimeValue);
        dischagreOne = (TextView) findViewById(R.id.tvDischargeOneValue);
        timeOne = (TextView) findViewById(R.id.tvTimeOneValue);
    }

    private void setValues() {
        totalRows.setText(Float.toString(Calculations.totalNumRows));
        totalDistance.setText(Float.toString(Calculations.totalDistance));
        totalTime.setText(Float.toString(Calculations.totalTime));
        dischagreOne.setText(Float.toString(Calculations.dischOneRow));
        timeOne.setText(Float.toString(Calculations.timeForOneRow));
    }

}

