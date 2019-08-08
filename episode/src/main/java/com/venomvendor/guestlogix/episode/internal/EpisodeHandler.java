/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 08-Aug'19.
 */

package com.venomvendor.guestlogix.episode.internal;

import com.venomvendor.guestlogix.core.NetworkManager;
import com.venomvendor.guestlogix.core.factory.AsyncListener;
import com.venomvendor.guestlogix.episode.EpisodeManager;
import com.venomvendor.guestlogix.episode.model.Character;
import com.venomvendor.guestlogix.episode.model.EpisodeRes;
import com.venomvendor.guestlogix.episode.network.CharacterRequest;
import com.venomvendor.guestlogix.episode.network.EpisodeRequest;

import java.util.List;

/**
 * Implementation for Episode Manager.
 */
public final class EpisodeHandler implements EpisodeManager {

    @Override
    public void getEpisodes(AsyncListener<EpisodeRes> listener) {
        NetworkManager.getInstance()
                .execute(new EpisodeRequest(), listener);
    }

    @Override
    public void getCharacters(int[] characters, AsyncListener<List<Character>> listener) {
        NetworkManager.getInstance()
                .execute(new CharacterRequest(characters), listener);
    }
}
