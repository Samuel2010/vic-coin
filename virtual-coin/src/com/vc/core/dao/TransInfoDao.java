package com.vc.core.dao;

import org.springframework.stereotype.Repository;

import com.tw.ei.baseclass.base.BaseIbatis3Dao;
import com.tw.ei.baseclass.page.Page;
import com.vc.core.model.TransInfo;

@Repository
public class TransInfoDao extends BaseIbatis3Dao<TransInfo, String> {

	public Page<TransInfo> queryTransInfoByPage(TransInfo transInfo) {
		Page<TransInfo> resultList = new Page<TransInfo>();

		transInfo.setSortColumns("TRANS_TIME DESC");

		resultList = this.pageQuery(getIbatisMapperNamesapce() + ".findPage",transInfo);

		return resultList;
	}
}