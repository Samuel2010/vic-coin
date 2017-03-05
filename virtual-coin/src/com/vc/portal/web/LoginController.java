package com.vc.portal.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vc.core.model.User;
import com.vc.core.service.UserService;
import com.vc.core.util.WebUtil;
import com.vc.core.web.BaseController;

@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController{
	@Autowired
	private UserService userSrv;

	/**
	 * 登陆主界面
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/", "" })
	public String index() {
		return "login";
	}

	/**
	 * 登陆验证
	 * 
	 * @param userId
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping("doLogin")
	@ResponseBody
	public Object doLogin(@RequestParam String loginId,@RequestParam String loginPwd, HttpServletRequest request) {
		User user = userSrv.login(loginId, loginPwd);
		Map<String,String> map = new HashMap<String,String>();
		if(user != null){
			request.getSession().setAttribute(WebUtil.LOGIN_USER_SESSION, user);
			map.put("flag", "true");
			
		}else{
			map.put("msg", "用户名或密码错误");
			map.put("flag", "false");
		}
		
		return map;
	}

	@RequestMapping("/doLoginOut")
	public String out(HttpServletRequest request) {
		request.getSession().removeAttribute("loginUser");
		return "redirect:/login";
	}

}