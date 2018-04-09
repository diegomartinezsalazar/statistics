import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import com.mycompany.sportstats.BBDD.BBDD;
import com.mycompany.sportstats.Statistics.StatisticsGenerator;
import com.mycompany.sportstats.Team.Match.*;
import com.mycompany.sportstats.Team.Match.Skill.*;
import com.mycompany.sportstats.Utils.Utils;
import com.mycompany.sportstats.Utils.UtilsNumber;

import org.apache.commons.lang3.StringUtils;

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
    static ArrayList doblePositivoBBDD = new ArrayList();
    static ArrayList positivoBBDD = new ArrayList();
    static ArrayList barraBBDD = new ArrayList();
    static ArrayList negativoBBDD = new ArrayList();
    static ArrayList dobleNegativoBBDD = new ArrayList();
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
    static String doblePositivo;
    static String positivo;
    static String barra;
    static String negativo;
    static String dobleNegativo;

    public static final String UTF8_BOM = "\uFeFF";

    public static void main(String[] args) throws Exception{
        Utils prueba = new Utils();
        //File fichero = new File("Stats.txt");
        System.out.println("Comienza lectura");
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        File conf = new File(classloader.getResource("Conf.txt").getFile());
        BufferedReader br = new BufferedReader(new FileReader(conf.getPath()));
        //BufferedReader br  = new BufferedReader(new InputStreamReader(new FileInputStream(conf.getPath()),"ISO-8859-1"));
        //BufferedReader br  = new BufferedReader(new InputStreamReader(new FileInputStream(conf.getPath()),"UTF-8"));
        //BufferedReader br  = new BufferedReader(new InputStreamReader(new FileInputStream(conf.getPath()),"windows-1252"));
        //BufferedReader br  = new BufferedReader(new InputStreamReader(new FileInputStream(conf.getPath()), Charset.forName("Cp1252")));
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
        //System.out.println(formatFile(fileInText));
        fileInText = StringUtils.stripAccents(fileInText);
        System.out.println(fileInText);
        ArrayList<String> lectura = Utils.stringToArray(formatFileOrderingByNumberOfWords(fileInText));
        
        for (String palabra: lectura) {
            database.insertDataRow("1718001", palabra.toString());
            //System.out.println(palabra.toString());
        }

        // Una vez pasado el fichero, lo organizo
        System.out.println("Final lectura");
        System.out.println("Comienzo formateo");
        prepareMatch("1718001");
        loadData();
        Match match = formatMatch();
        System.out.println("Final formateo" + "\n");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("Inicio estadísticas");
        StatisticsGenerator statisticsGenerator = new StatisticsGenerator();
        statisticsGenerator.matchTreatment(match);
        System.out.println("Final estadísticas");

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
        doblePositivoBBDD = database.readValuesFromBBDD("++");
        positivoBBDD = database.readValuesFromBBDD("+");
        barraBBDD = database.readValuesFromBBDD("/");
        negativoBBDD = database.readValuesFromBBDD("-");
        dobleNegativoBBDD = database.readValuesFromBBDD("--");
    }

    public static Match formatMatch(){
        Match match = new Match();
        String movement;
        ArrayList jugada;
        boolean jugadaTerminada;
        Object arrayMovimiento;

        match.setMatchId("1718001");
        // Voy leyendo el fichero por orden de lo que me tengo que ir encontrando
        if (Main.match.size() == 0){
            System.out.println("Tamaño 0 del partido");
        } else {
            //Si la primera letra de la posición es una interrogante, la elimino. Esto es un bug que se corregirá
            if (Main.match.get(0).toString().startsWith("?")){
                Main.match.set(0, Main.match.get(0).toString().substring(1));
            }
            for (int i = 0; i < Main.match.size(); i++) {
                movement = Main.match.get(i).toString();
                jugada = new ArrayList();
                jugadaTerminada = false;
                jugada.add(movement);
                while ((!jugadaTerminada) && (i < Main.match.size())){
                    if (i != Main.match.size()-1){
                        if (!database.isKeyWord(Main.match.get(++i).toString())){
                            jugada.add(Main.match.get(i).toString());
                        } else {
                            jugadaTerminada = true;
                            i--;
                            imprimirArray(jugada);
                            arrayMovimiento = generaJugada(match.getMatchId(), jugada);
                            match.addMovimiento(arrayMovimiento);
                        }
                    } else {
                        //if (!database.isKeyWord(match.get(++i).toString())) {
                        //    jugada.add(match.get(i).toString());
                        //} else {
                            jugadaTerminada = true;
                            imprimirArray(jugada);
                            arrayMovimiento = generaJugada(match.getMatchId(), jugada);
                            match.addMovimiento(arrayMovimiento);
                        //}
                    }
                }
            }
        }

        match.startMatch();
        return match;
    }

    public static Object generaJugada(String matchId, ArrayList<String> movement){

        String typeOfMovement = movement.get(0).toString();
        movement.remove(0);
        //Si es convocatoria
        if (esConvocatoria(typeOfMovement)) {
            // Generar convocatoria
            Convocatoria convocatoria = new Convocatoria(matchId, movement);
            return convocatoria;
        } else if (esAlineacion(typeOfMovement)){
            // Generar alineación
            Alineacion alineacion= new Alineacion(matchId, movement);
            return alineacion;
        } else if (esTanteo(typeOfMovement)) {
            // Generar tanteo
            Tanteo tanteo = new Tanteo(matchId, movement);
            return tanteo;
        } else if (esCambio(typeOfMovement)) {
            // Generar cambio
            Cambio cambio = new Cambio(matchId, movement);
            return cambio;
        } else if (esTiempo(typeOfMovement)) {
            // Generar tiempo
            Tiempo tiempo = new Tiempo(matchId, movement);
            return tiempo;
        } else if (esFinDeSet(typeOfMovement)) {
            // Generar fin de set
            FinDeSet finDeSet = new FinDeSet(matchId, movement);
            return finDeSet;
        } else if (esErrorContrario(typeOfMovement)) {
            // Generar error contrario
            ErrorContrario errorContrario = new ErrorContrario(matchId);
            return errorContrario;
        } else if (esPuntoContrario(typeOfMovement)) {
            // Generar punto contrario
            PuntoContrario puntoContrario = new PuntoContrario(matchId);
            return puntoContrario;
        } else if (esSaque(typeOfMovement)) {
            // Generar saque
            Saque saque = new Saque(matchId, movement);
            return saque;
        } else if (esRecepcion(typeOfMovement)) {
            // Generar recepción
            Recepcion recepcion = new Recepcion(matchId, movement);
            return recepcion;
        } else if (esColocacion(typeOfMovement)) {
            // Generar colocación
            Colocacion colocacion = new Colocacion(matchId, movement);
            return colocacion;
        } else if (esRemate(typeOfMovement)) {
            // Generar remate
            Remate remate = new Remate(matchId, movement);
            return remate;
        } else if (esBloqueo(typeOfMovement)) {
            // Generar bloqueo
            Bloqueo bloqueo = new Bloqueo(matchId, movement);
            return bloqueo;
        } else if (esDefensa(typeOfMovement)) {
            // Generar defensa
            Defensa defensa = new Defensa(matchId, movement);
            return defensa;
        } else {
            return new Object();
        }

    }

    public static boolean esAlineacion (String data){

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

    public static String formatFileOrderingByNumberOfWords (String fileInText){
        String result = "";
        result = fileInText.toUpperCase();
        Map<String, String> allWords = database.allWords;
        int maxCharactersInSpecialWords = maxCharactersInSpecialWords();

        for(int i=maxWordsInSpecialWords();i>0;i--) {
            for (int x = maxCharactersInSpecialWords;x>0;x--) {
                for (Map.Entry<String, String> entry : allWords.entrySet()) {
                    if (numberOfWords(entry.getKey()) == i) {
                        if (numberOfCharacters(entry.getKey()) == x) {
                            result = result.replaceAll(Pattern.quote(entry.getKey()), entry.getValue());
                        }
                    }
                }
            }
        }
        System.out.println(result);
        //result = result.replaceAll("DOBLE -", "--");
        //result = result.replaceAll("DOBLE +", "++");
        //System.out.println(result);

        for (Map.Entry<String, String> entry : UtilsNumber.mapa.entrySet())
        {
            result = result.replaceAll(entry.getKey(), entry.getValue());
        }

        System.out.println(result);

        return result;

    }

    public static int maxWordsInSpecialWords (){
        int wordsNumber = 0;
        Map<String, String> allWords = database.allWords;
        int wordsInCloseWord = 0;

        for (Map.Entry<String, String> entry : allWords.entrySet())
        {
            wordsInCloseWord = numberOfWords(entry.getKey().toString());
            wordsNumber = (wordsInCloseWord > wordsNumber)?wordsInCloseWord:wordsNumber;
        }

        return wordsNumber;
    }

    public static int numberOfWords (String text){
        StringTokenizer stringTokenizer = new StringTokenizer(text.trim());

        return stringTokenizer.countTokens();
    }

    public static int maxCharactersInSpecialWords (){
        int characterNumber = 0;
        Map<String, String> allWords = database.allWords;
        int charactersInCloseWord = 0;

        for (Map.Entry<String, String> entry : allWords.entrySet())
        {
            charactersInCloseWord = numberOfCharacters(entry.getKey().toString());
            characterNumber = (charactersInCloseWord > characterNumber)?charactersInCloseWord:characterNumber;
        }

        return characterNumber;
    }

    public static int numberOfCharacters (String text){
        return text.trim().length();
    }

    public static String formatFile (String fileInText) {
        String result = "";
        result = fileInText.toUpperCase();
        int i = 1;
        //result = "DOBLE +";

        for (Map.Entry<String, String> entry : database.allWords.entrySet())
        {
            result = result.replaceAll(Pattern.quote(entry.getKey().toString()), entry.getValue().toString());
            System.out.println("Cambio " + entry.getKey() +  " por " + entry.getValue() + " y queda:");
            //System.out.println(result);
            i++;
        }

        //System.out.println(result);
        //result = result.replaceAll("DOBLE -", "--");
        //result = result.replaceAll("DOBLE +", "++");
        //System.out.println(result);

        for (Map.Entry<String, String> entry : UtilsNumber.mapa.entrySet())
        {
            result = result.replaceAll(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
