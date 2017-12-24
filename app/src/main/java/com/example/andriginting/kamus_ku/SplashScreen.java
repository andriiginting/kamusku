package com.example.andriginting.kamus_ku;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.andriginting.kamus_ku.activity.MainActivity;
import com.example.andriginting.kamus_ku.database.KamusEnglishIndonesiaHelper;
import com.example.andriginting.kamus_ku.database.KamusIndonesiaEnglishHelper;
import com.example.andriginting.kamus_ku.model.KamusEnglishModel;
import com.example.andriginting.kamus_ku.model.KamusIndoModel;
import com.example.andriginting.kamus_ku.util.FirstPreference;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SplashScreen extends AppCompatActivity {

    KamusEnglishIndonesiaHelper englishIndonesia;
    KamusIndonesiaEnglishHelper indonesiaEnglish;

    //untuk cek load data pertama sekali di install
    FirstPreference firstPreference;
    Boolean firsInstalled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new LoadData().execute();
    }

    private class LoadData extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            englishIndonesia = new KamusEnglishIndonesiaHelper(getApplicationContext());
            indonesiaEnglish = new KamusIndonesiaEnglishHelper(getApplicationContext());
            firstPreference = new FirstPreference(SplashScreen.this);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            firsInstalled = firstPreference.getFirstRun();

            if (firsInstalled) {
                ArrayList<KamusEnglishModel> englishModels = LoadEnglishData();
                ArrayList<KamusIndoModel> indonesiaModels = LoadIndonesiaData();

                //untuk kamus inggris
                englishIndonesia.open();
                englishIndonesia.beginTransaction();
                try {
                    for (KamusEnglishModel model : englishModels) {
                        englishIndonesia.insertTransaction(model);
                    }
                    englishIndonesia.setTransactionSuccess();
                } catch (Exception e) {
                    e.getMessage();
                }
                englishIndonesia.endTransaction();
                englishIndonesia.close();

                //untuk kamus indonesia
                indonesiaEnglish.open();
                indonesiaEnglish.beginTransactionIndo();
                try {
                    for (KamusIndoModel model : indonesiaModels) {
                        indonesiaEnglish.insertTransactionIndo(model);
                    }
                    indonesiaEnglish.setTransactionSuccessIndo();
                } catch (Exception e) {
                    e.getMessage();
                }
                indonesiaEnglish.endTransactionIndo();
                indonesiaEnglish.closeIndo();

                firstPreference.setFirstInstalled(false);
            } else {
                try {
                    synchronized (this) {
                        this.wait(2000);
                    }
                } catch (Exception e) {

                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private ArrayList<KamusEnglishModel> LoadEnglishData() {
        ArrayList<KamusEnglishModel> kamusEnglishModels = new ArrayList<>();
        BufferedReader reader;
        int count = 0;
        String baris = null;

        try {
            Resources resources = getResources();
            InputStream dataEnglishDict = resources.openRawResource(R.raw.english_indonesia);
            reader = new BufferedReader(new InputStreamReader(dataEnglishDict));

            do {
                baris = reader.readLine();
                String[] split = baris.split("\t");

                KamusEnglishModel kamusEnglishModel = new KamusEnglishModel(split[0], split[1]);
                kamusEnglishModels.add(kamusEnglishModel);
            } while (baris != null);
        } catch (Exception e) {
            e.getMessage();
        }
        return kamusEnglishModels;
    }

    private ArrayList<KamusIndoModel> LoadIndonesiaData() {
        ArrayList<KamusIndoModel> kamusIndoModels = new ArrayList<>();
        BufferedReader reader;
        int count = 0;
        String baris = null;

        try {
            Resources resources = getResources();
            InputStream dataEnglishDict = resources.openRawResource(R.raw.english_indonesia);
            reader = new BufferedReader(new InputStreamReader(dataEnglishDict));

            do {
                baris = reader.readLine();
                String[] split = baris.split("\t");

                KamusIndoModel kamusEnglishModel = new KamusIndoModel(split[0], split[1]);
                kamusIndoModels.add(kamusEnglishModel);
            } while (baris != null);
        } catch (Exception e) {
            e.getMessage();
        }
        return kamusIndoModels;
    }
}
