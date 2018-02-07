package com.rekklesdroid.android.myfavouriteapplication.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.rekklesdroid.android.myfavouriteapplication.data.entities.Noun;


@Dao
public interface NounDao {

    @Insert
    void insertAllNouns(Noun... nouns);

    @Query("SELECT * FROM nouns ORDER BY RANDOM() LIMIT 1")
    Noun getRandomNoun();

}
