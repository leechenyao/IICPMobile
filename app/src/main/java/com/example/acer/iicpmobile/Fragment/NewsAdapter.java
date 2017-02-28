package com.example.acer.iicpmobile.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.acer.iicpmobile.R;

import java.util.List;

/**
 * Created by Acer on 2/16/2017.
 */


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<News> mNews;
    private Context mContext;

    public NewsAdapter(Context context, List<News> news) {
        mNews = news;
        mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public TextView txtDescription;
        public TextView txtBy;
        public TextView txtDate;

        public ViewHolder(final View itemView) {
            super(itemView);

            txtTitle = (TextView) itemView.findViewById(R.id.news_title);
            txtDescription = (TextView) itemView.findViewById(R.id.news_description);
            txtBy = (TextView) itemView.findViewById(R.id.news_by);
            txtDate = (TextView) itemView.findViewById(R.id.news_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://newinti.edu.my")));
                }
            });
        }
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View newsView = inflater.inflate(R.layout.list_item_news, parent, false);
        ViewHolder viewHolder = new ViewHolder(newsView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder viewHolder, int position) {
        News news = mNews.get(position);

        viewHolder.txtTitle.setText(news.getTitle());
        viewHolder.txtDescription.setText(news.getDescription());
        viewHolder.txtBy.setText(news.getBy());
        viewHolder.txtDate.setText(news.getDate());

    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }
}

