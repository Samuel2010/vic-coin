package com.vc.core.service;

import org.springframework.stereotype.Service;

import com.tw.ei.baseclass.page.Page;
import com.vc.core.model.TransInfo;

@Service
public interface TransInfoService {
	
	public Page<TransInfo>queryTransInfoByPage(TransInfo transInfo);
}
