import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    static ArrayList alineacionBBDD = new ArrayList();
    static ArrayList tanteoBBDD = new ArrayList();
    static ArrayList cambioBBDD = new ArrayList();
    static BBDD database = new BBDD();
    static ArrayList match = new ArrayList();
    static String alineacionPartido;
    static String tanteoPartido;
    static String cambioPartido;

    public static void main(String[] args) throws Exception{
        Utils prueba = new Utils();
        //File fichero = new File("Stats.txt");
        System.out.println("Comienza lectura");
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        File conf = new File(classloader.getResource("Conf.txt").getFile());
        BufferedReader br = new BufferedReader(new FileReader(conf.getPath()));
        String everything = "";
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                //sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        } finally {
            br.close();
        }

        File fichero = new File(everything);

        ArrayList<String> lectura = prueba.readFile(fichero);

        database = new BBDD();
        database.openconnection();

        for (String palabra: lectura) {
            database.insertDataRow("1718001", palabra.toString());
            //System.out.println(palabra.toString());
        }

        // Una vez pasado el fichero, lo organizo
        System.out.println("Final lectura");
        System.out.println("Comienzo formateo");
        prepareMatch("1718001");
        loadData();
        formatMatch();
        System.out.println("Final formateo");
    }

    public static void prepareMatch(String matchId){
        // Leer de la base de datos el partido
        database.openconnection();

        match = database.readMatch(matchId);
        System.out.println(match.size());
    }

    public static void loadData (){
        database = new BBDD();
        database.openconnection();

        alineacionBBDD = database.readDataBBDD("ALINEACION");
        tanteoBBDD = database.readDataBBDD("TANTEO");
        cambioBBDD = database.readDataBBDD("CAMBIO");
    }

    public static void formatMatch(){
        String movement;
        // Voy leyendo el fichero por orden de lo que me tengo que ir encontrando
        if (match.size() == 0){
            System.out.println("Tamaño 0 del partido");
        } else {
            for (int i= 0; i < match.size(); i++) {
                //Si es alineación
                movement = match.get(i).toString();
                if (esAlienacion(movement.toString().toUpperCase())) {
                    alineacionPartido = alineacionBBDD.get(0).toString() + " ";
                    i++;
                    alineacionPartido = alineacionPartido + match.get (i) + " ";
                    i++;
                    alineacionPartido = alineacionPartido + match.get (i) + " ";
                    i++;
                    alineacionPartido = alineacionPartido + match.get (i) + " ";
                    i++;
                    alineacionPartido = alineacionPartido + match.get (i) + " ";
                    i++;
                    alineacionPartido = alineacionPartido + match.get (i) + " ";
                    i++;
                    alineacionPartido = alineacionPartido + match.get (i);
                    System.out.println(alineacionPartido);
                }

                if (esTanteo(movement.toString().toUpperCase())) {
                    tanteoPartido = tanteoBBDD.get(0).toString() + " ";
                    i++;
                    tanteoPartido = tanteoPartido + match.get (i) + " ";
                    i++;
                    tanteoPartido = tanteoPartido + match.get (i);
                    System.out.println(tanteoPartido);
                }

                if (esCambio(movement.toString().toUpperCase())) {
                    cambioPartido = cambioBBDD.get(0).toString() + " ";
                    i++;
                    cambioPartido = cambioPartido + match.get (i) + " ";
                    i++;
                    cambioPartido = cambioPartido + match.get (i);
                    System.out.println(cambioPartido);
                }
            }
        }
    }

    public static boolean esAlienacion (String data){

        if (alineacionBBDD.contains(data)) {
            return true;
        }
        return false;
    }

    public static boolean esTanteo (String data){

        if (tanteoBBDD.contains(data)) {
            return true;
        }
        return false;
    }

    public static boolean esCambio (String data){

        if (cambioBBDD.contains(data)) {
            return true;
        }
        return false;
    }
}
