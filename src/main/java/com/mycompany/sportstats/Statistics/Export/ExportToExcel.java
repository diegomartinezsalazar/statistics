package com.mycompany.sportstats.Statistics.Export;

import com.mycompany.sportstats.Team.Match.Alineacion;
import com.mycompany.sportstats.Team.Match.Match;
import com.mycompany.sportstats.Team.Match.Set;
import com.mycompany.sportstats.Team.Player;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class ExportToExcel {
    private static final String FILE_NAME = "C:\\Users\\McMardigan\\Downloads\\Equipo.xlsm";
    //private static final String FILE_NAME = "/Users/dimartinez/Downloads/Equipo.xlsm";
    private static final int FIRST_PLAYER_LINE = 10;
    private static final int DISTANCE_BETWEEN_PLAYERS = 9;
    private static final int FIRST_ALINEACION_LINE = 43;
    private static final int FIRST_SET_COLUMN = 2;
    private static final int DISTANCE_BETWEEN_SETS = 6;
    ArrayList<Player> players;
    Match match;
    XSSFRow row;
    XSSFWorkbook wb;
    XSSFSheet sheet;

    public ExportToExcel(ArrayList<Player> players, Match match){
        this.players = players;
        this.match = match;
    }

    public void ExportToExcelFile()  throws IOException {
        Player player;
        int cellPosition = 1;
        try (FileInputStream fileIn = new FileInputStream(FILE_NAME)) {
            wb = new XSSFWorkbook(fileIn);
            sheet = wb.getSheet("Coslada V");
            System.out.println("Comienzo exportación estadísticas jugadoras");
            exportPlayersStatistics();
            System.out.println("Final exportación estadísticas jugadoras");
            System.out.println("Comienzo exportación alineación");
            exportAlineacion();
            System.out.println("Final exportación alineación");


            // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME)) {
                wb.write(fileOut);
            }
        }
    }

    private void exportPlayersStatistics() {
        int cellPosition;
        Player player;//Leemos cada una de las líneas de la primera columna donde empiezan los jugadores
        boolean finish = false;
        int numLinea = FIRST_PLAYER_LINE;
        String playerName = "";

        while (! finish){
            cellPosition = 1;
            row = sheet.getRow(numLinea);
            XSSFCell cell = row.getCell(0);
            if ((cell == null) || (Objects.equals(cell.getStringCellValue(), "TOTAL"))) {
                finish = true;
            } else {
                // Busco el jugador en la lista de jugadores
                player = getPlayerWithName(cell.getStringCellValue());
                if (player != null) {
                    // Comienzo a insertar las estadísticas
                    // Primero las colocaciones
                    insertSetStatistics(player.getSetStatistic().getLista(), cellPosition);
                    cellPosition += DISTANCE_BETWEEN_PLAYERS;
                    insertAttackStatistics(player.getAttackStatistic().getLista(), cellPosition);
                    cellPosition += DISTANCE_BETWEEN_PLAYERS;
                    insertServeStatistics(player.getServeStatistic().getLista(), cellPosition);
                    cellPosition += DISTANCE_BETWEEN_PLAYERS;
                    insertDigStatistics(player.getDigStatistic().getLista(), cellPosition);
                    cellPosition += DISTANCE_BETWEEN_PLAYERS;
                    insertBlockStatistics(player.getBlockStatistic().getLista(), cellPosition);
                    cellPosition += DISTANCE_BETWEEN_PLAYERS;
                    insertReceptionStatistics(player.getReceptionStatistic().getLista(), cellPosition);
                }
            }
            numLinea++;
        }
    }

    public void exportAlineacion (){
        int alineacionColumn = 0;
        Player player;
        int alineacionRowNumber;
        int alineacionSetColumn = FIRST_SET_COLUMN;
        for (Set set: match.getSets()
             ) {
            Alineacion alineacion = set.getAlineacionInicial();
            alineacionRowNumber = FIRST_ALINEACION_LINE;
            for (Integer numJugador: alineacion.getJugadoresAlineacion()
                 ) {
                player = getPlayerWithNumber(numJugador);
                row = sheet.getRow(alineacionRowNumber);
                insertStrValue(row, alineacionSetColumn, player.getName());
                alineacionRowNumber += 1;

            }
            alineacionSetColumn += DISTANCE_BETWEEN_SETS;
        }
    }

    public Player getPlayerWithName (String name){
        for (Player player: players
             ) {
            if (Objects.equals(player.getName(), name)){
                return player;
            }
        }
        return null;
    }

    public Player getPlayerWithNumber (int number){
        for (Player player: players
                ) {
            if (player.getNumber() == number){
                return player;
            }
        }
        return null;
    }

    public void insertIntValue(XSSFRow row, int column, int value){
        XSSFCell cell = row.getCell(column);
        if (cell == null) {
            row.createCell(column);
        }
        cell.setCellValue(value);
    }

    public void insertStrValue(XSSFRow row, int column, String value){
        XSSFCell cell = row.getCell(column);
        if (cell == null) {
            row.createCell(column);
        }
        cell.setCellValue(value);
    }

    public void updateFormula (XSSFWorkbook ws, XSSFRow row, int column){
        XSSFCell cell = row.getCell(column);
        if (cell == null) {
            row.createCell(column);
        }
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
        evaluator.evaluateFormulaCellEnum(cell);
    }

    public void insertSetStatistics (Map<String, Integer> lista, int firstColumn){
        insertStatistics(lista, firstColumn);
    }

    public void insertAttackStatistics (Map<String, Integer> lista, int firstColumn){
        insertStatistics(lista, firstColumn);
    }

    public void insertServeStatistics (Map<String, Integer> lista, int firstColumn){
        insertStatistics(lista, firstColumn);
    }

    public void insertDigStatistics (Map<String, Integer> lista, int firstColumn){
        insertStatistics(lista, firstColumn);
    }

    public void insertBlockStatistics (Map<String, Integer> lista, int firstColumn){
        insertStatistics(lista, firstColumn);
    }

    public void insertReceptionStatistics (Map<String, Integer> lista, int firstColumn){
        insertStatistics(lista, firstColumn);
    }

    private void insertStatistics(Map<String, Integer> lista, int firstColumn) {
        insertIntValue(row, firstColumn, lista.get("++"));
        insertIntValue(row, ++firstColumn, lista.get("+"));
        insertIntValue(row, ++firstColumn, lista.get("/"));
        insertIntValue(row, ++firstColumn, lista.get("-"));
        insertIntValue(row, ++firstColumn, lista.get("--"));

        updateFormula(wb , row, ++firstColumn);
        updateFormula(wb , row, ++firstColumn);
        updateFormula(wb , row, ++firstColumn);
    }
}
