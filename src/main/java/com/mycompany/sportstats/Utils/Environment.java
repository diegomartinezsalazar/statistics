package com.mycompany.sportstats.Utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Environment {
    private static ResourceBundle sportstats;
    private static String operatingSystem;

    private static Map <String, String> values = new HashMap<>();

    public static void readEnvironment(){
        sportstats = ResourceBundle.getBundle("sportstats");
        String value;
        String key;
        Enumeration<String> properties = sportstats.getKeys();
        while (properties.hasMoreElements()){
            key = properties.nextElement();
            value = readKey(key);
            if (value != null){
                values.put(key, value);
            }
        }

        operatingSystem = System.getProperty("os.name");
    }

    private static String readKey(String key){
        return (sportstats.containsKey(key)?sportstats.getString(key):null);
    }

    public static String getPropertyValue(String key){
        if (sportstats == null){
            readEnvironment();
        }
        return values.getOrDefault(key, null);
    }

    public static boolean isWindows(){
        return operatingSystem.contains("Windows");
    }

    public static boolean isMac(){
        return operatingSystem.contains("Mac");
    }
}
