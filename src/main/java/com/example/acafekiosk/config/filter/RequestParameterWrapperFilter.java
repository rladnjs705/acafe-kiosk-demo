package com.example.acafekiosk.config.filter;

import com.example.acafekiosk.config.wrapper.RequestParameterWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(2)
public class RequestParameterWrapperFilter extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(RequestParameterWrapperFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		logger.debug("RequestParameterWrapperFilter => Do FilterInternal " + request.getRequestURI());
		RequestParameterWrapper wrapper = new RequestParameterWrapper(request);
		filterChain.doFilter(wrapper, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		if ("/robots.txt".equals(request.getRequestURI()) || "/favicon.ico".equals(request.getRequestURI())
				|| request.getRequestURI().startsWith("/static/")) {
			return true;
		}
		return super.shouldNotFilter(request);
	}

	@Override
	public void destroy() {
		MDC.clear();
		super.destroy();
	}
}