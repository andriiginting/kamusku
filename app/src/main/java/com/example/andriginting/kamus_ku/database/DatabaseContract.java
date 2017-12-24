package com.example.andriginting.kamus_ku.database;

import android.provider.BaseColumns;

/**
 * Created by Andri Ginting on 12/24/2017.
 */

public class DatabaseContract {
    static String TABLE_ENGLISH_INDONESIA = "table_english_indonesia";
    static String TABLE_INDONESIA_ENGLISH = "table_indonesia_english";

    static final class EnglishIndonesiaCol implements BaseColumns{
        static String WORDS_ENGLISH = "words_english";
        static String DETAILS_ENGLISH = "details_english";
    }

    static final class IndonesiaEnglishCol implements BaseColumns{
        static String WORDS_INDONESIA = "words_indonesia";
        static String DETAILS_INDONESIA = "details_indonesia";
    }
}
