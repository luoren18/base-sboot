package top.luoren.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.luoren.system.entity.UserToken;

/**
 * 系统用户Token
 *
 * @author luoren
 * @email luoren96@gmail.com
 * @date 2019-09-05 11:37:43
 */
@Mapper
public interface UserTokenMapper extends BaseMapper<UserToken> {

}
