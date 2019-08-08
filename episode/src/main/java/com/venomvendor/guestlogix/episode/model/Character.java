package com.venomvendor.guestlogix.episode.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class Character implements Comparable<Character>, Parcelable {

    private String image;
    private String gender;
    private String species;
    private Date created;
    private Origin origin;
    private String name;
    private Location location;
    private List<String> episodes;
    private int id;
    private String type;
    private String url;
    private String status;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<String> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<String> episodes) {
        this.episodes = episodes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        if (status == null) {
            return "";
        }
        return status.toLowerCase();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return
                "Character{" +
                        "image = '" + image + '\'' +
                        ",gender = '" + gender + '\'' +
                        ",species = '" + species + '\'' +
                        ",created = '" + created + '\'' +
                        ",origin = '" + origin + '\'' +
                        ",name = '" + name + '\'' +
                        ",location = '" + location + '\'' +
                        ",episode = '" + episodes + '\'' +
                        ",id = '" + id + '\'' +
                        ",type = '" + type + '\'' +
                        ",url = '" + url + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }

    @Override
    public int compareTo(Character other) {
        if (created == null) {
            return 0;
        }
        return created.compareTo(other.getCreated());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.image);
        dest.writeString(this.gender);
        dest.writeString(this.species);
        dest.writeLong(this.created != null ? this.created.getTime() : -1);
        dest.writeParcelable(this.origin, flags);
        dest.writeString(this.name);
        dest.writeParcelable(this.location, flags);
        dest.writeStringList(this.episodes);
        dest.writeInt(this.id);
        dest.writeString(this.type);
        dest.writeString(this.url);
        dest.writeString(this.status);
    }

    public Character() {
    }

    protected Character(Parcel in) {
        this.image = in.readString();
        this.gender = in.readString();
        this.species = in.readString();
        long tmpCreated = in.readLong();
        this.created = tmpCreated == -1 ? null : new Date(tmpCreated);
        this.origin = in.readParcelable(Origin.class.getClassLoader());
        this.name = in.readString();
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.episodes = in.createStringArrayList();
        this.id = in.readInt();
        this.type = in.readString();
        this.url = in.readString();
        this.status = in.readString();
    }

    public static final Parcelable.Creator<Character> CREATOR = new Parcelable.Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel source) {
            return new Character(source);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };
}
