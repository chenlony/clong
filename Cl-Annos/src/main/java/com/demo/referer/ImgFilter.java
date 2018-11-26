package com.demo.referer;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;

/**
 * 
 * @author CL
 *如何实现防盗链
 *判断http请求头Referer域中的记录来源的值，如果和当前访问的域名不一致的情况下，说明该图片可能被其他服务器盗用。
 */
@WebFilter(filterName = "imgFilter", urlPatterns = "/imgs/*")
public class ImgFilter implements Filter{
	@Value("${domain.name}")
	private String domainName;
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String referer = req.getHeader("Referer");
		if (StringUtils.isEmpty(referer)) {
			request.getRequestDispatcher("/imgs/error.png").forward(request, response);
			return;
		}
		String domain = getDomain(referer);
		if (!domain.equals(domainName)) {
			request.getRequestDispatcher("/imgs/error.png").forward(request, response);
			return;
		}
		chain.doFilter(request, response);
		
	}
	/**
	 * 获取url对应的域名
	 *
	 * @param url
	 * @return
	 */
	public String getDomain(String url) {
		String result = "";
		int j = 0, startIndex = 0, endIndex = 0;
		for (int i = 0; i < url.length(); i++) {
			if (url.charAt(i) == '/') {
				j++;
				if (j == 2)
					startIndex = i;
				else if (j == 3)
					endIndex = i;
			}

		}
		result = url.substring(startIndex + 1, endIndex);
		return result;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
