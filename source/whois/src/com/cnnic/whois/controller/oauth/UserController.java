package com.cnnic.whois.controller.oauth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnnic.whois.bean.oauth.User;
import com.cnnic.whois.dao.oauth.UserDao;

@Controller
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/user")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		List<User> userList = userDao.getUsers();
		model.addAttribute("list", userList);
		return "/oauth/user";
	}
	
	@RequestMapping(value = "/user/add-update")
	public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model ) throws Exception{
		String id = request.getParameter("id");
		User user = null;
		if(id != null && !"".equals(id)){
			user = userDao.getUserById(Integer.valueOf(request.getParameter("id")));
		}
		model.addAttribute("user", user);
		return "/oauth/user_update";
	}
	
	@RequestMapping(value = "/user/save")
	public String save(HttpServletRequest request, HttpServletResponse response, ModelMap model ) throws Exception{
		String id = request.getParameter("id");
		String user_name = request.getParameter("user_name");
		String pwd = request.getParameter("pwd");
		User user = new User();
		user.setUser_name(user_name);
		user.setPwd(pwd);
		if(id != null && !"".equals(id)){
			userDao.update(Integer.valueOf(id), user);
		}else {
			userDao.save(new User(user_name, pwd));
		}
		return "redirect:/user";
	}
	
	@RequestMapping(value = "/user/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response ) throws Exception{
		userDao.delete(Integer.valueOf(request.getParameter("id")));
		return "redirect:/user";
	}

}
