package com.test.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

/**
 * 原本是想用poi做数据库文档的(doc),但是找到更好的方法freemark
 */
public class JavaWord {
    // 生成Word2007版本
    private FileInputStream fis2007;
    private XWPFDocument doc2007;
    private XWPFWordExtractor word2007;

    // 生成Word2003版本
    private FileInputStream fis2003;
    private HWPFDocument doc2003;
    private WordExtractor word2003;

    // 创建Word输出流
    private FileOutputStream fos;

    private File testFileExists(File file) throws IOException {
        if (!file.exists()){
            file.createNewFile();
        }
        return file;
    }

    @Before
    public void setUp() throws Exception {

        // 初始化2007版本
        fis2007 = new FileInputStream(testFileExists(new File("E:/test.docx")));
        doc2007 = new XWPFDocument(fis2007);
        word2007 = new XWPFWordExtractor(doc2007);

        // 初始化2003版本
        fis2003 = new FileInputStream(testFileExists(new File("E:/test2.doc")));
        doc2003 = new HWPFDocument(fis2003);
        word2003 = new WordExtractor(doc2003);

        // 初始化输出流
        fos = new FileOutputStream(new File("E:/testCreateWord.docx"));
    }

    @After
    public void tearDown() throws IOException {
        if (fis2003 != null) {
            fis2003.close();
        }
        if (fis2007 != null) {
            fis2007.close();
        }
        if (fos != null) {
            fos.close();
        }
    }
    @Test
    public void testReadWord2003() {
        // 直接通过getText()获取文本
        String text = word2003.getText();
        // 获取总页数
        int pageCount = doc2003.getSummaryInformation().getPageCount();
        // 获取总字数
        int wordCount = doc2003.getSummaryInformation().getWordCount();
        Assert.assertNotNull(text);
    }

    @Test
    public void testReadWord2007() {
        // 直接通过getText()获取文本
        String text = word2007.getText();

        // 获取总页数
        doc2007.getProperties().getExtendedProperties()
                .getUnderlyingProperties().getPages();

        // 获取去除空格的总页数
        doc2007.getProperties().getExtendedProperties()
                .getUnderlyingProperties().getCharacters();

        // 获取带空格的总页数
        doc2007.getProperties().getExtendedProperties()
                .getUnderlyingProperties().getCharactersWithSpaces();

        Assert.assertNotNull(text);
    }

    /*
     * 演示如何创建Word文档
     */
    @Test
    public void testWriteWord2007() throws IOException {
        XWPFDocument doc = new XWPFDocument();

        // 创建段落
        XWPFParagraph p1 = doc.createParagraph();
        // 设置样式,此时样式为一个正方形包围文字
        p1.setAlignment(ParagraphAlignment.CENTER);
        p1.setBorderBottom(Borders.DOUBLE);
        p1.setBorderTop(Borders.DOUBLE);
        p1.setBorderRight(Borders.DOUBLE);
        p1.setBorderLeft(Borders.DOUBLE);
        p1.setBorderBetween(Borders.SINGLE);
        p1.setVerticalAlignment(TextAlignment.TOP);

        // 创建1段文字,通过段落创建
        XWPFRun r1 = p1.createRun();
        // 设置是否粗体
        r1.setBold(true);
        r1.setText("The quick brown fox");
        r1.setBold(true);
        r1.setFontFamily("Courier");
        r1.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
        r1.setTextPosition(100);

        XWPFParagraph p2 = doc.createParagraph();
        p2.setAlignment(ParagraphAlignment.RIGHT);

        p2.setBorderBottom(Borders.DOUBLE);
        p2.setBorderTop(Borders.DOUBLE);
        p2.setBorderRight(Borders.DOUBLE);
        p2.setBorderLeft(Borders.DOUBLE);
        p2.setBorderBetween(Borders.SINGLE);

        XWPFRun r2 = p2.createRun();
        r2.setText("Hello Hello Hello Hello Hello Hello Hello");
        r2.setStrike(true);
        r2.setFontSize(20);

        XWPFRun r3 = p2.createRun();
        r3.setText("World World World World World World World");
        r3.setStrike(true);
        r3.setFontSize(20);

        XWPFParagraph p3 = doc.createParagraph();
        p3.setWordWrap(true);
        // 设置该段落填充满本页,下面在显示新文本将在下一页显示
        p3.setPageBreak(true);

        p3.setAlignment(ParagraphAlignment.DISTRIBUTE);
        p3.setAlignment(ParagraphAlignment.BOTH);
        p3.setSpacingLineRule(LineSpacingRule.EXACT);

        p3.setIndentationFirstLine(600);

        doc.write(fos);
    }

    //在一个文档上插入table 并填充数据
    @Test
    public void writeTable() throws IOException {
        XWPFDocument doc = new XWPFDocument();

        XWPFParagraph paragraph = doc.createParagraph();
        XWPFRun run = paragraph.insertNewRun(0);
        run.setText("农联中新");
        run.addBreak();
        run.setText("表名"+"("+"表中文名"+")");

        XWPFTable table = doc.createTable(5,6);
        XWPFTableRow row = table.getRow(0);
        row.getCell(0).setText("字段名");
        row.getCell(1).setText("类型");
        row.getCell(2).setText("是否为null");
        row.getCell(3).setText("是否主键");
        row.getCell(4).setText("默认值");
        row.getCell(5).setText("注释");


        XWPFParagraph paragraph2 = doc.createParagraph();
        XWPFRun run2 = paragraph2.insertNewRun(0);
        run2.addBreak();

        XWPFTable table2 = doc.createTable(5,6);
        XWPFTableRow row2 = table2.getRow(0);
        row2.getCell(0).setText("字段名");
        row2.getCell(1).setText("类型");
        row2.getCell(2).setText("是否为null");
        row2.getCell(3).setText("是否主键");
        row2.getCell(4).setText("默认值");
        row2.getCell(5).setText("注释");

        doc.write(fos);
    }

    @Test
    public void test5(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://rm-pz5mujrn0fnzkq38wo.mysql.rds.aliyuncs.com:3306/nlzx_dev", "nlzx_dev", "nlzx@59561748");
            conn.setAutoCommit(false);
            String sql ="SELECT table_name,table_comment from information_schema.TABLES WHERE table_schema = ? AND table_type = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "nlzx_dev");
            ps.setString(2, "base table");

            ResultSet rs = ps.executeQuery();
            int count = 0;
            while(rs.next()){
                count++;
                String tabName = rs.getString("table_name");
                String tabComment = rs.getString("table_comment");
                System.out.println(tabName+"    "+tabComment);
            }
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

}


