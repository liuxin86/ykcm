package cc.ykcm.controller;

import cc.ykcm.entity.User;
import cc.ykcm.service.ShiroService;
import cc.ykcm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liuxin
 */
public abstract class BaseController {

    @Resource
    private ShiroService shiroService;

    @Autowired
    private UserService userService;

    /**
     * 获取当前登录用户信息
     */
    protected User getUser(){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        return userService.findByUserName(username);
    }

    /**
     * 获取用户id
     */
    protected Long getUserId(){
        return getUser().getId();
    }

    /**
     * 获取用户名
     */
    protected String getUsername(){
        return getUser().getUsername();
    }

    /**
     * 获取当前用户状态
     */
    protected Integer getStatus(){
        return 0;
    }


}
