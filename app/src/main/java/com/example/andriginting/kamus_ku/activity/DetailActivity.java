package com.example.andriginting.kamus_ku.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.andriginting.kamus_ku.R;

import butterknife.BindView;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.words_detail)
    TextView wordDetail;

    @BindView(R.id.details_detail)
    TextView details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String kata = getIntent().getStringExtra("word");
        String detail = getIntent().getStringExtra("detail");

        wordDetail = findViewById(R.id.words_detail);
        details = findViewById(R.id.details_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.details_activity);
        actionBar.setSubtitle(kata);

        wordDetail.setText(kata);
        details.setText(detail);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
