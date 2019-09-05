package top.luoren.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.luoren.system.entity.Menu;

/**
 * 菜单管理
 * 
 * @author luoren
 * @email luoren96@gmail.com
 * @date 2019-09-05 11:37:43
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
	
}
