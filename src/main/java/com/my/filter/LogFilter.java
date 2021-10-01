package com.my.filter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LogFilter implements Filter {
	private String logFile;

	public LogFilter() {
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.logFile = fConfig.getInitParameter("logFile");

		System.out.println("Log File " + logFile);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		String servletPath = req.getServletPath();
		String requestUrl = req.getServletPath();

		System.out.println("#INFO " + new Date() + " - ServletPath :" + servletPath + ", URL =" + requestUrl);

		if (this.logFile != null) {
			this.logToFile(this.logFile, servletPath, requestUrl);
		}
		chain.doFilter(request, response);
	}

	public void logToFile(String fileName, String servletPath, String requestUrl) {
		System.out.println("Write log to file " + fileName);

		File f = new File(fileName);

		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			FileWriter fw = new FileWriter(f, true);
			//fw.append("\n####### " + new Date());
			fw.append("\n#INFO " + new Date()).append(" - ServletPath :" + servletPath).append(", URL =" + requestUrl);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
