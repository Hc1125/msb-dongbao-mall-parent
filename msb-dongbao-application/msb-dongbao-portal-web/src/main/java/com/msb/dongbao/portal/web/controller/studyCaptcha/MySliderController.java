package com.msb.dongbao.portal.web.controller.studyCaptcha;

import com.msb.dongbao.portal.web.util.SliderUtil;
import com.msb.dongbao.portal.web.util.VerificationVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/my-slider")
public class MySliderController {
    @GetMapping("/generator")
    public VerificationVO generatorCode(HttpServletRequest request, HttpServletResponse response) {

        return SliderUtil.cut();
    }

    @GetMapping("/verify")
    public String verify(String verifyCode, HttpServletRequest request) {


//        Boolean aBoolean = kaptcha.validate(verifyCode);
//        if (aBoolean) {
//            HappyCaptcha.remove(request);
//            return "验证码校验通过";
//        }

        return "验证码校验不通过";
    }
}
