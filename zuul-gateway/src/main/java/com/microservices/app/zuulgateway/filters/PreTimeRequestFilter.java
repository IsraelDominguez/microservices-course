package com.microservices.app.zuulgateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PreTimeRequestFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(PreTimeRequestFilter.class);

    // Type: pre, post, route
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    // When execute filter, if always return true
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s request route to %s", request.getMethod(), request.getRequestURL().toString()));

        Long initTime = System.currentTimeMillis();
        request.setAttribute("initTime", initTime);

        return null;
    }
}
