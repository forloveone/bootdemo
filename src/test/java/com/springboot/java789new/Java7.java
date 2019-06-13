package com.springboot.java789new;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.AccessException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Java7 {
    @Test
    public void test() {
        //泛型后面的不用再写了
        Map<String, List<String>> map = new HashMap<>();
    }

    @Test
    public void test2() {
        //自动关闭资源,不用在Finally中关闭了.但是不常见.
        try (BufferedReader br = new BufferedReader(new FileReader("E:/test.txt"))) {
            String s = br.readLine();
            System.out.println(s);
            throw new AccessException("test");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //允许在catch块中捕获多个异常 (FileNotFoundException | AccessException e)
    }

    @Test
    public void test3() {
        //字符串可以做 switch了
        String type = "1";
        switch (type) {
            case "test":
                System.out.println("chenggng");
                break;
            case "1":
                System.out.println("one");
                break;
            default:
                System.out.println("error");
        }
    }

    @Test
    public void Test4() throws IOException {
        //读取文件
        Path path = Paths.get("E:/test.txt");
        BufferedReader reader = Files.newBufferedReader(path, Charset.forName("GBK"));
        String str = null;
        while ((str = reader.readLine()) != null) {
            System.out.println(str);
        }
        //创建文件
        if (!Files.exists(path)) {
            Files.createFile(path);
        }

//        BufferedWriter writer = Files.newBufferedWriter(path,Charset.forName("GBK"));
//        //不能实现追加
//        writer.write("test");
//        writer.close();
        //遍历文件夹
        DirectoryStream<Path> paths = Files.newDirectoryStream(path);
        for (Path p : paths) {
            System.out.println(p.getFileName());
        }
        //复制文件 Files.copy 方法.
    }
}
