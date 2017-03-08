package com.vc.core.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tw.ei.baseclass.page.Page;
import com.vc.core.dao.TransInfoDao;
import com.vc.core.model.TransInfo;
import com.vc.core.service.TransInfoService;

@Service
public class TransInfoServiceImpl implements TransInfoService {
	private static Logger logger = Logger.getLogger(TransInfoServiceImpl.class);
	
	@Autowired
	private TransInfoDao transDao;

	@Override
	public Page<TransInfo> queryTransInfoByPage(TransInfo transInfo) {
		return transDao.queryTransInfoByPage(transInfo);
	}

}
