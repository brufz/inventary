package br.com.ecommerce.inventario.adapters.interceptor;

import br.com.ecommerce.inventario.usecase.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import static br.com.ecommerce.inventario.utils.Constants.AUTHORIZATION;

@Service
@RequiredArgsConstructor
public class RestInterceptor implements HandlerInterceptor {

    private final TokenService tokenService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        String token = request.getHeader(AUTHORIZATION);
        tokenService.validateToken(token);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
