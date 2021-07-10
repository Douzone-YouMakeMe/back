package com.ymm.back.config;

import com.ymm.back.domain.tables.User;
import com.ymm.back.s3.FileUploadService;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Configuration
public class HandShakeInterceptor implements HandshakeInterceptor {
    private final DSLContext dslContext;
    private final JdbcTemplate jdbcTemplate;
    private final FileUploadService fileUploadService;
    @Autowired
    public HandShakeInterceptor(DSLContext dslContext, JdbcTemplate jdbcTemplate, FileUploadService fileUploadService) {
        this.dslContext = dslContext;
        this.jdbcTemplate = jdbcTemplate;
        this.fileUploadService = fileUploadService;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        //String jwtToken = serverHttpRequest.getURI().getQuery().substring(6);
        //String wsToken = serverHttpRequest.getURI().getQuery().substring(6);
        String wsToken = serverHttpRequest.getURI().getQuery();
        if(StringUtils.isEmpty(wsToken)){
            return false;
        }
        User user=  User.USER;
        String name = dslContext.select(user.NAME).from(user).where(user.JWT.eq(wsToken)).fetchInto(String.class).get(0);
        return !StringUtils.isEmpty(name);
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
