package com.venomvendor.guestlogix.episode.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Origin implements Parcelable {

    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return
                "Origin{" +
                        "name = '" + name + '\'' +
                        ",url = '" + url + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.url);
    }

    public Origin() {
    }

    protected Origin(Parcel in) {
        this.name = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Origin> CREATOR = new Parcelable.Creator<Origin>() {
        @Override
        public Origin createFromParcel(Parcel source) {
            return new Origin(source);
        }

        @Override
        public Origin[] newArray(int size) {
            return new Origin[size];
        }
    };
}
