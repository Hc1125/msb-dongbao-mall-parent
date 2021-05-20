package com.msb.dongbao.ums.entity.reponse;

import com.msb.dongbao.ums.entity.UmsMember;
import lombok.Data;

@Data
public class UserMemberLoginResponse {
    private String token;
    private UmsMember umsMember;
}
