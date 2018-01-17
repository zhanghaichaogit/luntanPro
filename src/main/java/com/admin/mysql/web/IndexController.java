package com.admin.mysql.web;

import com.admin.mysql.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class IndexController {
    @Resource
    private UserService userService;


    /**
     * 查询用户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findalluser")
    public String findUser2() {
        return JSON.toJSONString(userService.findAllT());
    }

}
