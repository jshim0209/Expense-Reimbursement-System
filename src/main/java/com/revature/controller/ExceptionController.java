package com.revature.controller;

import com.revature.exception.ImageNotFoundException;
import com.revature.exception.InvalidImageException;
import com.revature.exception.UserDoesNotExist;
import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;

import javax.security.auth.login.FailedLoginException;

public class ExceptionController implements Controller {

    private ExceptionHandler<FailedLoginException> failedLogin = (exception, ctx) -> {
        ctx.status(400);
        ctx.json(exception.getMessage());
    };

    private ExceptionHandler<IllegalArgumentException> invalidArgument = (exception, ctx) -> {
        ctx.status(400);
        ctx.json(exception.getMessage());
    };

    private ExceptionHandler<ImageNotFoundException> noImage = (exception, ctx) -> {
        ctx.status(400);
        ctx.json(exception.getMessage());
    };

    private ExceptionHandler<InvalidImageException> invalidImage = (exception, ctx) -> {
        ctx.status(400);
        ctx.json(exception.getMessage());
    };

    private ExceptionHandler<UserDoesNotExist> noMatchingUser = (exception, ctx) -> {
        ctx.status(400);
        ctx.json(exception.getMessage());
    };



    @Override
    public void mapEndpoints(Javalin app) {
        app.exception(FailedLoginException.class, failedLogin);
        app.exception(IllegalArgumentException.class, invalidArgument);
        app.exception(ImageNotFoundException.class, noImage);
        app.exception(InvalidImageException.class, invalidImage);
        app.exception(UserDoesNotExist.class, noMatchingUser);



    }
}