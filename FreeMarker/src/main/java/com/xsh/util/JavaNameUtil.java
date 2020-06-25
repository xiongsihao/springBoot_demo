package com.xsh.util;

/**
 * @author : xsh
 * @create : 2020-06-25 - 15:01
 * @describe:因为直接获取的表名，列信息有时候不满足命名规范，所以这个工具类的作用是元信息名字转换
 * 驼峰命名：第一个单词以小写字母开始；第二个单词的首字母大写或每一个单词的首字母都采用大写字母
 * 帕斯卡命名法：每个单词都大写,常用于类名，函数名，属性，命名空间。
 */
public class JavaNameUtil {

    public static void main(String[] args) {
        String str="imagines_age_name";
        String javaname1 = JavaNameUtil.toPascal(str);
        String javaname2 = JavaNameUtil.toCamel(str);
        System.out.println("帕斯卡命名:"+javaname1);
        System.out.println("驼峰命名转换："+javaname2);
    }


    /**
     * 将数据库（表、字段）转换以java命名方式帕斯卡或者驼峰
     * @param unberscoreName
     * @param isPascal     是否将首字母转化大写，true则转化为驼峰命名，false则转换为帕斯卡命名
     * @return 驼峰或帕斯卡命名字符串
     */
    public static String translate(String unberscoreName, boolean isPascal) {
        StringBuilder result = new StringBuilder();
        //从第一个字母
        if (unberscoreName != null && unberscoreName.length() !=0) {
            boolean flag = false;
            char firstChar = unberscoreName.charAt(0);  //得到首字母
            if (isPascal) {
                result.append(Character.toUpperCase(firstChar));
            } else {
                result.append(firstChar);
            }
            //从第二个字母以后开始
            for (int i = 1, length = unberscoreName.length(); i < length; i++) {
                char ch = unberscoreName.charAt(i);
                if ('_' == ch) {
                    flag = true;
                } else {
                    if (flag) { //标记上一个是下划线，就转化为大写。
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }

    /**
     * 调用translate() 转换为帕斯卡命名
     * @param unberscoreName 数据库（表名、字段名）
     * @return
     */
    public static String toPascal(String unberscoreName) {
        return translate(unberscoreName, true);
    }

    /**
     * 调用translate() 转换为驼峰命名
     * @param unberscoreName 数据库（表名、字段名）
     * @return
     */
    public static String toCamel(String unberscoreName) {
        return translate(unberscoreName, false);
    }

    /**
     *
     * 将获取数据库类型转化为java类型
     * @param dbTypeName 实际的数据库类型
     * @return
     */
    public static String dbTypeChangeJavaType(String dbTypeName){
        String javaType=null;
        switch(dbTypeName){
            case "VARCHAR" :javaType="String";break;
            case "BIGINT" :javaType="Long";break;
            case "INT" :javaType="Integer";break;
            case "DATETIME" :javaType="Date";break;
            default:javaType="String";break;
        }
        return javaType;
    }
}
