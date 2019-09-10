package top.luoren.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import top.luoren.common.util.PageUtils;
import top.luoren.common.util.RedisUtil;
import top.luoren.system.entity.User;
import top.luoren.system.mapper.UserMapper;
import top.luoren.system.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author luoren
 * @date 2019/9/4 14:27
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private RedisUtil redisUtil;

    //设置用户数据在Redis中的key的前缀
    private static final String REDIS_KEY_PREFIX = "sboot:user:username:";
    //设置Redis中用户数据的有效时间
    private static final long USER_EXPIRE_TIME = 7200L;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<User> page = this.page(new Page<>(), new QueryWrapper<>());
        return new PageUtils(page);
    }

    @Override
    public User getUser(String username) {
        User user = null;
        boolean isExistInRedis = true;
        if (!StringUtils.isEmpty(username)) {
            user = redisUtil.get(REDIS_KEY_PREFIX + username, User.class);
            if (ObjectUtils.isEmpty(user)) {
                isExistInRedis = false;
                user = baseMapper.queryByUserName(username);
            }
        }
        if (ObjectUtils.isEmpty(user)) {
            log.info("用户：" + username + " 不存在.");
            throw new AuthenticationException("User didn't existed!");
        }
        if (!isExistInRedis) {
            redisUtil.set(REDIS_KEY_PREFIX + username, user, USER_EXPIRE_TIME);
        }
        return user;
    }

    @Override
    public Set<String> getUserRolesSet(String username) {
        return baseMapper.queryRoles(username);
    }

    @Override
    public Set<String> getUserPermissionsSet(String username) {
        Set<String> permissions = baseMapper.queryPermissions(username);
        if (!CollectionUtils.isEmpty(permissions)) {
            Set<String> results = new HashSet<>();
            for (String permission : permissions) {
                results.addAll(Arrays.asList(permission.split(",")));
            }
            return results;
        }
        return null;
    }
}
