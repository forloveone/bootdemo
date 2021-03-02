package com.test.base.doc;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取一个excel,并解析其中的全部数据(拉取到内存中)
 * 问题:一行单元格中有一个没有内容,遍历的时候,就没有遍历它,这如何映射到entity
 */
public class ExcelTest {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private DecimalFormat decimalFormat = new DecimalFormat("#");

    @Test
    public void test() throws IllegalAccessException, InstantiationException, IOException {

        InputStream in = new FileInputStream("src/main/java/com/future/test/excel/工作簿1.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(in);

        Sheet sheet0 = wb.getSheetAt(0);
        List list = new ArrayList();
        for (Row row : sheet0) {
            int rowNum = row.getRowNum();
            if (rowNum == 1) {
                Person person = Person.class.newInstance();
                Field[] declaredFields = Person.class.getDeclaredFields();
                //如果用fore循环默认会把null的去掉(好像更不容易处理)
                for (int i = 0; i < 3; i++) {
                    Object cellValue = this.getCellValue(row.getCell(i));
                    declaredFields[i].setAccessible(true);
                    declaredFields[i].set(person, cellValue);
                }
                list.add(person);
            }
        }
        System.out.print("");
    }

    private Object getCellValue(Cell cell) {
        Object cellValue;
        if (cell == null) {
            cellValue = "";//单元格没有内容
        } else {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:    // 如果当前Cell的Type为NUMERIC
                    if (DateUtil.isCellDateFormatted(cell)) {  // 判断当前的cell是否为Date
                        cellValue = cell.getDateCellValue();
                    } else {// 如果是纯数字
                        cellValue = Integer.valueOf(decimalFormat.format(cell.getNumericCellValue()));
                    }
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    cellValue = cell.getCellFormula();
                    break;
                case Cell.CELL_TYPE_STRING:  // 如果当前Cell的Type为STRING
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:  //boolea 型
                    cellValue = cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_BLANK:
                    cellValue = "";
                    break;
                default:
                    cellValue = "未知类型";
            }
        }
        return cellValue;
    }
}
