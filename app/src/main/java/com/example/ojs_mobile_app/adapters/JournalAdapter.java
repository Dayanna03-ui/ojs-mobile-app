package com.example.ojs_mobile_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ojs_mobile_app.R;
import com.example.ojs_mobile_app.VolumesActivity;
import com.example.ojs_mobile_app.models.Journal;
import com.squareup.picasso.Picasso;
import java.util.List;

public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.JournalViewHolder> {
    private final List<Journal> journalList;
    private final Context context;

    public JournalAdapter(Context context, List<Journal> journalList) {
        this.context = context;
        this.journalList = journalList;
    }

    @NonNull
    @Override
    public JournalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_journal, parent, false);
        return new JournalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JournalViewHolder holder, int position) {
        Journal journal = journalList.get(position);
        holder.titleTextView.setText(journal.getTitle());

        // Cargar imagen con Picasso
        Picasso.get().load(journal.getImageUrl()).into(holder.imageView);

        // Hacer visible el logo de la UTEQ solo para el último elemento
        if (position == journalList.size() - 1) {
            holder.uteqLogoImageView.setVisibility(View.VISIBLE);
        } else {
            holder.uteqLogoImageView.setVisibility(View.GONE);
        }

        // Agregar clic a cada elemento de la lista
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, VolumesActivity.class);
            intent.putExtra("journal_id", journal.getId()); // Enviar el ID de la revista
            intent.putExtra("journal_title", journal.getTitle()); // También enviar el título
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return journalList.size();
    }

    public static class JournalViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView imageView;
        ImageView uteqLogoImageView; // Agregar ImageView para el logo de la UTEQ

        public JournalViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.journalTitle);
            imageView = itemView.findViewById(R.id.journalImage);
            uteqLogoImageView = itemView.findViewById(R.id.imgUteqLogo); // Inicializar el ImageView del logo
        }
    }
}