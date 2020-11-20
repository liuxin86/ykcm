package cc.ykcm.service.impl;

import cc.ykcm.service.ShiroService;
import org.springframework.stereotype.Service;

import java.util.Set;


/**
 * shiro 的一些 常用的方法
 * @author liuxin
 */
@Service("shiroService")
public class ShiroServiceImpl implements ShiroService {


    /**
     * 获取权限列表
     * @param userId
     * @return
     */
    @Override
    public Set<String> getPermsById(long userId) {


        return null;
    }
}
