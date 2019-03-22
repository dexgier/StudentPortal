package com.example.studentportal;

import android.os.Parcel;
import android.os.Parcelable;

public class Portal implements Parcelable {

    private String title;
    private String url;

    public String getmPortalText() { return title; }

    public Portal(String title, String url) {
        this.title = title;
        this.url = url;
    }

    @Override
    public String toString() { return title; }

    public void setTitle(String title) { this.title = title; }
    public String getTitle() { return title; }

    protected Portal(Parcel in) {
        this.title = in.readString();
        this.url = in.readString();
    }


    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }



    public static final Parcelable.Creator<Portal> CREATOR = new Parcelable.Creator<Portal>() {
        @Override
        public Portal createFromParcel(Parcel in) {
            return new Portal(in);
        }

        @Override
        public Portal[] newArray(int size) {
            return new Portal[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.url);
    }
}
