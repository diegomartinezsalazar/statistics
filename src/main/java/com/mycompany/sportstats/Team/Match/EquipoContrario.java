package com.mycompany.sportstats.Team.Match;

import java.util.ArrayList;

public class EquipoContrario {
    private String nombre;

    public EquipoContrario(ArrayList<String> nombre){
        this.nombre = String.join(" ", nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
