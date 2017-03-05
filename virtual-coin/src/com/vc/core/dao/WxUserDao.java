package com.vc.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tw.ei.baseclass.base.BaseIbatis3Dao;
import com.vc.core.model.WxUser;

@Repository
public class WxUserDao extends BaseIbatis3Dao<WxUser, String>
{
  @Override
  public String getIbatisMapperNamesapce()
  {
    return "WxUser";
  }

  public WxUser login(String userId, String password)
  {
    Map<String, String> params = new HashMap<String, String>();
    params.put("userId", userId);
    params.put("password", password);

    return (WxUser) this.getSqlSessionTemplate().selectOne(getIbatisMapperNamesapce() + ".login", params);
  }
  
  public List<WxUser> getUserManager(WxUser wxUser) {
	  return this.getSqlSessionTemplate().selectList(getIbatisMapperNamesapce() + ".getUserManager", wxUser);
  }
}
