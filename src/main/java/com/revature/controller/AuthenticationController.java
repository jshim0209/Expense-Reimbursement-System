package com.revature.controller;

import com.revature.dto.LoginDTO;
import com.revature.model.User;
import com.revature.service.JWTService;
import com.revature.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class AuthenticationController implements Controller {

    private UserService userService;
    private JWTService jwtService;

    public AuthenticationController() {
        this.userService = new UserService();
        this.jwtService = JWTService.getInstance();
    }

    private Handler login = (ctx) -> {
        System.out.println("Login endpoint invoked");

        LoginDTO loginInfo = ctx.bodyAsClass(LoginDTO.class);

        User user = userService.login(loginInfo);

        String jwt = this.jwtService.createJWT(user);

        ctx.header("Access-Control-Expose-Headers", "*");

        ctx.header("Token", jwt);
        ctx.json(user);
    };

    private Handler signUp = (ctx) -> {

        User user = ctx.bodyAsClass(User.class);

        User newUser = userService.signUp(user);
        String jwt = this.jwtService.createJWT(newUser);

        ctx.header("Access-Control-Expose-Headers", "*");
        ctx.header("Token", jwt);

        ctx.json(newUser);
    };

    @Override
    public void mapEndpoints(Javalin app) {
        app.post("/login", login);
        app.post("/signUp",signUp);
    }
}
