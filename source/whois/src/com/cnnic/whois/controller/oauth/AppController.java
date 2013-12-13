package com.cnnic.whois.controller.oauth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnnic.whois.bean.oauth.UserApp;
import com.cnnic.whois.dao.oauth.UserAppDao;

@Controller
public class AppController {
	
	@Autowired
	private UserAppDao userAppDao;
	
	@RequestMapping(value = "/app")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		String idValue = String.valueOf(request.getParameter("user_id"));
		List<UserApp> userAppList = userAppDao.getUserApps(Integer.valueOf(idValue));
		model.addAttribute("list", userAppList);
		model.addAttribute("user_id", idValue);
		return "/oauth/user_app";
	}
	
	@RequestMapping(value = "/app/add-update")
	public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model ) throws Exception{
		String id = request.getParameter("id");
		UserApp userApp = null;
		if(id != null && !"".equals(id)){
			userApp = userAppDao.getUserAppById(Integer.valueOf(request.getParameter("id")));
		}
		model.addAttribute("userApp", userApp);
		model.addAttribute("user_id", request.getParameter("user_id"));
		return "/oauth/user_app_update";
	}
	
	@RequestMapping(value = "/app/save")
	public String save(HttpServletRequest request, HttpServletResponse response, ModelMap model ) throws Exception{
		String id = request.getParameter("id");
		String app_description = request.getParameter("app_description");
		String user_id = request.getParameter("user_id");
		UserApp userApp = new UserApp();
		userApp.setUser_id(Integer.valueOf(user_id));
		userApp.setApp_description(app_description);
		if(id != null && !"".equals(id)){
			userAppDao.update(Integer.valueOf(id), userApp);
		}else {
			userAppDao.save(new UserApp(app_description, Integer.valueOf(user_id)));
		}
		return "redirect:/app?user_id="+user_id;
	}
	
	@RequestMapping(value = "/app/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response ) throws Exception{
		userAppDao.delete(Integer.valueOf(request.getParameter("id")));
		String user_id = request.getParameter("user_id");
		return "redirect:/app?user_id="+user_id;
	}
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		String listValue = String.valueOf(request.getParameter("queryType"));
//		String idValue = String.valueOf(request.getParameter("user_id"));
//		
//		if("list".equals(listValue)){
//			 List<UserApp> userAppList = userAppDao.getUserApps(Integer.valueOf(idValue));
//			 request.setAttribute("list", userAppList);
//			 request.setAttribute("user_id", idValue);
//			 getServletContext().getRequestDispatcher(WHOIS_USER_LIST_PAGE).forward(request, response);
//			 
//		}else if("save".equals(listValue)){
//			String id = request.getParameter("id");
//			String app_description = request.getParameter("app_description");
//			String user_id = request.getParameter("user_id");
//			UserApp userApp = new UserApp();
//			userApp.setUser_id(Integer.valueOf(user_id));
//			userApp.setApp_description(app_description);
//			if(id != null && !"".equals(id)){
//				userAppDao.update(Integer.valueOf(id), userApp);
//			}else {
//				userAppDao.save(new UserApp(app_description, Integer.valueOf(user_id)));
//			}
//			response.sendRedirect(request.getContextPath()+"/AppServlet.do?queryType=list&user_id="+idValue); 
//		}else if("delete".equals(listValue)){
//			userAppDao.delete(Integer.valueOf(request.getParameter("id")));
//			response.sendRedirect(request.getContextPath()+"/AppServlet.do?queryType=list&user_id="+idValue); 
//		}else if("update".equals(listValue)){
////			User user = userAppDao.getUserById(Integer.valueOf(request.getParameter("id")));
////			request.setAttribute("user", user);
////			getServletContext().getRequestDispatcher(WHOIS_USER_UPDATE_PAGE).forward(request, response);
//		}else if("add".equals(listValue)){
//			request.setAttribute("add", "add");
//			request.setAttribute("user_id", idValue);
//			getServletContext().getRequestDispatcher(WHOIS_USER_UPDATE_PAGE).forward(request, response);
//		}
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}

}
