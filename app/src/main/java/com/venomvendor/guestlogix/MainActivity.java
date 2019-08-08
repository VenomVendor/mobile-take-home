/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 07-Aug'19.
 */

package com.venomvendor.guestlogix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.venomvendor.guestlogix.core.ex.GLException;
import com.venomvendor.guestlogix.core.factory.AsyncListener;
import com.venomvendor.guestlogix.episode.model.Episode;
import com.venomvendor.guestlogix.episode.viewmodel.EpisodeViewModel;
import com.venomvendor.guestlogix.util.AppConstant;
import com.venomvendor.guestlogix.view.EpisodeAdapter;

import java.util.List;

public class MainActivity extends Activity {

    private ListView mEpisodeView;
    private EpisodeAdapter mAdapter;
    private EpisodeViewModel mViewModel;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initListeners();

        initModels();

        getData();
    }

    private void initViews() {
        mEpisodeView = findViewById(R.id.episode_view);
        mProgressBar = findViewById(R.id.progress_bar);
        mAdapter = new EpisodeAdapter(getApplicationContext());
        mEpisodeView.setAdapter(mAdapter);
    }

    private void initListeners() {
        mEpisodeView.setOnItemClickListener((parent, view, position, id) -> {
            Episode episode = (Episode) parent.getItemAtPosition(position);
            System.out.println(episode);

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(AppConstant.KEY_EPISODE, episode);
            intent.putExtras(bundle);

            startActivity(intent);
        });
    }

    private void initModels() {
        mViewModel = new EpisodeViewModel();
    }

    private void getData() {
        mProgressBar.setVisibility(View.VISIBLE);
        mViewModel.getEpisodes(new AsyncListener<List<Episode>>() {
            @Override
            public void onResponse(List<Episode> response) {
                displayData(response);
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(GLException ex) {
                Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void displayData(List<Episode> response) {
        mAdapter.setData(response);
    }
}
