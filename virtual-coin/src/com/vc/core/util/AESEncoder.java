package com.vc.core.util;

import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESEncoder
{
  private final static String KEY = "talkweb123456talkweb1234";
  private final static String CIPHER_INSTANCE = "DESede/ECB/PKCS5Padding";
  private final static String ALGORITHM = "DESede";

  public static String decrypt(String src)
  {
    return decrypt(src, KEY);
  }

  public static String decrypt(String src, String key)
  {
    try
    {
      if (key == null)
      {
        return null;
      }

      byte[] raw = key.getBytes("UTF-8");

      SecretKey skeySpec = new SecretKeySpec(raw, ALGORITHM);
      Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
      cipher.init(2, skeySpec);
      byte[] encrypted = hex2byte(src);
      byte[] original = cipher.doFinal(encrypted);
      return new String(original);
    } catch (Exception ex)
    {
    }
    return null;
  }

  public static String encrypt(String src)
  {
    return encrypt(src, KEY);
  }

  public static String encrypt(String src, String key)
  {
    try
    {
      if (key == null)
      {
        return null;
      }

      byte[] raw = key.getBytes("UTF-8");
      SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM);
      Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
      cipher.init(1, skeySpec);
      byte[] encrypted = cipher.doFinal(src.getBytes());
      return byte2hex(encrypted).toLowerCase(Locale.ENGLISH);
    } catch (Exception ex)
    {
    }
    return null;
  }

  public static void main(String[] args)
  {
    String str = "{\"errcode\":0,\"errmsg\":\"ok\",\"userid\":\"test\",\"name\":\"test\",\"department\":[1],\"gender\":\"1\",\"weixinid\":\"test\",\"avatar\":\"http://shp.qpic.cn/bizmp/SVhYhMxibTYUibgNlITYbhgzTD3Gz49e09C32CsiaKqkkR5P5yAvKa2AQ/\",\"status\":1,\"extattr\":{\"attrs\":[]}}";
    String enStr = encrypt(str, KEY);
    System.out.println(enStr);
    String deStr = decrypt(enStr, KEY);
    System.out.println(deStr);
    deStr = decrypt("8208900202E672AD", KEY);
    System.out.println(deStr);
  }

  private static byte[] hex2byte(String hex)
  {
    if (hex == null)
      return null;
    int l = hex.length();
    if (l % 2 == 1)
      return null;
    byte[] bytes = new byte[l / 2];
    for (int i = 0; i != l / 2; i++)
      bytes[i] = ((byte) Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16));
    return bytes;
  }

  private static String byte2hex(byte[] bytes)
  {
    String hs = "";
    String stmp = "";
    for (int i = 0; i < bytes.length; i++)
    {
      stmp = Integer.toHexString(bytes[i] & 0xFF);
      if (stmp.length() == 1)
        hs = hs + "0" + stmp;
      else
        hs = hs + stmp;
    }
    return hs.toUpperCase(Locale.ENGLISH);
  }
}