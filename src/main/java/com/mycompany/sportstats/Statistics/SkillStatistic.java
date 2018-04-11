package com.mycompany.sportstats.Statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SkillStatistic {
    private String tipo = new String();
    private Map<String, Integer> lista = new HashMap<>();

    public SkillStatistic(ArrayList<String> values){
        for (String value: values
             ) {
            getLista().put(value, 0);
        }
    }

    public SkillStatistic(SkillStatistic skillStatistic){
        this.setLista(new HashMap<>(skillStatistic.getLista()));
    }

    public void addSkill(String key){
        int skillNumber = getLista().get(key);

        skillNumber += 1;

        getLista().replace(key, skillNumber);
    }

    public void substractSkill(String key){
        int skillNumber = getLista().get(key);

        skillNumber -= 1;

        getLista().replace(key, skillNumber);
    }

    public Map<String, Integer> getLista() {
        return lista;
    }

    public void setLista(Map<String, Integer> lista) {
        this.lista = lista;
    }
}
