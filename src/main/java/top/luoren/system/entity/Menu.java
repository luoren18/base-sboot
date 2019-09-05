package top.luoren.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.luoren.common.base.entity.BaseEntity;

/**
 * 菜单管理
 *
 * @author luoren
 * @date 2019-09-05 11:37:43
 */
@Data
@TableName("sys_menu")
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 父菜单ID，一级菜单为0
     */
    private Long parentId;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单URL
     */
    private String url;
    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;
    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
    private Integer type;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 排序
     */
    private Integer orderNum;

}
