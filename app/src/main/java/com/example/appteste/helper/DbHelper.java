package com.example.appteste.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 3;
    public static String NAME_DB = "DB_TAREFAS";
    public static String TABELA_TAREFAS = "tarefas";
    public DbHelper(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS "
                + TABELA_TAREFAS
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL); ";

        try {
            db.execSQL(sql);
            Log.i("DB", "tabela criada");
        }catch (Exception e){
            Log.i("DB", "Erro ao criar tabela");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DB", "tabela atualizada");
    }
}
