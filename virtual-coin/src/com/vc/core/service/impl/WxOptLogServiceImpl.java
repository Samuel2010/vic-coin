package com.vc.core.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vc.core.dao.WxOptLogDao;
import com.vc.core.model.WxOptLog;
import com.vc.core.service.WxOptLogService;

@Service
public class WxOptLogServiceImpl implements WxOptLogService {
	private static Logger logger = Logger.getLogger(WxOptLogServiceImpl.class);
	
	@Autowired
	private WxOptLogDao logDao;

	@Override
	public String addLog(WxOptLog optLog) {
		String result = "";
		String errorMsg = "";
		
		if(optLog != null){
			String logId = UUID.randomUUID().toString();
			optLog.setLogId(logId);
			
			String logUserId = optLog.getLogUserId();
			String logPsId = optLog.getLogPsId();
			int logOpt = optLog.getLogOpt();
			
			if(logUserId == null || logUserId.length() == 0){
				errorMsg += "logUserId 为空，";
			}
			if(logPsId == null || logPsId.length() == 0){
				errorMsg += "logPsId 为空，";
			}
			if(logOpt <= 0){
				errorMsg += "logOpt 无效，";
			}
			
			if(errorMsg.length() == 0){
				try{
					optLog.setLogCreateTime(new Date());
					optLog.setLogModifyTime(new Date());
					
					boolean flag = logDao.save(optLog);
					if(!flag){
						errorMsg = "执行新增日志信息关注操作失败了。";
						logger.error("方法 addLog，"+errorMsg+"当前日志信息: "+optLog.toString());
					}
				}catch(Exception e){
					logger.error("方法 addLog，执行新增日志信息时发生异常，当前要新增的日志信息: "+optLog.toString(),e);
					errorMsg = "执行新增日志信息操作发生异常";
				}
			}
			
		}else{
			logger.error("方法 addLog，参数 optLog 对象为空。");
			errorMsg = "执行新增用户关注失败，WxOptLog对象为空!";
		}
		
		if(errorMsg.length() > 0){
			if(errorMsg.endsWith("，")){
				errorMsg = errorMsg.substring(0,errorMsg.lastIndexOf("，"));
			}
			result = "{\"status\":\"0\",\"errorMsg\":\""+errorMsg+"\"}";
		}else{
			result = "{\"status\":\"1\"}";
		}
		
		return result;
	}

	@Override
	public WxOptLog getOptLogById(String logId) {
		return logDao.getById(logId);
	}

	@Override
	public List<WxOptLog> getOptLogList(WxOptLog optLog) {
		return logDao.findByExample(optLog);
	}

}
