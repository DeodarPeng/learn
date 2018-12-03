package cc.learn.user.service;

import cc.learn.user.po.User;

/**
 * @Description:
 * @author: C
 * @date: 2018年11月14日 下午12:37:30
 */
public interface UserService {
    User getUserById(String id);

    /**
     * @Description:
     * @author: C
     * @date: 2018年11月14日 下午12:44:38
     * @param user
    */
    User saveUser(User user);

    Integer updateUserById(User user);
}
