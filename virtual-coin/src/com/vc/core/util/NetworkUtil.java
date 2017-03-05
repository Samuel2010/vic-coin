package com.vc.core.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * 常用获取客户端信息的工具
 * 
 */
public final class NetworkUtil
{
  /**
   * Logger for this class
   */
  private static Logger logger = Logger.getLogger(NetworkUtil.class);

  /**
   * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
   * 
   * @param request
   * @return
   * @throws IOException
   */
  public final static String getIpAddress(HttpServletRequest request) throws IOException
  {
    // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

    String ip = request.getHeader("X-Forwarded-For");
    if (logger.isDebugEnabled())
    {
      logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
    }

    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
    {
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
      {
        ip = request.getHeader("Proxy-Client-IP");
        if (logger.isDebugEnabled())
        {
          logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
        }
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
      {
        ip = request.getHeader("WL-Proxy-Client-IP");
        if (logger.isDebugEnabled())
        {
          logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
        }
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
      {
        ip = request.getHeader("HTTP_CLIENT_IP");
        if (logger.isDebugEnabled())
        {
          logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
        }
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
      {
        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (logger.isDebugEnabled())
        {
          logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
        }
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
      {
        ip = request.getRemoteAddr();
        if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1"))
        {
          // 根据网卡取本机配置的IP
          InetAddress inet = null;
          try
          {
            inet = InetAddress.getLocalHost();
          } catch (UnknownHostException e)
          {
            e.printStackTrace();
          }
          ip = inet.getHostAddress();
        }
        if (logger.isDebugEnabled())
        {
          logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
        }
      }
    } else if (ip.length() > 15)
    {
      String[] ips = ip.split(",");
      for (int index = 0; index < ips.length; index++)
      {
        String strIp = (String) ips[index];
        if (!("unknown".equalsIgnoreCase(strIp)))
        {
          ip = strIp;
          break;
        }
      }
    }
    logger.info("getIpAddress(HttpServletRequest) - String ip=" + ip);
    return ip;
  }
}