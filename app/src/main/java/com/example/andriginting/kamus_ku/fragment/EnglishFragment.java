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
import com.example.andriginting.kamus_ku.adapter.KamusEngAdapter;
import com.example.andriginting.kamus_ku.database.KamusEnglishIndonesiaHelper;
import com.example.andriginting.kamus_ku.model.KamusEnglishModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnglishFragment extends Fragment implements SearchView.OnQueryTextListener{

    @BindView(R.id.recycler_english)
    RecyclerView recyclerViewEnglish;

    KamusEngAdapter adapter;
    KamusEnglishIndonesiaHelper kamusEnglishIndonesiaHelper;
  List<KamusEnglishModel> kamusEnglishModels = new ArrayList<>();
    public EnglishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_english, container, false);
        ButterKnife.bind(this,v);

        setHasOptionsMenu(true);
        recyclerViewEnglish = v.findViewById(R.id.recycler_english);
        recyclerViewEnglish.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewEnglish.setAdapter(adapter);
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

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        kamusEnglishIndonesiaHelper = new KamusEnglishIndonesiaHelper(getContext());

        recyclerViewEnglish.setLayoutManager(new LinearLayoutManager(getContext()));

        kamusEnglishIndonesiaHelper.open();
        kamusEnglishModels = kamusEnglishIndonesiaHelper.getDataByWord(newText);
        kamusEnglishIndonesiaHelper.close();

        recyclerViewEnglish.setAdapter(new KamusEngAdapter(kamusEnglishModels,getActivity()));

        return true;
    }
    private void loadData(){

        kamusEnglishIndonesiaHelper = new KamusEnglishIndonesiaHelper(getContext());

        recyclerViewEnglish.setLayoutManager(new LinearLayoutManager(getContext()));

        kamusEnglishIndonesiaHelper.open();
        kamusEnglishModels = kamusEnglishIndonesiaHelper.getDataByWord("");
        kamusEnglishIndonesiaHelper.close();

        recyclerViewEnglish.setAdapter(new KamusEngAdapter(kamusEnglishModels,getActivity()));

    }
}
