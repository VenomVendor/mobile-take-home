/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 08-Aug'19.
 */

package com.venomvendor.guestlogix;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.venomvendor.guestlogix.episode.model.Character;
import com.venomvendor.guestlogix.util.AppConstant;

public class CharacterDetailActivity extends Activity {

    private ImageView dp;
    private TextView name;
    private TextView id;
    private TextView status;
    private TextView species;
    private TextView gender;
    private TextView origin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            finish();
            return;
        }

        initViews();

        Character episode = bundle.getParcelable(AppConstant.KEY_CHARACTER);
        setData(episode);
    }

    private void initViews() {
        dp = findViewById(R.id.dp);
        name = findViewById(R.id.name);
        id = findViewById(R.id.id);
        status = findViewById(R.id.status);
        species = findViewById(R.id.species);
        gender = findViewById(R.id.gender);
        origin = findViewById(R.id.origin);
    }

    private void setData(Character episode) {
        name.setText(episode.getName());
        id.setText(String.valueOf(episode.getId()));
        status.setText(episode.getStatus());
        species.setText(episode.getSpecies());
        gender.setText(episode.getGender());
        origin.setText(episode.getOrigin().getName());
    }
}
