package com.forever.proxyPattern.dynamicProxy.GPProxy.core;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/4/22
 * @ Description  : what to do ...
 */
public class GPClassLoader extends ClassLoader{

    private File classPath;

    public GPClassLoader(){
        String classFilePath = GPClassLoader.class.getResource("").getPath();
        classPath = new File(classFilePath);
    }

    @Override
    public Class<?> findClass(String name) {
        String className = GPClassLoader.class.getPackage().getName() + "." + name;
        File classFile = new File(classPath, name.replaceAll("\\.", "/") + ".class");
        if(!classFile.exists()){
            System.out.println(classFile.getName()+"不存在！");
            return null;
        }
        FileInputStream fin = null;
        ByteArrayOutputStream out = null;
        try {
            fin = new FileInputStream(classFile);
            out = new ByteArrayOutputStream();

            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = fin.read(bytes)) != -1){
                out.write(bytes, 0, len);
            }

            Class<?> clazz = defineClass(className, out.toByteArray(), 0, out.size());
            return clazz;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
