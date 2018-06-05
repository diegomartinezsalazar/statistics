package com.mycompany.sportstats.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public class Utils {
    public static ArrayList<String> readFile(File fichero) {

        //Scanner s = new Scanner(new File("filepath"));
        ArrayList<String> list = new ArrayList<String>();

        try {

            Scanner sc = new Scanner(fichero, "UTF-8");

            while (sc.hasNext()) {
                list.add(sc.next());
            }
            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public static ArrayList<String> stringToArray(String text) {

        //Scanner s = new Scanner(new File("filepath"));
        ArrayList<String> list = new ArrayList<String>();

        try {
            list = new ArrayList<>(Arrays.asList(text.split(" ")));
            System.out.println(list.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public String fileToString(File fichero){
        String content = "";
        try {

            content = readFile("test.txt", Charset.defaultCharset());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return content;
        }
    }

    public static String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static String readFile(com.google.api.services.drive.model.File file, Charset encoding) throws IOException{
        //Scanner scanner = new Scanner (file);
        //return FileUtils.readFileToString(file, encoding.toString());
        return "";
    }
}


