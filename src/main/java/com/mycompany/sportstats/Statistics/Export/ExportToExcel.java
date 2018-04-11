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

    public ExportToExcel(ArrayList<Player> players){
        this.players = players;
    }

    public void ExportToExcelFile()  throws IOException {
        Player player;
        int cellPosition = 2;
        try (FileInputStream fileIn = new FileInputStream(FILE_NAME)) {
                XSSFWorkbook wb = new XSSFWorkbook(fileIn);
                XSSFSheet sheet = wb.getSheet("Coslada V");

                //Leemos cada una de las líneas de la primera columna donde empiezan los jugadores
                boolean finish = false;
                int numLinea = FIRST_PLAYER_LINE;
                String playerName = "";

                while (! finish){
                    XSSFRow row = sheet.getRow(numLinea);
                    XSSFCell cell = row.getCell(0);
                    if ((cell == null) || (Objects.equals(cell.getStringCellValue(), "TOTAL"))) {
                        finish = true;
                    } else {
                        // Busco el jugador en la lista de jugadores
                        player = getPlayerWithName(cell.getStringCellValue());
                        if (player != null) {
                            // Comienzo a insertar las estadísticas
                            // Primero las colocaciones
                            cell = row.getCell(1);
                            if (cell == null) {
                                row.createCell(1);
                            }
                            cell.setCellValue(player.getSetStatistic().getLista().get("++"));
                            cell = row.getCell(2);
                            if (cell == null) {
                                row.createCell(2);
                            }
                            cell.setCellValue(player.getSetStatistic().getLista().get("+"));
                            cell = row.getCell(3);
                            if (cell == null) {
                                row.createCell(3);
                            }
                            cell.setCellValue(player.getSetStatistic().getLista().get("/"));
                            cell = row.getCell(4);
                            if (cell == null) {
                                row.createCell(4);
                            }
                            cell.setCellValue(player.getSetStatistic().getLista().get("-"));
                            cell = row.getCell(5);
                            if (cell == null) {
                                row.createCell(5);
                            }
                            cell.setCellValue(player.getSetStatistic().getLista().get("--"));
                            cell = row.getCell(6);
                            if (cell == null) {
                                row.createCell(6);
                            }
                            FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
                            evaluator.evaluateFormulaCellEnum(cell);
                            cell = row.getCell(7);
                            if (cell == null) {
                                row.createCell(7);
                            }
                            evaluator = wb.getCreationHelper().createFormulaEvaluator();
                            evaluator.evaluateFormulaCellEnum(cell);
                        }
                    }
                    numLinea++;
                }

                /*XSSFRow row = sheet.getRow(0);
                if (row == null)
                    row = sheet.createRow(0);
                XSSFCell cell = row.getCell(0);
                if (cell == null)
                    cell = row.createCell(0);
                cell.setCellValue("a test");*/

                // Write the output to a file
                try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME)) {
                    wb.write(fileOut);
                }
            }

            /*InputStream excelFileToRead = new FileInputStream(FILE_NAME);
            XSSFWorkbook wb = new XSSFWorkbook (excelFileToRead);

            XSSFSheet  sheet = wb.getSheet("Coslada V");
            XSSFRow row;
            XSSFCell cell;

            row = sheet.getRow(1);
            cell = row.getCell(1);
            if (cell == null) {
                cell = row.createCell(1);
            }
            cell.setCellValue("Hola");

            row = sheet.getRow(2);
            cell = row.getCell(1);
            if (cell == null) {
                cell = row.createCell(1);
            }
            cell.setCellValue("Hola");

            row = sheet.getRow(3);
            cell = row.getCell(1);
            if (cell == null) {
                cell = row.createCell(1);
            }
            cell.setCellValue("Hola");


            excelFileToRead.close();

            FileOutputStream output_file =new FileOutputStream(new File("/Users/dimartinez/Downloads/Equipo.xlsm"));  //Open FileOutputStream to write updates

            wb.write(output_file); //write changes

            output_file.close();  //close the stream
        }catch (IOException e){
            e.printStackTrace();
        }*/

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
}
