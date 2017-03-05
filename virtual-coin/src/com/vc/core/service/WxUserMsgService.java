package com.vc.core.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vc.core.model.WxUserMsg;

@Service
public interface WxUserMsgService {
	public String addUserMsg(WxUserMsg userMsg);
	
	public boolean deleteUserMsg(String msgId);
	
	public WxUserMsg getUserMsgById(String msgId);
	
	public List<WxUserMsg> getUserMsgList(WxUserMsg userMsg);
	
	public int getMyMsgCount(String psUserId);
	
	public void updateUserMsg(String psId);
}
