package top.luoren.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.luoren.system.entity.Menu;

import java.util.List;

/**
 * 菜单管理
 * 
 * @author luoren
 * @date 2019-09-05 11:37:43
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     *
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @return
     */
    List<Menu> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     * @return
     */
    List<Menu> queryNotButtonList();
}
