package cc.ykcm;

import cc.ykcm.dao.UserDao;
import cc.ykcm.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = YkcmApplication.class)
class YkcmApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void testFind() {
        QueryWrapper<User> wapper = new QueryWrapper<>();
        wapper.eq("username","liuxin");
        User user = userDao.selectOne(wapper);
        System.out.println(user);
    }

    @Test
    void testInsert() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        userDao.insert(user);
    }
}
