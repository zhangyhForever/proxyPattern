package com.forever.proxyPattern.dynamicProxy.GPProxy.core;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @ Author       : forever
 * @ Date         : Created in 2019/4/22
 * @ Description  : what to do ...
 */
public class GPProxy {

    private static final String ln = "\r\n";

    public static Object newProxyInstance(GPClassLoader classLoader, Class<?>[] interfaces, GPInvocationHandler h) throws Exception {

        String src = generate(interfaces);

        File javaFile = writeJavaFile(src);

        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(null, null, null);
        Iterable iterable = fileManager.getJavaFileObjects(javaFile);

        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, fileManager, null, null, null, iterable);
        task.call();
        fileManager.close();


        Class<?> proxy0 = classLoader.findClass("$Proxy0");
        Constructor<?> constructor = proxy0.getConstructor(GPInvocationHandler.class);
        Object o = constructor.newInstance(h);
        javaFile.delete();
        return o;
    }

    private static File writeJavaFile(String src) {
        String classFilePath = GPProxy.class.getResource("").getPath();
        File file = new File(classFilePath,"$Proxy0.java");
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(src);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private static String generate(Class<?>[] interfaces) {
        StringBuffer sb = new StringBuffer();
        sb.append("package " + GPProxy.class.getPackage().getName() + ";" + ln);
        sb.append("import java.lang.reflect.*;" + ln);
        sb.append("import "+interfaces[0].getName()+";" + ln);
        sb.append("public class $Proxy0 implements "+ interfaces[0].getSimpleName() +"{" + ln );

            sb.append("GPInvocationHandler h;"  + ln);
            sb.append("public $Proxy0(GPInvocationHandler h){" + ln);
                sb.append("this.h = h;" + ln);
            sb.append("}" + ln);

            for(Method method: interfaces[0].getMethods()){
                sb.append("public "+ method.getReturnType() +" "+method.getName()+"(){" + ln);
                    sb.append("try{" + ln);
                        sb.append("Method m = " + interfaces[0].getSimpleName() + ".class.getMethod(\""+ method.getName() +"\", new Class[]{});" + ln );
                        sb.append("h.invoke(this, m, null);" + ln);
                    sb.append("}catch(Throwable e){" + ln);
                        sb.append("e.printStackTrace();" + ln);
                    sb.append("}" + ln);
                sb.append("}" + ln);
            }
        sb.append("}" + ln);
        return sb.toString();
    }
}
