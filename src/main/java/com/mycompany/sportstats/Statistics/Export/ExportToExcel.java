package com.mycompany.sportstats.Statistics.Export;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExportToExcel {
    //private static final String FILE_NAME = "C:\\Users\\McMardigan\\Downloads\\Equipo.xlsm";
    private static final String FILE_NAME = "/Users/dimartinez/Downloads/Equipo.xlsm";
    private static final int FIRST_PLAYER_LINE = 10;

    public ExportToExcel(){}

    public void ExportToExcelFile()  throws IOException {

        //try {

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
                    if (cell == null) {
                        finish = true;
                    } else {
                        System.out.println(cell.getStringCellValue());
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
}
