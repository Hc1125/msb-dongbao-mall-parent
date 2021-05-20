package com.msb.dongbao.ums.service;

import com.msb.dongbao.common.base.result.ResultWrapper;
import com.msb.dongbao.ums.entity.UmsMember;
import com.msb.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.msb.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author 黄城
 * @since 2021-05-19
 */
public interface UmsMemberService {

    ResultWrapper register(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO);

    ResultWrapper login(UmsMemberLoginParamDTO umsMemberLoginParamDTO);

    ResultWrapper edit(UmsMember umsMember);
}
