package com.test.base.doc;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Iterator;

//操作Excel
public class CreateExcel {
    /**
     * 通过HSSFWorkbook----->HSSFSheet----->HSSFRow----->HSSFCell
     * 找到了能操作的单元格,并对单元格进行操作,使用流把文件从内存导入到硬盘中
     */
    public static void createExcel() throws IOException {
        HSSFWorkbook excel = new HSSFWorkbook();
        HSSFSheet sheet1 = excel.createSheet();
        HSSFRow row1 = sheet1.createRow(1);
        HSSFCell cell06 = row1.createCell(6);
        cell06.setCellValue("zhongguo");

        // OutputStream out = new FileOutputStream("d://22/2.xls");
        // 如果路径正确,但是文件不存在,它会自动帮你创建,如果路径错误会报找不到指定路径(FileNotFoundException)
        OutputStream out = new FileOutputStream("d://1.xls");
        excel.write(out);
    }

    /**
     * 单元格合并 CellRangeAddress 表示范围区域
     */
    public static void rangeAddress() throws IOException {
        HSSFWorkbook excel = new HSSFWorkbook();
        HSSFSheet sheet = excel.createSheet("我们");
        CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 5);
        sheet.addMergedRegion(cra);
        sheet.createRow(0).createCell(0).setCellValue("中国人");
        OutputStream out = new FileOutputStream("d://中国1.xls");
        excel.write(out);
    }

    /**
     * 读取硬盘上的Excel 数组是应该有规律的,无规律无法解析 deptNo dname loc
     * 如果连接数据库,就可以把数据addBatch导入到库里
     *
     * @throws IOException
     */
    public static void readExcel() throws IOException {
        FileInputStream in = new FileInputStream("d://1.xls");
        HSSFWorkbook excel = new HSSFWorkbook(in);
        HSSFSheet sheet0 = excel.getSheetAt(0);
        Iterator<Row> it = sheet0.rowIterator();
        while (it.hasNext()) {
            HSSFRow row = (HSSFRow) it.next();
            HSSFCell deptno = row.getCell(0);
            HSSFCell name = row.getCell(1);
            HSSFCell loc = row.getCell(2);
            System.out.println(deptno + "   " + name + "   " + loc);
        }

    }

    /**
     * 备份
     */
    public static void bak() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test", "root", "123");
        PreparedStatement ps = con
                .prepareStatement("select ename,sal,hireDate from emp");
        ResultSet rs = ps.executeQuery();

        HSSFWorkbook excelObj = new HSSFWorkbook();
        HSSFSheet sheetObj = excelObj.createSheet("职员信息小页");
        int line = 0;
        while (rs.next()) {
            HSSFRow rowObj = sheetObj.createRow(line++);
            HSSFCell cell0 = rowObj.createCell(0);// ename
            HSSFCell cell1 = rowObj.createCell(1);// sal
            HSSFCell cell2 = rowObj.createCell(2);// hireDate

            cell0.setCellValue(rs.getString("ename"));
            cell1.setCellValue(rs.getDouble("sal"));
            // 将获得日期类型转换字符串
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = sdf.format(rs.getDate("hireDate"));
            cell2.setCellValue(strDate);
        }
        // 3. 将内存中excel文档导出到硬盘上
        OutputStream out = new FileOutputStream("d://职员信息一览.xls");
        excelObj.write(out);
        out.close();
    }

    /**
     * 导入
     */
    public static void inDate() throws Exception {
        // 1. 将硬盘上Excel加载到内存，交给指定的对象管理
        InputStream in = new FileInputStream("d://temp/dept.xls");
        HSSFWorkbook excelObj = new HSSFWorkbook(in);
        // 2. 定位Excel文档对象的第一个【小页】对象
        HSSFSheet sheetObj = excelObj.getSheetAt(0);
        // 3. 将【小页】对象所拥有数据的行对象进行定位，并封装到Iterator
        Iterator<Row> it = sheetObj.rowIterator();

        // 4. 将内存中数据，生成sql命令保存到数据库操作对象中，进行部门批处理插入操作
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test", "root", "123");
        con.setAutoCommit(false);
        PreparedStatement ps = con
                .prepareStatement("insert into dept values(?,?,?)");
        while (it.hasNext()) {
            HSSFRow rowObj = (HSSFRow) it.next();
            HSSFCell cell0 = rowObj.getCell(0);// deptNo
            HSSFCell cell1 = rowObj.getCell(1);// dname
            HSSFCell cell2 = rowObj.getCell(2);// loc

            ps.setInt(1, (int) cell0.getNumericCellValue());
            ps.setString(2, cell1.getStringCellValue());
            ps.setString(3, cell2.getStringCellValue());
            ps.addBatch();
        }
        try {
            ps.executeBatch();
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
        }

    }

    public static void main(String[] args) throws Exception {
        // createExcel();
        // rangeAddress();
        // readExcel();
        bak();
    }
}
