package com.msb.dongbao.portal.web.controller;

import com.msb.dongbao.common.base.annotations.TokenCheck;
import com.msb.dongbao.common.base.result.ResultWrapper;
import com.msb.dongbao.ums.entity.UmsMember;
import com.msb.dongbao.ums.service.UmsMemberService;
import com.msb.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.msb.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;
import com.msb.msbdongbaocommonutil.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user-member")
public class UserMemberController {
    @Autowired
    UmsMemberService umsMemberService;


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/register")
    public ResultWrapper register(@RequestBody @Valid UmsMemberRegisterParamDTO umsMemberRegisterParamDTO) {
        return umsMemberService.register(umsMemberRegisterParamDTO);
    }

    @PostMapping("/login")
    public ResultWrapper login(@RequestBody UmsMemberLoginParamDTO umsMemberLoginParamDTO) {
        return umsMemberService.login(umsMemberLoginParamDTO);
    }

    @PostMapping("/edit")
    @TokenCheck
    public ResultWrapper edit(@RequestBody UmsMember umsMember) {
        System.out.println("edit");
        return umsMemberService.edit(umsMember);
    }

    /**
     * 这是测试的，系统中的任意一个几口
     * 修改用户信息
     * @param token
     * @return
     */
    @GetMapping("/test-verify")
    public ResultWrapper verify(String token) {
        System.out.println("正常业务");
        return ResultWrapper.getSuccessBuilder().build();
    }
}
