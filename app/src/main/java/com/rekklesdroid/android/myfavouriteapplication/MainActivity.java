package com.rekklesdroid.android.myfavouriteapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.rekklesdroid.android.myfavouriteapplication.data.WordsDatabase;
import com.rekklesdroid.android.myfavouriteapplication.data.entities.Word;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    /**
     * Space that is used for creating StringBuilder object
     * in {@link #shareCompliment} method
     */
    private static final String BLANK_SPACE = " ";

    @BindView(R.id.txt_word_you)
    TextView textWordYou;
    @BindView(R.id.txt_adverb_quantity)
    TextView textQuantityAdverb;
    @BindView(R.id.txt_adverb_quality)
    TextView textQualityAdverb;
    @BindView(R.id.txt_verb)
    TextView textVerb;
    @BindView(R.id.txt_noun)
    TextView textNoun;

    WordsDatabase wordsDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        wordsDb = WordsDatabase.getInstance(getApplicationContext());
        refreshAll();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * This method invokes new AsyncTask for doing query to database,
     * that retrieve word from database{@link RetrieveWordTask#doInBackground(View...)}
     * and set this word to certain TextView object{@link RetrieveWordTask#onPostExecute(Word)}
     *
     * @param textView TextView object which was clicked on
     */
    @OnClick({R.id.txt_adverb_quality, R.id.txt_adverb_quantity, R.id.txt_verb, R.id.txt_noun})
    public void wordChange(final TextView textView) {

        new RetrieveWordTask().execute(textView);
    }

    /**
     *  This method starts after refresh button was pressed
     *  and it invokes {@link #wordChange(TextView)} method for every textView
     *
     */
    @OnClick(R.id.imgbtn_refresh)
    void refreshAll() {
        wordChange(textQuantityAdverb);
        wordChange(textQualityAdverb);
        wordChange(textVerb);
        wordChange(textNoun);
    }

    /**
     * Method starts after send button was pressed.
     * It creates new message getting Text from all textViews and make
     * implicit intent to other Apps with ACTION_SEND property
     *
     */
    @OnClick(R.id.imgbtn_send)
    void shareCompliment() {

        StringBuilder complimentMessage = new StringBuilder();
        complimentMessage.append(textWordYou.getText()).append(BLANK_SPACE)
                .append(textQuantityAdverb.getText()).append(BLANK_SPACE)
                .append(textQualityAdverb.getText()).append(BLANK_SPACE)
                .append(textVerb.getText()).append(BLANK_SPACE)
                .append(textNoun.getText()).append("!");

        // Create the text message with a string
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, complimentMessage.toString());
        sendIntent.setType("text/plain");

// Verify that the intent will resolve to an activity
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }

    }

    /**
     * AsyncTask class which performs
     * retrieving data in background thread from database
     */
    @SuppressLint("StaticFieldLeak")
    private class RetrieveWordTask extends AsyncTask<View, Void, Word> {

        TextView view;

        @Override
        protected Word doInBackground(View... views) {
            Word word = null;
            for (View view : views) {
                switch (view.getId()) {
                    case R.id.txt_adverb_quantity:
                        word = wordsDb.adverbQuantityDao().getRandomQuantityAdverb();
                        break;
                    case R.id.txt_adverb_quality:
                        word = wordsDb.adverbQualityDao().getRandomQualityAdverb();
                        break;
                    case R.id.txt_verb:
                        word = wordsDb.verbDao().getRandomVerb();
                        break;
                    case R.id.txt_noun:
                        word = wordsDb.nounDao().getRandomNoun();
                        break;
                }
                this.view = (TextView) view;
            }
            return word;
        }

        @Override
        protected void onPostExecute(Word word) {
            view.setText(word.getWord());
        }
    }
}

