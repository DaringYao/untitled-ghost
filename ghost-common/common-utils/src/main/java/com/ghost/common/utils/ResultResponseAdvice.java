package com.ghost.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;


//只对com.demo.controller包下的接口生效
@RestControllerAdvice
@SuppressWarnings("ALL")
public class ResultResponseAdvice implements ResponseBodyAdvice {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supports(@Nullable MethodParameter methodParameter, @Nullable Class aClass) {
        return true;
    }


    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object returnValue, MethodParameter returnType, @Nullable MediaType selectedContentType, @Nullable Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        GhostResult result;
        //获取返回值类型
        String returnClassType = returnType.getParameterType().getSimpleName();

        //如果返回值类型为void，则默认返回成功
        switch (returnClassType) {
            case "void" -> result = GhostResult.success();
            case "Result" -> result = (GhostResult) returnValue;
            case "String" -> {
                //json的转换会出问题
                result = GhostResult.success(returnValue);
                return objectMapper.writeValueAsString(result);
            }
            default -> {
                if (Objects.isNull(returnValue)) {
                    result = GhostResult.success();
                } else {
                    result = GhostResult.success(returnValue);
                }
            }
        }
        //一定要转换为String，否则会报错
        return result;
    }
}
