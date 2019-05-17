package com.springboot.file;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class FileTest {
    /**
     * apache FileUtils
     *
     * @throws IOException
     */
    @Test
    public void readFileToString() throws IOException {
//        FileUtil.listFile(new File("E:/Game"),0);
        String content = FileUtils.readFileToString(new File("E:/123.txt"), "GBK");
        System.out.println(content);
    }

    /**
     * 类似java中的find 通过文件名来搜索文件
     * 第一个参数是 查找的路径, 第二个是后缀名, 第三个是否递归查询
     */
    @Test
    public void findFile() {
        String[] s = {"txt", "java"};
        Collection<File> files = FileUtils.listFiles(new File("E:/test"), s, false);
        System.out.println();
    }

    /**
     * 类似 grep
     */
    @Test
    public void grepFile() throws IOException {
//        ;杀寇决浮动垃圾阿道夫sajdfk
//                看见了开卷考试
        String content = FileUtils.readFileToString(new File("E:/test/123.txt"), "GBK");
        System.out.println(content.contains("开卷"));
        //得到String 了就可以正则匹配了 TODO 正则表达式
    }
}
