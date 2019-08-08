/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 08-Aug'19.
 */

package com.venomvendor.guestlogix;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.venomvendor.guestlogix.core.ex.GLException;
import com.venomvendor.guestlogix.core.factory.AsyncListener;
import com.venomvendor.guestlogix.episode.model.Character;
import com.venomvendor.guestlogix.util.AppConstant;
import com.venomvendor.guestlogix.util.ImageHelper;

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

        Character character = bundle.getParcelable(AppConstant.KEY_CHARACTER);
        setData(character);
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

    private void setData(Character character) {
        name.setText(character.getName());
        id.setText(String.valueOf(character.getId()));
        status.setText(character.getStatus());
        species.setText(character.getSpecies());
        gender.setText(character.getGender());
        origin.setText(character.getOrigin().getName());

        ImageHelper.getImage(character.getImage(), new AsyncListener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                dp.setImageBitmap(response);
            }

            @Override
            public void onError(GLException ex) {

            }
        });
    }
}
