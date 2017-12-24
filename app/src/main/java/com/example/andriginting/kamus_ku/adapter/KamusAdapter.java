package com.example.andriginting.kamus_ku.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andriginting.kamus_ku.R;
import com.example.andriginting.kamus_ku.model.KamusEnglishModel;

import java.util.ArrayList;

/**
 * Created by Andri Ginting on 12/24/2017.
 */

public class KamusAdapter extends RecyclerView.Adapter<KamusAdapter.ViewHolder> {
    private ArrayList<KamusEnglishModel> mKamus = new ArrayList<>();
    private Context context;

    public KamusAdapter(ArrayList<KamusEnglishModel> mKamus, Context context) {
        this.mKamus = mKamus;
        this.context = context;
        notifyDataSetChanged();
    }

    @Override
    public KamusAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kamus_konten,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(KamusAdapter.ViewHolder holder, int position) {
        holder.kamusKonten.setText(mKamus.get(position).getWords());
        holder.kamusKonten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
