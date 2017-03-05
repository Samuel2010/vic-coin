package com.vc.core.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vc.core.model.WxUser;

@Service
public interface WxUserService
{
  public WxUser getWxUser(String userId);

  public List<WxUser> getWxUserLits(WxUser wxUser);

  public String addWxUser(WxUser wxUser);

  public String updateWxUser(WxUser wxUser);

  public boolean deleteWxUser(String userId);

  public WxUser login(String userId, String password);
  
  public List<WxUser> getUserManager(WxUser wxUser);

}
