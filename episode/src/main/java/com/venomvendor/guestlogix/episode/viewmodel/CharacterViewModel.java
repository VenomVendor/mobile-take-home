/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 08-Aug'19.
 */

package com.venomvendor.guestlogix.episode.viewmodel;

import com.venomvendor.guestlogix.core.factory.AsyncListener;
import com.venomvendor.guestlogix.core.factory.TransformerListener;
import com.venomvendor.guestlogix.episode.EpisodeManager;
import com.venomvendor.guestlogix.episode.model.Character;
import com.venomvendor.guestlogix.episode.model.Episode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharacterViewModel {

    /**
     * Returns List of Characters
     */
    public void getCharacters(Episode episode, AsyncListener<List<Character>> listener) {
        List<String> characters = episode.getCharacters();
        int size = characters.size();

        String[] chars = new String[size];

        for (int i = 0; i < size; ++i) {
            String character = characters.get(i);
            String[] split = character.split("/");

            if (split.length > 0) {
                chars[i] = split[split.length - 1];
            }
        }

        getCharacters(chars, listener);
    }

    /**
     * Returns List of Characters
     */
    public void getCharacters(String[] chars, AsyncListener<List<Character>> listener) {
        EpisodeManager.getManager()
                .getCharacters(chars, new TransformerListener<>(listener, in -> {
                    Collections.sort(in);

                    List<Character> alive = new ArrayList<>(in.size() / 2);
                    List<Character> dead = new ArrayList<>(in.size() / 2);
                    List<Character> unknown = new ArrayList<>(in.size() / 2);

                    List<Character> all = new ArrayList<>(in.size());

                    for (Character character : in) {
                        switch (character.getStatus()) {
                            case "alive":
                                alive.add(character);
                                break;
                            case "dead":
                                dead.add(character);
                                break;
                            default:
                                unknown.add(character);
                                break;
                        }
                    }

                    all.addAll(alive);
                    all.addAll(dead);
                    all.addAll(unknown);

                    return all;
                }));
    }
}
