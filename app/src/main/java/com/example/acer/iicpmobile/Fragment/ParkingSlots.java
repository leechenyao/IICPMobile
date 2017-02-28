package com.example.acer.iicpmobile.Fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.acer.iicpmobile.R;

import java.util.Random;

/**
 * Created by Acer on 2/15/2017.
 */

public class ParkingSlots extends Fragment {
    private Spinner ddlFloor;
    private ProgressBar pbSlots;
    private TextView txtParkSlot;
    private TextView txtparkedCount;
    private int row = 10;
    private int column = 14;
    private int unparkedCount = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.parking_slot_list, container, false);

        ddlFloor = (Spinner) v.findViewById(R.id.ddlFloor);
        pbSlots = (ProgressBar) v.findViewById(R.id.pbSlots);
        txtParkSlot = (TextView) v.findViewById(R.id.txtParkSlot);
        txtparkedCount = (TextView) v.findViewById(R.id.txtparkedCount);

        ddlFloor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                generateX();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        generateX();

        return v;
    }

    // Generate color X
    private void generateX() {
        SpannableString spannable = new SpannableString("X");
        txtParkSlot.setText("");
        txtParkSlot.append("\n");
        unparkedCount = 0;

        for(int i=0; i<row; i++) {
            for(int j=0; j<column; j++) {
                Random rand = new Random();
                if((rand.nextInt(10)) > 7) {// Generate random number from 0 - 9, if 8/9 set Green,
                    spannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.iicpGreen)), 0, 1, 0);
                    unparkedCount++;
                }
                else { //else set Red
                    spannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.iicpRed)), 0, 1, 0);
                }
                txtParkSlot.append(spannable);

                if(j % 2 == 0) txtParkSlot.append("  ");
            }
            txtParkSlot.append("\n");
        }

        pbSlots.post(new Runnable() {
            @Override
            public void run() {
                pbSlots.setProgress(unparkedCount);
                txtparkedCount.setText(Integer.toString(unparkedCount) + " LEFT");
            }
        });
    }
}
