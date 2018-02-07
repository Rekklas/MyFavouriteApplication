package com.rekklesdroid.android.myfavouriteapplication.data.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "nouns",
        indices = {@Index(value = {"word"},
        unique = true)})
public class Noun extends Word{

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "word")
    private String word;

    public Noun(String word) {
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
        Noun noun = (Noun) o;
        return word.equals(noun.word);
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

    public static Noun[] populateNouns() {
        return new Noun[]{
                new Noun("під зірками"),
                new Noun("коли смієшся"),
                new Noun("їжачок"),
                new Noun("коли ми вдома"),
                new Noun("завжди")
        };
    }
}
