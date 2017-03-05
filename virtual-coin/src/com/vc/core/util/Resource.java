package com.vc.core.util;

import java.io.IOException;
import java.io.InputStream;

public class Resource
{
  public InputStream getInputStream(String fileName) throws IOException
  {
    // 返回读取指定资源的输入流
    InputStream is = this.getClass().getResourceAsStream(fileName);
    return is;
  }
}
