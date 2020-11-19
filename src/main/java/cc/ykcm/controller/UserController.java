package cc.ykcm.controller;

import cc.ykcm.common.R;
import cc.ykcm.entity.User;
import cc.ykcm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author liuxin
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    /**
     * 用来处理身份认证
     * @return
     */
    @RequestMapping("login")
    public R login(@RequestBody Map<String,Object> map){
        // 获取主体对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(String.valueOf(map.get("username")),String.valueOf(map.get("password"))));
            return R.ok();
        }catch (UnknownAccountException e){
            e.printStackTrace();
            return R.error("用户名错误");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            R.error("密码错误");
        }
        return R.error("登录失败");
    }


    /**
     * 注册
     */
    @RequestMapping("register")
    public R register(User user){
        try {
            userService.save(user);
        }catch (Exception e){
            e.printStackTrace();
            R.error("注册失败");
        }
        return R.ok();
    }

    /**
     * 用户退出
     * @return
     */
    @RequestMapping("logout")
    public R logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return R.ok();
    }


}
