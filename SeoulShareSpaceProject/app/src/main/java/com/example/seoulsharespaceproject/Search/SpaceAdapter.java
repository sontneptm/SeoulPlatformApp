package com.example.seoulsharespaceproject.Search;

import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.seoulsharespaceproject.R;

import java.util.List;

public class SpaceAdapter extends RecyclerView.Adapter<SpaceAdapter.SpaceViewHolder> {


    class SpaceViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView title,min;

        public SpaceViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.item_Image);
            title =(TextView)itemView.findViewById(R.id.item_title);
            min = (TextView)itemView.findViewById(R.id.item_min);//소분류명
        }
    }

    private List<ShareSpace> datalists=null;

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
       /* if(data.getBitmap() != null) {
            holder.shareSpaceImage.setImageBitmap(data.getBitmap());
        }else{
                Log.e("에러","에러");
        }*/
        Glide.with(holder.image.getContext()).load(data.getImgURL()).into(holder.image);
        holder.title.setText(data.getTitle());
        holder.min.setText(data.getMinclassnm());
    }

    @Override
    public int getItemCount() {
        return datalists.size();
    }
}

