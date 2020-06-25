package com.xsh.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;

/**
 * @author : xsh
 * @create : 2020-06-25 - 14:57
 * @describe: Freemarker加载类
 *  项目不要存放在带有中文目录的文件夹下
 *  否则获取classpath路径时会因为有中文名而乱码，获取不到文件，抛出java.io.FileNotFoundException异常
 */
public class FreeMarkerInit {

    private static FreeMarkerInit single= new FreeMarkerInit();
    private FreeMarkerInit() {}
    //静态工厂方法
    public static FreeMarkerInit getInstance() {
        return single;
    }

    public Template getDefinedTemplate(String templateName) throws Exception{
        //配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        //得到classpath路径，即模板存放路径
        String classpath = this.getClass().getResource("/").getPath();
        //定义模板路径
        configuration.setDirectoryForTemplateLoading(new File(classpath+"/templates/"));
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return configuration.getTemplate(templateName);
    }
}