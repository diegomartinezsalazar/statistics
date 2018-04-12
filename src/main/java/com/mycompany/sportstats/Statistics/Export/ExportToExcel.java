package com.mycompany.sportstats.Statistics.Export;

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
    ArrayList<Player> players = new ArrayList<>();
    XSSFRow row;
    XSSFWorkbook wb;

    public ExportToExcel(ArrayList<Player> players){
        this.players = players;
    }

    public void ExportToExcelFile()  throws IOException {
        Player player;
        int cellPosition = 1;
        try (FileInputStream fileIn = new FileInputStream(FILE_NAME)) {
            wb = new XSSFWorkbook(fileIn);
            XSSFSheet sheet = wb.getSheet("Coslada V");

            //Leemos cada una de las líneas de la primera columna donde empiezan los jugadores
            boolean finish = false;
            int numLinea = FIRST_PLAYER_LINE;
            String playerName = "";

            while (! finish){
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
                        cellPosition += 8;
                        insertAttackStatistics(player.getSetStatistic().getLista(), cellPosition);
                        cellPosition += 8;
                        insertServeStatistics(player.getSetStatistic().getLista(), cellPosition);
                        cellPosition += 8;
                        insertDigStatistics(player.getSetStatistic().getLista(), cellPosition);
                        cellPosition += 8;
                        insertBlockStatistics(player.getSetStatistic().getLista(), cellPosition);
                        cellPosition += 8;
                        insertReceptionStatistics(player.getSetStatistic().getLista(), cellPosition);
                    }
                }
                numLinea++;
            }

            // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME)) {
                wb.write(fileOut);
            }
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

    public void insertIntValue(XSSFRow row, int column, int value){
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
