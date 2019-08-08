/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 08-Aug'19.
 */

package com.venomvendor.guestlogix.episode.network;

import com.venomvendor.guestlogix.core.network.BaseRequest;
import com.venomvendor.guestlogix.episode.model.Character;
import com.venomvendor.guestlogix.episode.model.Location;
import com.venomvendor.guestlogix.episode.model.Origin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

public class CharacterRequest extends BaseRequest<List<Character>> {

    private static final String URL = "https://rickandmortyapi.com/api/character/";

    private final int[] characters;

    public CharacterRequest(int[] characters) {
        if (characters == null || characters.length == 0) {
            throw new UnsupportedOperationException("Characters cannot be null/empty");
        }
        this.characters = characters;
    }

    @Override
    public String getUrl() {
        StringBuilder urlBuilder = new StringBuilder(URL).append('[');
        for (int character : characters) {
            urlBuilder.append(character).append(',');
        }
        urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        return urlBuilder.append(']').toString();
    }

    @Override
    public Method getMethod() {
        return Method.GET;
    }

    @Override
    public List<Character> getData(String response) throws JSONException {
        return getParsedResult(response);
    }

    private List<Character> getParsedResult(String response) throws JSONException {
        JSONArray res = (JSONArray) new JSONTokener(response).nextValue();

        int length = res.length();
        List<Character> characters = new ArrayList<>(length);

        for (int i = 0; i < length; i++) {
            JSONObject jObj = res.getJSONObject(i);

            Character character = new Character();
            character.setId(jObj.getInt("id"));
            character.setName(jObj.getString("name"));
            character.setStatus(jObj.getString("status"));
            character.setSpecies(jObj.getString("species"));
            character.setType(jObj.getString("type"));
            character.setGender(jObj.getString("gender"));
            character.setImage(jObj.getString("image"));
            character.setUrl(jObj.getString("url"));
            character.setCreated(jObj.getString("created"));

            JSONObject jOrigin = jObj.getJSONObject("origin");
            Origin origin = new Origin();
            origin.setName(jOrigin.getString("name"));
            origin.setUrl(jOrigin.getString("url"));
            character.setOrigin(origin);

            JSONObject jLocation = jObj.getJSONObject("location");
            Location location = new Location();
            location.setName(jLocation.getString("name"));
            location.setUrl(jLocation.getString("url"));
            character.setLocation(location);

            JSONArray jEpisodes = jObj.getJSONArray("episode");
            int epiLength = jEpisodes.length();

            List<String> episodes = new ArrayList<>(epiLength);

            for (int j = 0; j < epiLength; j++) {
                episodes.add(jEpisodes.getString(i));
            }

            character.setEpisodes(episodes);

            characters.add(character);
        }

        return characters;
    }
}
