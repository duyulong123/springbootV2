package com.springboot.v1.util;

import java.util.List;
import java.util.Map;

public class StringUtil {

  /**
   * @Title: isEmptyString     
   * @Description: 判断一个字符串是否为空   
   * @param str
   * @return       
   */
  public static boolean isEmptyString(String str) {
    if (str != null) {
      int l = str.trim().length();
      return l == 0;
    } else {
      return true;
    }
  }

  /**
   * @Title: isEmptyObject     
   * @Description: 判断一个对象是否为空    
   * @param o
   * @return       
   */
  public static boolean isEmptyObject(Object o) {
    if (o == null) {
      return true;
    }
    return isEmptyString(o.toString());
  }

  /**
   * 判断是否是空字符串
   * 
   * @param obj
   *            要判断的对象
   * @return 如果 obj 为 null 或者是长度为零的字符串，返回 true； 其它情况返回 false，如果 obj 不是 String
   *         类型，返回 false。<code>
   *   return (null == obj ||
   *          (obj instanceof String && 0 == ((String)obj).length()));
   * </code>
   */
  public static boolean isEmptyString(Object obj) {
    return (null == obj || (obj instanceof String && 0 == ((String) obj).length()));
  }

  /**
   * @MethodName:isBlank
   * @Description:判断是否为空
   * @param cs
   * @return boolean
   */
  public static boolean isBlank(CharSequence cs) {
    int strLen;
    if ((cs == null) || ((strLen = cs.length()) == 0)) {
      return true;
    }
    for (int i = 0; i < strLen; i++) {
      if (!Character.isWhitespace(cs.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  /**
   * @Title: trim     
   * @Description: 对一个字符串去空格   
   * @param str
   * @return       
   */
  public static String trim(String str) {
    return str == null ? "" : str.trim();
  }

  public static String nullToEmpty(Object value) {
    if (null == value) {
      return "";
    }
    String tempString = String.valueOf(value);
    if ("null".equalsIgnoreCase(tempString) || "".equals(tempString)) {
      return "";
    }
    return tempString;
  }

  public static boolean isEmpty(Object value) {
    String valueString = nullToEmpty(value);
    if (null == valueString) {
      return true;
    }
    if ("null".equalsIgnoreCase(valueString) || "".equals(valueString)) {
      return true;
    }
    return false;
  }

  public static boolean isNotEmpty(String value) {
    return !isEmpty(value);
  }

  public static boolean isNotEmpty(Object value) {
    return !isEmpty(value);
  }

  public static boolean isNotEmpty(Map<?, ?> map) {
    return !(map == null || map.isEmpty());
  }

  public static boolean isNotEmpty(List<?> list) {
    return !(list == null || list.isEmpty());
  }

}
