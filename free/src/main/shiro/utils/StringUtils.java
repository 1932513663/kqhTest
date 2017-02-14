package shiro.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @ClassName: StringUtils
 * @Title: StringUtils.java
 * @Description: TODO
 *
 * @author kang
 * @version V1.0 
 * @company 麦田
 * @date 2017年2月13日 下午12:55:20
 */
public class StringUtils {

    /**
     * String转Integer
     * @param str
     * @return
     */
    public static Integer string2Integer(String str) {
        if (str == null||"".equals(str)) return null;
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return null;
        }
        return Integer.parseInt(str);

    }

    /**
     * 转换UTF-8编码
     * @param str
     * @return
     */
    public static String toUnicodeString(String str){
        if(str==null) return null;
        try{
            str=new String(str.trim().getBytes("ISO-8859-1"),"UTF-8");
        }catch(Exception e){
            return str;
        }

        return str;
    }
}
