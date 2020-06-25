package com.xsh;

import com.xsh.model.Attribute;
import com.xsh.util.FreeMarkerInit;
import com.xsh.util.JavaNameUtil;
import com.xsh.util.MetadataUtil;
import freemarker.template.Template;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : xsh
 * @create : 2020-06-25 - 15:10
 * @describe: 根据数据库内字段名，自动生成java实体类
 */
public class CreateJavaTemplateTest {
    //生成java实体类
    public void createBean() throws Exception{
        //获取项目根目录
        String basicPath=System.getProperty("user.dir");
        //文件最终生成路径
        String savePath=basicPath+"\\FreeMarker\\src\\main\\java\\com\\xsh\\pojo";
        //判断文件夹是否存在，不存在则创建文件夹
        File file=new File(savePath);
        if(!file.exists()){
            throw new Exception("文件夹不存在，请检查路径是否存在："+savePath);
        }

        //获取模板
        Template temp = FreeMarkerInit.getInstance().getDefinedTemplate("javabean.ftl");
        //获取表名集合
        List<String> strs= MetadataUtil.getTableNames();
        for (String str1: strs
        ) {
            //Attribute里面封装模板使用属性
            List<String[]> strList=MetadataUtil.getTableColumnsInfo(str1);
            List<Attribute> attr_list = new ArrayList<Attribute>();
            for (String[] c:strList
            ) {
                attr_list.add(new Attribute(JavaNameUtil.dbTypeChangeJavaType(c[2]), JavaNameUtil.toCamel(c[0]),c[1]));
            }

            //装换为帕斯卡命名
            String str=JavaNameUtil.toPascal(str1);

            Map<String, Object> root = new HashMap<String, Object>();
            root.put("packageName", "com.xsh.pojo");//包名
            root.put("className", str);
            root.put("attrs", attr_list);
            OutputStream fos = new FileOutputStream( new File(savePath, str+".java"));
            Writer out = new OutputStreamWriter(fos);
            temp.process(root, out);
            fos.flush();
            fos.close();
        }
    }



    public static void main(String[] args) {
        CreateJavaTemplateTest test = new CreateJavaTemplateTest();
        try {
            test.createBean();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
