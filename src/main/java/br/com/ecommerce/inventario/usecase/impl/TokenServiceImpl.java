package br.com.ecommerce.inventario.usecase.impl;

import br.com.ecommerce.inventario.usecase.TokenService;
import br.com.ecommerce.inventario.usecase.exception.ForbiddenException;
import br.com.ecommerce.inventario.usecase.exception.InvalidTokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.ecommerce.inventario.utils.Constants.ADMIN;
import static br.com.ecommerce.inventario.utils.Constants.FORBIDDEN_MESSAGE;
import static br.com.ecommerce.inventario.utils.Constants.ROLE;
import static br.com.ecommerce.inventario.utils.Constants.USER;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    @Override
    public void validateToken(String token, boolean isGet) {
        if(token == null || token.isBlank()){
            throw new InvalidTokenException("Token não informado");
        }
        if(!token.contains("Bearer")){
            throw new InvalidTokenException("Token inválido");
        }
        verifyRole(token, isGet);
    }

    private static void verifyRole(String token, boolean isGet) {
        String actualToken = token.substring(7);
        DecodedJWT decode = JWT.decode(actualToken);

        if(!isGet && !decode.getClaim(ROLE).asString().equals(ADMIN)){
            throw new ForbiddenException(FORBIDDEN_MESSAGE);
        }

        if(isGet && !decode.getClaim(ROLE).asString().equals(ADMIN) && !decode.getClaim(ROLE).asString().equals(USER)){
            throw new ForbiddenException(FORBIDDEN_MESSAGE);
        }
    }
}
