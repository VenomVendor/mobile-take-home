package com.venomvendor.guestlogix.episode.model;

import java.util.List;

public class EpisodeRes {

    private List<Episode> episodes;
    private Info info;

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "EpisodeRes{" +
                "episodes = '" + episodes + '\'' +
                ",info = '" + info + '\'' +
                "}";
    }
}
