package com.example.appteste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.appteste.adapter.MyAdapter;
import com.example.appteste.helper.DAOTarefa;
import com.example.appteste.helper.DbHelper;
import com.example.appteste.helper.RecyclerItemClickListener;
import com.example.appteste.model.Tarefa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Tarefa> tarefaList = new ArrayList<>();
    private MyAdapter myAdapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //para tirar a sombra da action bar
//        getSupportActionBar().setElevation(0);

        ContentValues cv = new ContentValues();
        cv.put("name", "teste");

        ///DbHelper db = new DbHelper(getApplicationContext());


        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TelaAdicionar.class);
                startActivity(intent);
            }
        });


    }

    private void inicializarListTarefas(){

        //criar tarefas dinamicamente do DB
        DAOTarefa daoTarefa = new DAOTarefa(getApplicationContext());
        tarefaList = daoTarefa.listar();

        //add de forma estatica
//        Tarefa t1 = new Tarefa("Tarefa1");
//        Tarefa t2 = new Tarefa("Tarefa2");
//        tarefaList.add(t1);
//        tarefaList.add(t2);

        //criar adapter
        myAdapter = new MyAdapter(tarefaList);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Tarefa tf = tarefaList.get(position);
                                Intent intent = new Intent(getApplicationContext(), TelaAdicionar.class);
                                intent.putExtra("tarefa", tf);
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Log.i("ItemClick", "LongItemClick");
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        inicializarListTarefas();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.salvar){
            Toast.makeText(this, "Item Salvar", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.editar) {
            Toast.makeText(this, "Item editar", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.configurar) {
            Toast.makeText(this, "Item configurar", Toast.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }
}