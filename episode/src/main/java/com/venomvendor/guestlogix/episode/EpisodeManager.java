/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 08-Aug'19.
 */

package com.venomvendor.guestlogix.episode;

import com.venomvendor.guestlogix.core.factory.AsyncListener;
import com.venomvendor.guestlogix.episode.internal.EpisodeHandler;
import com.venomvendor.guestlogix.episode.model.Character;
import com.venomvendor.guestlogix.episode.model.EpisodeRes;

import java.util.List;

/**
 * Manager Episodes
 */
public interface EpisodeManager {

    static EpisodeManager getManager() {
        return new EpisodeHandler();
    }

    /**
     * Returns List of Episodes
     */
    void getEpisodes(AsyncListener<EpisodeRes> listener);

    /**
     * Returns List of Characters
     */
    void getCharacters(int[] characters, AsyncListener<List<Character>> listener);
}
