package com.my.filter;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.Logger;

@WebFilter(filterName = "CharsetFilter")
public class CharsetFilter implements Filter {
	private static final Logger log = Logger.getLogger(CharsetFilter.class);

	private String encoding = "UTF-8";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		String chSetParam = config.getInitParameter("encoding");
		if (chSetParam != null) {
			encoding = chSetParam;
		}
	}
}
