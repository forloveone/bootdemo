package com.springboot.jvm;

import java.io.InputStream;

public class MyClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
                    InputStream in = getClass().getResourceAsStream(fileName);
                    if (in != null) {
                        byte[] b = new byte[in.available()];
                        in.read(b);
                        return defineClass(name,b,0,b.length);
                    }else{
                        return super.loadClass(name);
                    }
                } catch (Exception e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = myLoader.loadClass("com.springboot.jvm.MyClassLoader").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.springboot.jvm.MyClassLoader);
    }
}
