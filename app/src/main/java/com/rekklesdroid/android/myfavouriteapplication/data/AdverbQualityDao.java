package com.rekklesdroid.android.myfavouriteapplication.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


@Dao
public interface AdverbQualityDao {

    @Insert
    void insertAllQualityAdverbs(AdverbQuality... qualityAdverbs);

    @Query("SELECT * FROM quality_adverbs ORDER BY RANDOM() LIMIT 1")
    AdverbQuality getRandomQualityAdverb();

}
