package com.example.acer.iicpmobile;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Acer on 2/16/2017.
 */

public class BookingActivity extends AppCompatActivity {
    private static final String ROOM_NO = "com.example.acer.iicpmobile.Fragment.ChildFragment.ROOM_NO";
    private TextView txtRoomNo;
    private TextView txtBookDate;
    private Spinner ddlTimeSlots;
    private Button btnBook;
    private AlertDialog mAlertDialog;
    private boolean mDialogShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        txtRoomNo = (TextView)findViewById(R.id.txtRoomNo);
        txtBookDate = (TextView)findViewById(R.id.txtBookDate);
        ddlTimeSlots = (Spinner) findViewById(R.id.ddlTimeSlots);
        btnBook = (Button) findViewById(R.id.btnBook);

        if (savedInstanceState != null) {
            ddlTimeSlots.setSelection(savedInstanceState.getInt("ddlTimeSlots", 0));
            mDialogShown = savedInstanceState.getBoolean("mDialogShown", false);
        }

        if(mDialogShown) createDialog();

        txtRoomNo.setText("Discussion Room " + Integer.toString(getIntent().getIntExtra(ROOM_NO, 0)));
        txtBookDate.setText("Date: " + DateFormat.getDateInstance().format(new Date()));
        btnBook.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                mDialogShown = true;
                createDialog();
            }
        });
    }

    private void createDialog() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(BookingActivity.this)
                    .setMessage(R.string.confirmBooking_message)
                    .setPositiveButton(R.string.btnBook_text, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)    {
                            setResult(RESULT_OK, null);
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.btnCancel_text, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)    {
                            mDialogShown = false;
                            dialog.dismiss();
                        }
                    });
        mAlertDialog = mBuilder.create();
        mAlertDialog.show();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("ddlTimeSlots", ddlTimeSlots.getSelectedItemPosition());
        savedInstanceState.putBoolean("mDialogShown", mDialogShown);
    }
}
