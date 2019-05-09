package com.springboot.utils;

import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * TODO 这只是个初步的
 */
public class ExcelUtil {
    /**
     * 导出excel 到浏览器(直接下载的方式)
     * @param datas 这个是要导出的数据,格式为List<HashMap>
     * @param response 做写会到浏览器下载用的
     * @param type 这个是excel文件名,sheet名,可以根据这个区分不同的导出(可以是中文)
     */
    public static void outPutExcelToBrowser(List datas, HttpServletResponse response, String type) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();//创建工作薄
            HSSFSheet sheet = workbook.createSheet(type); //创建sheet页

            HSSFRow row;
            //产生表格标题行
            row = sheet.createRow(0);
            row.setHeightInPoints(20);
            //给第一行赋值
            setValueInCell(type,row);

            Map map;//单条数据
            for (int i = 0; i < datas.size(); i++) {
                row = sheet.createRow((i + 1));
                row.setHeightInPoints(20);
                map = (Map) datas.get(i);

                if (map != null) {
                    //这里需要自定义key的规则,每个字段的顺序和对应单元格的赋值
                    setValueInCell(type, row, map);
                }
            }

            //输出到浏览器
            response.reset();
            response.setContentType("multipart/form-data"); //自动识别
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+new String(type.getBytes("gb2312"), "ISO8859-1")+".xls");
            //文件流输出到rs里
            OutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setValueInCell(String type, HSSFRow row) {
        if("授信查询".equals(type)){
            String[] titles = {"项目名称","项目编号","授信编号","贷款人姓名","证件号码","授信额度","贷款利率%","贷款利率类型", "还款类型","贷款期限",
                    "贷款期限单位","授信期限","授信期限单位","授信使用有效期开始日期","授信使用有效期结束日期", "费率值","费用类型", "授信结果","授信提交日期","客户类型",
                    "证件类型"};
            HSSFCell cell;
            HSSFCell[] cells = new HSSFCell[22];
            for (int i = 0; i < 21; i++) {
                cell = row.createCell(i);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cells[i] = cell;
            }
            for (int i = 0;i<21;i++){
                cells[i].setCellValue(new HSSFRichTextString(titles[i]));
            }
        }
    }

    //这个是处理单条数据的, type 是文件名和sheet名,row 是行对象,map是一条数据
    private static void setValueInCell(String type, HSSFRow row, Map map) {
        if ("授信查询".equals(type)){
            HSSFCell cell;
            //先创初始化cell
            HSSFCell[] cells = new HSSFCell[22];
            for (int i = 0; i < 21; i++) {
                cell = row.createCell(i);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cells[i] = cell;
            }
            //赋值
            cells[0].setCellValue(new HSSFRichTextString(map.get("name").toString()));
            cells[1].setCellValue(new HSSFRichTextString(map.get("plan_id").toString()));
            cells[2].setCellValue(new HSSFRichTextString(map.get("credit_code").toString()));
            cells[3].setCellValue(new HSSFRichTextString(map.get("customer_name").toString()));
            cells[4].setCellValue(new HSSFRichTextString(map.get("paper_code").toString()));
            cells[5].setCellValue(new HSSFRichTextString(map.get("credit_line").toString()));
            cells[6].setCellValue(new HSSFRichTextString(map.get("loan_interest_rate").toString()));
            cells[7].setCellValue(new HSSFRichTextString(""));//贷款利率类型没有
            switch(Integer.valueOf((String)map.get("other_repay_method"))){
                case 1:
                    cells[8].setCellValue(new HSSFRichTextString("等额本息"));
                    break;
                case 2:
                    cells[8].setCellValue(new HSSFRichTextString("等额本金"));
                    break;
                case 3:
                    cells[8].setCellValue(new HSSFRichTextString("一次性还本付息"));
                    break;
                case 4:
                    cells[8].setCellValue(new HSSFRichTextString("预收利息一次性还本"));
                    break;
                case 5:
                    cells[8].setCellValue(new HSSFRichTextString("一次性还本、逐月付息"));
                    break;
                case 6:
                    cells[8].setCellValue(new HSSFRichTextString("约定还款"));
                    break;
                case 7:
                    cells[8].setCellValue(new HSSFRichTextString("自由还款(有最低还款额限制)"));
                    break;
                case 8:
                    cells[8].setCellValue(new HSSFRichTextString("一次性还本、按季付息"));
                    break;
                case 9:
                    cells[8].setCellValue(new HSSFRichTextString("其他"));
                    break;
                default:
                    cells[8].setCellValue(new HSSFRichTextString(""));
                    break;
            }
            cells[9].setCellValue(new HSSFRichTextString(map.get("loan_term").toString()));
            if("YEAR".equals(map.get("loan_term_unit"))){
                cells[10].setCellValue(new HSSFRichTextString("年"));
            }else if("MONTH".equals(map.get("loan_term_unit"))){
                cells[10].setCellValue(new HSSFRichTextString("月"));
            }else{
                cells[10].setCellValue(new HSSFRichTextString("日"));
            }
            cells[11].setCellValue(new HSSFRichTextString(map.get("credit_term").toString()));
            if("YEAR".equals(map.get("credit_term_unit"))){
                cells[12].setCellValue(new HSSFRichTextString("年"));
            }else if("MONTH".equals(map.get("credit_term_unit"))){
                cells[12].setCellValue(new HSSFRichTextString("月"));
            }else{
                cells[12].setCellValue(new HSSFRichTextString("日"));
            }
            cells[13].setCellValue(new HSSFRichTextString(String.valueOf(map.get("start_date"))));
            cells[14].setCellValue(new HSSFRichTextString(String.valueOf(map.get("end_date"))));
            cells[15].setCellValue(new HSSFRichTextString(String.valueOf(map.get("fee_rate"))));
            cells[16].setCellValue(new HSSFRichTextString(String.valueOf(map.get("fee_type"))));
            if ("ACCEPTED".equals(map.get("result"))){
                cells[17].setCellValue(new HSSFRichTextString("审批通过"));
            }else{
                cells[17].setCellValue(new HSSFRichTextString("审批拒绝"));
            }
            cells[18].setCellValue(new HSSFRichTextString(""));//授信提交日期 没有
            if (Integer.valueOf("1").equals(map.get("customer_type"))){
                cells[19].setCellValue(new HSSFRichTextString("个人客户"));
            }else{
                cells[19].setCellValue(new HSSFRichTextString("法人客户"));
            }
            if(Integer.valueOf("1").equals(map.get("customer_type")) && "0".equals(map.get("papers_type"))){
                cells[20].setCellValue(new HSSFRichTextString("身份证"));
            }else if (Integer.valueOf("1").equals(map.get("customer_type")) && "2".equals(map.get("papers_type"))){
                cells[20].setCellValue(new HSSFRichTextString("护照"));
            }else if(Integer.valueOf("1").equals(map.get("customer_type")) && "X".equals(map.get("papers_type"))){
                cells[20].setCellValue(new HSSFRichTextString("其他证件"));
            }else if (!Integer.valueOf("1").equals(map.get("customer_type")) && "0".equals(map.get("papers_type"))){
                cells[20].setCellValue(new HSSFRichTextString("三证合一"));
            }else if (!Integer.valueOf("1").equals(map.get("customer_type")) && "1".equals(map.get("papers_type"))){
                cells[20].setCellValue(new HSSFRichTextString("三证"));
            }
        }
    }
}
