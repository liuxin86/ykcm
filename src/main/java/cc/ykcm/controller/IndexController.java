package cc.ykcm.controller;

import cc.ykcm.common.R;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 *
 * 页面跳转 controller
 * @author liuxin
 */
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
    @RequestMapping("/index/login")
    public String login(){
        return "login";
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping("/index/register")
    public String register(){
        return "register";
    }

    /**
     * 存放一些 初始化信息
     * 例如 对象存储的 url
     * @return
     */
    @RequestMapping("/index/init")
    @ResponseBody
    public R init(){
        Map<String,String> map = Maps.newHashMap();
        map.put("init","我是初始化的信息");
        map.put("cos","https://ykcm-1259164643.cos.ap-beijing.myqcloud.com");
        return R.ok().put("data",map);
    }

}
