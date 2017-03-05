package com.vc.core.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vc.core.model.WxOptLog;

@Service
public interface WxOptLogService {
	public String addLog(WxOptLog optLog);
	
	public WxOptLog getOptLogById(String logId);
	
	public List<WxOptLog> getOptLogList(WxOptLog optLog);
}
