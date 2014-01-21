package com.cnnic.whois.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.RedirectView;

import com.cnnic.whois.execption.RedirectExecption;
@Deprecated
public class MappingExceptionResolver extends SimpleMappingExceptionResolver {

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {	
		if (ex instanceof RedirectExecption) {
			RedirectExecption rEx = (RedirectExecption)ex;
			response.setHeader("Accept", BaseController.getFormatCookie(request));
			response.setHeader("Location", rEx.getRedirectURL());
			response.setHeader("Connection", "close");
			RedirectView redirectView = new RedirectView(rEx.getRedirectURL());
			redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
			return new ModelAndView(redirectView);
		}
		return null;
	}
}
