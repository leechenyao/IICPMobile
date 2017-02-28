package com.example.acer.iicpmobile.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.iicpmobile.LoginActivity;
import com.example.acer.iicpmobile.PictureUtils;
import com.example.acer.iicpmobile.R;
import com.example.acer.iicpmobile.SaveSharedPreference;
import com.example.acer.iicpmobile.database.IICPDataBaseAdapter;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Acer on 2/16/2017.
 */

public class Profile extends Fragment {
    private TextView txtName;
    private TextView txtCourse;
    private TextView txtContacts;
    private ImageButton btnCamera;
    private ImageView imgProfile;
    private Button btnLogout;

    IICPDataBaseAdapter mIICPDataBaseAdapter;

    private static final int REQUEST_PHOTO= 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile, container, false);

        imgProfile = (ImageView) v.findViewById(R.id.imgProfile);
        btnCamera = (ImageButton) v.findViewById(R.id.btnCamera);
        txtName = (TextView) v.findViewById(R.id.txtName);
        txtCourse = (TextView) v.findViewById(R.id.txtCourse);
        txtContacts = (TextView) v.findViewById(R.id.txtContacts);
        btnLogout = (Button)v.findViewById(R.id.btnLogout);

        mIICPDataBaseAdapter = new IICPDataBaseAdapter(getContext());
        mIICPDataBaseAdapter = mIICPDataBaseAdapter.Open();
        Cursor cursor = mIICPDataBaseAdapter.SelectAll("student", "student_id=?", new String[]{SaveSharedPreference.getStudentId(getContext())});
        if (cursor != null ) {
            cursor.moveToFirst();
            txtName.setText(cursor.getString(cursor.getColumnIndex("name")));
            txtCourse.setText(cursor.getString(cursor.getColumnIndex("school")) + "\n" + cursor.getString(cursor.getColumnIndex("course")) + "\nSemester " + cursor.getString(cursor.getColumnIndex("semester")));
            txtContacts.setText(cursor.getString(cursor.getColumnIndex("phone_no")) + "\n" + cursor.getString(cursor.getColumnIndex("student_id")) + "@iicp.com");
        }
        mIICPDataBaseAdapter.Close();

        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean canTakePhoto = getPhotoFile() != null && captureImage.resolveActivity(getActivity().getPackageManager()) != null;

        if (canTakePhoto) {
            Uri uri = Uri.fromFile(getPhotoFile());
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }

        btnCamera.setEnabled(canTakePhoto);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });
        updatePhotoView();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        else if (requestCode == REQUEST_PHOTO) updatePhotoView();
    }

    private void updatePhotoView() {
        if (getPhotoFile() == null || !getPhotoFile().exists()) {
            imgProfile.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(getPhotoFile().getPath(), getActivity());
            imgProfile.setImageBitmap(bitmap);
        }
    }

    public File getPhotoFile() {
        File externalFilesDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFilesDir == null) return null;
        return new File(externalFilesDir,  "IICP_IMG_" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".jpg");
    }

    private void logout() {
        SaveSharedPreference.clearStudentId(getContext());
        Intent i = new Intent(getContext(), LoginActivity.class);
        startActivity(i);
    }
}
