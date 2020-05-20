package com.springboot.utils;

import java.io.File;

/**
 * 应参考 apche 工具类
 */
public class FileUtil {

    /**
     * 递归读取某个目录及子目录下的所有文件
     *
     * @param f     new File("F:\\文档\\")
     * @param level 0
     */
    @Deprecated
    public static void listFile(File f, int level) {
        String s = "";
        for (int i = 0; i < level; i++) {
            s += "--";
        }
        File[] files = f.listFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println(s + files[i].getName());
            if (files[i].isDirectory()) {
                listFile(files[i], level + 1);
            }
        }
    }
}
