import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

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

        database.deleteRawMatchTable();

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
        // Voy leyendo el fichero por orden de lo que me tengo que ir encontrando
        if (match.size() == 0){
            System.out.println("Tamaño 0 del partido");
        } else {
            //Primero formateo números
            for (int i= 0; i < match.size(); i++) {
                movement = match.get(i).toString();
                if (UtilsNumber.isNumberInLetters(movement)){
                    match.set(i, UtilsNumber.numberInNumber(movement));
                }
            }

            for (int i= 0; i < match.size(); i++) {
                movement = match.get(i).toString();

                //Si es convocatoria
                if (esConvocatoria(movement.toString().toUpperCase())) {
                    convocatoriaPartido = convocatoriaBBDD.get(0).toString() + " ";
                    boolean endConvocatoria = false;
                    i++;
                    while (!esConvocatoria(match.get(i).toString().toUpperCase())) {
                        convocatoriaPartido = convocatoriaPartido + " " + match.get(i);
                        i++;
                    }
                    System.out.println(convocatoriaPartido);
                    continue;
                }

                //Si es alineación
                if (esAlienacion(movement.toString().toUpperCase())) {
                    alineacionPartido = alineacionBBDD.get(0).toString() + " " +
                            match.get(++i) + " " +
                            match.get(++i) + " " +
                            match.get(++i) + " " +
                            match.get(++i) + " " +
                            match.get(++i) + " " +
                            match.get(++i);
                    //i--;
                    System.out.println(alineacionPartido);
                    continue;
                }

                if (esTanteo(movement.toString().toUpperCase())) {
                    tanteoPartido = tanteoBBDD.get(0).toString() + " " +
                            match.get(++i) + " " +
                            match.get(++i);
                    //i--;
                    System.out.println(tanteoPartido);
                    continue;
                }

                if (esCambio(movement.toString().toUpperCase())) {
                    cambioPartido = cambioBBDD.get(0).toString() + " " +
                            match.get(++i) + " " +
                            match.get(++i);
                    //i--;
                    System.out.println(cambioPartido);
                    continue;
                }

                if (esTiempo(movement.toString().toUpperCase())) {
                    tiempoPartido = tiempoBBDD.get(0).toString();
                    System.out.println(tiempoPartido);
                    continue;
                }

                if (match.size() > i+2){
                    if (esFinDeSet(movement.toString().toUpperCase() + " " + match.get(++i).toString().toUpperCase() + " " + match.get(++i).toString().toUpperCase())) {
                        finalDeSetPartido = finalDeSetBBDD.get(0).toString();
                        System.out.println(finalDeSetPartido);
                        continue;
                    } else {
                        --i;
                        --i;
                    }
                }

                if (match.size() > i+1){
                    if (esErrorContrario(movement.toString().toUpperCase() + " " + match.get(++i).toString().toUpperCase())) {
                        errorContrarioPartido = errorContrarioBBDD.get(0).toString();
                        System.out.println(errorContrarioPartido);
                        continue;
                    } else {
                        --i;
                    }
                }

                if (match.size() > i+1){
                    if (esPuntoContrario(movement.toString().toUpperCase() + " " + match.get(++i).toString().toUpperCase())) {
                        puntoContrarioPartido = puntoContrarioBBDD.get(0).toString();
                        System.out.println(puntoContrarioPartido);
                        continue;
                    } else {
                        --i;
                    }
                }

                // Si es por aquí, entonces tiene que ser un movimiento de un juegador
                if (UtilsNumber.isNumber(movement)){
                    if (esSaque(match.get(i+1).toString().toUpperCase())) {
                        if (match.size() > i + 5) {
                            saquePartido = movement + " " +
                                    saqueBBDD.get(0).toString() + " " +
                                    match.get(++i) + " " +
                                    match.get(++i) + " " +
                                    match.get(++i) + " " +
                                    match.get(++i) + " " +
                                    match.get(++i) + " " +
                                    match.get(++i);
                            //i--;
                            System.out.println(saquePartido);
                            continue;
                        }
                    }
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
}
