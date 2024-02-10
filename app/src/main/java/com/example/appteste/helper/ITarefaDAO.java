package com.example.appteste.helper;

import com.example.appteste.model.Tarefa;

import java.util.List;

public interface ITarefaDAO {

    public boolean save(Tarefa tarefa);
    public boolean update(Tarefa tarefa);
    public boolean delete(Tarefa tarefa);
    public List<Tarefa> listar();
}
