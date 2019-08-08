/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 08-Aug'19.
 */

package com.venomvendor.guestlogix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.venomvendor.guestlogix.core.ex.GLException;
import com.venomvendor.guestlogix.core.factory.AsyncListener;
import com.venomvendor.guestlogix.episode.model.Character;
import com.venomvendor.guestlogix.episode.model.Episode;
import com.venomvendor.guestlogix.episode.viewmodel.CharacterViewModel;
import com.venomvendor.guestlogix.util.AppConstant;
import com.venomvendor.guestlogix.view.CharacterAdapter;

import java.util.List;

public class DetailActivity extends Activity {

    private GridView mEpisodeView;
    private CharacterAdapter mAdapter;
    private CharacterViewModel mViewModel;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            finish();
            return;
        }

        initViews();

        initListeners();

        initModels();

        Episode episode = bundle.getParcelable(AppConstant.KEY_EPISODE);
        getData(episode);
    }

    private void initViews() {
        mEpisodeView = findViewById(R.id.episode_view);
        mProgressBar = findViewById(R.id.progress_bar);
        mAdapter = new CharacterAdapter(getApplicationContext());
        mEpisodeView.setAdapter(mAdapter);
    }

    private void initListeners() {
        mEpisodeView.setOnItemClickListener((parent, view, position, id) -> {
            Character character = (Character) parent.getItemAtPosition(position);

            Intent intent = new Intent(DetailActivity.this, CharacterDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(AppConstant.KEY_CHARACTER, character);
            intent.putExtras(bundle);

            startActivity(intent);
        });
    }

    private void initModels() {
        mViewModel = new CharacterViewModel();
    }

    private void getData(Episode episode) {
        mProgressBar.setVisibility(View.VISIBLE);
        mViewModel.getCharacters(episode, new AsyncListener<List<Character>>() {
            @Override
            public void onResponse(List<Character> response) {
                displayData(response);

                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(GLException ex) {
                Toast.makeText(DetailActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void displayData(List<Character> response) {
        mAdapter.setData(response);
    }
}
