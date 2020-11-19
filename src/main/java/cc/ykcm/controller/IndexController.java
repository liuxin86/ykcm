package cc.ykcm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping(value = {"/","","index"})
    public String index(){
        return "index";
    }


    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("login")
    public String login(){
        return "login";
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping("register")
    public String register(){
        return "register";
    }
}
