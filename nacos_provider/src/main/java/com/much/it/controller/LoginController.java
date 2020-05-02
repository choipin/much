package com.much.it.controller;

import com.much.it.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.Map;

/**
 * @description:
 * @project: much
 * @date: 2020/5/2
 * @author: Wenxin
 * @version: 1.0
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/verification/{phone}")
    @Pattern(regexp = "^[1]([3-9])[0-9]{9}$",message = "please put a phone number")
    public Map<String,String> getPublicSerek(@PathVariable("phone") String phone){
        return loginService.verification(phone);
    }
    @PostMapping("/login")
    public String login(@RequestBody Map<String,String> map){
        return loginService.login(map);
    }
}
