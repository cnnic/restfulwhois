package com.cnnic.whois.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnnic.whois.service.admin.ExColumnService;
import com.cnnic.whois.util.WhoisUtil;

@Controller
public class ExColumnController {

	@Autowired
	private ExColumnService exColumnService;
	
	@RequestMapping(value = "/admin/columnController/list")
	public String list(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {

		String tableType = request.getParameter("tableType");
		Map<String, String> columnInfoList = exColumnService.listCoulumn(tableType);

		request.setAttribute("columnInfoList", columnInfoList);
		request.setAttribute("tableType", tableType);
		return "/admin/exColumnList";
	}

	@RequestMapping(value = "/admin/columnController/add")
	public String add(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		String tableType = request.getParameter("tableType");

		String column = request.getParameter("columnArray");
		String[] columnArray = column.split(",");
		Map<String, String> columnMap = new HashMap<String, String>();
		for (String columnInfo : columnArray) {
			String[] value = columnInfo.split("'~'");
			columnMap.put(WhoisUtil.EXTENDPRX + value[0], value[1]);
		}

		exColumnService.addCoulumn(tableType, columnMap);

		return "redirect:/admin/columnController/list?tableType="+tableType;
	}
	
	@RequestMapping(value = "/admin/columnController/update")
	public String update(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		
		String tableType = request.getParameter("tableType");
		
		String oldColumnName = request.getParameter("oldColumnName");
		String newCloumnName = request.getParameter("newCoulumnName");
		String columnLength = request.getParameter("coulumnLength");

		exColumnService.updateCoulumn(tableType, oldColumnName, newCloumnName, columnLength);

		return "redirect:/admin/columnController/list?tableType="+tableType;
	}
	
	@RequestMapping(value = "/admin/columnController/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		
		String tableType = request.getParameter("tableType");
		exColumnService.deleteCoulumn(tableType, request.getParameter("coName"));

		return "redirect:/admin/columnController/list?tableType="+tableType;
	}

}
