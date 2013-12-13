/*
 * Copyright 2007 AOL, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cnnic.whois.controller.oauth;

import java.io.IOException;
import java.net.IDN;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.oauth.User;
import com.cnnic.whois.controller.BaseController;
import com.cnnic.whois.dao.oauth.UserDao;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;
import com.cnnic.whois.service.QueryService;
import com.cnnic.whois.util.OAuthProvider;
import com.cnnic.whois.util.WhoisUtil;
import com.cnnic.whois.util.validate.ValidateUtils;
import com.cnnic.whois.view.FormatType;
import com.cnnic.whois.view.ViewResolver;

import net.oauth.OAuthAccessor;
import net.oauth.OAuthMessage;
import net.oauth.server.OAuthServlet;

/**
 * A text servlet to echo incoming "echo" param along with userId
 * 
 * @author Praveen Alavilli
 * @author John Kristian
 */
@Controller
public class EchoController extends BaseController {

	@Autowired
	private QueryService queryService;
	@Autowired
	private ViewResolver viewResolver;

	@RequestMapping(value = "/echo")
	@ResponseBody
	public void queryDomain(HttpServletRequest request,
			HttpServletResponse response) throws QueryException,
			RedirectExecption, IOException, ServletException {
		try {
			OAuthMessage requestMessage = OAuthServlet
					.getMessage(request, null);
			OAuthAccessor accessor = OAuthProvider.getAccessor(requestMessage);
			OAuthProvider.VALIDATOR.validateMessage(requestMessage, accessor);

			String domainName = StringUtils.trim("z.cn");
			String queryParaPuny = IDN.toASCII(domainName);
			Map<String, Object> resultMap = null;
			QueryParam queryParam = super.praseQueryParams(request);
			if (!ValidateUtils.validateDomainName(queryParaPuny)) {
				resultMap = WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE);
			} else {
				queryParam.setQ(domainName);
				resultMap = queryService.queryDomain(queryParam);
				System.err.println(resultMap);
			}
			
			viewResolver.writeResponse(queryParam.getFormat(), request,
					response, resultMap, 0);

			// String userId = (String) accessor.getProperty("user");
			// response.setContentType("text/plain");
			// PrintWriter out = response.getWriter();
			// for (Object item : request.getParameterMap().entrySet()) {
			// Map.Entry parameter = (Map.Entry) item;
			// String[] values = (String[]) parameter.getValue();
			// for (String value : values) {
			// out.println(parameter.getKey() + ": " + value);
			// }
			// }

		} catch (Exception e) {
			OAuthProvider.handleException(e, request, response, false);
		}

	}

}
