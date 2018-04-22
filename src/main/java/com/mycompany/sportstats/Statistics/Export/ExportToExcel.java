package com.mycompany.sportstats.Statistics.Export;

import com.mycompany.sportstats.Team.Match.Alineacion;
import com.mycompany.sportstats.Team.Match.Cambio;
import com.mycompany.sportstats.Team.Match.Match;
import com.mycompany.sportstats.Team.Match.Set;
import com.mycompany.sportstats.Team.Player;

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
    private static final int FIRST_CHANGE_LINE = 50;
    private static final int TEAM_COLUMN = 24;
    private static final int RESULT_ROW = 42;
    private static final int FIRST_RESULT_SET_COLUMN = 2;
    private static final int DISTANCE_BETWEEN_WINNER_AND_RESULT = 2;
    private static final int DISTANCE_BETWEEN_RESULTS = 2;
    private static final int PLAYER_PERCENTAJE_PLAYED = 5;
    private static final int TEAM_STATISTICS_ROW = 24;
    private static final int TEAM_PERCENTAJE_ROW = 25;
    private static final int FIRST_MIDDLE_PLAYER_ROW = 84;
    private static final int FIRST_BEST_PLAYERS_ROW = 27;
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
            System.out.println("Comienzo exportación cambios");
            exportChanges();
            System.out.println("Final exportación cambios");
            System.out.println("Comienzo exportación equipos");
            exportTeams();
            System.out.println("Final exportación equipos");
            System.out.println("Comienzo exportación resultado");
            exportResult();
            System.out.println("Final exportación resultado");
            System.out.println("Comienzo exportación resultado");
            updateFormula();
            System.out.println("Final exportación resultado");

            // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME)) {
                wb.write(fileOut);
            }
        }
    }

    private void exportPlayersStatistics() {
        int cellPosition = 0;
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

    public void exportResult(){
        int cellPosition = FIRST_RESULT_SET_COLUMN;
        row = sheet.getRow(RESULT_ROW);
        for (Set set: match.getSets()
                ) {
            insertStrValue(row, cellPosition,match.setWinner(set));
            insertIntValue(row, cellPosition + DISTANCE_BETWEEN_WINNER_AND_RESULT, match.homePoints(set));
            insertIntValue(row, cellPosition + DISTANCE_BETWEEN_WINNER_AND_RESULT + DISTANCE_BETWEEN_RESULTS,match.visitorPoints(set));
            cellPosition += 6;
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

    public void exportChanges (){
        Player playerEntra;
        Player playerSeRetira;
        int changeRowNumber;
        int changeSetColumn = FIRST_SET_COLUMN;
        for (Set set: match.getSets()
                ) {
            changeRowNumber = FIRST_CHANGE_LINE;
            for (Cambio cambio: set.getCambios()
                 ) {
                playerEntra = getPlayerWithNumber(cambio.getEntra());
                playerSeRetira = getPlayerWithNumber(cambio.getSeRetira());
                row = sheet.getRow(changeRowNumber);
                insertStrValue(row, changeSetColumn, playerSeRetira.getName());
                insertStrValue(row, changeSetColumn + 2, playerEntra.getName());
                insertIntValue(row, changeSetColumn + 4, cambio.getSuTanteo());
                insertIntValue(row, changeSetColumn + 5, cambio.getNuestroTanteo());
                changeRowNumber += 1;
            }
            changeSetColumn += DISTANCE_BETWEEN_SETS;
        }
    }

    public void exportTeams (){
        row = sheet.getRow(0);
        insertStrValue(row, TEAM_COLUMN, match.getEquipoLocal());
        row = sheet.getRow(1);
        insertStrValue(row, TEAM_COLUMN, match.getEquipoVisitante());
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
            cell = row.createCell(column);
        }
        cell.setCellValue(value);
    }

    public void updateFormula (XSSFWorkbook ws, XSSFRow row, int column){
        XSSFCell cell = row.getCell(column);
        if (cell == null) {
            cell = row.createCell(column);
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
    }

    public void updateFormula(){
        // Firstly, player statistics
        int cellPosition = 0;
        Player player;//Leemos cada una de las líneas de la primera columna donde empiezan los jugadores
        boolean finish = false;
        int numLinea = FIRST_PLAYER_LINE;

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
                    for (int i = 0;i < 5; i++) {
                        updateStatisticsFormulas(cellPosition + 4);
                        cellPosition += DISTANCE_BETWEEN_PLAYERS;
                    }
                }
            }
            numLinea++;
        }

        // Secondly, the teams
        updateFormulaRow(RESULT_ROW - 1);

        // Thirdly, alineación, changes and final result
        updateFormulaRow(PLAYER_PERCENTAJE_PLAYED);

        // Fourthly, team statistics
        updateFormulaRow(TEAM_STATISTICS_ROW);
        updateFormulaRow(TEAM_PERCENTAJE_ROW);

        // Middle player statistics
        numLinea = FIRST_MIDDLE_PLAYER_ROW;
        for (Player playerStatistic: players
             ) {
            updateFormulaRow(numLinea);
            numLinea++;
        }

        // Best players
        updateFormulaRow(FIRST_BEST_PLAYERS_ROW);
        updateFormulaRow(FIRST_BEST_PLAYERS_ROW + 1);
        updateFormulaRow(FIRST_BEST_PLAYERS_ROW + 2);
    }

    public void updateFormulaRow (int column){
        row = sheet.getRow(column);
        for (int i = 0;i < 100; i++) {
            XSSFCell cell = row.getCell(i);
            if (cell != null){
                updateFormula(wb , row, i);
            }
        }
    }

    public void updateStatisticsFormulas(int column){
        updateFormula(wb , row, ++column);
        updateFormula(wb , row, ++column);
        updateFormula(wb , row, ++column);
    }
}
