//package br.com.ecommerce.inventario.usecase.service;
//
//import br.com.ecommerce.inventario.usecase.impl.TokenServiceImpl;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.exceptions.JWTDecodeException;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockedStatic;
//import org.mockito.Mockito;
//import org.mockito.stubbing.OngoingStubbing;
//
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//class TokenServiceTest {
//    @InjectMocks
//    TokenServiceImpl tokenService;
//
//    @Mock
//    JWT jwt;
//
//    @Mock
//    DecodedJWT decodedJWT;
//
//    @Test
//    void testValidateTokenWhenTokenValid() {
//        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiQURNSU4iLCJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.yCdPVMPz5eIv_oZ4Js08DhLE4BoOuuXLOnKm6YIsN6A";
//
//        try (MockedStatic<JWT> mockedJWT = Mockito.mockStatic(JWT.class)) {
//            mockedJWT.when(() -> JWT.decode(token)).thenReturn(decodedJWT);
//            when(decodedJWT.getClaim("role").asString()).thenReturn("ADMIN");
//
//            assertDoesNotThrow(() -> tokenService.validateToken(token));
//        }
//    }
//}