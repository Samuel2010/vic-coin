package com.vc.core.dao;

import org.springframework.stereotype.Repository;

import com.tw.ei.baseclass.base.BaseIbatis3Dao;
import com.vc.core.model.WxOptLog;

@Repository
public class WxOptLogDao extends BaseIbatis3Dao<WxOptLog,String>{
	@Override
	public String getIbatisMapperNamesapce() {
		return "WxOptLog";
	}
}
