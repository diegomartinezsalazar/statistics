package Team.Statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SkillStatistic {
    private String tipo = new String();
    private Map<String, Integer> lista = new HashMap<>();

    public SkillStatistic(ArrayList<String> values){
        for (String value: values
             ) {
            lista.put(value, 0);
        }
    }

    public void addSkill(String key){
        int skillNumber = lista.get(key);

        skillNumber += 1;

        lista.replace(key, skillNumber);
    }

    public void substractSkill(String key){
        int skillNumber = lista.get(key);

        skillNumber -= 1;

        lista.replace(key, skillNumber);
    }
}
