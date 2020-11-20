package cc.ykcm.service;

import java.util.List;
import java.util.Set;

/**
 * @author liuxin
 */
public interface ShiroService {

    /**
     * 根据用户id获取权限列表
     */
    Set<String> getPermsById(long userId);

}
