package com.rekklesdroid.android.myfavouriteapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rekklesdroid.android.myfavouriteapplication.data.WordsDatabase;
import com.rekklesdroid.android.myfavouriteapplication.data.entities.Word;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    /**
     * Space that is used for creating StringBuilder object
     * in {@link #shareCompliment} method
     */
    private static final String BLANK_SPACE = " ";
    /**
     * Constants which are used by onSaveInstanceState method
     * {@link #onSaveInstanceState(Bundle)}
     */
    private static final String STATE_QUANTITY_ADVERB = "quantity_adverb";
    private static final String STATE_QUALITY_ADVERB = "quality_adverb";
    private static final String STATE_VERB = "verb";
    private static final String STATE_NOUN = "noun";

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

    @BindView(R.id.imgbtn_refresh)
    ImageButton imgBtnRefresh;
    @BindView(R.id.imgbtn_send)
    ImageButton imgBtnSend;

    WordsDatabase wordsDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setupSharedPreferences();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupDrawables();

        wordsDb = WordsDatabase.getInstance(getApplicationContext());

        if (savedInstanceState != null) {
            textQuantityAdverb.setText(savedInstanceState.getString(STATE_QUANTITY_ADVERB));
            textQualityAdverb.setText(savedInstanceState.getString(STATE_QUALITY_ADVERB));
            textVerb.setText(savedInstanceState.getString(STATE_VERB));
            textNoun.setText(savedInstanceState.getString(STATE_NOUN));
        } else{
            refreshAll();
        }
    }

    /**
     * This method setup proper Application's theme according
     * to shared preferences
     */
    private void setupSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String theme = sharedPreferences.getString(getResources().getString(R.string.pref_change_theme_key),
                getResources().getString(R.string.pref_default_theme_value));
        if (theme.equals(getResources().getString(R.string.pref_fire_theme_value))) {
            setTheme(R.style.AppThemeFire);
        } else if (theme.equals(getResources().getString(R.string.pref_water_theme_value))) {
            setTheme(R.style.AppThemeWater);
        }
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    /**
     * This method is invoked after activity was created and setup
     * correct drawables for Application's theme
     */
    private void setupDrawables() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String theme = sharedPreferences.getString(getResources().getString(R.string.pref_change_theme_key),
                getResources().getString(R.string.pref_default_theme_value));
        if (theme.equals(getResources().getString(R.string.pref_fire_theme_value))) {
            imgBtnRefresh.setImageResource(R.drawable.ic_refresh);
            imgBtnSend.setImageResource(R.drawable.ic_send);
        } else if (theme.equals(getResources().getString(R.string.pref_water_theme_value))) {
            imgBtnRefresh.setImageResource(R.drawable.ic_refresh_blue);
            imgBtnSend.setImageResource(R.drawable.ic_send_blue);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString(STATE_QUANTITY_ADVERB, textQuantityAdverb.getText().toString());
        outState.putString(STATE_QUALITY_ADVERB, textQualityAdverb.getText().toString());
        outState.putString(STATE_VERB, textVerb.getText().toString());
        outState.putString(STATE_NOUN, textNoun.getText().toString());

        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
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
     * This method starts after refresh button was pressed
     * and it invokes {@link #wordChange(TextView)} method for every textView
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    /**
     * Method taht recreates this activity
     *
     * @param sharedPreferences sharedPreferences' Object
     * @param key "key" of preference that was changed
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        recreate();
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
            if (word == null) {
                switch (view.getId()) {
                    case R.id.txt_adverb_quantity:
                        view.setText(getResources().getString(R.string.default_quantity_adverb));
                        break;
                    case R.id.txt_adverb_quality:
                        view.setText(getResources().getString(R.string.default_quality_adverb));
                        break;
                    case R.id.txt_verb:
                        view.setText(getResources().getString(R.string.default_verb));
                        break;
                    case R.id.txt_noun:
                        view.setText(getResources().getString(R.string.default_noun));
                        break;
                }
            } else
                view.setText(word.getWord());
        }
    }
}

