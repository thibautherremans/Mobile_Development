package com.example.myportfolio;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.zip.Inflater;

public class PortfolioListAdapter extends RecyclerView.Adapter<PortfolioListAdapter.NumberViewHolder> {
    private final LinkedList<String> title;
    private final LinkedList<String> image;
    private final LinkedList<String> body;
    private final LinkedList<String> url;
    private Context context;
    private LayoutInflater inflater;

    public PortfolioListAdapter(LinkedList<String> title, LinkedList<String> image, LinkedList<String> body, LinkedList<String> url, Context context) {
        this.title = title;
        this.image = image;
        this.body = body;
        this.url = url;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PortfolioListAdapter.NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.itemview, parent, false);
        return new NumberViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull PortfolioListAdapter.NumberViewHolder holder, int position) {
        String current_title = title.get(position);
        String current_image = image.get(position);
        String current_body = body.get(position);
        String current_url = url.get(position);

        holder.title.setText(current_title);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, item.class);
                intent.putExtra("title", current_title);
                intent.putExtra("image", current_image);
                intent.putExtra("body", current_body);
                intent.putExtra("url", current_url);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    static class NumberViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final ImageView image;
        public final ConstraintLayout constraintLayout;
        final PortfolioListAdapter adapter;

        public NumberViewHolder(@NonNull View itemView, PortfolioListAdapter adapter) {
            super(itemView);
            this.title = itemView.findViewById(R.id.portfolioItem_title);
            this.image = itemView.findViewById(R.id.portfolioItem_image);
            this.constraintLayout = itemView.findViewById(R.id.portfolioItem_layout);
            this.adapter = adapter;
        }
    }
}
