package com.venomvendor.guestlogix.episode.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Episode implements Parcelable {

    private String airDate;
    private List<String> characters;
    private String created;
    private String name;
    private String episode;
    private int id;
    private String url;

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "air_date = '" + airDate + '\'' +
                ",characters = '" + characters + '\'' +
                ",created = '" + created + '\'' +
                ",name = '" + name + '\'' +
                ",episode = '" + episode + '\'' +
                ",id = '" + id + '\'' +
                ",url = '" + url + '\'' +
                "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.airDate);
        dest.writeStringList(this.characters);
        dest.writeString(this.created);
        dest.writeString(this.name);
        dest.writeString(this.episode);
        dest.writeInt(this.id);
        dest.writeString(this.url);
    }

    public Episode() {
    }

    protected Episode(Parcel in) {
        this.airDate = in.readString();
        this.characters = in.createStringArrayList();
        this.created = in.readString();
        this.name = in.readString();
        this.episode = in.readString();
        this.id = in.readInt();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Episode> CREATOR = new Parcelable.Creator<Episode>() {
        @Override
        public Episode createFromParcel(Parcel source) {
            return new Episode(source);
        }

        @Override
        public Episode[] newArray(int size) {
            return new Episode[size];
        }
    };
}
