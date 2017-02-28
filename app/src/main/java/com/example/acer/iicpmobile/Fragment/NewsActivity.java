package com.example.acer.iicpmobile.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.iicpmobile.R;
import com.example.acer.iicpmobile.database.IICPDataBaseAdapter;

import java.util.ArrayList;

/**
 * Created by Acer on 2/16/2017.
 */

public class NewsActivity extends Fragment {
    IICPDataBaseAdapter mIICPDataBaseAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.news_list, container, false);

        RecyclerView rvNews = (RecyclerView) v.findViewById(R.id.rvNews);
        ArrayList<News> news = new ArrayList<News>();

        mIICPDataBaseAdapter = new IICPDataBaseAdapter(getContext());
        mIICPDataBaseAdapter = mIICPDataBaseAdapter.Open();
        Cursor cursor = mIICPDataBaseAdapter.SelectAll("news", null, null);
        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                do {
                    news.add(new News(cursor.getString(cursor.getColumnIndex("news_title")),
                            cursor.getString(cursor.getColumnIndex("news_description")),
                            cursor.getString(cursor.getColumnIndex("news_date")),
                            cursor.getString(cursor.getColumnIndex("news_by"))));
                }while (cursor.moveToNext());
            }
        }
        mIICPDataBaseAdapter.Close();

        NewsAdapter adapter = new NewsAdapter(getActivity(), news);
        rvNews.setAdapter(adapter);
        rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }
}
