package com.revature.controller;

import com.revature.dto.LoginDTO;
import com.revature.dto.UserResponseDTO;
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
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");
        String firstName = ctx.formParam("firstName");
        String lastName = ctx.formParam("lastName");
        String email = ctx.formParam("email");
        String role = ctx.formParam("user_role");

        User newUser = new User(-1, username, password, firstName,lastName,email, role);
        User user = userService.signUp(newUser);
        String jwt = this.jwtService.createJWT(user);

        ctx.header("Access-Control-Expose-Headers", "*");
        ctx.header("Token", jwt);
        UserResponseDTO userResponseDTO = new UserResponseDTO(user.getUser_id(), user.getUsername(),
                user.getFirstName(), user.getLastName(), user.getEmail(), user.getUser_role());
        ctx.json(userResponseDTO);
    };

    @Override
    public void mapEndpoints(Javalin app) {
        app.post("/login", login);
        app.post("/signUp",signUp);
    }
}
