package com.grinyov.web.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vgrinyov.
 */
@Controller
public class ExampleController {
    private static final Logger LOGGER = Logger.getLogger(ExampleController.class.getName());

    @ResponseBody
    @RequestMapping(value = "/spring-hello", method = RequestMethod.POST)
    public Callable<String> hello(@RequestParam(value = "username", required = false) final String name) {
        LOGGER.log(Level.INFO, "Action from {0}.", Thread.currentThread().getName());
        return () -> {
            LOGGER.log(Level.INFO, "Action from {0}.", Thread.currentThread().getName());
            if (name == null || name.trim().isEmpty()) {
                return "<p style=\"color: red;\">Input correct name!</p>";
            }
            return String.format("<p style=\"color: green;\">Hello, %s!</p>", name);
        };
    }
}
