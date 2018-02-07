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

    private static final String BLANK_SPACE = " ";

    @BindView(R.id.txt_word_you)
    TextView text1;
    @BindView(R.id.txt_adverb_quantity)
    TextView text2;
    @BindView(R.id.txt_adverb_quality)
    TextView text3;
    @BindView(R.id.txt_verb)
    TextView text4;
    @BindView(R.id.txt_noun)
    TextView text5;

    WordsDatabase wordsDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        wordsDb = WordsDatabase.getInstance(getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshAll();
    }


    @OnClick({R.id.txt_adverb_quality, R.id.txt_adverb_quantity, R.id.txt_verb, R.id.txt_noun})
    public void wordChange(final TextView textView) {
        new RetrieveWordTask().execute(textView);
    }

    @OnClick(R.id.imgbtn_refresh)
    void refreshAll() {
        wordChange(text2);
        wordChange(text3);
        wordChange(text4);
        wordChange(text5);
    }

    @OnClick(R.id.imgbtn_send)
    void shareCompliment() {

        StringBuilder complimentMessage = new StringBuilder();
        complimentMessage.append(text1.getText()).append(BLANK_SPACE)
                .append(text2.getText()).append(BLANK_SPACE)
                .append(text3.getText()).append(BLANK_SPACE)
                .append(text4.getText()).append(BLANK_SPACE)
                .append(text5.getText()).append("!");

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

