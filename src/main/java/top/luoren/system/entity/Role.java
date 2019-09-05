package top.luoren.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.luoren.common.base.entity.BaseEntity;

/**
 * 角色
 *
 * @author luoren
 * @date 2019-09-05 11:37:43
 */
@Data
@TableName("sys_role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 备注
     */
    private String remark;
}
