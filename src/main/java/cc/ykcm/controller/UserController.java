package cc.ykcm.controller;

import cc.ykcm.common.R;
import cc.ykcm.entity.User;
import cc.ykcm.service.UserService;
import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 存放一些 初始化信息
     * 例如 对象存储的 url
     * @return
     */
    @RequestMapping("init")
    public R init(){
        Map<String,String> map = Maps.newHashMap();
        map.put("init","我是初始化的信息");
        map.put("oss","");
        return R.ok().put("map",map);
    }

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
    @PostMapping("register")
    public R register(@RequestBody User user){
        try {
            userService.save(user);
        }catch (Exception e){
            e.printStackTrace();
            R.error("注册失败");
        }
        return R.ok("注册成功");
    }

    /**
     * 用户退出
     * @return
     */
    @GetMapping("logout")
    public R logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return R.ok();
    }

    /**
     * 修改用户信息
     */
    @PostMapping("update")
    public R update(@RequestBody User user){
        try {
            userService.update(user);
        }catch (Exception e){
            e.printStackTrace();
            R.error("修改失败，请重试");
        }
        return R.ok("修改成功");
    }

    /**
     * 批量删除用户
     */
    @RequestMapping("delete")
    public R delete(long[] ids){
        try {
            userService.delete(ids);
        }catch (Exception e){
            e.printStackTrace();
            return R.error("删除失败");
        }
        return R.ok("删除成功");
    }
}
