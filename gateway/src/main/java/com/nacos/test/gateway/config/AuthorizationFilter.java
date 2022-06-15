package com.nacos.test.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 鉴权过滤器
 * @author visy.wang
 * @date 2022/6/15 10:26
 */
@Slf4j
//@Component
public class AuthorizationFilter implements GlobalFilter, Ordered {
    //@Value("${auth.login-path}")
    private String loginPath;
    //@Value("${auth.token-name}")
    private String tokenName;

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求&响应信息
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //请求路径
        String uriPath = request.getURI().getPath();

        //放行登录请求
        if(uriPath.contains(loginPath)){
            return chain.filter(exchange);
        }

        //请求头
        HttpHeaders herders = request.getHeaders();

        //获取token值
        String tokenValue = herders.getFirst(tokenName);

        //token为空则返回无权限
        if(StringUtils.isBlank(tokenValue)){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }







        return null;
    }

    public int getOrder() {
        return 0;
    }
}
