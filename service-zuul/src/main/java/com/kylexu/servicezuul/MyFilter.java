package com.kylexu.servicezuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * filterType:
 * <p>
 * pre：路由之前
 * routing：路由之时
 * post： 路由之后
 * error：发送错误调用
 * <p>
 * filterOrder：过滤的顺序
 * shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
 * run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
 */
@Component
public class MyFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(MyFilter.class);


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();

        HttpServletRequest request = context.getRequest();
        logger.info("{} >>> {}", request.getMethod(), request.getRequestURL().toString());

        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            logger.warn("token is empty");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);

            try {
                context.getResponse().getWriter().write("token is empty");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        logger.info("OK!!");

        return null;
    }
}
