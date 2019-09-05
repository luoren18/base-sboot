package top.luoren.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luoren.common.util.PageUtils;
import top.luoren.system.entity.User;

import java.util.Map;

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
}
