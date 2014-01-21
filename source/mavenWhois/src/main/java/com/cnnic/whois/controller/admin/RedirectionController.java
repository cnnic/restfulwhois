package com.cnnic.whois.controller.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnnic.whois.service.admin.RedirectionService;
import com.cnnic.whois.util.WhoisUtil;

@Controller
public class RedirectionController {

	@Autowired
	private RedirectionService redirectionService;
	
	@RequestMapping(value = "/admin/redirectionController/list")
	public String list(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		String tableName = request.getParameter("tableName");
		
		Map<Integer, List<String>> redirectList = redirectionService.listRedirect(tableName);

		request.setAttribute("redirectList", redirectList);
		request.setAttribute("tableName", tableName);
		
		return "/admin/redirectionTable";
	}

	@RequestMapping(value = "/admin/redirectionController/add")
	public String add(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		
		String start = request.getParameter("start");
		String redirectUrl = request.getParameter("redirectURl");
		String tableName = request.getParameter("tableName");
		String end = null;
		if (tableName.equals(WhoisUtil.IP) || tableName.equals(WhoisUtil.AUTNUM)) {
			end = request.getParameter("end");
		}

		redirectionService.addRedirect(start, end, redirectUrl, tableName);
		
		request.setAttribute("tableName", tableName);

		return "redirect:/admin/redirectionController/list?tableName="+tableName;
	}
	
	@RequestMapping(value = "/admin/redirectionController/update")
	public String update(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		
		String redirectId = request.getParameter("redirectId");
		String start = request.getParameter("start");
		String redirectUrl = request.getParameter("redirectURl");
		String tableName = request.getParameter("tableName");
		String end = null;

		if (tableName.equals(WhoisUtil.IP)
				|| tableName.equals(WhoisUtil.AUTNUM))
			end = request.getParameter("end");

		redirectionService.updateRedirect(start, end, redirectUrl, Integer.parseInt(redirectId), tableName);
		request.setAttribute("tableName", tableName);

		return "redirect:/admin/redirectionController/list?tableName="+tableName;
	}
	
	@RequestMapping(value = "/admin/redirectionController/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		String tableName = request.getParameter("tableName");
		
		redirectionService.deleteRedirect(Integer.parseInt(request.getParameter("id")), tableName);
		request.setAttribute("tableName", tableName);

		return "redirect:/admin/redirectionController/list?tableName="+tableName;
	}

}
