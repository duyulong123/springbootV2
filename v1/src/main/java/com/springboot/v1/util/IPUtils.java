package com.springboot.v1.util;

import javax.servlet.http.HttpServletRequest;

public class IPUtils {

  /**
   * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
   * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
   *
   */
  public static String getRealIP(HttpServletRequest request) {
    String XIp = request.getHeader("X-Real-IP");
    String XFor = request.getHeader("X-Forwarded-For");
    if ((!StringUtil.isBlank(XFor)) && (!"unKnown".equalsIgnoreCase(XFor))) {
      int index = XFor.indexOf(",");
      if (index != -1) {
        return XFor.substring(0, index);
      }
      return XFor;
    }

    XFor = XIp;
    if ((!StringUtil.isBlank(XFor)) && (!"unKnown".equalsIgnoreCase(XFor))) {
      return XFor;
    }
    if ((StringUtil.isBlank(XFor)) || ("unknown".equalsIgnoreCase(XFor))) {
      XFor = request.getHeader("Proxy-Client-IP");
    }
    if ((StringUtil.isBlank(XFor)) || ("unknown".equalsIgnoreCase(XFor))) {
      XFor = request.getHeader("WL-Proxy-Client-IP");
    }
    if ((StringUtil.isBlank(XFor)) || ("unknown".equalsIgnoreCase(XFor))) {
      XFor = request.getHeader("HTTP_CLIENT_IP");
    }
    if ((StringUtil.isBlank(XFor)) || ("unknown".equalsIgnoreCase(XFor))) {
      XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if ((StringUtil.isBlank(XFor)) || ("unknown".equalsIgnoreCase(XFor))) {
      XFor = request.getRemoteAddr();
    }
    return XFor;
  }
}
