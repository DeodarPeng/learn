package cc.learn.repository.impl;

import cc.learn.mapper.UserRowMapper;
import cc.learn.po.User;
import cc.learn.repository.UserReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcUserRepository implements UserReposity {

    private static final String INSERT_SPITTER = "insert into user (id, user_name, create_user, update_user, create_date, update_date) values (?, ?, ?, ?, ?)";
    private static final String SELECT_SPITTER = "select id, user_name from user";
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public User save(User user) {
        jdbcOperations.update(INSERT_SPITTER,
                user.getId(),
                user.getUserName(),
                user.getCreateUser(),
                user.getUpdateUser(),
                new Date(),
                new Date());
        return user;
    }

    @Override
    public User findOneById(String id) {
        //查询并将结果映射到对象
        return jdbcOperations.queryForObject("sql",new UserRowMapper(),id);
    }

    /**
     * 使用 Lambda 表达式
     * @param id
     * @return
     */
    public User findOneById_lambda(String id) {
        return jdbcOperations.queryForObject("sql",
                (rs,rowNum) -> {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setUserName(rs.getString("user_name"));
                return  user;
        },id);
    }

    /**
     * 使用 Java 8 的方法引用
     * @param id
     * @return
     */
    public User findOneById_mapMethod(String id) {
        return jdbcOperations.queryForObject("sql", this :: mapUserMapper,id);
    }
    private User mapUserMapper(ResultSet rs,int row) throws SQLException {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setUserName(rs.getString("user_name"));
        return  user;
    }

    @Override
    public List<User> listAllUser() {
        return null;
    }


    /**
     * 插入记录
     *
     * @param user
     * @return
     */
    private long insertSpitterAndReturnId(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert((JdbcTemplate) jdbcOperations).withTableName("user");
        jdbcInsert.setGeneratedKeyName("id");
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("user_name", user.getUserName());
        args.put("update_date", new Date());
        long userId = jdbcInsert.executeAndReturnKey(args).longValue();
        return userId;
    }
}
