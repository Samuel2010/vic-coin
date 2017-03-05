package com.vc.core.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vc.core.model.WxUserRight;

@Service
public interface WxUserRightService {
	public String addUserRight(WxUserRight userRight);
	
	public String updateUserRight(WxUserRight userRight);
	
	public WxUserRight getUserRightById(String rightId);
	
	public List<WxUserRight> getUserRightList(WxUserRight userRight);
}
