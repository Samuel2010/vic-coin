package com.vc.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.util.Assert;

import com.tw.ei.baseclass.util.PropertiesHelper;

public class SysConfig
{
  public static final String SYS_CONFIG_FILE = "sys-config.xml";
  private static PropertiesHelper propertiesHelper = null;

  static
  {
    try
    {
      Properties properties = new Properties();
      propertiesHelper = new PropertiesHelper(properties);
      propertiesHelper.loadFromXML(getPathConfig(SYS_CONFIG_FILE));
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public static Properties getConfigPropertis()
  {
    return propertiesHelper.getProperties();
  }

  public static Object getValue(String key)
  {
    return propertiesHelper.get(key);

  }

  public static Map mergePropertiesToMap(Properties props)
  {
    Map newMap = new HashMap();
    Assert.notNull(props, "props must not be null");
    if (props != null)
    {
      String key;
      for (Enumeration enumn = props.propertyNames(); enumn.hasMoreElements(); newMap.put(key, props.getProperty(key)))
        key = (String) enumn.nextElement();

    }
    return newMap;
  }

  public static PropertiesHelper getPropertiesHelper()
  {
    return propertiesHelper;
  }

  private static InputStream getPathConfig(String filePathName) throws IOException
  {
    InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePathName);
    return in;
  }

  public static void main(String[] args)
  {
    System.out.println(SysConfig.getValue("contract_url"));
  }
}
