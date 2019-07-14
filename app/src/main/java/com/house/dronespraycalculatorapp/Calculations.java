package com.house.dronespraycalculatorapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.jellytogglebutton.JellyToggleButton;
import com.nightonke.jellytogglebutton.State;

import java.math.BigDecimal;

public class Calculations extends AppCompatActivity {
    private EditText lengthOfAcreEdit, WidthOfAcreEdit, sprayingWidthEdit, speedEdit, dischargeEdit;
    private float lengthOfAcre, widthOfAcre, sprayingWidth, speed, discharge;
    private Float feetToMeter = 0.3048f;
    static Float totalDistance;
    static Float totalNumRows;
    static Float totalTime;
    static Float dischOneRow;
    static Float timeForOneRow;
    JellyToggleButton btToggle;
    String unit = "Meter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculations);
        initGUI();
    }


    private void initGUI() {
        lengthOfAcreEdit = (EditText) findViewById(R.id.etLength);
        WidthOfAcreEdit = (EditText) findViewById(R.id.etWidth);
        sprayingWidthEdit = (EditText) findViewById(R.id.etSpray);
        speedEdit = (EditText) findViewById(R.id.etSpeed);
        dischargeEdit = (EditText) findViewById(R.id.etDischarge);
        btToggle = (JellyToggleButton) findViewById(R.id.btToggle);
        btToggle.setOnStateChangeListener(new JellyToggleButton.OnStateChangeListener() {
            @Override
            public void onStateChange(float process, State state, JellyToggleButton jbt) {
                if (state.equals(State.LEFT)) {
                    unit = "Meter";
                    lengthOfAcreEdit.setHint("Length of Land (meter)");
                    WidthOfAcreEdit.setHint("Width of Land (meter)");
                    sprayingWidthEdit.setHint("Spray Width (meter)");
                }
                if (state.equals(State.RIGHT)) {
                    unit = "Feet";
                    lengthOfAcreEdit.setHint("Length of Land (feet)");
                    WidthOfAcreEdit.setHint("Width of Land (feet)");
                    sprayingWidthEdit.setHint("Spray Width (feet)");
                }
            }
        });
    }

    public void btCalculateClicked(View view) {
        if (validate()) {
            setVaues();
            calculateTotalRows();
            calculateTotalDistance();
            calcultateTotalTime();
            calacuteDischforOneRow();
            calculateTimeforOneRow();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(Calculations.this, Result.class));
                }
            }, 2000);
        }
    }

    private void setVaues() {
        if (unit.matches("Feet")) {
            lengthOfAcre = (Float.valueOf(lengthOfAcreEdit.getText().toString())) * feetToMeter;
            widthOfAcre = (Float.valueOf(WidthOfAcreEdit.getText().toString())) * feetToMeter;
            sprayingWidth = (Float.valueOf(sprayingWidthEdit.getText().toString())) * feetToMeter;
            speed = Float.valueOf(speedEdit.getText().toString());
            discharge = Float.valueOf(dischargeEdit.getText().toString());
        } else {
            lengthOfAcre = Float.valueOf(lengthOfAcreEdit.getText().toString());
            widthOfAcre = Float.valueOf(WidthOfAcreEdit.getText().toString());
            sprayingWidth = Float.valueOf(sprayingWidthEdit.getText().toString());
            speed = Float.valueOf(speedEdit.getText().toString());
            discharge = Float.valueOf(dischargeEdit.getText().toString());
        }
    }

    private void calculateTimeforOneRow() {
        timeForOneRow = round((totalTime / totalNumRows) / 60, 2);
    }

    private void calacuteDischforOneRow() {
        dischOneRow = round(discharge / totalNumRows, 2);
    }

    private void calcultateTotalTime() {
        totalTime = round((totalDistance / speed) / 60, 2);
    }

    private void calculateTotalDistance() {
        totalDistance = round((lengthOfAcre * widthOfAcre) / sprayingWidth, 2);
    }

    private void calculateTotalRows() {
        totalNumRows = round(lengthOfAcre / sprayingWidth, 2); //Answer will always be in meter
    }

    public static float round(float number, int decimalPlace) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public Boolean validate() {
        if (lengthOfAcreEdit.getText().toString().trim().equalsIgnoreCase("")) {
            lengthOfAcreEdit.setError("Fill this field");
            return false;
        }
        if (WidthOfAcreEdit.getText().toString().trim().equalsIgnoreCase("")) {
            WidthOfAcreEdit.setError("Fill this field");
            return false;
        }
        if (sprayingWidthEdit.getText().toString().trim().equalsIgnoreCase("")) {
            sprayingWidthEdit.setError("Fill this field");
            return false;
        }
        if (speedEdit.getText().toString().trim().equalsIgnoreCase("")) {
            speedEdit.setError("Fill this field");
            return false;
        }
        if (dischargeEdit.getText().toString().trim().equalsIgnoreCase("")) {
            dischargeEdit.setError("Fill this field");
            return false;
        }
        return true;
    }

}
