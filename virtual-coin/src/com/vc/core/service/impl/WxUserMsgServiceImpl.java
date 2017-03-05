package com.vc.core.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vc.core.dao.WxUserMsgDao;
import com.vc.core.model.WxUserMsg;
import com.vc.core.service.WxUserMsgService;

@Service
public class WxUserMsgServiceImpl implements WxUserMsgService {
	private static Logger logger = Logger.getLogger(WxUserMsgServiceImpl.class);
	
	@Autowired
	private WxUserMsgDao msgDao;

	@Override
	public String addUserMsg(WxUserMsg userMsg) {
		String result = "";
		String errorMsg = "";
		
		if(userMsg != null){
			String msgId = UUID.randomUUID().toString();
			userMsg.setMsgId(msgId);
			
			String msgUserId = userMsg.getMsgUserId();
			String msgContent = userMsg.getMsgContent();
			
			if(msgUserId == null || msgUserId.length() == 0){
				errorMsg += "msgUserId 为空，";
			}
			if(msgContent == null || msgContent.length() == 0){
				errorMsg += "msgContent 为空，";
			}
			
			if(errorMsg.length() == 0){
				try{
					userMsg.setMsgCreateTime(new Date());
					userMsg.setMsgModifyTime(new Date());
					if(userMsg.getMsgStatus() <= 0){
						userMsg.setMsgStatus(1);
					}
					
					boolean flag = msgDao.save(userMsg);
					if(!flag){
						errorMsg = "执行新增用户消息操作失败了。";
						logger.error("方法 addAttention，"+errorMsg+"当前用户消息信息: "+userMsg.toString());
					}
				}catch(Exception e){
					logger.error("方法 addUserMsg，执行新增用户消息时发生异常，当前用户消息信息: "+userMsg.toString(),e);
					errorMsg = "执行新增用户消息操作发生异常";
				}
			}
		}else{
			logger.error("方法 addUserMsg，参数 userMsg 对象为空。");
			errorMsg = "执行新增用户关注失败，WxUserMsg对象为空!";
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
	public boolean deleteUserMsg(String msgId) {
		return msgDao.deleteById(msgId);
	}

	@Override
	public WxUserMsg getUserMsgById(String msgId) {
		return msgDao.getById(msgId);
	}

	@Override
	public List<WxUserMsg> getUserMsgList(WxUserMsg userMsg) {
		return msgDao.findByExample(userMsg);
	}

	@Override
	public int getMyMsgCount(String psUserId) {
		return msgDao.getMyMsgCount(psUserId);
	}

	@Override
	public void updateUserMsg(String psId) {
		msgDao.updateUserMsg(psId);
	}	
}
