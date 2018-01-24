package com.admin.luntan.web;

import com.admin.luntan.api.TabUserService;
import com.admin.luntan.base.ServiceResult;
import com.admin.luntan.entity.TabUserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 基础用户控制器
 *
 * @author zhanghaichao on 2018/1/24.
 */
@Controller
@RequestMapping("/bususer")
public class BusUserController {
    @Resource
    private TabUserService tabUserService;


    /**
     * 查询用户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findalluser")
    public String findUser2() {
        TabUserEntity tabUserEntity = new TabUserEntity();
        ServiceResult<List<TabUserEntity>> result = tabUserService.searchAllData(tabUserEntity, null);
        return result.toJson();
    }
}
