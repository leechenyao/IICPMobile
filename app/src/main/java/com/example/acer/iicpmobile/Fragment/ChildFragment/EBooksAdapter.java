package com.example.acer.iicpmobile.Fragment.ChildFragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.iicpmobile.R;

/**
 * Created by Acer on 2/24/2017.
 */

public class EBooksAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] EBooksTitle;
    private final Drawable[] EBooksCover;

    public EBooksAdapter(Context context, String[] EBooksTitle, Drawable[] EBooksCover) {
        mContext = context;
        this.EBooksTitle = EBooksTitle;
        this.EBooksCover = EBooksCover;
    }

    public int getCount() {
        return EBooksTitle.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new View for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= inflater.inflate(R.layout.list_item_ebook, null);
        ImageView imgEBooks = (ImageView)view.findViewById(R.id.imgEBooks);
        TextView txtEBooks = (TextView) view.findViewById(R.id.txtEBooks);

        imgEBooks.setImageDrawable(EBooksCover[position]);
        txtEBooks.setText(EBooksTitle[position]);

        return view;
    }
}
