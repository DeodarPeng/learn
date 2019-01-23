package cc.learn.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.learn.mapper.UserMapper;
import cc.learn.po.User;
import cc.learn.po.UserExample;
import cc.learn.service.UserService;

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
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(id);
        List<User> list = userMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
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
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(user.getId());
        return userMapper.updateByExampleSelective(user, example);
    }

    /** 
     * Description  
     * @see cc.learn.user.service.UserService#listAllUser()   
     */
    @Override
    public List<User> listAllUser() {
        return userMapper.selectByExample(null);
    }
}
