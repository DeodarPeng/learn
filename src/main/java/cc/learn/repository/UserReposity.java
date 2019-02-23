package cc.learn.repository;

import cc.learn.po.User;

import java.util.List;

public interface UserReposity {
    User save(User user);
    User findOneById(String id);
    List<User> listAllUser();
}
