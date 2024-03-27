package br.com.ecommerce.inventario.adapters.interceptor;

import br.com.ecommerce.inventario.usecase.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class RestInterceptor implements HandlerInterceptor {

    @Autowired
    TokenService tokenService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        tokenService.validateToken(token);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
