package cc.learn.config;/*
 @author Cedar
 @DESCRIPTION 
 @create 2019/2/27
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;

@Configuration
@EnableCaching
public class CachingConfig {
    /**
     * @author Cedar
     * @DESCRIPTION: 声明Redis缓存管理器
     * @params: []
     * @return: org.springframework.cache.CacheManager
     * @Date: 2019/2/27 16:08
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }

    /**
     * @author Cedar
     * @DESCRIPTION:多缓存管理器
     * @params: [cm, jcm]
     * @return: org.springframework.cache.CacheManager
     * @Date: 2019/3/6 17:15
    */
 /*   @Bean
    public CacheManager cacheManager(net.sf.ehcache.CacheManager cm, javax.cache.CacheManager jcm){
        CompositeCacheManager cacheManager = new CompositeCacheManager();
        ArrayList<CacheManager> cacheManagers = new ArrayList<>();
        cacheManagers.add(new JCacheCacheManager(jcm));
        cacheManagers.add(new EhCacheCacheManager(cm));
        cacheManagers.add(new RedisCacheManager(redisTemplate()));
        cacheManager.setCacheManagers(cacheManagers);
        return cacheManager;
    }*/

    /**
     * @author Cedar
     * @DESCRIPTION: Redis连接工程 bean
     * @params: []
     * @return: org.springframework.data.redis.connection.jedis.JedisConnectionFactory
     * @Date: 2019/2/27 16:37
    */
   /* @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }*/

    /**
     * @author Cedar
     * @DESCRIPTION: RedisTemplate
     * @params: [redisCF]
     * @return: org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.String>
     * @Date: 2019/2/27 16:40
    */
    @Bean
    public RedisTemplate<String,String> redisTemplate (RedisConnectionFactory redisCF){
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisCF);
        redisTemplate.afterPropertiesSet();
        return  redisTemplate;
    }
}
