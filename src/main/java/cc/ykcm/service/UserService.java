package cc.ykcm.service;

import cc.ykcm.entity.User;

public interface UserService {

    User findByUserName(String username);

    void save(User user);
}
