import Match.Alineacion;
import Match.Convocatoria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    static BBDD database = new BBDD();
    static ArrayList match = new ArrayList();
    static ArrayList alineacionBBDD = new ArrayList();
    static ArrayList tanteoBBDD = new ArrayList();
    static ArrayList cambioBBDD = new ArrayList();
    static ArrayList convocatoriaBBDD = new ArrayList();
    static ArrayList tiempoBBDD = new ArrayList();
    static ArrayList finalDeSetBBDD = new ArrayList();
    static ArrayList errorContrarioBBDD = new ArrayList();
    static ArrayList puntoContrarioBBDD = new ArrayList();
    static ArrayList recepcionBBDD = new ArrayList();
    static ArrayList colocacionBBDD = new ArrayList();
    static ArrayList remateBBDD = new ArrayList();
    static ArrayList bloqueoBBDD = new ArrayList();
    static ArrayList defensaBBDD = new ArrayList();
    static ArrayList saqueBBDD = new ArrayList();
    static String alineacionPartido;
    static String tanteoPartido;
    static String cambioPartido;
    static String convocatoriaPartido;
    static String tiempoPartido;
    static String finalDeSetPartido;
    static String errorContrarioPartido;
    static String puntoContrarioPartido;
    static String recepcionPartido;
    static String colocacionPartido;
    static String rematePartido;
    static String bloqueoPartido;
    static String defensaPartido;
    static String saquePartido;

    public static void main(String[] args) throws Exception{
        Utils prueba = new Utils();
        //File fichero = new File("Stats.txt");
        System.out.println("Comienza lectura");
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        File conf = new File(classloader.getResource("Conf.txt").getFile());
        BufferedReader br = new BufferedReader(new FileReader(conf.getPath()));
        String everything = "";
        UtilsNumber.loadNumbers();

        try {
            database = new BBDD();
            database.openconnection();

            database.deleteRawMatchTable();
            loadData();
            
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

        //ArrayList<String> lecturaBruta = prueba.readFile(fichero);
        //ArrayList<String> lectura;

        String fileInText = Utils.readFile(everything, Charset.defaultCharset());
        System.out.println(fileInText);
        System.out.println(formatFile(fileInText));

        ArrayList<String> lectura = Utils.stringToArray(formatFile(fileInText));
        
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
        database.openconnection();

        alineacionBBDD = database.readCloseWordsFromBBDD("ALINEACION");
        tanteoBBDD = database.readCloseWordsFromBBDD("TANTEO");
        cambioBBDD = database.readCloseWordsFromBBDD("CAMBIO");
        convocatoriaBBDD = database.readCloseWordsFromBBDD("CONVOCATORIA");
        tiempoBBDD = database.readCloseWordsFromBBDD("TIEMPO");
        finalDeSetBBDD = database.readCloseWordsFromBBDD("FINALDESET");
        errorContrarioBBDD = database.readCloseWordsFromBBDD("ERRORCONTRARIO");
        puntoContrarioBBDD = database.readCloseWordsFromBBDD("PUNTOCONTRARIO");
        saqueBBDD = database.readSkillsFromBBDD("SAQUE");
        remateBBDD = database.readSkillsFromBBDD("REMATE");
        recepcionBBDD = database.readSkillsFromBBDD("RECEPCION");
        colocacionBBDD = database.readSkillsFromBBDD("COLOCACION");
        defensaBBDD = database.readSkillsFromBBDD("DEFENSA");
        bloqueoBBDD = database.readSkillsFromBBDD("BLOQUEO");
    }

    public static void formatMatch(){
        String movement;
        ArrayList jugada;
        boolean jugadaTerminada;
        // Voy leyendo el fichero por orden de lo que me tengo que ir encontrando
        if (match.size() == 0){
            System.out.println("Tamaño 0 del partido");
        } else {
            for (int i= 0; i < match.size(); i++) {
                movement = match.get(i).toString();
                jugada = new ArrayList();
                jugadaTerminada = false;
                jugada.add(movement);
                while ((!jugadaTerminada) && (i < match.size())){
                    if (i != match.size()-1){
                        if (!database.isKeyWord(match.get(++i).toString())){
                            jugada.add(match.get(i).toString());
                        } else {
                            jugadaTerminada = true;
                            i--;
                            imprimirArray(jugada);
                        }
                    } else {
                        //if (!database.isKeyWord(match.get(++i).toString())) {
                        //    jugada.add(match.get(i).toString());
                        //} else {
                            jugadaTerminada = true;
                            imprimirArray(jugada);
                        //}
                    }
                }
            }
        }
    }

    public static void generaJugada(ArrayList<String> movement, String matchId){

        String typeOfMovement = movement.get(0).toString();
        //Si es convocatoria
        if (esConvocatoria(typeOfMovement)) {
            // Generar convocatoria
            Convocatoria convocatoria = new Convocatoria(matchId, movement);
        } else if (esAlienacion(typeOfMovement)){
            // Generar alineación
            Alineacion alineacion= new Alineacion(matchId, movement);
        } else if (esTanteo(typeOfMovement)) {
            // Generar tanteo
        } else if (esCambio(typeOfMovement)) {
            // Generar cambio
        } else if (esTiempo(typeOfMovement)) {
            // Generar tiempo
        } else if (esFinDeSet(typeOfMovement)) {
            // Generar fin de set
        } else if (esErrorContrario(typeOfMovement)) {
            // Generar error contrario
        } else if (esPuntoContrario(typeOfMovement)) {
            // Generar punto contrario
        } else if (esSaque(typeOfMovement)) {
            // Generar saque
        } else if (esRecepcion(typeOfMovement)) {
            // Generar recepción
        } else if (esColocacion(typeOfMovement)) {
            // Generar colocación
        } else if (esRemate(typeOfMovement)) {
            // Generar remate
        } else if (esBloqueo(typeOfMovement)) {
            // Generar bloqueo
        } else if (esDefensa(typeOfMovement)) {
            // Generar defensa
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

    public static boolean esConvocatoria (String data){
        if (convocatoriaBBDD.contains(data)) {
            return true;
        }
        return false;
    }

    public static boolean esTiempo (String data){
        if (tiempoBBDD.contains(data)) {
            return true;
        }
        return false;
    }

    public static boolean esFinDeSet (String data){
        if (finalDeSetBBDD.contains(data)) {
            return true;
        }
        return false;
    }

    public static boolean esErrorContrario (String data){
        if (errorContrarioBBDD.contains(data)) {
            return true;
        }
        return false;
    }

    public static boolean esPuntoContrario (String data){
        if (puntoContrarioBBDD.contains(data)) {
            return true;
        }
        return false;
    }

    public static boolean esSaque (String data){
        if (saqueBBDD.contains(data)) {
            return true;
        }
        return false;
    }

    public static boolean esRecepcion (String data){
        if (recepcionBBDD.contains(data)) {
            return true;
        }
        return false;
    }

    public static boolean esColocacion (String data){
        if (colocacionBBDD.contains(data)) {
            return true;
        }
        return false;
    }

    public static boolean esRemate (String data){
        if (remateBBDD.contains(data)) {
            return true;
        }
        return false;
    }

    public static boolean esBloqueo (String data){
        if (bloqueoBBDD.contains(data)) {
            return true;
        }
        return false;
    }

    public static boolean esDefensa (String data){
        if (defensaBBDD.contains(data)) {
            return true;
        }
        return false;
    }

    public static void imprimirArray (ArrayList<String> array){
        String jugada = "";
        for (String data: array
             ) {
            jugada = jugada + " " + data;
        }
        System.out.println(jugada);
    }
    
    public static String formatFile (String fileInText) {
        String result = "";
        result = fileInText.toUpperCase();

        for (Map.Entry<String, String> entry : database.allWords.entrySet())
        {
            result = result.replaceAll(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, String> entry : UtilsNumber.mapa.entrySet())
        {
            result = result.replaceAll(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
