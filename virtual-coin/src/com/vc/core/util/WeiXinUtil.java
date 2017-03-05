package com.vc.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.tw.ei.baseclass.util.PropertiesHelper;

public class WeiXinUtil
{
  private static Logger logger = Logger.getLogger(WeiXinUtil.class);

  private static PropertiesHelper propertiesHelper = null;

  public static int PS_ATN_YES = 1; // 帖子关注状态：关注
  public static int PS_ATN_NO = 0; // 帖子关注状态：未关注
  public static int PS_ATN_THUMBS_YES = 1; // 帖子类型：点赞
  public static int PS_ATN_THUMBS_NO = 0; // 帖子类型：未点赞

  public static int PS_STATUS_OK = 1; // 帖子状态：可用
  public static int PS_STATUS_LOCK = 0; // 帖子状态：不可用

  public static int PS_CHECK_STATUS_DAI_SHEN_HE = 0; // 帖子状态：待审核
  public static int PS_CHECK_STATUS_SHEN_HE_ZHONG = 1; // 帖子状态：审核中
  public static int PS_CHECK_STATUS_SHEN_HE_PASS = 2; // 帖子状态：审核通过
  public static int PS_CHECK_STATUS_SHEN_HE_FAIL = -1; // 帖子状态：审核不通过

  public static int PS_DYEJ_SEC_ID=36;//党员E家SEC_ID
  
  public static int PS_ADVICES_SEC_ID=37;//建议反馈SEC_ID
  
  public static int FILE_TYPE_NORMAL = 1; // 附件类型：普通帖子附件
  public static int FILE_TYPE_GUNDONG_PIC = 2; // 附件类型：滚动图片

  public static int FILE_STATUS_OK = 1; // 附件状态：正常
  public static int FILE_STATUS_DELETE = 0; // 附件状态：删除

  public static int FILE_IS_PIC = 1; // 附件是否为图片：是
  public static int FILE_IS_NOT_PIC = 2; // 附件是否为图片：否
  
  public static int LOG_OPT_ADD = 1; // 日志记录的操作类型：新增
  public static int LOG_OPT_UPDATE = 2; // 日志记录的操作类型：修改
  public static int LOG_OPT_DELETE = 3; // 日志记录的操作类型：删除
  public static int LOG_OPT_ZHIDING = 4; // 日志记录的操作类型：置顶
  public static int LOG_OPT_CANCEL_ZHIDING = 5; // 日志记录的操作类型：取消置顶
  public static int LOG_OPT_VIEW = 6; // 日志记录的操作类型：阅读
  public static int LOG_OPT_ADD_FILE = 7; // 日志记录的操作类型：添加帖子附件
  public static int LOG_OPT_ATTENTION = 8; // 日志记录的操作类型：关注帖子
  
  
  public static String SC_CODE_ATTENTION_WX = "001"; // 积分规则编码：关注微信
  public static String SC_CODE_POST_PASS = "002"; // 积分规则编码：发帖-审核通过
  public static String SC_CODE_POST_TOP = "003"; // 积分规则编码：发帖-置顶
  public static String SC_CODE_POST_REPLY = "004"; // 积分规则编码：回帖
  public static String SC_CODE_POST_REPLY_TOP = "005"; // 积分规则编码：回帖置顶
  public static String SC_CODE_POST_BROWSE = "006"; // 积分规则编码：浏览帖子
  public static String SC_CODE_RECOMMEND = "011"; // 积分规则编码：建议反馈
  public static String SC_CODE_MODERATOR = "012"; // 积分规则编码：版主，按月结算
  public static String SC_CODE_GIVE_10 = "013"; // 积分规则编码：获赠积分（前10），按月积分排行进行结算
  public static String SC_CODE_GIVE_50 = "014"; // 积分规则编码：获赠积分（前11-50），按月积分排行进行结算
  public static String SC_CODE_POST_INCIVI_LANG = "015"; // 积分规则编码：低俗不文明语言
  public static String SC_CODE_POST_NO_THEME = "016"; // 积分规则编码：帖子与主题无关
  public static String SC_CODE_POST_NO_MODE = "017"; // 积分规则编码：未对应模块发贴
  public static String SC_CODE_POST_REPLY_NO_THEME = "018"; // 积分规则编码：回贴无针对性
  public static String SC_CODE_EVENT_SHARE = "019"; // 平台活动推广：分享赚
  public static String SC_CODE_EVENT_READ = "020"; // 平台活动推广：学习赚
  public static String SC_CODE_POST_NO_LAW = "021"; //积分规则编码:违反法律或公司规定
  public static String SC_CODE_POST_FUNTIME = "022"; //积分规则编码:发布轻松一刻

  public static String WEIXIN_API_QY_USER = "WEIXIN_API_QY_USER";

  public static String WEIXIN_QY_USER = "WEIXIN_QY_USER";

  public static String WEIXIN_QY_USER_RIGHTS = "WEIXIN_QY_USER_RIGHTS";

  /**
   * 获取企业号ID
   * 
   * @return
   */
  public static String getCorpId()
  {
    return WeiXinUtil.getSysConfigValue("corpId");
  }

  /**
   * 获取当前应用路径
   * 
   * @return
   */
  public static String getAppUrl()
  {
    String url = WeiXinUtil.getSysConfigValue("app_path");
    if (!url.endsWith("/"))
    {
      url = url + "/";
    }

    return url;
  }

  /**
   * 读sysConfig.xml中的内容
   * 
   * @param key
   * @return
   */
  public static String getSysConfigValue(String key)
  {
    return WeiXinUtil.getSysConfigValue(key, false);
  }

  /**
   * 读sysConfig.xml中的内容
   * 
   * @param key
   * @param reload
   * @return
   */
  public static String getSysConfigValue(String key, boolean reload)
  {
    if (WeiXinUtil.propertiesHelper == null || reload)
    {
      try
      {
        String filePath = SysConfig.class.getResource("").getPath();
        filePath = java.net.URLDecoder.decode(filePath, "utf-8");
        if (filePath != null && filePath.length() > 0)
        {
          if (filePath.indexOf("/com") > 0)
          {
            filePath = filePath.substring(0, filePath.indexOf("/com"));
          }
          if (!filePath.endsWith("/"))
          {
            filePath += "/";
          }
          filePath += "sys-config.xml";
          File file = new File(filePath);
          if (file.exists())
          {
            InputStream is = new FileInputStream(file);

            Properties properties = new Properties();
            WeiXinUtil.propertiesHelper = new PropertiesHelper(properties);
            WeiXinUtil.propertiesHelper.loadFromXML(is);
          }
        }
      } catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    String result = "";
    if (WeiXinUtil.propertiesHelper.get(key) != null)
    {
      result = (String) WeiXinUtil.propertiesHelper.get(key);
    }

    return result;
  }

  /**
   * 正则查找，返回第一个结果集
   * 
   * @param regex
   * @param findStr
   * @param groupIndex
   * @return
   */
  public static String findMatcher(String regex, CharSequence findStr, int groupIndex)
  {
    String result = "";

    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(findStr);
    if (matcher.find())
    {
      result = matcher.group(groupIndex);
    }
    if (result == null || result.trim().equals(""))
    {
      logger.info("没有找到regex在:\n" + findStr.toString() + "里面");
    }
    return result.trim();
  }

  /**
   * 正则查找，返回所有匹配结果集
   * 
   * @param regex
   * @param input
   * @param groupIndex
   * @return
   */
  public static List<String> findAllMatch(String regex, CharSequence input, int groupIndex)
  {
    List<String> list = new ArrayList<String>();

    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(input);
    int start = 0;
    while (matcher.find(start))
    {
      start = matcher.end();

      String findStr = matcher.group(groupIndex);
      if (findStr != null && findStr.trim().length() > 0)
      {
        list.add(findStr.trim());
      }
    }

    return list;
  }

  /**
   * 字符串去空格
   * 
   * @param str
   * @return
   */
  public static String trimStr(String str)
  {
    if (str != null && str.trim().length() > 0)
    {
      str = str.trim();
    } else
    {
      str = "";
    }

    return str;
  }

  /**
   * 从json字符串中获取值
   * 
   * @param flag
   * @return
   */
  public static String getRegexFromJson(String flag)
  {
    flag = trimStr(flag);
    return "\"*" + flag + "\"*\\s*:\\s*\"*([^,|\"|\\}]+)\"*";
  }

  /**
   * 获取带字母的随机数
   * 
   * @param length
   * @return
   */
  public static String getCharAndNumr(int length)
  {
    String val = "";
    Random random = new Random();
    for (int i = 0; i < length; i++)
    {
      // 输出字母还是数字
      String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
      // 字符串
      if ("char".equalsIgnoreCase(charOrNum))
      {
        // 取得大写字母还是小写字母
        int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
        val += (char) (choice + random.nextInt(26));
      } else if ("num".equalsIgnoreCase(charOrNum))
      { // 数字
        val += String.valueOf(random.nextInt(10));
      }
    }

    return val;
  }

  /**
   * 获取帖子的审核状态描述
   * 
   * @param checkStatus
   * @return
   */
  public static String getPsShenHeDesc(int checkStatus)
  {
    String result = "";
    if (checkStatus == WeiXinUtil.PS_CHECK_STATUS_DAI_SHEN_HE)
    {
      result = "待审核";
    } else if (checkStatus == WeiXinUtil.PS_CHECK_STATUS_SHEN_HE_ZHONG)
    {
      result = "审核中";
    } else if (checkStatus == WeiXinUtil.PS_CHECK_STATUS_SHEN_HE_PASS)
    {
      result = "审核通过";
    } else if (checkStatus == WeiXinUtil.PS_CHECK_STATUS_SHEN_HE_FAIL)
    {
      result = "审核不通过";
    }

    return result;
  }

  public static void main(String args[])
  {
    int length = 120;
    String str = WeiXinUtil.getCharAndNumr(length);
    System.out.println("str: " + str);
  }
}
