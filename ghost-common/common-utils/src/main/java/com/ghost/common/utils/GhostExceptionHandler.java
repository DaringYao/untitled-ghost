package com.ghost.common.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GhostExceptionHandler {

    @ExceptionHandler({ArithmeticException.class})
    private GhostResult handlerNullPointException() {
        return GhostResult.fail(500, " / by zero");
    }
//    @ExceptionHandler(NotLoginException.class)
//    public String handleNotLoginException() {
//        return "请先登录";
//    }
}
