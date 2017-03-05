package com.vc.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tw.ei.baseclass.base.BaseIbatis3Dao;
import com.vc.core.model.User;

@Repository
public class UserDao extends BaseIbatis3Dao<User, String>
{
  @Override
  public String getIbatisMapperNamesapce()
  {
    return "User";
  }

  public User login(String userLoginId, String userLoginPwd)
  {
    Map<String, String> params = new HashMap<String, String>();
    params.put("userLoginId", userLoginId);
    params.put("userLoginPwd", userLoginPwd);

    return (User) this.getSqlSessionTemplate().selectOne(getIbatisMapperNamesapce() + ".login", params);
  }
  
  public List<User> getUserManager(User User) {
	  return this.getSqlSessionTemplate().selectList(getIbatisMapperNamesapce() + ".getUserManager", User);
  }
}
