package com.test.rsslist.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.rsslist.R;
import com.test.rsslist.data.model.RssItem;

import java.util.List;

/**
 * Created on 04.10.2015.
 */
public class RssAdapter extends RecyclerView.Adapter<RssAdapter.ViewHolder> {

    private final List<RssItem> mData;

    public RssAdapter(List<RssItem> data) {
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.item_rss, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTitle;
        public TextView mDate;
        public TextView mAuthor;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image_picture);
            mTitle = (TextView) itemView.findViewById(R.id.text_title);
            mDate = (TextView) itemView.findViewById(R.id.text_date);
            mAuthor = (TextView) itemView.findViewById(R.id.text_author);
        }

        public void onBind(RssItem item){
            //TODO: Implement onBind
        }
    }
}
