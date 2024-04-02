package br.com.ecommerce.inventario.adapters.interceptor;

import br.com.ecommerce.inventario.usecase.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

import static br.com.ecommerce.inventario.utils.Constants.AUTHORIZATION;

@Service
@RequiredArgsConstructor
public class RestInterceptor implements HandlerInterceptor {

    private final TokenService tokenService;
    private boolean isGet = false;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        if(Objects.nonNull(request.getMethod()) && request.getMethod().equalsIgnoreCase("GET")){
            isGet = true;
        }
        String token = request.getHeader(AUTHORIZATION);
        tokenService.validateToken(token, isGet);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
