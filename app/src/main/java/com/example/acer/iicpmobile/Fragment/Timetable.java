package com.example.acer.iicpmobile.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.acer.iicpmobile.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Acer on 2/27/2017.
 */

public class Timetable extends Fragment {
    private TextView txtDayOfWeek;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.timetable, container, false);

        txtDayOfWeek = (TextView) v.findViewById(R.id.txtDayOfWeek);
        txtDayOfWeek.setText(new SimpleDateFormat("EEEE").format(new Date()));

        return v;
    }
}