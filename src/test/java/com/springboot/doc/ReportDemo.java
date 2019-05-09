package com.springboot.doc;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.RectangleEdge;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 结合poi 和 freechart 做报表,图片导入
 */
public class ReportDemo {

    @Test
    public void test() throws IOException {
        HSSFWorkbook excel = new HSSFWorkbook();
        HSSFSheet sheet= excel.createSheet("报表");

        this.chineseCharacter();
        DefaultPieDataset data = this.dataCollection();

        JFreeChart chart = ChartFactory.createPieChart("测试饼状图",data,true,true,true);

        //ChartUtilities.saveChartAsJPEG(new File("E:\\pie01.jpg"), chart, 800, 400);//直接保存为图片
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ChartUtilities.writeChartAsJPEG(out,chart,800,400);

        // 画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 八个参数，前四个表示图片离起始单元格和结束单元格边缘的位置，后四个表示起始和结束单元格的位置，如下表示从第2列到第12列，从第1行到第15行,需要注意excel起始位置是0
        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) 2, (short) 1, (short) 12, (short) 15);
        anchor.setAnchorType(3);
        // 插入图片
        patriarch.createPicture(anchor, excel.addPicture(out.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
        // excel2003后缀
        File file = new File("E:\\pie01.xls");
        FileOutputStream fileOut = new FileOutputStream(file);
        excel.write(fileOut);
        fileOut.close();
    }

    //jfreechart 生成图片乱码
    private void chineseCharacter(){
        //创建主题样式
        StandardChartTheme standardChartTheme=new StandardChartTheme("CN");
        //设置标题字体
        standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));
        //设置图例的字体
        standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));
        //设置轴向的字体
        standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,15));
        //应用主题样式
        ChartFactory.setChartTheme(standardChartTheme);//设置中文显示
    }

    private DefaultPieDataset dataCollection(){
        DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
        defaultpiedataset.setValue("我们", new Double(43.2));
        defaultpiedataset.setValue("Two", new Double(10.0));
        defaultpiedataset.setValue("Three", new Double(27.5));
        defaultpiedataset.setValue("Four", new Double(17.5));
        defaultpiedataset.setValue("Five", new Double(11.0));
        defaultpiedataset.setValue("Six", new Double(19.4));
        return defaultpiedataset;
    }

    public static void main(String[] args) throws Exception {
        // excel2003工作表
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Sheet 1");
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        // 设置具体数据
        List<String> timeList = new ArrayList<String>();
        timeList.add("10:00");
        timeList.add("11:00");
        timeList.add("12:00");
        List<Integer> appList = new ArrayList<Integer>();
        appList.add(120);
        appList.add(200);
        appList.add(150);
        List<Integer> oraList = new ArrayList<Integer>();
        oraList.add(230);
        oraList.add(200);
        oraList.add(235);
        // 设置图片中的字体和颜色以及字号
        Font titleFont = new Font("黑体", Font.BOLD, 12);
        Font xfont = new Font("黑体", Font.BOLD, 10);
        Font labelFont = new Font("黑体", Font.BOLD, 10);
        // 设置数据区域
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < timeList.size(); i++) {
            String time = timeList.get(i);
            dataset.addValue(appList.get(i), "苹果", time);
            dataset.addValue(oraList.get(i), "橘子", time);
        }
        JFreeChart chart = ChartFactory.createLineChart("水果时间段销量", "时间", "销量", dataset, PlotOrientation.VERTICAL, true,
                true, true);
        // 设置图例字体
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 10));
        // 设置标题字体
        chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));
        // 图形的绘制结构对象
        CategoryPlot plot = chart.getCategoryPlot();
        // 获取显示线条的对象
        LineAndShapeRenderer lasp = (LineAndShapeRenderer) plot.getRenderer();
        // 设置拐点是否可见/是否显示拐点
        lasp.setBaseShapesVisible(true);
        // 设置拐点不同用不同的形状
        lasp.setDrawOutlines(true);
        // 设置线条是否被显示填充颜色
        lasp.setUseFillPaint(false);
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        // 设置折线大小以及折线的颜色
        renderer.setSeriesStroke(0, new BasicStroke(1.0F));
        renderer.setSeriesPaint(0, new Color(210, 105, 30));
        renderer.setSeriesStroke(1, new BasicStroke(1.0F));
        renderer.setSeriesPaint(1, new Color(0, 191, 255));
        // 设置折点的大小
        lasp.setSeriesOutlineStroke(0, new BasicStroke(0.025F));
        lasp.setSeriesOutlineStroke(1, new BasicStroke(0.05F));
        // 设置网格线
        plot.setDomainGridlinePaint(Color.gray);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.gray);
        plot.setRangeGridlinesVisible(true);
        // x轴
        CategoryAxis domainAxis = plot.getDomainAxis();
        // 设置x轴不显示，即让x轴和数据区重合
        domainAxis.setAxisLineVisible(false);
        // x轴标题
        domainAxis.setLabelFont(xfont);
        // x轴数据倾斜
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.95D));
        // X轴坐标上数值字体
        domainAxis.setTickLabelFont(labelFont);
        // 设置Y轴间隔
        NumberAxis numAxis = (NumberAxis) plot.getRangeAxis();
        numAxis.setTickUnit(new NumberTickUnit(50));
        // y轴
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setLabelFont(xfont);
        // 设置y轴不显示，即和数据区重合
        rangeAxis.setAxisLineVisible(false);
        // y轴坐标上数值字体
        rangeAxis.setTickLabelFont(labelFont);
        rangeAxis.setFixedDimension(0);
        CategoryPlot cp = chart.getCategoryPlot();
        // 背景色设置
        cp.setBackgroundPaint(ChartColor.WHITE);
        cp.setRangeGridlinePaint(ChartColor.GRAY);
        // 创建图例，设置图例的位置，这里的设置实际不起作用，怎么设都在下边
        LegendTitle legendTitle = new LegendTitle(chart.getPlot());
        legendTitle.setPosition(RectangleEdge.BOTTOM);
        try {
            ChartUtilities.writeChartAsPNG(byteArrayOut, chart, 400, 200);
            String fileSavePath = "exTest.png";
            BufferedImage bufferImg = ImageIO.read(new File(fileSavePath));
            ImageIO.write(bufferImg, "png", byteArrayOut);
        } catch (IOException e) {
        }
        // 画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 八个参数，前四个表示图片离起始单元格和结束单元格边缘的位置，
        // 后四个表示起始和结束单元格的位置，如下表示从第2列到第12列，从第1行到第15行,需要注意excel起始位置是0
        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) 2, (short) 1, (short) 12, (short) 15);
        anchor.setAnchorType(3);
        // 插入图片
        patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
        // excel2003后缀
        FileOutputStream fileOut = new FileOutputStream("exTest.xls");
        wb.write(fileOut);
        fileOut.close();
    }

    @Test
    public void test2() throws IOException, IllegalAccessException, NoSuchFieldException {
        DefaultValueDataset data = new DefaultValueDataset(175);
        MeterPlot plot = new MeterPlot(data);
        Field field = plot.getClass().getDeclaredField("DEFAULT_METER_ANGLE");
        field.setAccessible(true);
        field.setInt((Object) plot,180);
        plot.setDialShape(DialShape.CHORD);
        plot.setDialBackgroundPaint(Color.WHITE);
        plot.setRange(new Range(0, 180));
        plot.setDialOutlinePaint(Color.GRAY);
        plot.setNeedlePaint(Color.BLACK);
        plot.setTickLabelsVisible(false);
        plot.setTickLabelPaint(Color.BLACK);
        plot.setTickPaint(Color.GRAY);
        plot.setTickLabelFormat(NumberFormat.getNumberInstance());
        plot.setTickSize(10);
        plot.setValuePaint(Color.BLACK);
        plot.addInterval(new MeterInterval("Low", new Range(0, 45), null, null,new Color(171, 4, 1, 243) ));
        plot.addInterval(new MeterInterval("Normal", new Range(45, 90), null, null, new Color(251, 143, 18, 254)));
        plot.addInterval(new MeterInterval("High", new Range(90, 125), null, null, new Color(24, 58, 156, 235)));
        plot.addInterval(new MeterInterval("4", new Range(125, 180), null, null, new Color(18, 156, 35, 235)));

        JFreeChart chart = new JFreeChart("Meter Chart", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
        ChartUtilities.saveChartAsJPEG(new File("E:\\pie01.jpg"), chart, 800, 400);//直接保存为图片
    }
}
