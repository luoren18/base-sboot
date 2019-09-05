package top.luoren.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.luoren.system.entity.UserRole;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 * @author luoren
 * @date 2019-09-05 11:37:43
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据用户ID，获取角色ID列表
     *
     * @param userId long
     * @return list
     */
    List<Long> queryRoleIdList(Long userId);


    /**
     * 根据角色ID数组，批量删除
     *
     * @param roleIds array
     * @return int
     */
    int deleteBatch(Long[] roleIds);
}
