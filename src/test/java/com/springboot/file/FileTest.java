package com.springboot.file;

import com.springboot.utils.FileUtil;
import org.junit.Test;

import java.io.File;

public class FileTest {
    @Test
    public void test(){
        FileUtil.listFile(new File("E:/Game"),0);
    }
}
