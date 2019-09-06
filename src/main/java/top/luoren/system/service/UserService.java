package top.luoren.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoren.common.util.PageUtils;
import top.luoren.system.entity.User;

import java.util.Map;
import java.util.Set;

/**
 * @author luoren
 * @date 2019/9/4 14:24
 */
public interface UserService extends IService<User> {
    /**
     * 分页查询
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 返回一个用户实例
     */
    User getUser(String username);

    /**
     * 通过用户名获取用户角色集合
     *
     * @param username 用户名
     * @return 角色集合
     */
    Set<String> getUserRolesSet(String username);

    /**
     * 通过用户名获取用户权限集合
     *
     * @param username 用户名
     * @return 权限集合
     */
    Set<String> getUserPermissionsSet(String username);
}
