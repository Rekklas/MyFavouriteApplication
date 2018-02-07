package com.rekklesdroid.android.myfavouriteapplication;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.rekklesdroid.android.myfavouriteapplication.data.entities.Verb;
import com.rekklesdroid.android.myfavouriteapplication.data.VerbDao;
import com.rekklesdroid.android.myfavouriteapplication.data.WordsDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


@RunWith(AndroidJUnit4.class)
public class VerbEntityWriteReadTest {
    private VerbDao verbDao;
    private WordsDatabase wordsDb;

    @Before
    public void createDb(){
        Context context = InstrumentationRegistry.getTargetContext();
        wordsDb = Room.inMemoryDatabaseBuilder(context, WordsDatabase.class).build();
        verbDao = wordsDb.verbDao();
    }

    @After
    public void closeDb() throws IOException{
        wordsDb.close();
    }

    @Test
    public void writeAndReadVerb() throws Exception{
        Verb verb = new Verb("dao");
        verbDao.insertAllVerbs(verb);
        Verb readVerb = verbDao.getRandomVerb();
        assertThat(readVerb, equalTo(verb));
    }
}