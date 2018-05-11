package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginform() {
		return "user/loginForm";
	}
	
	@RequestMapping(value="/loginprocess", method=RequestMethod.POST)
	public String login(@ModelAttribute UserVo userVo,HttpSession session,Model model) {
		UserVo authUser = userService.login(userVo);
		if(authUser!=null) {
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		}else {
			model.addAttribute("result", "fail");
			return "user/loginForm";
		}	
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String joinform() {
		return "user/joinForm";
	}
	
	@RequestMapping(value="/joinprocess", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		int result = userService.join(userVo);
		if(result==3) {
			return "user/joinSuccess";
		}else {
			return "redirect:/";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/idcheck", method=RequestMethod.POST)
	public boolean idcheck(@RequestParam("id") String id) {
		boolean isExists = userService.idcheck(id);
		return isExists;
	}
	
}
