package br.com.ecommerce.inventario.usecase.impl;

import br.com.ecommerce.inventario.usecase.TokenService;
import br.com.ecommerce.inventario.usecase.exception.InvalidTokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public void validateToken(String token) {

        if(token == null || token.isBlank()){
            throw new InvalidTokenException("Token não informado");
        }
        if(!token.contains("Bearer")){
            throw new InvalidTokenException("Token inválido");
        }
        String actualToken = token.substring(7);
        DecodedJWT decode = JWT.decode(actualToken);

        if(!decode.getClaim("role").asString().equals("ADMIN")){
            throw new InvalidTokenException("Usuário sem permissão para acessar o recurso");
        }
    }
}
