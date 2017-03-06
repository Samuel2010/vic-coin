 
package com.vc.core.service;
import org.springframework.stereotype.Service;

import com.tw.ei.baseclass.page.Page;
import com.vc.core.model.EntrustInfo;

@Service
public interface EntrustInfoService{
	//分页查询
	public Page<EntrustInfo> findPage(EntrustInfo query);
	
}
