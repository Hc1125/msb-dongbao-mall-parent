package com.msb.dongbao.ums.mapper;

import com.msb.dongbao.ums.entity.UmsMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = com.msb.dongbao.ums.MsbDongbaoUmsApplication.class)
public class UserMemberTest {

    @Autowired
    UmsMemberMapper umsMemberMapper;

    @Test
    void testInsert(){


        UmsMember t = new UmsMember();
        t.setUsername("hc");
        t.setStatus(0);
        t.setPassword("1");
        t.setNote("note");
        t.setNickName("nick");
        t.setEmail("email");


        umsMemberMapper.insert(t);

    }

    /*@Test
    void testUpdate(){
        UmsMember t = new UmsMember();
        t.setNickName("尴尬");
        t.setId(17L);

        umsMemberMapper.updateById(t);
    }*/


    /*@Test
    void testSelect(){
        UmsMember cpf = umsMemberMapper.selectByName("cpf");
        System.out.println(cpf.getNickName());
    }*/


}