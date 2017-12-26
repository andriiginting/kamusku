package com.example.andriginting.kamus_ku.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andriginting.kamus_ku.R;
import com.example.andriginting.kamus_ku.activity.DetailActivity;
import com.example.andriginting.kamus_ku.model.KamusIndoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andri Ginting on 12/25/2017.
 */

public class KamudIndoAdapter extends RecyclerView.Adapter<KamudIndoAdapter.ViewHolder> {
    private List<KamusIndoModel> mKamus = new ArrayList<>();
    private Context context;

    public KamudIndoAdapter(List<KamusIndoModel> mKamus, Context context) {
        this.mKamus = mKamus;
        this.context = context;
    }

    @Override
    public KamudIndoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kamus_konten,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(KamudIndoAdapter.ViewHolder holder, final int position) {
        holder.kamusKonten.setText(mKamus.get(position).getWords());
        holder.kamusKonten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("word",mKamus.get(position).getWords());
                intent.putExtra("detail",mKamus.get(position).getDetails());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mKamus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView kamusKonten;
        public ViewHolder(View itemView) {
            super(itemView);
            kamusKonten = itemView.findViewById(R.id.teks_konten_kamus);
        }
    }
}

