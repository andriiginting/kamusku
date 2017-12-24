package com.example.andriginting.kamus_ku.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Andri Ginting on 12/24/2017.
 */

public class KamusEnglishModel implements Parcelable{
    private int id;
    private String words;
    private String details;

    public KamusEnglishModel(){

    }

    public KamusEnglishModel(int id, String words, String details) {
        this.id = id;
        this.words = words;
        this.details = details;
    }

    public KamusEnglishModel(String words, String details) {
        this.words = words;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.words);
        dest.writeString(this.details);
    }

    protected KamusEnglishModel(Parcel in) {
        this.id = in.readInt();
        this.words = in.readString();
        this.details = in.readString();
    }

    public static final Parcelable.Creator<KamusEnglishModel> CREATOR = new Parcelable.Creator<KamusEnglishModel>() {
        @Override
        public KamusEnglishModel createFromParcel(Parcel source) {
            return new KamusEnglishModel(source);
        }

        @Override
        public KamusEnglishModel[] newArray(int size) {
            return new KamusEnglishModel[size];
        }
    };
}
