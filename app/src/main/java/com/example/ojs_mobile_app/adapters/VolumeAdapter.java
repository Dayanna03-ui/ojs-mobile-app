package com.example.ojs_mobile_app.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ojs_mobile_app.R;
import com.example.ojs_mobile_app.models.Issue;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VolumeAdapter extends RecyclerView.Adapter<VolumeAdapter.VolumeViewHolder> {
    private final Context context;
    public final List<Issue> volumeList;

    public VolumeAdapter(Context context, List<Issue> volumeList) {
        this.context = context;
        this.volumeList = volumeList;
    }

    @NonNull
    @Override
    public VolumeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_volumen, parent, false);
        return new VolumeViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull VolumeViewHolder holder, int position) {
        try {
            Issue volume = volumeList.get(position);

            holder.dateTextView.setText(volume.getDate());
            holder.volumeNumTextView.setText("Volumen: " + volume.getVolume() + " Num: " + volume.getNumber());
            holder.yearTextView.setText("Año: " + volume.getYear());
            holder.rcTextView.setText(volume.getTitle());
            holder.volNoTextView.setText("Vol. " + volume.getVolume() + " No. " + volume.getNumber());
            holder.periodTextView.setText(volume.getId());

            String imageUrl = volume.getImageUrl();
            if (imageUrl != null && !imageUrl.isEmpty()) {
                Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .into(holder.volumeImageView);
            } else {
                holder.volumeImageView.setImageResource(R.drawable.ic_launcher_background);
            }
        } catch (Exception e) {
            Log.e("VolumeAdapter", "Error en onBindViewHolder: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return volumeList.size();
    }

    public static class VolumeViewHolder extends RecyclerView.ViewHolder {
        ImageView volumeImageView;
        TextView dateTextView;
        TextView volumeNumTextView;
        TextView yearTextView;
        TextView rcTextView;
        TextView volNoTextView;
        TextView periodTextView;

        public VolumeViewHolder(@NonNull View itemView) {
            super(itemView);
            volumeImageView = itemView.findViewById(R.id.volumeImageView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            volumeNumTextView = itemView.findViewById(R.id.volumeNumTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
            rcTextView = itemView.findViewById(R.id.rcTextView);
            volNoTextView = itemView.findViewById(R.id.volNoTextView);
            periodTextView = itemView.findViewById(R.id.periodTextView);
        }
    }
}