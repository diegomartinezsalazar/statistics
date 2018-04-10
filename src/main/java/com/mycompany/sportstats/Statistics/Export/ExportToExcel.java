package com.mycompany.sportstats.Statistics.Export;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExportToExcel {
    private static final String FILE_NAME = "C:\\Users\\McMardigan\\Downloads\\Equipo.xlsm";

    public ExportToExcel(){}

    public void ExportToExcelFile()  throws IOException {

        try {
            InputStream excelFileToRead = new FileInputStream(FILE_NAME);
            XSSFWorkbook wb = new XSSFWorkbook (excelFileToRead);

            XSSFSheet  sheet = wb.getSheet("Coslada V");
            XSSFRow row;
            XSSFCell cell;
            row = sheet.getRow(11);
            cell = row.getCell(2);
            cell.setCellValue("Hola");

            excelFileToRead.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
