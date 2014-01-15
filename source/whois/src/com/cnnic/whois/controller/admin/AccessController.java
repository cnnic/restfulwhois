package com.cnnic.whois.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnnic.whois.service.admin.AccessControlService;

@Controller
public class AccessController {

	@Autowired
	private AccessControlService accessControlService;
	
	@RequestMapping(value = "/admin/accessController/list")
	public String list(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		String tableType = request.getParameter("tableType");
		Map<String, Object> columnPermissionList = accessControlService.listPermissionCoulumn(tableType);
		
		request.setAttribute("columnPermissionList", columnPermissionList);
		request.setAttribute("tableType", tableType);
		
		return "/admin/permissions";
	}
	
	@RequestMapping(value = "/admin/accessController/update")
	public String update(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {

		String tableType = request.getParameter("tableType");
		
		String anonymous = request.getParameter("anonymous");
		String authenticated = request.getParameter("authenticated");
		String root = request.getParameter("root");
			
		String[] anonymousArray = anonymous.split(",");
		String[] authenticatedArray = authenticated.split(","); //get the parameters from the request scope
		String[] rootArray = root.split(",");
			
		accessControlService.updatePermission(tableType, anonymousArray,
					authenticatedArray, rootArray);

		request.setAttribute("successFlag", "UpdateSucess");

		return "redirect:/admin/accessController/list?tableType="+tableType;
	}

}
