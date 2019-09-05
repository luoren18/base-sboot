package top.luoren.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoren.common.util.PageUtils;
import top.luoren.system.bean.User;
import top.luoren.system.mapper.UserMapper;
import top.luoren.system.service.UserService;

import java.util.Map;

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
}
