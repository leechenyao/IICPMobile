package com.example.acer.iicpmobile.Fragment.ChildFragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.acer.iicpmobile.R;
import com.example.acer.iicpmobile.BookingActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Acer on 2/15/2017.
 */

public class DiscussionRooms extends Fragment {
    private static final String ROOM_NO = "com.example.acer.iicpmobile.Fragment.ChildFragment.ROOM_NO";
    private static final int REQUEST_BOOK = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.discussion_room_list, container, false);

        ListView lvDiscussionRoom = (ListView)v.findViewById(R.id.lvDiscussionRoom);
        lvDiscussionRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getContext(), BookingActivity.class);
                i.putExtra(ROOM_NO, position + 1);
                startActivityForResult(i, REQUEST_BOOK);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_BOOK) {
            if (resultCode == RESULT_OK) {
                new AlertDialog.Builder(getContext())
                    .setTitle(R.string.doneBooking_title)
                    .setMessage(R.string.doneBooking_message)
                    .setNegativeButton(R.string.btnOK_text, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
            }
        }
    }
}
