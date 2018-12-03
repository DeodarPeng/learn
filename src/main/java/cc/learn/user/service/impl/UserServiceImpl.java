package cc.learn.user.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.learn.user.mapper.UserMapper;
import cc.learn.user.po.User;
import cc.learn.user.service.UserService;

/**
 * @Description:
 * @author: C
 * @date: 2018年11月14日 下午12:40:41
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /** 
     * Description  
     * @see cc.learn.user.service.UserService#getUserById(java.lang.String)   
     */
    @Override
    public User getUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /** 
     * Description  
     * @see cc.learn.user.service.UserService#saveUser(cc.learn.user.po.User)   
     */
    @Override
    public User saveUser(User user) {
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        userMapper.insert(user);
        return user;
    }

    /** 
     * Description  
     * @see cc.learn.user.service.UserService#updateUserById(cc.learn.user.po.User)   
     */
    @Override
    @Transactional
    public Integer updateUserById(User user) {
        user.setUpdateDate(new Date());
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
