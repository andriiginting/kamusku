package com.example.andriginting.kamus_ku.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.andriginting.kamus_ku.model.KamusIndoModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.andriginting.kamus_ku.database.DatabaseContract.IndonesiaEnglishCol.DETAILS_INDONESIA;
import static com.example.andriginting.kamus_ku.database.DatabaseContract.IndonesiaEnglishCol.WORDS_INDONESIA;
import static com.example.andriginting.kamus_ku.database.DatabaseContract.TABLE_INDONESIA_ENGLISH;

/**
 * Created by Andri Ginting on 12/24/2017.
 */

public class KamusIndonesiaEnglishHelper  {
    private Context context;
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase database;

    public KamusIndonesiaEnglishHelper(Context context) {
        this.context = context;
    }

    public KamusIndonesiaEnglishHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void closeIndo(){
        databaseHelper.close();
    }

    public ArrayList<KamusIndoModel> getDataByWordIndo(String word){
        String result = "";
        Cursor cursor = database.query(TABLE_INDONESIA_ENGLISH, null, WORDS_INDONESIA + " LIKE ?", new String[]{"%" + word + "%"},null, null, WORDS_INDONESIA);
        cursor.moveToFirst();
        ArrayList<KamusIndoModel> arrayList = new ArrayList<>();
        KamusIndoModel kamusIndoModel;
        if(cursor.getCount()>0){
            do{
                kamusIndoModel = new KamusIndoModel();
                kamusIndoModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusIndoModel.setWords(cursor.getString(cursor.getColumnIndexOrThrow(WORDS_INDONESIA)));
                kamusIndoModel.setDetails(cursor.getString(cursor.getColumnIndexOrThrow(DETAILS_INDONESIA)));

                arrayList.add(kamusIndoModel);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public void beginTransactionIndo(){
        database.beginTransaction();
    }

    public void setTransactionSuccessIndo(){
        database.setTransactionSuccessful();
    }

    public void endTransactionIndo(){
        database.endTransaction();
    }

    public void insertTransactionIndo(KamusIndoModel kamusIndoModel){
        String sql = "INSERT INTO " + TABLE_INDONESIA_ENGLISH + " (" + WORDS_INDONESIA + ", " + DETAILS_INDONESIA + ") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, kamusIndoModel.getWords());
        stmt.bindString(2, kamusIndoModel.getDetails());
        stmt.execute();
        stmt.clearBindings();
    }
}
