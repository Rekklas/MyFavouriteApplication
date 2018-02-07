package com.rekklesdroid.android.myfavouriteapplication.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.rekklesdroid.android.myfavouriteapplication.data.entities.AdverbQuantity;


@Dao
public interface AdverbQuantityDao {

    @Insert
    void insertAllQuantityAdverbs(AdverbQuantity... quantityAdverbs);

    @Query("SELECT * FROM quantity_adverbs ORDER BY RANDOM() LIMIT 1")
    AdverbQuantity getRandomQuantityAdverb();

}
