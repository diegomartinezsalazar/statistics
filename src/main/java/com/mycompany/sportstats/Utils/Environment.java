package com.mycompany.sportstats.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Environment {
    public String database_jdbc;
    public String sqlServerConnection;
    public String mySQLConnection;
    public Boolean exportToExcel;
    ResourceBundle sportstats;

    public Map <String, String> values = new HashMap<>();

    public void Environment(){
        sportstats = ResourceBundle.getBundle("sportstats");
        String value;
        String key;
        while (sportstats.getKeys().hasMoreElements()){
            key = sportstats.getKeys().nextElement();
            value = readKey(key);
            if (value != null){
                values.put(key, value);
            }
        }

    }

    private String readKey(String key){
        return (sportstats.containsKey(key)?sportstats.containsKey(key):null;
    }

    public String propertyValue(String key){
        return values.get
    }
}
