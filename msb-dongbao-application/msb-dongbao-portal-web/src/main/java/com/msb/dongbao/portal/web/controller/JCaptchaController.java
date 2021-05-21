package com.msb.dongbao.portal.web.controller;

import com.msb.dongbao.common.base.annotations.TokenCheck;
import com.msb.dongbao.portal.web.code.ImageCode;
import com.msb.dongbao.portal.web.util.JCaptchaUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/jcaptcha")
public class JCaptchaController {

    String attrName = "verifyCode";
    @GetMapping("/generator")
    @TokenCheck(required = false)
    public void generatorCode(HttpServletRequest request, HttpServletResponse response) {

        try {
            String id = request.getSession().getId();
            BufferedImage bufferedImage = JCaptchaUtil.getService().getImageChallengeForID(id);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(byteArrayOutputStream);
            jpegEncoder.encode(bufferedImage);

            response.setHeader("Cache-Control", "no-store");
            response.setContentType("image/jpeg");
            byte[] bytes = byteArrayOutputStream.toByteArray();
            ServletOutputStream servletOutputStream = response.getOutputStream();

            servletOutputStream.write(bytes);
            servletOutputStream.flush();
            servletOutputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request) {
        Boolean aBoolean = JCaptchaUtil.getService().validateResponseForID(request.getSession().getId(), verifyCode);
        if (aBoolean) {
            return "验证码校验通过";
        }
        return "验证码校验不通过";
    }
}
