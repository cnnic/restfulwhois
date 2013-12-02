package com.cnnic.whois.controller.oauth;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cnnic.whois.bean.UserApp;
import com.cnnic.whois.dao.oauth.UserAppDao;
import com.cnnic.whois.util.BeanFactory;

public class AppServlet extends HttpServlet {
	
	private UserAppDao userAppDao = (UserAppDao) BeanFactory.getBean("userAppDao");
	
	private static final long serialVersionUID = 1L;
	private static final String WHOIS_USER_LIST_PAGE = "/WEB-INF/oauth/user_app.jsp";
	private static final String WHOIS_USER_UPDATE_PAGE = "/WEB-INF/oauth/user_app_update.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String listValue = String.valueOf(request.getParameter("queryType"));
		String idValue = String.valueOf(request.getParameter("user_id"));
		
		if("list".equals(listValue)){
			 List<UserApp> userAppList = userAppDao.getUserApps(Integer.valueOf(idValue));
			 request.setAttribute("list", userAppList);
			 request.setAttribute("user_id", idValue);
			 getServletContext().getRequestDispatcher(WHOIS_USER_LIST_PAGE).forward(request, response);
			 
		}else if("save".equals(listValue)){
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
			response.sendRedirect(request.getContextPath()+"/AppServlet.do?queryType=list&user_id="+idValue); 
		}else if("delete".equals(listValue)){
			userAppDao.delete(Integer.valueOf(request.getParameter("id")));
			response.sendRedirect(request.getContextPath()+"/AppServlet.do?queryType=list&user_id="+idValue); 
		}else if("update".equals(listValue)){
//			User user = userAppDao.getUserById(Integer.valueOf(request.getParameter("id")));
//			request.setAttribute("user", user);
//			getServletContext().getRequestDispatcher(WHOIS_USER_UPDATE_PAGE).forward(request, response);
		}else if("add".equals(listValue)){
			request.setAttribute("add", "add");
			request.setAttribute("user_id", idValue);
			getServletContext().getRequestDispatcher(WHOIS_USER_UPDATE_PAGE).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
