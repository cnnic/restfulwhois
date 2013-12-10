package com.cnnic.whois.controller.oauth;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cnnic.whois.bean.User;
import com.cnnic.whois.dao.oauth.UserDao;
import com.cnnic.whois.util.BeanFactory;

/**
 * Servlet implementation class HelpServlet
 */
public class UserManageServlet extends HttpServlet {
	
	private UserDao userDao = (UserDao) BeanFactory.getBean("userDao");
	
	private static final long serialVersionUID = 1L;
	private static final String WHOIS_USER_LIST_PAGE = "/WEB-INF/oauth/user.jsp";
	private static final String WHOIS_USER_UPDATE_PAGE = "/WEB-INF/oauth/user_update.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String listValue = String.valueOf(request.getParameter("queryType"));
		if("list".equals(listValue)){
			 List<User> userList = userDao.getUsers();
			 request.setAttribute("list", userList);
			 getServletContext().getRequestDispatcher(WHOIS_USER_LIST_PAGE).forward(request, response);
		}else if("save".equals(listValue)){
			String id = request.getParameter("id");
			String user_name = request.getParameter("user_name");
			String pwd = request.getParameter("pwd");
			User user = new User();
			user.setUser_name(user_name);
			if(id != null && !"".equals(id)){
				userDao.update(Integer.valueOf(id), user);
			}else {
				userDao.save(new User(user_name, pwd));
			}
			response.sendRedirect(request.getContextPath()+"/UserManageServlet?queryType=list"); 
		}else if("delete".equals(listValue)){
			userDao.delete(Integer.valueOf(request.getParameter("id")));
			response.sendRedirect(request.getContextPath()+"/UserManageServlet?queryType=list"); 
		}else if("update".equals(listValue)){
			User user = userDao.getUserById(Integer.valueOf(request.getParameter("id")));
			request.setAttribute("user", user);
			getServletContext().getRequestDispatcher(WHOIS_USER_UPDATE_PAGE).forward(request, response);
		}else if("add".equals(listValue)){
			request.setAttribute("add", "add");
			getServletContext().getRequestDispatcher(WHOIS_USER_UPDATE_PAGE).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
