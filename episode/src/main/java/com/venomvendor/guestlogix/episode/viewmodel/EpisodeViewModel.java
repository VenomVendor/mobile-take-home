/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 08-Aug'19.
 */

package com.venomvendor.guestlogix.episode.viewmodel;

import com.venomvendor.guestlogix.core.factory.AsyncListener;
import com.venomvendor.guestlogix.core.factory.TransformerListener;
import com.venomvendor.guestlogix.episode.EpisodeManager;
import com.venomvendor.guestlogix.episode.model.Episode;
import com.venomvendor.guestlogix.episode.model.EpisodeRes;

import java.util.List;

public class EpisodeViewModel {

    /**
     * Returns List of Episodes
     */
    public void getEpisodes(AsyncListener<List<Episode>> listener) {
        EpisodeManager.getManager()
                .getEpisodes(new TransformerListener<>(listener, EpisodeRes::getEpisodes));
    }
}
