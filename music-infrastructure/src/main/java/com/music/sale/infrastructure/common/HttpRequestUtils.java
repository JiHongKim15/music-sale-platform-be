package com.music.sale.infrastructure.common;

import jakarta.servlet.http.HttpServletRequest;

public final class HttpRequestUtils {

  private HttpRequestUtils() {}

  public static String getClientIP(HttpServletRequest request) {
    String ip = request.getHeader("X-Forwarded-For");
    if (isInvalidIp(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (isInvalidIp(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (isInvalidIp(ip)) {
      ip = request.getHeader("HTTP_CLIENT_IP");
    }
    if (isInvalidIp(ip)) {
      ip = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (isInvalidIp(ip)) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }

  public static String getUserAgent(HttpServletRequest request) {
    return request.getHeader("User-Agent");
  }

  private static boolean isInvalidIp(String ip) {
    return ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip);
  }
}
