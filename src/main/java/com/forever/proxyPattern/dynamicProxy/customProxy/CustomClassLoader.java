package com.forever.proxyPattern.dynamicProxy.customProxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/3/21
 * @ Description  : what to do ...
 */
public class CustomClassLoader extends ClassLoader{

    private File classFilePath;
    public CustomClassLoader(){
        String classPath = CustomClassLoader.class.getResource("").getPath();
        this.classFilePath = new File(classPath);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String className = CustomClassLoader.class.getPackage().getName()+ "." + name;
        System.out.println("className====="+className);
        if(classFilePath != null) {
            File classFile = new File(classFilePath, name.replaceAll("\\.", "/") + ".class");
            System.out.println("File=====" + classFile.getPath());
            if (classFile.exists()) {
                ByteArrayOutputStream bos = null;
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(classFile);
                    bos = new ByteArrayOutputStream();
                    int len;
                    byte[] buff = new byte[1024];
                    while ((len = fis.read()) != -1) {
                        bos.write(buff, 0, len);
                    }
                    return defineClass(className, bos.toByteArray(), 0, bos.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
