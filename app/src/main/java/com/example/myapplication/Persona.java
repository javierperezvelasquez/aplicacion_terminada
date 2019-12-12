package com.example.myapplication;

public class Persona {
    public String nombre;
    public String comentario;

    public Persona(String nombre, String comentario) {
        this.nombre = nombre;
        this.comentario = comentario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
