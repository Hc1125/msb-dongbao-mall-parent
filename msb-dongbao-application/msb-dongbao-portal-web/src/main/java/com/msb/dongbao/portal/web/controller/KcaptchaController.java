package com.msb.dongbao.portal.web.controller;

import com.baomidou.kaptcha.Kaptcha;
import com.baomidou.kaptcha.exception.KaptchaException;
import com.msb.dongbao.common.base.annotations.TokenCheck;
import com.msb.dongbao.portal.web.custom.MyGoogleKaptcha;
import com.ramostear.captcha.HappyCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/kcaptcha")
public class KcaptchaController {
    @Autowired
    private Kaptcha kaptcha;

    @Autowired
    private MyGoogleKaptcha myGoogleKaptcha;


    @GetMapping("/generator")
    public void generatorCode(HttpServletRequest request, HttpServletResponse response) {
        kaptcha.render();
    }

    @GetMapping("/verify")
    public String verify(String verifyCode, HttpServletRequest request) {


        Boolean aBoolean = kaptcha.validate(verifyCode);
        if (aBoolean) {
            HappyCaptcha.remove(request);
            return "验证码校验通过";
        }

        return "验证码校验不通过";
    }

    @GetMapping("/generator-redis")
    public void generatorCodeMy(HttpServletRequest request, HttpServletResponse response) {
        myGoogleKaptcha.render();
    }

    @GetMapping("/verify-redis")
    public String verifyMy(String verifyCode, HttpServletRequest request) {


        Boolean aBoolean = myGoogleKaptcha.validate(verifyCode, 30);
        if (aBoolean) {
            HappyCaptcha.remove(request);
            return "验证码校验通过";
        }

        return "验证码校验不通过";
    }
}
