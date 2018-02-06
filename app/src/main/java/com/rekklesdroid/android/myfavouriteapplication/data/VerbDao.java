package com.rekklesdroid.android.myfavouriteapplication.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


@Dao
public interface VerbDao {

    @Insert
    void insertAllVerbs(Verb... verbs);

    @Query("SELECT * FROM verbs ORDER BY RANDOM() LIMIT 1")
    Verb getRandomVerb();

}
