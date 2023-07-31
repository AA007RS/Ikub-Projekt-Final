package com.rscinema.finalproject.configuration;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class SecurityUtils {

    public static Integer getLoggedUserId(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        //(JWTs) claims are pieces of information asserted about a subject
        if(authentication!=null && authentication.getPrincipal() instanceof Jwt){
            // handle jwt profile
            return getLoggedUser();
        }else {
            //handle null authentication
            return null;
        }
    }

    public static Integer getLoggedUser(){
        var authentication = (Jwt)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return authentication.getClaim("sub");
    }
}
