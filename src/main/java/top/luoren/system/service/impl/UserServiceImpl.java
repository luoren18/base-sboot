package top.luoren.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoren.common.util.PageUtils;
import top.luoren.system.entity.User;
import top.luoren.system.mapper.UserMapper;
import top.luoren.system.service.UserService;

import java.util.Map;
import java.util.Set;

/**
 * @author luoren
 * @date 2019/9/4 14:27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<User> page = this.page(new Page<>(), new QueryWrapper<>());
        return new PageUtils(page);
    }

    @Override
    public User getUser(String username) {
        return baseMapper.queryByUserName(username);
    }

    @Override
    public Set<String> getUserRolesSet(String username) {
        return baseMapper.queryRoles(username);
    }

    @Override
    public Set<String> getUserPermissionsSet(String username) {
        return baseMapper.queryPermissions(username);
    }
}
