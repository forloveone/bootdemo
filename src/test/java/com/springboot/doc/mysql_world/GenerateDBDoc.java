package com.springboot.doc.mysql_world;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * freemarker生成doc文档,
 * 1.先用word文档把格式(样式)做出来,动态数据用${name}来替换,替换过后保存为xml文件,直接改名为.ftl文件,
 * 2.从数据库中取出数据,用freemarker的表达式来对list循环,使用template做渲染(写入)
 * <p>
 * 其实核心还是两个sql
 * <p>
 * 查询nlzx_dev库的所有表名,和表的注释
 * SELECT
 * table_name,
 * TABLE_COMMENT
 * FROM
 * information_schema.TABLES
 * WHERE
 * table_schema = 'nlzx_dev'
 * AND table_type = 'base table';
 * <p>
 * 根据表名和库名,查询到字段名称,字段类型,是否可为空,主键,列默认值,列注释
 * SELECT
 * COLUMN_NAME,
 * COLUMN_TYPE,
 * IS_NULLABLE,
 * COLUMN_KEY,
 * COLUMN_DEFAULT,
 * COLUMN_COMMENT
 * FROM
 * information_schema.COLUMNS
 * WHERE
 * table_schema = 'nlzx_dev'
 * AND table_name = 'nlzx_corporate_enter_prise_invested'
 */
public class GenerateDBDoc {

    private Configuration configuration = new Configuration();
    private Connection conn;

    private String url = "jdbc:mysql://rm-pz5mujrn0fnzkq38wo.mysql.rds.aliyuncs.com:3306/nlzx_test";
    private String user = "nlzx_dev02";
    private String password = "nlzx_dev02@cicnl.cn";

    private String table_schema ="nlzx_dev";
    private String table_type = "base table";
    @Test
    public void createDBDoc() {
        createMysqlWord();
    }

    /**
     * 连接数据库,查询出来数据,组织好,template根据ftl渲染页面
     */
    private void createMysqlWord() {
        Map<String, Object> dataMap = new HashMap<>();
        List onlyList = new ArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);

            String sql = "SELECT table_name,table_comment from information_schema.TABLES WHERE table_schema = ? AND table_type = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, table_schema);
            ps.setString(2, table_type);

            String sql2 = "SELECT COLUMN_NAME,COLUMN_TYPE,IS_NULLABLE,COLUMN_KEY,COLUMN_COMMENT FROM information_schema.COLUMNS WHERE table_schema = ? AND table_name = ?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1,table_schema);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map maps = new HashMap();
                maps.put("tablename", rs.getString("table_name").trim() + "(" + rs.getString("table_comment").trim() + ")");

                ps2.setString(2, rs.getString("table_name"));
                ResultSet rs2 = ps2.executeQuery();
                List map = new ArrayList();
                while (rs2.next()) {
                    map.add(rs2.getString("COLUMN_NAME") + ";" + rs2.getString("COLUMN_TYPE") + ";" + rs2.getString("IS_NULLABLE") + ";" + rs2.getString("COLUMN_KEY") + ";" + rs2.getString("COLUMN_COMMENT"));
                }
                maps.put("list", map);
                onlyList.add(maps);
            }
            dataMap.put("list", onlyList);

            configuration.setDirectoryForTemplateLoading(new File("E:/template")); // FTL文件所存在的位置
            Template template = configuration.getTemplate("22.ftl");

            File outFile = new File("E:/tesst" + ".doc");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
            template.process(dataMap, out);
            out.close();
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
