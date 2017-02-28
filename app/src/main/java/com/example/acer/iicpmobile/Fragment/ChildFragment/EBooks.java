package com.example.acer.iicpmobile.Fragment.ChildFragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.acer.iicpmobile.R;

import java.io.IOException;

/**
 * Created by Acer on 2/15/2017.
 */

public class EBooks extends Fragment {
    private GridView gvEBooks;
    private static final String EBOOKS_FOLDER = "sample_ebooks";
    String[] EBooksTitle = new String[9];
    Drawable[] EBooksCover = new Drawable[9];

    private int progressStatus = 0;
    Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ebook_list, container, false);

        getEBooks();

        gvEBooks = (GridView) v.findViewById(R.id.gvEBooks);
        gvEBooks.setAdapter(new EBooksAdapter(getContext(), EBooksTitle, EBooksCover));
        gvEBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                download();
            }
        });

        return v;
    }

    private void getEBooks() {
        try {
            AssetManager mAssets = getContext().getAssets();
            String filename[] = mAssets.list(EBOOKS_FOLDER);
            for (int i = 0; i < filename.length; i++) {
                EBooksTitle[i] = filename[i].replace("_", " ").replace(".jpg", "");
                EBooksCover[i] = Drawable.createFromStream(mAssets.open(EBOOKS_FOLDER + "/" + filename[i]), null);
            }
        } catch (IOException e) {
            return;
        }
    }

    // Download animation
    private void download() {
        final ProgressDialog mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setTitle("Please Wait..");
        mProgressDialog.setMessage("Downloading E-Book ...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMax(100);
        mProgressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface arg0) {
                Toast.makeText(getContext(), "Download Complete", Toast.LENGTH_SHORT).show();
            }
        });
        mProgressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    try{
                        Thread.sleep(200);
                        progressStatus += 5;
                    } catch (InterruptedException e){
                        return;
                    }

                    handler.post(new Runnable() {
                        public void run() {
                            mProgressDialog.setProgress(progressStatus);
                        }
                    });
                }
                mProgressDialog.dismiss();
                progressStatus = 0;
            }
        }).start();
    }
}

