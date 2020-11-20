package cc.ykcm.service.impl;

import cc.ykcm.dao.UserDao;
import cc.ykcm.entity.User;
import cc.ykcm.service.UserService;
import cc.ykcm.utils.SaltUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * @author admin
 */
@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    @Override
    public User findByUserName(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return userDao.selectOne(wrapper);
    }

    /**
     * 保存用户
     * @param user
     */
    @Override
    public void save(User user) {
        // 生成随机盐
        String salt = SaltUtil.getSalt(10);
        // 将salt 保存到数据库
        user.setSalt(salt);
        // 明文密码进行 md5 + salt + hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        // 将加密后的密码放入 user 中
        user.setPassword(md5Hash.toHex());
        userDao.insert(user);
    }

    /**
     * 修改用户信息
     * @param user
     */
    @Override
    public void update(User user) {
        userDao.updateById(user);
    }

    /**
     * 根据id删除用户
     * @param ids
     */
    @Override
    public void delete(long[] ids) {
        userDao.deleteBatchIds(Arrays.asList(ids));
    }
}
