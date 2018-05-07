package com.mycompany.sportstats.Statistics.Export;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class Utils {
    public static XSSFCell returnCell (XSSFRow row, int column){
        XSSFCell cell = null;
        try {
            if (row != null) {
                cell = row.getCell(column, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            }
        } catch (NullPointerException e){
            System.out.println("Error de lectura de la fila " + row.getRowNum() + " columna " + column);
        }
        return cell;
    }
}
