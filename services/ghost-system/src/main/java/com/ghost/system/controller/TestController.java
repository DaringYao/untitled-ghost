package com.ghost.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yaolsœ
 * @version 1.0
 * @since 2025/6/2 05:50
 */

@RequestMapping
@RestController
public class TestController {

    @GetMapping("test")
    public String str(){
        return "hello world";
    }
}
