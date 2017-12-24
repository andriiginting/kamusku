package com.example.andriginting.kamus_ku.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.andriginting.kamus_ku.model.KamusEnglishModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.andriginting.kamus_ku.database.DatabaseContract.EnglishIndonesiaCol.DETAILS_ENGLISH;
import static com.example.andriginting.kamus_ku.database.DatabaseContract.EnglishIndonesiaCol.WORDS_ENGLISH;
import static com.example.andriginting.kamus_ku.database.DatabaseContract.TABLE_ENGLISH_INDONESIA;

/**
 * Created by Andri Ginting on 12/24/2017.
 */

public class KamusEnglishIndonesiaHelper  {
    private Context context;
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase database;

    public KamusEnglishIndonesiaHelper(Context context) {
        this.context = context;
    }

    public KamusEnglishIndonesiaHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    public ArrayList<KamusEnglishModel> getDataByWord(String word){
        String result = "";
        Cursor cursor = database.query(TABLE_ENGLISH_INDONESIA,null, WORDS_ENGLISH + " LIKE ?", new String[]{"%" + word + "%"},null, null,WORDS_ENGLISH);
        cursor.moveToFirst();
        ArrayList<KamusEnglishModel> arrayList = new ArrayList<>();
        KamusEnglishModel kamusEnglishModel;
        if(cursor.getCount()>0){
            do{
                kamusEnglishModel = new KamusEnglishModel();
                kamusEnglishModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusEnglishModel.setWords(cursor.getString(cursor.getColumnIndexOrThrow(WORDS_ENGLISH)));
                kamusEnglishModel.setDetails(cursor.getString(cursor.getColumnIndexOrThrow(DETAILS_ENGLISH)));

                arrayList.add(kamusEnglishModel);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }


    public void beginTransaction(){
        database.beginTransaction();
    }

    public void setTransactionSuccess(){
        database.setTransactionSuccessful();
    }

    public void endTransaction(){
        database.endTransaction();
    }

    public void insertTransaction(KamusEnglishModel kamusEnglishModel){
        String sql = "INSERT INTO " + TABLE_ENGLISH_INDONESIA + " (" + WORDS_ENGLISH + ", " + DETAILS_ENGLISH + ") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, kamusEnglishModel.getWords());
        stmt.bindString(2, kamusEnglishModel.getDetails());
        stmt.execute();
        stmt.clearBindings();
    }
}
