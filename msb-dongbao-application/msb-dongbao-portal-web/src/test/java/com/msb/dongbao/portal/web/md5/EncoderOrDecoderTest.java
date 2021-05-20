package com.msb.dongbao.portal.web.md5;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncoderOrDecoderTest {
    @Test
    public void bcrypt() {
        String sourceString = "123456";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(sourceString);
        System.out.println("第一次加密值：" + encode);
        boolean matches = bCryptPasswordEncoder.matches(sourceString, encode);
        System.out.println("第一次验证：" + matches);

        encode = bCryptPasswordEncoder.encode(sourceString);
        System.out.println("第二次加密值：" + encode);
        matches = bCryptPasswordEncoder.matches(sourceString, encode);
        System.out.println("第二次验证：" + matches);
    }
}
