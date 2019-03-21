package com.forever.proxyPattern.dynamicProxy.customProxy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
/**
 *
 * 创建代理类
 * @ Author       : forever
 * @ Date         : Created in 2019/3/21
 * @ Description  : what to do ...
 */
public class CustomProxy {

    private static final String ln = "\r\n";

    public static Object newInstance(CustomClassLoader loader, Class<?>[] interfaces, CustomInvocationHandler h){
        try{

            //动态生成源代码
            String src = generateSrc(interfaces);

            //保存java文件到磁盘
            File file = saveJavaFile(src);
            System.out.println("javaFile======"+file.getPath());

            //手动生成的java文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

            //标准文件管理器
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            Iterable iterable = manager.getJavaFileObjects(file);

            //创建编译任务
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
            task.call();
            manager.close();

            //编译生成的.class文件加载到JVM中来
            Class proxyClass = loader.findClass("$Proxy0");
            Constructor constructor = proxyClass.getConstructor(CustomInvocationHandler.class);
            file.delete();
            return constructor.newInstance(h);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static File saveJavaFile(String src) {
        String path = CustomProxy.class.getResource("").getPath();
        File f = new File(path + "$Proxy0.java");
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(src);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }

    private static String generateSrc(Class<?>[] interfaces) {
        StringBuilder sb = new StringBuilder();
        sb.append("package com.forever.proxyPattern.dynamicProxy.customProxy;" + ln);
        sb.append("import java.lang.reflect.*;" + ln);
        sb.append("public final class $Proxy0 implements " + interfaces[0].getName() + "{" + ln);
            sb.append("CustomInvocationHandler h;" + ln);
            sb.append("public $Proxy0(CustomInvocationHandler h){" + ln);
                sb.append("this.h = h;" + ln);
            sb.append("}" + ln);
            for(Method m: interfaces[0].getMethods()){
                sb.append("public " + m.getReturnType() + " " + m.getName() + "(){" + ln);
                    sb.append("try{" + ln);
                        sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + m.getName() + "\",new Class[]{});" + ln);
                        sb.append("this.h.invoke(this, m, null);" + ln);
                    sb.append("}catch(Throwable e){" + ln);
                    sb.append("e.printStackTrace();" + ln);
                sb.append("}" + ln);
            }
            sb.append("}" + ln);
        sb.append("}" + ln);
        return sb.toString();
    }

}
