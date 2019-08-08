/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 08-Aug'19.
 */

package com.venomvendor.guestlogix.episode.network;

import com.venomvendor.guestlogix.core.network.BaseRequest;
import com.venomvendor.guestlogix.episode.model.Episode;
import com.venomvendor.guestlogix.episode.model.EpisodeRes;
import com.venomvendor.guestlogix.episode.model.Info;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

public class EpisodeRequest extends BaseRequest<EpisodeRes> {

    private static final String URL = "https://rickandmortyapi.com/api/episode/";

    @Override
    public String getUrl() {
        return URL;
    }

    @Override
    public Method getMethod() {
        return Method.GET;
    }

    @Override
    public EpisodeRes getData(String response) throws JSONException {
        return getParsedResult(response);
    }

    private EpisodeRes getParsedResult(String response) throws JSONException {
        JSONObject res = (JSONObject) new JSONTokener(response).nextValue();

        EpisodeRes episodeRes = new EpisodeRes();
        JSONObject infoObj = res.getJSONObject("info");

        Info info = new Info();
        info.setCount(infoObj.getInt("count"));
        info.setPages(infoObj.getInt("pages"));
        info.setNext(infoObj.getString("next"));
        info.setPrev(infoObj.getString("prev"));

        episodeRes.setInfo(info);

        JSONArray results = res.getJSONArray("results");
        int length = results.length();

        List<Episode> episodes = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            Episode episode = new Episode();
            JSONObject epi = (JSONObject) results.get(i);

            episode.setId(epi.getInt("id"));
            episode.setName(epi.getString("name"));
            episode.setAirDate(epi.getString("air_date"));
            episode.setEpisode(epi.getString("episode"));
            episode.setUrl(epi.getString("url"));
            episode.setCreated(epi.getString("created"));

            JSONArray locations = epi.getJSONArray("characters");
            int locSize = locations.length();

            List<String> characters = new ArrayList<>(locSize);
            for (int j = 0; j < locSize; j++) {
                characters.add(locations.getString(j));
            }

            episode.setCharacters(characters);

            episodes.add(episode);
        }

        episodeRes.setEpisodes(episodes);
        return episodeRes;
    }
}
