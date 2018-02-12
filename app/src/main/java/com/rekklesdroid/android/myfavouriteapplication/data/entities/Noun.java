package com.rekklesdroid.android.myfavouriteapplication.data.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Class that represents entity of noun
 */
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

    /**
     * Method is used to populate database with nouns
     *
     * @return an array of nouns
     */
    public static Noun[] populateNouns() {
        return new Noun[]{
                new Noun("совушка"),
                new Noun("совенятко"),
                new Noun("кошенятко"),
                new Noun("зайченятко"),
                new Noun("коли ми граємо"),
                new Noun("під краплинами дощу"),
                new Noun("під музику"),
                new Noun("під час масажу"),
                new Noun("в моїх думках"),
                new Noun("коли йде сніг"),
                new Noun("під ковдрою"),
                new Noun("на свіжому повітрі"),
                new Noun("після шопінгу"),
                new Noun("у парку"),
                new Noun("у погляді"),
                new Noun("під мелодію вітру"),
                new Noun("біля водоспаду"),
                new Noun("на природі"),
                new Noun("у ліжку"),
                new Noun("сонечко"),
                new Noun("в моїх обіймах"),
                new Noun("наодинці зі мною"),
                new Noun("після кави"),
                new Noun("за кавою"),
                new Noun("янголе"),
                new Noun("коли ми з друзями"),
                new Noun("коли я поруч"),
                new Noun("коли ми разом"),
                new Noun("на тому фото"),
                new Noun("після подорожі"),
                new Noun("щовечора"),
                new Noun("щодня"),
                new Noun("щоранку"),
                new Noun("на світанку"),
                new Noun("спросоння"),
                new Noun("на вихідних"),
                new Noun("по життю"),
                new Noun("восени"),
                new Noun("взимку"),
                new Noun("навесні"),
                new Noun("влітку"),
                new Noun("цієї ночі"),
                new Noun("за чаєм"),
                new Noun("сьогодні"),
                new Noun("під зірками"),
                new Noun("коли смієшся"),
                new Noun("їжачок"),
                new Noun("коли ми вдома"),
                new Noun("завжди"),
                new Noun("у горах"),
                new Noun("вдома"),
                new Noun("після чаю"),
                new Noun("на прогулянці"),
                new Noun("після обіду"),
                new Noun("біля озера"),
                new Noun("біля моря")


        };
    }
}
