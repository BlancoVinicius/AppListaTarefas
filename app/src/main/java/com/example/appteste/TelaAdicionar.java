package com.example.appteste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.appteste.helper.DAOTarefa;
import com.example.appteste.model.Tarefa;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class TelaAdicionar extends AppCompatActivity {

    private TextInputEditText txtItem;
    private Tarefa tarefaAtual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adicionar);

        txtItem = findViewById(R.id.txtAddTarefa);
        //recuperar tarefa
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefa");
        if(tarefaAtual != null){
            txtItem.setText(tarefaAtual.getName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_tela_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.addItem){

            if(tarefaAtual == null) {//salvar nova tarefa
                DAOTarefa daoTarefa = new DAOTarefa(getApplicationContext());
                String text = txtItem.getText().toString();
                if (!text.isEmpty()) {
                    Tarefa tarefa = new Tarefa(text);
                    boolean resultSave = daoTarefa.save(tarefa);
                    if (resultSave) {
                        finish();
                        Toast.makeText(getApplicationContext(), "Tarefa salva com sucesso", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_SHORT).show();
                    }
                }
            }else {//editar tarefa
                DAOTarefa daoTarefa = new DAOTarefa(getApplicationContext());
                tarefaAtual.setName(txtItem.getText().toString());
                boolean resultSave = daoTarefa.update(tarefaAtual);
                if (resultSave) {
                    finish();
                    Toast.makeText(getApplicationContext(), "Tarefa editada com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_SHORT).show();
                }
            }
        }

        return super.onOptionsItemSelected(item);
    }

}