package com.mycompany.sportstats.Team.Match;

import java.util.ArrayList;

public class NuestroEquipo {
    private String nombre;

    public NuestroEquipo(ArrayList<String> nombre){
        this.nombre = String.join(" ", nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
