package com.msb.dongbao.portal.web.controller;

import com.msb.dongbao.common.base.annotations.TokenCheck;
import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import com.ramostear.captcha.support.CaptchaType;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/easy-captcha")
public class EasyCaptchaController {
    @GetMapping("/generator")
    @TokenCheck(required = false)
    public void generatorCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 基础
            //CaptchaUtil.out(request, response);

//            // 算术
//            ArithmeticCaptcha arithmeticCaptcha = new ArithmeticCaptcha(200, 50);
//
//            // 三个数的运算
//            arithmeticCaptcha.setLen(3);
//            arithmeticCaptcha.text();
//            CaptchaUtil.out(arithmeticCaptcha, request, response);

            ChineseCaptcha chineseCaptcha = new ChineseCaptcha(150, 50);
            CaptchaUtil.out(chineseCaptcha, request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/generator-redis")
    @TokenCheck(required = false)
    public Map<String, String> generatorCodeRedis(HttpServletRequest request, HttpServletResponse response) {

        SpecCaptcha specCaptcha = new SpecCaptcha(100, 50);
        String text = specCaptcha.text();
        System.out.println("验证码：" + text);
        String uuid = UUID.randomUUID().toString();
        String sessionId = request.getSession().getId();

        stringRedisTemplate.opsForValue().set(uuid, text);

        String s1 = specCaptcha.toBase64();
        System.out.println("base64:" + s1);
        Map<String, String> map = new HashMap<>();
        map.put("uuid", uuid);
        map.put("base64", s1);
        return map;
    }



    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request) {
        Boolean aBoolean = CaptchaUtil.ver(verifyCode, request);
        if (aBoolean) {
            HappyCaptcha.remove(request);
            return "验证码校验通过";
        }
        return "验证码校验不通过";
    }

    @GetMapping("/verify-redis")
    @TokenCheck(required = false)
    public String verifyRedis(String verifyCode, HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        String c = stringRedisTemplate.opsForValue().get(sessionId);

        if (verifyCode.equals(c)) {
            HappyCaptcha.remove(request);
            return "验证码校验通过";
        }
        return "验证码校验不通过";
    }
}
