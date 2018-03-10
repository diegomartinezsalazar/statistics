import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
    public ArrayList<String> readFile(File fichero) {

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

    static String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}


