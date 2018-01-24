package com.admin.luntan.web;

import com.admin.luntan.api.TabUserService;
import com.admin.luntan.base.ServiceResult;
import com.admin.luntan.entity.TabUserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 文章
 *
 * @author zhanghaichao on 2018/1/24.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private TabUserService tabUserService;


    /**
     * 查询用户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveNewArt", method = RequestMethod.GET)
    public String findUser2(HttpServletRequest request) {
        request.getSession().setAttribute("request Url", request.getRequestURL());
        TabUserEntity tabUserEntity = new TabUserEntity();
        ServiceResult<List<TabUserEntity>> result = tabUserService.searchAllData(tabUserEntity, null);
        return result.toJson();
    }
}
