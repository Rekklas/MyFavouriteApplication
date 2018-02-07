package com.rekklesdroid.android.myfavouriteapplication.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.rekklesdroid.android.myfavouriteapplication.data.entities.AdverbQuality;
import com.rekklesdroid.android.myfavouriteapplication.data.entities.AdverbQuantity;
import com.rekklesdroid.android.myfavouriteapplication.data.entities.Noun;
import com.rekklesdroid.android.myfavouriteapplication.data.entities.Verb;

import java.util.concurrent.Executors;


@Database(entities = {AdverbQuantity.class, AdverbQuality.class,
        Verb.class, Noun.class}, version = 1, exportSchema = false)
public abstract class WordsDatabase extends RoomDatabase {

    private static WordsDatabase INSTANCE;

    public abstract AdverbQuantityDao adverbQuantityDao();

    public abstract AdverbQualityDao adverbQualityDao();

    public abstract VerbDao verbDao();

    public abstract NounDao nounDao();

    public synchronized static WordsDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    private static WordsDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context, WordsDatabase.class,
                "Words.db")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                WordsDatabase instance = getInstance(context);
                                instance.adverbQualityDao().
                                        insertAllQualityAdverbs(AdverbQuality.populateQualityAdverbs());
                                instance.adverbQuantityDao().
                                        insertAllQuantityAdverbs(AdverbQuantity.populateQuantityAdverbs());
                                instance.verbDao().
                                        insertAllVerbs(Verb.populateVerbs());
                                instance.nounDao().
                                        insertAllNouns(Noun.populateNouns());
                            }
                        });
                    }
                })
                .build();
    }

}
