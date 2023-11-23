package com.example.foodplanner.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ExceptionController implements ErrorController {


    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status =
                request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error500";
            }else if (statusCode == HttpStatus.BAD_REQUEST.value()){
                return "error400";
            }else if (statusCode == HttpStatus.UNAUTHORIZED.value()){
                return "error401";
            }else if (statusCode == HttpStatus.FORBIDDEN.value()){
                return "error403";
            }
        }
        return "error";
    }



    public String getErrorPath() {
        return "/error";
    }
}
