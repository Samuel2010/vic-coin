package com.vc.core.dao;

import org.springframework.stereotype.Repository;

import com.tw.ei.baseclass.base.BaseIbatis3Dao;
import com.vc.core.model.WxUserMsg;

@Repository
public class WxUserMsgDao extends BaseIbatis3Dao<WxUserMsg,String>{
	@Override
	public String getIbatisMapperNamesapce() {
		return "WxUserMsg";
	}
	
	public int getMyMsgCount(String psUserId)
	{
	    return (Integer) this.getSqlSessionTemplate().selectOne(getIbatisMapperNamesapce() + ".myMsgCount", psUserId);
	}
	
	public void updateUserMsg(String psId) {
		this.getSqlSessionTemplate().selectOne(getIbatisMapperNamesapce() + ".updateUserMsg", psId);
	}
}
