package com.leo.androidcomponents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<AlbumVO> list_album;
    public OnItemClickListener mOnItemClickListener = null;

    public interface OnItemClickListener {
        void onItemClick(View view, AlbumVO albumVO);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public RecyclerViewAdapter(Context mContext, ArrayList<AlbumVO> list_album) {
        this.mContext = mContext;
        this.list_album = list_album;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final AlbumVO albumVO = list_album.get(position);
        Glide.with(mContext)
                .load(albumVO.getThumb())
                .thumbnail(0.5f)
                .into(holder.img_thumb);
        holder.txt_artist.setText(albumVO.getArtist());
        holder.txt_title.setText(albumVO.getTitle());
        holder.layout_album_panel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, albumVO);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_album.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout_album_panel;
        private ImageView img_thumb;
        private TextView txt_artist;
        private TextView txt_title;

        public ViewHolder(View convertView) {
            super(convertView);

            layout_album_panel = (LinearLayout) convertView.findViewById(R.id.layout_album_panel);
            img_thumb = (ImageView) convertView.findViewById(R.id.img_thumb);
            txt_artist = (TextView) convertView.findViewById(R.id.txt_artist);
            txt_title = (TextView) convertView.findViewById(R.id.txt_title);
        }
    }
}