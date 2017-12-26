package com.example.andriginting.kamus_ku.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.andriginting.kamus_ku.R;
import com.example.andriginting.kamus_ku.adapter.KamudIndoAdapter;
import com.example.andriginting.kamus_ku.database.KamusIndonesiaEnglishHelper;
import com.example.andriginting.kamus_ku.model.KamusIndoModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndoFragment extends Fragment implements SearchView.OnQueryTextListener {
    @BindView(R.id.recycler_indonesia)
    RecyclerView recyclerViewIndonesia;

    KamudIndoAdapter adapter;
    KamusIndonesiaEnglishHelper kamusIndonesiaEnglishHelper;
    List<KamusIndoModel> kamusIndoModels;
    public IndoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_indo, container, false);
        ButterKnife.bind(this,v);

        setHasOptionsMenu(true);
        recyclerViewIndonesia = v.findViewById(R.id.recycler_indonesia);
        recyclerViewIndonesia.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewIndonesia.setAdapter(adapter);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main,menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        kamusIndonesiaEnglishHelper = new KamusIndonesiaEnglishHelper(getContext());

        recyclerViewIndonesia.setLayoutManager(new LinearLayoutManager(getContext()));

        kamusIndonesiaEnglishHelper.open();
        kamusIndoModels = kamusIndonesiaEnglishHelper.getDataByWordIndo(query);
        kamusIndonesiaEnglishHelper.closeIndo();

        recyclerViewIndonesia.setAdapter(new KamudIndoAdapter(kamusIndoModels,getActivity()));
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }

    private void loadData(){
        kamusIndonesiaEnglishHelper = new KamusIndonesiaEnglishHelper(getContext());

        recyclerViewIndonesia.setLayoutManager(new LinearLayoutManager(getContext()));

        kamusIndonesiaEnglishHelper.open();
        kamusIndoModels = kamusIndonesiaEnglishHelper.getDataByWordIndo("");
        kamusIndonesiaEnglishHelper.closeIndo();

        recyclerViewIndonesia.setAdapter(new KamudIndoAdapter(kamusIndoModels,getActivity()));
    }
}
