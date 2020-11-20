package cc.ykcm.config;

import cc.ykcm.shiro.realms.YkcmRealm;
import com.google.common.collect.Maps;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 整合 shiro 相关配置
 * @author liuxin
 */
@Configuration
public class ShrioConfig {

    /**
     * 创建ShiroFilter
     * @return
     */
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        // 创建shiro的filter
        // 负责拦截所有请求
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String,String> map = Maps.newHashMap();
        // 配置系统公共资源
        map.put("/css/**","anon");
        map.put("/js/**","anon");
        map.put("/static/**","anon");
        map.put("/layer/**","anon");
        map.put("/favicon.ico","anon");
        // 用户登录
        map.put("/user/login","anon");
        // 用户注册
        map.put("/user/register","anon");
        // 主页
        map.put("/index","anon");
        // 注册页面
        map.put("/register","anon");
        map.put("/","anon");
        // 配置系统受限资源
        map.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        // 默认 认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login");
        return shiroFilterFactoryBean;
    }

    /**
     * 配置web安全管理器
     */
    @Bean
    public DefaultWebSecurityManager securityManager(Realm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    /**
     * 创建自定义realm
     */
    @Bean("realm")
    public Realm realm(){
        YkcmRealm realm = new YkcmRealm();
        // 设置 hash 凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 设置 md5 加密
        credentialsMatcher.setHashAlgorithmName("md5");
        // 设置散列次数
        credentialsMatcher.setHashIterations(1024);
        // 将 凭证匹配器 赋值  给realm
        realm.setCredentialsMatcher(credentialsMatcher);
        return realm;
    }
}
