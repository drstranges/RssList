package com.test.rsslist.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test.rsslist.R;
import com.test.rsslist.data.model.RssItem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created on 04.10.2015.
 */
public class RssAdapter extends RecyclerView.Adapter<RssAdapter.ViewHolder> {

    private static final DateFormat DATE_FORMAT =
            SimpleDateFormat.getDateTimeInstance();

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

        public void onBind(RssItem item) {
            mTitle.setText(item.getTitle());
            mAuthor.setText(item.getAuthor());

            Date date = null;
            try {
                date = item.getDate();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            mDate.setText(formatDate(date));

            String picture = item.getPicture();
            Glide.with(itemView.getContext())
                    .load(picture)
                    .placeholder(android.R.drawable.gallery_thumb)
                    .crossFade()
                    .centerCrop()
                    .into(mImageView);
        }

        private String formatDate(Date date) {
            return date == null ? null : DATE_FORMAT.format(date);
        }
    }
}
