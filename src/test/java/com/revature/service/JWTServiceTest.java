package com.revature.service;

import com.revature.model.User;
import io.javalin.http.UnauthorizedResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

public class JWTServiceTest {

    private JWTService jwtService;

    @BeforeEach
    public void setup() {
        jwtService = new JWTService();
    }

    @Test
    public void test_createJWT(){
        User fakeUser = new User (10,"username","password","firstName",
                "lastName", "email@email.com", "employee");
        String jwt = jwtService.createJWT(fakeUser);
        Assertions.assertNotNull(jwt);
    }

    //    negative
    @Test
    public void test_parseJwt(){

        String jwt = "ejigjeihjaiehwjawihosjahoiawejhoawishdjhoiawjehoiawejhiosadhjaosdhe";
        Assertions.assertThrows(UnauthorizedResponse.class, ()->{
            jwtService.parseJwt(jwt);
        });
    }

}
