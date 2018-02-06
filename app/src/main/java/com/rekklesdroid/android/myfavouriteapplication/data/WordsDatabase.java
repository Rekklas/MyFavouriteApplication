package com.rekklesdroid.android.myfavouriteapplication.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {AdverbQuantity.class, AdverbQuality.class,
        Verb.class, Noun.class}, version = 1)
public abstract class WordsDatabase extends RoomDatabase {

    public abstract AdverbQuantityDao adverbQuantityDao();

    public abstract AdverbQualityDao adverbQualityDao();

    public abstract VerbDao verbDao();

    public abstract NounDao nounDao();

}
