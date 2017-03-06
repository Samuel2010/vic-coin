/*
 * 该类主要负责系统主要业务逻辑的实现,如多表处理的事务操作、权限控制等
 * 该类根据具体的业务逻辑来调用该实体对应的Dao或者多个Dao来实现数据库操作
 * 实际的数据库操作在对应的Dao或其他Dao中实现
 */
 
package com.vc.core.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import com.tw.ei.baseclass.base.*;
import com.tw.ei.baseclass.util.*;
import com.tw.ei.baseclass.page.*;

import com.vc.core.model.*;
import com.vc.core.dao.*;
import com.vc.core.service.*;

/**
 * @author lw
 * @version 1.0
 * @since  
 */

@Service
public class EntrustInfoServiceImpl implements EntrustInfoService{
	@Autowired
	//EntrustInfo对应的DAO类,主要用于数据库的增删改查等操作
	private EntrustInfoDao entrustInfoDao;
	
	public EntityDao getEntityDao() {
		return this.entrustInfoDao;
	}
	//分页查询
	public Page<EntrustInfo> findPage(EntrustInfo query) {
		return entrustInfoDao.pageQuery(query);
	}
	
}
