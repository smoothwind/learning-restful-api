package com.lx3.learning;

import com.lx3.learning.service.TokenService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationTokenFilter extends OncePerRequestFilter {
    private final static Log log = LogFactory.getLog(AuthenticationTokenFilter.class);
    @Autowired
    TokenService tokenService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authToken = null;

        //下面的代码Http Header的Authorization中获取token,也可以从其他header,cookies中获取，看客户端怎么传递
        String requeatHeader = request.getHeader("Authorization");
        if(requeatHeader != null && requeatHeader.startsWith("Bearer ")){
            authToken = requeatHeader.substring(7);
        }
        if (log.isTraceEnabled()){
            log.trace("token is :" + authToken);
        }
        if (authToken != null){
            UserDetails user = null;
            //查询token对应的用户
            user = tokenService.getUserFromToken(authToken);
            if(user !=null){
                //把user设置到SecurityContextHolder内，提供给Spring使用
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request,response);
    }
}
