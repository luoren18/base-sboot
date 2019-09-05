package top.luoren.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.luoren.system.entity.Role;

import java.util.List;

/**
 * 角色
 *
 * @author luoren
 * @date 2019-09-05 11:37:43
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 查询用户创建的角色ID列表
     *
     * @param createBy
     * @return
     */
    List<Long> queryRoleIdList(String createBy);
}
