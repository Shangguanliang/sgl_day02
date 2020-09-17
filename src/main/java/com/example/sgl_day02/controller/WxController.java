package com.example.sgl_day02.controller;

import com.example.sgl_day02.properties.Jdbcproperties;
import com.example.sgl_day02.properties.Wxproperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标识为控制器类
 */
@RestController
public class WxController {
    //自动注入
    @Autowired
    private Wxproperties wxproperties;

    @Autowired
    private Jdbcproperties jdbcproperties;
    //获取wx属性
    @GetMapping("/wx")
    public void wxmsg(){
        System.out.println(wxproperties.getAppid());
        System.out.println(wxproperties.getSecret());
    }

    //获取geek属性
    @GetMapping("/geek/attr")
    public String geekAttr(){
        return jdbcproperties.toString();
    }
}
