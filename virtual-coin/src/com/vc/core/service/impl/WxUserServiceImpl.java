package com.vc.core.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vc.core.dao.WxUserDao;
import com.vc.core.model.WxUser;
import com.vc.core.service.WxUserService;
import com.vc.core.util.AESEncoder;

@Service
public class WxUserServiceImpl implements WxUserService
{
  private static Logger logger = Logger.getLogger(WxUserServiceImpl.class);

  @Autowired
  private WxUserDao userDao;

  public WxUser getWxUser(String userId)
  {
    WxUser wxUser = null;
    if (userId != null && userId.length() > 0)
    {
      wxUser = userDao.getById(userId);
    } else
    {
      logger.error("方法 getWxUser，参数userId为空。");
    }

    return wxUser;
  }

  @Override
  public String addWxUser(WxUser wxUser)
  {
    String result = "";
    String errorMsg = "";

    if (wxUser != null)
    {
      String userId = wxUser.getUserId();
      String userName = wxUser.getUserName();
      String userPhone = wxUser.getUserPhone();
      String userSecret = wxUser.getUserSecret();

      if (userId == null || userId.length() == 0)
      {
        errorMsg += "userId为空，";
      }
      if (userName == null || userName.length() == 0)
      {
        errorMsg += "userName为空，";
      }
      if (userPhone == null || userPhone.length() == 0)
      {
        errorMsg += "userPhone为空，";
      }
      if (userSecret == null || userSecret.length() == 0)
      {
        errorMsg += "userSecret为空，";
      }

      if (errorMsg.length() == 0)
      {
        try
        {
          WxUser existUser = this.getWxUser(userId);
          boolean flag = false;

          if (existUser == null || existUser.getUserId() == null || existUser.getUserId().length() == 0)
          {
            if (wxUser.getUserStatus() <= 0)
            {
              wxUser.setUserStatus(1);
            }
            if (wxUser.getUserJiFen() <= 0)
            {
              wxUser.setUserJiFen(0);
            }
            wxUser.setUserCreateTime(new Date());
            wxUser.setUserModifyTime(new Date());

            flag = userDao.save(wxUser);
          } else
          {
            errorMsg = "数据插入失败，当前用户已经存在";
            logger.error("方法 addWxUser，当前用户数据已经存在，无法执行插入，用户id: " + userId + "，本次插入信息: " + wxUser.toString());
          }

          if (!flag)
          {
            errorMsg = "执行用户新增操作失败了。";
            logger.error("方法 addWxUser，" + errorMsg + "本次插入信息: " + wxUser.toString());
          }
        } catch (Exception e)
        {
          errorMsg = "执行用户新增操作发生异常";
          logger.error("方法 addWxUser，执行用户新增操作发生异常，用户信息: " + wxUser.toString(), e);
        }
      } else if (errorMsg.endsWith("，"))
      {
        errorMsg = errorMsg.substring(0, errorMsg.lastIndexOf("，"));
      }
    } else
    {
      logger.error("方法 addWxUser，插入数据时，参数 WxUser 对象为空。");
      errorMsg = "执行用户新增失败，WxUser对象为空!";
    }

    if (errorMsg.length() > 0)
    {
      result = "{\"status\":\"0\",\"errorMsg\":\"" + errorMsg + "\"}";
    } else
    {
      result = "{\"status\":\"1\"}";
    }

    return result;
  }

  @Override
  public String updateWxUser(WxUser wxUser)
  {
    String result = "";
    String errorMsg = "";

    if (wxUser != null)
    {
      String userId = wxUser.getUserId();
      String userName = wxUser.getUserName();
      String userPhone = wxUser.getUserPhone();
      String userSecret = wxUser.getUserSecret();

      if (userId == null || userId.length() == 0)
      {
        errorMsg += "userId为空，";
      }
      if (userName == null || userName.length() == 0)
      {
        errorMsg += "userName为空，";
      }
      if (userPhone == null || userPhone.length() == 0)
      {
        errorMsg += "userPhone为空，";
      }
      if (userSecret == null || userSecret.length() == 0)
      {
        errorMsg += "userSecret为空，";
      }

      if (errorMsg.length() == 0)
      {
        try
        {
          WxUser existUser = this.getWxUser(userId);
          boolean flag = false;

          if (existUser != null && existUser.getUserId() != null && existUser.getUserId().length() > 0)
          {
            wxUser.setUserModifyTime(new Date());
            flag = userDao.update(wxUser); // 已存在记录，将进行update
          } else
          {
            logger.error("方法 updateWxUser，执行用户信息修改时，用户不存在。当前要修改的用户信息： " + wxUser.toString());
            errorMsg = "执行修改失败，要修改的用户在数据库中不存在。";
          }

          if (!flag)
          {
            errorMsg = "执行用户修改操作失败了。";
            logger.error("方法 updateWxUser，" + errorMsg + "当前要修改的用户信息： " + wxUser.toString());
          }
        } catch (Exception e)
        {
          errorMsg = "执行用户修改操作发生异常";
          logger.error("方法 updateWxUser，执行用户修改操作发生异常，用户信息: " + wxUser.toString(), e);
        }
      } else if (errorMsg.endsWith("，"))
      {
        errorMsg = errorMsg.substring(0, errorMsg.lastIndexOf("，"));
      }
    } else
    {
      logger.error("方法 updateWxUser，参数 WxUser 对象为空。");
      errorMsg = "执行用户修改失败，WxUser对象为空!";
    }

    if (errorMsg.length() > 0)
    {
      result = "{\"status\":\"0\",\"errorMsg\":\"" + errorMsg + "\"}";
    } else
    {
      result = "{\"status\":\"1\"}";
    }

    return result;
  }

  public WxUser login(String userId, String password)
  {
    return userDao.login(userId, AESEncoder.encrypt(password));
  }

  @Override
  public boolean deleteWxUser(String userId)
  {
    return userDao.deleteById(userId);
  }

  @Override
  public List<WxUser> getWxUserLits(WxUser wxUser)
  {
    return userDao.findByExample(wxUser);
  }

@Override
public List<WxUser> getUserManager(WxUser wxUser) {
	return userDao.getUserManager(wxUser);
}
}
