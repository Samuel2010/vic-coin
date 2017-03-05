package com.vc.core.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vc.core.model.WxUser;
import com.vc.core.service.WxUserService;
import com.vc.core.util.WeiXinUtil;

@Component("scoreCalcTask")
public class ScoreCalcTask {

	/*@Autowired
	private WxUserService wxUserService;


	@Autowired
	private WxScoreRuleService wxScoreRuleService;

	@Autowired
	private WxScoreLogService wxScoreLogService;

	*//**
	 * 月度积分结算，根据配置规则，版主增加对应积分
	 *//*
	// @Scheduled(fixedDelay = 30000)// 测试，30秒调度一次
	@Scheduled(cron = "0 0 4 1 * ?")//每月1号凌晨4点执行
	public void calcUserScore() {
		List<WxUser> userMangerList = wxUserService.getUserManager(new WxUser());
		
		WxScoreRule wxScoreRule = new WxScoreRule();
		wxScoreRule.setRuleCode(WeiXinUtil.SC_CODE_MODERATOR);
	    List<WxScoreRule> wxScoreRuleList = wxScoreRuleService.getWxScoreRuleList(wxScoreRule);
	    
	    WxScoreRule rule = new WxScoreRule();
	    if(wxScoreRuleList != null && wxScoreRuleList.size()>0){
	    	rule = wxScoreRuleList.get(0);
	    }
	    
		if (rule.getRuleCode() != null && userMangerList != null) {
			WxScoreLog wxScoreLog = new WxScoreLog();
			wxScoreLog.setLogScore(rule.getRuleScore());
			wxScoreLogService.updateUserScore(wxScoreLog);
			//版块管理员和系统管理员数据量不大，采用循环更新数据
			for (WxUser wxUser : userMangerList) {
				WxScoreLog log = new WxScoreLog();
				log.setLogUserId(wxUser.getUserId());
				log.setLogRuleId(rule.getRuleCode());
				log.setLogOpt(rule.getRuleScoreType());
				log.setLogScore(rule.getRuleScore());
				log.setLogRemark("月度结算，版主增加积分");
				wxScoreLogService.addLog(log);
			}
		}
		
	}
	
	*//**
	 * 月度积分结算，根据排行，给予对应积分
	 *//*
	//@Scheduled(fixedDelay = 30000)// 测试，30秒调度一次
	@Scheduled(cron = "0 0 4 1 * ?")//每月1号凌晨4点执行
	public void calcUserScoreByTop() {
		//查询规则
		WxScoreRule wxScoreRule = new WxScoreRule();
		wxScoreRule.setRuleCode(WeiXinUtil.SC_CODE_GIVE_10);
	    List<WxScoreRule> wxScoreRuleList = wxScoreRuleService.getWxScoreRuleList(wxScoreRule);
	    
	    WxScoreRule rule = new WxScoreRule();
	    if(wxScoreRuleList != null && wxScoreRuleList.size()>0){
	    	rule = wxScoreRuleList.get(0);
	    }
	    String beginDate = getFormatDate(1)+" 00:00:00";
	    String endDate = getFormatDate(2)+" 23:59:59";
	    //查询前10数据，取userId，插入log表使用
	    WxScoreLog qryScoreLog = new WxScoreLog();
		qryScoreLog.setRowNumBegin(1);
		qryScoreLog.setRowNumEnd(10);
		qryScoreLog.setLogCreateTimeBegin(beginDate);
		qryScoreLog.setLogCreateTimeEnd(endDate);
		List<WxScoreLog> scoreTopList = wxScoreLogService.queryScoreTop(qryScoreLog);
	    
		if (rule.getRuleCode() != null && scoreTopList != null) {
			WxScoreLog wxScoreLog = new WxScoreLog();
			wxScoreLog.setLogScore(rule.getRuleScore());
			wxScoreLog.setRowNumBegin(1);
			wxScoreLog.setRowNumEnd(10);
			wxScoreLog.setLogCreateTimeBegin(beginDate);
			wxScoreLog.setLogCreateTimeEnd(endDate);
			//批量更新用户积分
			wxScoreLogService.updateUserScoreByTop(wxScoreLog);
			//循环插入日志数据
			for (WxScoreLog scoreLogTop : scoreTopList) {
				WxScoreLog log = new WxScoreLog();
				log.setLogUserId(scoreLogTop.getLogUserId());
				log.setLogRuleId(rule.getRuleCode());
				log.setLogOpt(rule.getRuleScoreType());
				log.setLogScore(rule.getRuleScore());
				log.setLogRemark("月度结算，根据用户排行增加积分");
				wxScoreLogService.addLog(log);
			}
			
		}
		
		wxScoreRule = new WxScoreRule();
		wxScoreRule.setRuleCode(WeiXinUtil.SC_CODE_GIVE_50);
	    wxScoreRuleList = wxScoreRuleService.getWxScoreRuleList(wxScoreRule);
	    rule = new WxScoreRule();
	    if(wxScoreRuleList != null && wxScoreRuleList.size()>0){
	    	rule = wxScoreRuleList.get(0);
	    }
	    
		qryScoreLog.setRowNumBegin(11);
		qryScoreLog.setRowNumEnd(50);
		scoreTopList = wxScoreLogService.queryScoreTop(qryScoreLog);
		
		if (rule.getRuleCode() != null && scoreTopList != null) {
			WxScoreLog wxScoreLog = new WxScoreLog();
			wxScoreLog.setLogScore(rule.getRuleScore());
			wxScoreLog.setRowNumBegin(11);
			wxScoreLog.setRowNumEnd(50);
			wxScoreLog.setLogCreateTimeBegin(beginDate);
			wxScoreLog.setLogCreateTimeEnd(endDate);
			wxScoreLogService.updateUserScoreByTop(wxScoreLog);
			for (WxScoreLog scoreLogTop : scoreTopList) {
				WxScoreLog log = new WxScoreLog();
				log.setLogUserId(scoreLogTop.getLogUserId());
				log.setLogRuleId(rule.getRuleCode());
				log.setLogOpt(rule.getRuleScoreType());
				log.setLogScore(rule.getRuleScore());
				log.setLogRemark("月度结算，根据用户排行增加积分");
				wxScoreLogService.addLog(log);
			}
			
		}
	}
	
	
	private static String getFormatDate(int type){
		String formatDate = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(type == 1){
			//获取上月的第一天
	        Calendar   cal_1=Calendar.getInstance();//获取当前日期 
	        cal_1.add(Calendar.MONTH, -1);
	        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
	        formatDate = format.format(cal_1.getTime());
		}else if(type == 2){
			//获取上月的最后一天
	        Calendar cale = Calendar.getInstance();   
	        cale.set(Calendar.DAY_OF_MONTH,0);
	        formatDate = format.format(cale.getTime());
		}else if(type == 3){
			//获取当前月第一天：
	        Calendar c = Calendar.getInstance();    
	        c.add(Calendar.MONTH, 0);
	        c.set(Calendar.DAY_OF_MONTH,1);
	        formatDate = format.format(c.getTime());
		}else if(type == 4){
	        //获取当前月最后一天
	        Calendar ca = Calendar.getInstance();    
	        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
	        formatDate = format.format(ca.getTime());
		}
		return formatDate;
	}
	
	public static void main(String[] args) {
		System.out.println(getFormatDate(3));
		System.out.println(getFormatDate(4));
	}*/
	
}
