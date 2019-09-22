package com.example.seoulsharespaceproject.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seoulsharespaceproject.R;

import java.util.List;

public class SpaceAdapter extends RecyclerView.Adapter<SpaceAdapter.SpaceViewHolder> {

    private List<ShareSpace> datalists;

    public SpaceAdapter(List<ShareSpace> datalists) {
        this.datalists = datalists;
    }

    @NonNull
    @Override
    public SpaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SpaceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sharespace_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SpaceViewHolder holder, int position) {

        ShareSpace data = datalists.get(position);
        holder.shareSpaceImage.setImageDrawable(data.getImage());

    }

    @Override
    public int getItemCount() {
        return datalists.size();
    }

    class SpaceViewHolder extends RecyclerView.ViewHolder {

        private ImageView shareSpaceImage;

        public SpaceViewHolder(@NonNull View itemView) {
            super(itemView);
            shareSpaceImage = (ImageView) itemView.findViewById(R.id.item_Image);
        }
    }

}
