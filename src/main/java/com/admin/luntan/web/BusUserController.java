package com.admin.luntan.web;

import com.admin.luntan.api.TabUserService;
import com.admin.luntan.base.BusinessException;
import com.admin.luntan.base.ServiceResult;
import com.admin.luntan.entity.TabUserEntity;
import com.admin.luntan.util.Global;
import com.admin.luntan.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
     * 用户登陆
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userLogin.api", method = {RequestMethod.POST})
    public String userLogin(TabUserEntity user) {
        ServiceResult<Boolean> result = new ServiceResult<>();
        try {
            TabUserEntity userEntity = new TabUserEntity();
            if (StringUtil.isNotBlank(user.getUsername())) {
                userEntity.setUsername(user.getUsername());
            } else {
                throw new BusinessException("用户名不能为空");
            }

            //todo 后面做密码验证
            if (StringUtil.isNotBlank(user.getPassword())) {
                userEntity.setPassword(user.getPassword());
            } else {
                throw new BusinessException("密码不能为空");
            }
            ServiceResult<List<TabUserEntity>> users = tabUserService.searchAllData(userEntity, null);
            List<TabUserEntity> userList = users.getResult();
            if (users.getSuccess() && !userList.isEmpty()) {
                if (userList.size() > 1) {
                    throw new BusinessException("用户名错误");
                }
                TabUserEntity loginUser = userList.get(0);
                Global.setUserSession(loginUser);
            } else {
                throw new BusinessException("用户不存在");
            }
            result.setSuccess(true);
            result.setResult(true);
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setResult(false);
            result.setMessage(e.getMessage());
        }
        return result.toJson();
    }
}
