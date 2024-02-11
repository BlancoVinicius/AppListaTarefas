package com.example.appteste.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.appteste.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class DAOTarefa implements ITarefaDAO{

    private SQLiteDatabase write;
    private SQLiteDatabase header;
    public DAOTarefa(Context context) {
        DbHelper db = new DbHelper(context);
        header = db.getReadableDatabase();
        write = db.getWritableDatabase();
    }

    @Override
    public boolean save(Tarefa tarefa) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("name", tarefa.getName());
            Long linha = write.insert(DbHelper.TABELA_TAREFAS, null, cv);
            Log.i("DB", "linha: " + linha);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean update(Tarefa tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("name", tarefa.getName());

        write.update(DbHelper.TABELA_TAREFAS, cv,"id=?", new String[]{tarefa.getId().toString()} );
        return true;
    }

    @Override
    public boolean delete(Tarefa tarefa) {

        int linhasDeletadas = write.delete(DbHelper.TABELA_TAREFAS, "id=?", new String[]{tarefa.getId().toString()});
        if(linhasDeletadas > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Tarefa> listar() {

        List<Tarefa> tarefas = new ArrayList<>();
        boolean item;

        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + " ;";
        Cursor cursor = write.rawQuery(sql, null);

        item = cursor.moveToFirst();
        while (item){
            Tarefa tarefa = new Tarefa();

            int indexId = cursor.getColumnIndex("id");
            int indexName = cursor.getColumnIndex("name");

            Long campo = cursor.getLong(indexId);
            String name = cursor.getString(indexName);

            tarefa.setId(campo);
            tarefa.setName(name);
            tarefas.add(tarefa);

            item = cursor.moveToNext();
        }
        return tarefas;
    }
}
