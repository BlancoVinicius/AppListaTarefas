package com.example.appteste.model;

import java.io.Serializable;

public class Tarefa implements Serializable {

    private Long id;
    private String name;

    public Tarefa() {
    }

    public Tarefa(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
