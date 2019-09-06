package top.luoren.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.luoren.system.entity.User;

import java.util.List;
import java.util.Set;

/**
 * @author luoren
 * @date 2019/9/4 14:30
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询用户的所有权限
     *
     * @param username 用户名
     * @return set
     */
    Set<String> queryPermissions(String username);

    /**
     * 查询用户的所有菜单ID
     *
     * @param userId long
     * @return list
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     *
     * @param username String
     * @return User
     */
    User queryByUserName(String username);

    /**
     * 根据用户名查询角色名
     *
     * @param username 用户名
     * @return set
     */
    Set<String> queryRoles(String username);


}
