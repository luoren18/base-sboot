package top.luoren.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.luoren.system.entity.UserRole;

/**
 * 用户与角色对应关系
 *
 * @author luoren
 * @email luoren96@gmail.com
 * @date 2019-09-05 11:37:43
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

}
