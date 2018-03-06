import java.io.File;
import java.io.FileNotFoundException;
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
}


