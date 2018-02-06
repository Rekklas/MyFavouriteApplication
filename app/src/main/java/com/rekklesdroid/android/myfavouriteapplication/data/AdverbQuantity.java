package com.rekklesdroid.android.myfavouriteapplication.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "quantity_adverbs",
        indices = {@Index(value = {"word"},
        unique = true)})
public class AdverbQuantity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "word")
    private String word;

    public AdverbQuantity(String word) {
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
        AdverbQuantity adverbQuanyity = (AdverbQuantity) o;
        return word.equals(adverbQuanyity.word);
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

    public static AdverbQuantity[] populateQuantityAdverbs() {
        return new AdverbQuantity[]{
                new AdverbQuantity("більш ніж"),
                new AdverbQuantity("до хвилювання"),
                new AdverbQuantity("до божевілля"),
                new AdverbQuantity("несамовито"),
                new AdverbQuantity("несказанно")
        };
    }
}
