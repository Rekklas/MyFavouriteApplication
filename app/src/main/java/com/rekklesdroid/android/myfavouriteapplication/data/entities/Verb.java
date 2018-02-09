package com.rekklesdroid.android.myfavouriteapplication.data.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "verbs",
        indices = {@Index(value = {"word"},
        unique = true)})
public class Verb extends Word{

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "word")
    private String word;

    public Verb(String word) {
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
        Verb verb = (Verb) o;
        return word.equals(verb.word);
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

    public static Verb[] populateVerbs() {
        return new Verb[]{
                new Verb("заграєш"),
                new Verb("пахнеш"),
                new Verb("спокушаєш"),
                new Verb("вражаєш"),
                new Verb("випромінюєш щастя"),
                new Verb("лежиш"),
                new Verb("підтримуєш"),
                new Verb("цілуєшся"),
                new Verb("любиш мене"),
                new Verb("кохаєш мене"),
                new Verb("цінуєш мене"),
                new Verb("катаєшся на машинках"),
                new Verb("зігріваєш"),
                new Verb("хвилюєш"),
                new Verb("радієш"),
                new Verb("допомагаєш"),
                new Verb("створюєш затишок"),
                new Verb("зникаєш"),
                new Verb("співаєш"),
                new Verb("говориш"),
                new Verb("випромінюєш світло"),
                new Verb("зустрічаєш"),
                new Verb("проводжаєш"),
                new Verb("чуєш"),
                new Verb("вітаєшся"),
                new Verb("обіймаєш мене"),
                new Verb("виглядаєш"),
                new Verb("мовчиш"),
                new Verb("готуєш"),
                new Verb("насолоджуєшся життям"),
                new Verb("одягаєшся"),
                new Verb("заколисуєш"),
                new Verb("муркочеш"),
                new Verb("впливаєш на мене"),
                new Verb("згадуєш"),
                new Verb("розповідаєш"),
                new Verb("занурюєшся в обійми"),
                new Verb("надихаєш"),
                new Verb("розквітаєш"),
                new Verb("малюєш"),
                new Verb("шепочеш"),
                new Verb("чекаєш на мене"),
                new Verb("грієш"),
                new Verb("дихаєш"),
                new Verb("позіхаєш")
        };
    }
}
