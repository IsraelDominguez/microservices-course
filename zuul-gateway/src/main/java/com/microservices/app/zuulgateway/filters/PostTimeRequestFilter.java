package com.microservices.app.zuulgateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PostTimeRequestFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(PostTimeRequestFilter.class);

    // Type: pre, post, route
    @Override
    public String filterType() {
        return "post";
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
        log.info("Enter post filter");

        Long initTime = (Long) request.getAttribute("initTime");
        Long endTime = System.currentTimeMillis();
        Long costTime = endTime - initTime;

        log.info(String.format("Request Time %s seg.", costTime.doubleValue()/1000.00));
        log.info(String.format("Request Time %s mlseg.", costTime.doubleValue()));
        return null;
    }
}
