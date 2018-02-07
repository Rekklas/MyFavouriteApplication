package com.rekklesdroid.android.myfavouriteapplication.data.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "quality_adverbs",
        indices = {@Index(value = {"word"},
        unique = true)})
public class AdverbQuality extends Word{

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "word")
    private String word;

    public AdverbQuality(String word) {
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdverbQuality adverbQuality = (AdverbQuality) o;
        return word.equals(adverbQuality.word);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + word.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return word;
    }

    public static AdverbQuality[] populateQualityAdverbs() {
        return new AdverbQuality[]{
                new AdverbQuality("прекрасно"),
                new AdverbQuality("дивно"),
                new AdverbQuality("казково"),
                new AdverbQuality("потрясно"),
                new AdverbQuality("чаруюче")
        };
    }
}
