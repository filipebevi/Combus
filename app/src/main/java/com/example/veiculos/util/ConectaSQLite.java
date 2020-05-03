package com.example.veiculos.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class ConectaSQLite extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "db_veiculos";
    public static String TABELA = "tarefas";

    public ConectaSQLite(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS "+TABELA+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " tipo TEXT NOT NULL," +
                " data TEXT NOT NULL,"+
                " valor REAL NOT NULL,"+
                " litros REAL NOT NULL,"+
                " posto TEXT NOT NULL,"+
                " km REAL NOT NULL,"+
                " total REAL NOT NULL); ";
        try {
            db.execSQL(sql);
            Log.i("INFO","TABELA CRIADA COM SUCESSO");
        }catch (Exception e){
            Log.i("ERRO","ERRO AO CRIAR TABELA");
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
